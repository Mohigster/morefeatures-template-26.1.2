package com.mohigster.morefeatures.item.custom;

import com.mohigster.morefeatures.data.component.MFDataComponentTypes;
import com.mohigster.morefeatures.data.material.BowMaterial;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import org.jspecify.annotations.NonNull;

public class MFBowItem extends BowItem {
    private final int useDuration;

    public MFBowItem(BowMaterial material, Properties properties) {
        this.useDuration = material.useTime();

        super(properties
                .durability(material.durability())
                .repairable(material.repairItems())
                .enchantable(material.enchantmentValue())
                .component(MFDataComponentTypes.BOW_DAMAGE_BONUS,  material.damageBonus())
        );
    }

    @Override
    public int getUseDuration(@NonNull ItemStack itemStack, @NonNull LivingEntity user) {
        return this.useDuration;
    }
}
