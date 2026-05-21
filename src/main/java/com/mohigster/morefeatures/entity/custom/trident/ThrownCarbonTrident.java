package com.mohigster.morefeatures.entity.custom.trident;

import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class ThrownCarbonTrident extends ThrownTrident {

    public ThrownCarbonTrident(Level level, LivingEntity shooter, ItemStack stack) {
        super(level, shooter, stack);
    }

    public ThrownCarbonTrident(EntityType<? extends ThrownCarbonTrident> type, Level level) {
        super(type, level);
    }

    public ThrownCarbonTrident(Level level, double x, double y, double z, ItemStack itemStack) {
        super(level, x, y, z, itemStack);
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        // Essential: Makes sure the player gets back a Carbon Trident when picked up
        return new ItemStack(ModItems.CARBON_TRIDENT.get());
    }





}
