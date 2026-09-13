package com.mohigster.morefeatures.data.material;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public record BowMaterial(
        int durability,
        float speed,
        double damageBonus,
        int enchantmentValue,
        int useTime,
        TagKey<Item> repairItems
) {
    public static BowMaterial fromToolMaterial(ToolMaterial base, int useTime, double damageBonus) {
        int durability = (int) Math.round(base.durability() * 0.85D);

        return new BowMaterial(
                durability,
                base.speed(),
                damageBonus,
                base.enchantmentValue(),
                useTime,
                base.repairItems()
        );
    }

    @SuppressWarnings("unused")
    public static BowMaterial fromToolMaterial(ToolMaterial base, int useTime) {
        return fromToolMaterial(base, useTime, base.attackDamageBonus());
    }

    public static final BowMaterial BISMUTH_BOW_MATERIAL = fromToolMaterial(MFToolMaterial.BISMUTH_TOOL_MATERIAL, 90000, 1.45D);
    public static final BowMaterial CARBON_BOW_MATERIAL = fromToolMaterial(MFToolMaterial.CARBON_TOOL_MATERIAL, 82500, 1.2D);
}
