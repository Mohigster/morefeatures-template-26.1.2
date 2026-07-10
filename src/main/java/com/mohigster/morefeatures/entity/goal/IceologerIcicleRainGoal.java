package com.mohigster.morefeatures.entity.goal;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.level.block.SpeleothemBlock;

import java.util.EnumSet;

public class IceologerIcicleRainGoal extends Goal {
    public final IceologerEntity iceologer;
    private int castTicks = 0; // Wind-up time before the icicles fall

    private static final int ICICLE_COUNT = 7;
    private static final double RADIUS = 5.0D;
    private static final double SPAWN_HEIGHT = 6.0D;

    public IceologerIcicleRainGoal(IceologerEntity mob) {
        this.iceologer = mob;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.iceologer.getTarget();
        return target != null && target.isAlive()
                && !this.iceologer.isAttackOnCooldown()
                && this.iceologer.shouldUseIcicleRain();
    }

    @Override
    public void start() {
        this.castTicks = 25; // Slightly longer wind-up than the single-target attack
        this.iceologer.getNavigation().stop();
        this.iceologer.setIsCastingSpell(true);
    }

    @Override
    public void tick() {
        LivingEntity target = this.iceologer.getTarget();
        if (target == null) return;

        this.iceologer.getLookControl().setLookAt(target, 30.0F, 30.0F);

        if (this.castTicks > 0) {
            this.castTicks--;

            if (this.iceologer.level() instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                        this.iceologer.getX(), this.iceologer.getEyeY() + 0.5D, this.iceologer.getZ(),
                        2, 0.2D, 0.2D, 0.2D, 0.0D);
            }

            if (this.castTicks == 0) {
                this.performIcicleRain(target);
                this.iceologer.startAttackCooldown(80); // A bit longer cooldown - it's the bigger attack
                this.iceologer.toggleNextAttack();        // hand the turn back to the single-target attack
            }
        }
    }

    private void performIcicleRain(LivingEntity target) {
        if (!(this.iceologer.level() instanceof ServerLevel level)) return;

        double centerX = target.getX();
        double centerZ = target.getZ();

        for (int i = 0; i < ICICLE_COUNT; i++) {
            double angle = level.getRandom().nextDouble() * Math.PI * 2.0D;
            double dist = level.getRandom().nextDouble() * RADIUS;
            double x = centerX + Mth.cos((float) angle) * dist;
            double z = centerZ + Mth.sin((float) angle) * dist;

            BlockPos spawnPos = BlockPos.containing(x, target.getY() + SPAWN_HEIGHT, z);

            FallingBlockEntity icicle = FallingBlockEntity.fall(level, spawnPos, MFBlocks.ICICLE.get().defaultBlockState().setValue(SpeleothemBlock.TIP_DIRECTION, Direction.DOWN));
            icicle.setHurtsEntities(5.0F, 20);
            icicle.dropItem = false;
        }

        level.playSound(null, centerX, target.getY(), centerZ,
                SoundEvents.EVOKER_CAST_SPELL, SoundSource.HOSTILE, 1.0F, 0.8F);
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
}
