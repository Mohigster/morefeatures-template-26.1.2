package com.mohigster.morefeatures.entity.custom.projectile.trident;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import org.jspecify.annotations.NullMarked;

public class ThrownMFTrident extends ThrownTrident {
    private final EntityType<? extends ThrownTrident> tridentEntity;
    private final Identifier pickupItem;
    private final float projectileDamage;

    public ThrownMFTrident(Level level, LivingEntity shooter, ItemStack stack, EntityType<? extends ThrownTrident> tridentEntity, Identifier pickupItem, float projectileDamage) {
        super(level, shooter, stack);
        this.tridentEntity = tridentEntity;
        this.pickupItem = pickupItem;
        this.projectileDamage = projectileDamage;
    }

    public ThrownMFTrident(EntityType<? extends ThrownTrident> tridentEntity, Level level, Identifier pickupItem, float projectileDamage) {
        super(tridentEntity, level);
        this.tridentEntity = tridentEntity;
        this.pickupItem = pickupItem;
        this.projectileDamage = projectileDamage;
    }

    public ThrownMFTrident(Level level, double x, double y, double z, ItemStack itemStack, EntityType<? extends ThrownTrident> tridentEntity, Identifier pickupItem, float projectileDamage) {
        super(level, x, y, z, itemStack);
        this.tridentEntity = tridentEntity;
        this.pickupItem = pickupItem;
        this.projectileDamage = projectileDamage;
    }

    @SuppressWarnings("deprecation")
    @Override
    protected void onHitEntity(EntityHitResult hitResult) {
        Entity entity = hitResult.getEntity();
        float dmg = projectileDamage;
        Entity currentOwner = this.getOwner();
        DamageSource damageSource = this.damageSources().trident(this, (currentOwner == null ? this : currentOwner));
        Level var7 = this.level();
        if (var7 instanceof ServerLevel serverLevel) {
            dmg = EnchantmentHelper.modifyDamage(serverLevel, this.getWeaponItem(), entity, damageSource, dmg);
        }

        this.dealtDamage = true;
        if (entity.hurtOrSimulate(damageSource, dmg)) {
            if (entity.is(EntityTypes.ENDERMAN)) {
                return;
            }

            var7 = this.level();
            if (var7 instanceof ServerLevel serverLevel) {
                EnchantmentHelper.doPostAttackEffectsWithItemSourceOnBreak(serverLevel, entity, damageSource, this.getWeaponItem(), (_) -> this.kill(serverLevel));
            }

            if (entity instanceof LivingEntity mob) {
                this.doKnockback(mob, damageSource);
                this.doPostHurtEffects(mob);
            }
        }

        this.deflect(ProjectileDeflection.REVERSE, entity, this.owner, false);
        this.setDeltaMovement(this.getDeltaMovement().multiply(0.02, 0.2, 0.02));
        this.playSound(SoundEvents.TRIDENT_HIT, 1.0F, 1.0F);
    }

    @NullMarked
    @Override
    public EntityType<?> getType() {
        return tridentEntity;
    }

    @NullMarked
    @Override
    protected ItemStack getDefaultPickupItem() {
        Item trident = BuiltInRegistries.ITEM.getValue(pickupItem);

        return new ItemStack(trident);
    }
}
