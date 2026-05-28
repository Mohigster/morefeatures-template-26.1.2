package com.mohigster.morefeatures.entity.projectile.brine;

import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class BrineBallEntity extends ThrowableProjectile {
    protected BrineBallEntity(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    public BrineBallEntity(Level level, LivingEntity shooter) {
        super(EntityType.SNOWBALL, level); // Replace with your registered EntityType later
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (!this.level().isClientSide() && result.getEntity() instanceof LivingEntity target) {
            // Deal damage
            target.hurt(this.damageSources().thrown(this, this.getOwner()), 5.0F);

            // Apply Saltwater Slowness/Blinding effect
            target.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 60, 1));

            // Push them back (Hydraulic force)
            Vec3 look = this.getLookAngle();
            target.push(look.x * 1.5, 0.2, look.z * 1.5);
        }
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide()) {
            // Extinguish fire on hit, crack open like a splash potion
            this.level().levelEvent(2002, this.blockPosition(), 0); // Potion splash effect
            this.discard();
        }
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }
}
