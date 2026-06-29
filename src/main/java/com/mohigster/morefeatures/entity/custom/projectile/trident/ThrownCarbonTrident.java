package com.mohigster.morefeatures.entity.custom.projectile.trident;

import com.mohigster.morefeatures.entity.entity_types.ModEntityTypes;
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
    public EntityType<?> getType() {
        return ModEntityTypes.CARBON_TRIDENT.get();
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.CARBON_TRIDENT.get());
    }
}
