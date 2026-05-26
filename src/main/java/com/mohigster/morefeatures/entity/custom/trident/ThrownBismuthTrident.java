package com.mohigster.morefeatures.entity.custom.trident;

import com.mohigster.morefeatures.entity.entity_types.ModEntityTypes;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class ThrownBismuthTrident extends ThrownTrident {

    public ThrownBismuthTrident(Level level, LivingEntity shooter, ItemStack stack) {
        super(level, shooter, stack);
    }

    public ThrownBismuthTrident(EntityType<? extends ThrownBismuthTrident> type, Level level) {
        super(type, level);
    }

    public ThrownBismuthTrident(Level level, double x, double y, double z, ItemStack itemStack) {
        super(level, x, y, z, itemStack);
    }

    @Override
    public EntityType<?> getType() {
        return ModEntityTypes.BISMUTH_TRIDENT.get();
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.BISMUTH_TRIDENT.get());
    }
}
