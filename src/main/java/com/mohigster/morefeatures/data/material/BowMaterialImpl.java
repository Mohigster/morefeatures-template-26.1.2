package com.mohigster.morefeatures.data.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

record BowMaterialImpl(
        int durability,
        float speed,
        double damageBonus,
        int enchantmentValue,
        int useTime,
        TagKey<Item> repairItems
) implements BowMaterial {
    static BowMaterialImpl newBM(
            ToolMaterial base,
            int durability,
            double damageBonus,
            int useTime
    ) {
        return new BowMaterialImpl(
                durability,
                base.speed(),
                damageBonus,
                base.enchantmentValue(),
                useTime,
                base.repairItems()
        );
    }
}
