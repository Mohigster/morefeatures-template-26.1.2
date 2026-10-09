package com.mohigster.morefeatures.item.custom;

import com.mohigster.morefeatures.data.material.BowMaterial;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class MFBowItem extends BowItem {
    private final int useDuration;

    public MFBowItem(BowMaterial material, Properties properties) {
        this.useDuration = material.useTime();

        super(material.applyProperties(properties));
    }

    @Override
    public int getUseDuration(@NonNull ItemStack stack, @NonNull LivingEntity user) {
        return this.getUseDuration();
    }

    // This is a custom method. No annotation needed
    public int getUseDuration() {
        return this.useDuration;
    }
}
