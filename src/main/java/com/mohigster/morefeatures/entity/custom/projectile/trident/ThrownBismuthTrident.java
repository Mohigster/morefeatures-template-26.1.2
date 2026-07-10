package com.mohigster.morefeatures.entity.custom.projectile.trident;

import com.mohigster.morefeatures.entity.entity_types.MFEntityTypes;
import com.mohigster.morefeatures.item.MFItems;
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
        return MFEntityTypes.BISMUTH_TRIDENT.get();
    }


    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(MFItems.BISMUTH_TRIDENT.get());
    }
}
