package com.mohigster.morefeatures.entity.goal;

import com.mohigster.morefeatures.entity.custom.BrineEntity;
import com.mohigster.morefeatures.entity.custom.projectile.brine.BrineBallEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class BrineAttackGoal extends Goal {
    private final BrineEntity brine;
    private int attackStep;
    private int attackTime;

    public BrineAttackGoal(BrineEntity brine) {
        this.brine = brine;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.brine.getTarget();
        return target != null && target.isAlive();
    }

    @Override
    public void start() {
        this.attackStep = 0;
    }

    @Override
    public void tick() {
        LivingEntity target = this.brine.getTarget();
        if (target == null) return;

        this.brine.getLookControl().setLookAt(target, 30.0F, 30.0F);
        double distanceSq = this.brine.distanceToSqr(target);

        if (distanceSq < 256.0D) { // Within 16 blocks
            if (this.attackTime <= 0) {
                this.attackStep++;
                if (this.attackStep == 1) {
                    this.attackTime = 60; // 3 seconds charging/spinning rods
                } else if (this.attackStep <= 4) { // Shoot 3 shots
                    this.attackTime = 6;

                    // Spawn Projectile
                    BrineBallEntity brineBall = new BrineBallEntity(this.brine.level(), this.brine);
                    brineBall.setPos(brine.getX(), brine.getY(0.5D), brine.getZ());

                    double d0 = target.getX() - brine.getX();
                    double d1 = target.getY(0.5D) - brine.getY(0.5D);
                    double d2 = target.getZ() - brine.getZ();
                    brineBall.shoot(d0, d1, d2, 1.5F, 1.0F);

                    this.brine.level().addFreshEntity(brineBall);
                } else {
                    this.attackTime = 100; // Cooldown
                    this.attackStep = 0;
                }
            }
            this.attackTime--;
        }
        super.tick();
    }
}
