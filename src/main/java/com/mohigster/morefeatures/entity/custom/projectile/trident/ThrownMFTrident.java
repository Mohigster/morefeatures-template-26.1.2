package com.mohigster.morefeatures.entity.custom.projectile.trident;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NullMarked;


public class ThrownMFTrident extends ThrownTrident {
    private final EntityType<? extends ThrownTrident> tridentEntity;
    private final Identifier pickupItem;

    public ThrownMFTrident(Level level, LivingEntity shooter, ItemStack stack, EntityType<? extends ThrownTrident> tridentEntity, Identifier pickupItem) {
        super(level, shooter, stack);
        this.tridentEntity = tridentEntity;
        this.pickupItem = pickupItem;
    }

    public ThrownMFTrident(EntityType<? extends ThrownTrident> tridentEntity, Level level, Identifier pickupItem) {
        super(tridentEntity, level);
        this.tridentEntity = tridentEntity;
        this.pickupItem = pickupItem;
    }

    public ThrownMFTrident(Level level, double x, double y, double z, ItemStack itemStack, EntityType<? extends ThrownTrident> tridentEntity, Identifier pickupItem) {
        super(level, x, y, z, itemStack);
        this.tridentEntity = tridentEntity;
        this.pickupItem = pickupItem;
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
