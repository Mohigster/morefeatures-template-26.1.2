package com.mohigster.morefeatures.entity.goal;

import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.Blocks;

import java.util.EnumSet;

public class IceologerAttackSpellGoal extends Goal {
    public final IceologerEntity iceologer;
    private int attackCooldown = 40; // Ticks between attacks (~2 seconds)
    private int castTicks = 0;       // How long he's been winding up the spell

    public IceologerAttackSpellGoal(IceologerEntity mob) {
        this.iceologer = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.iceologer.getTarget();
        return target != null && target.isAlive() && this.attackCooldown <= 0;
    }

    @Override
    public void start() {
        this.castTicks = 20; // 1 second wind-up/animation time
        this.iceologer.getNavigation().stop();
        this.iceologer.setIsCastingSpell(true);
    }

    @Override
    public void tick() {
        LivingEntity target = this.iceologer.getTarget();
        if (target == null) return;

        // Always face the victim
        this.iceologer.getLookControl().setLookAt(target, 30.0F, 30.0F);

        // Simple kite AI: back up slightly if the target gets too close
        double distanceSq = this.iceologer.distanceToSqr(target);
        if (distanceSq < 16.0D) { // Closer than 4 blocks
            this.iceologer.getNavigation().moveTo(
                    this.iceologer.getX() - (target.getX() - this.iceologer.getX()),
                    this.iceologer.getY(),
                    this.iceologer.getZ() - (target.getZ() - this.iceologer.getZ()),
                    1.0D
            );
        }

        if (this.castTicks > 0) {
            this.castTicks--;

            // Spawn icy casting particles around the hands/head
            if (this.iceologer.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                        this.iceologer.getX(), this.iceologer.getEyeY() + 0.5D, this.iceologer.getZ(),
                        2, 0.2D, 0.2D, 0.2D, 0.0D);
            }

            // Time to strike!
            if (this.castTicks == 0) {
                this.performIceAttack(target);
                this.attackCooldown = 60; // 3 second cooldown before next attack
            }
        }

    }

    private void performIceAttack(LivingEntity target) {
        if (!(this.iceologer.level() instanceof ServerLevel level)) return;

        BlockPos spawnPos = BlockPos.containing(target.getX(), target.getY() + 4.0D, target.getZ());

        FallingBlockEntity iceChunk = FallingBlockEntity.fall(level, spawnPos, ModBlocks.CONJURED_ICE.get().defaultBlockState());

        iceChunk.setHurtsEntities(5.0F, 20);

        level.playSound(null, target.getX(), target.getY(), target.getZ(),
                SoundEvents.EVOKER_CAST_SPELL, SoundSource.HOSTILE, 1.0F, 1.0F);
    }

    @Override
    public void stop() {
        this.castTicks = 0;
        this.iceologer.setIsCastingSpell(false);
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    // Tick down cooldown even when not actively attacking
    public void decrementCooldown() {
        if (this.attackCooldown > 0) {
            this.attackCooldown--;
        }
    }
}
