package com.mohigster.morefeatures.data.material;

import com.mohigster.morefeatures.data.component.MFDataComponentTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;

public sealed interface BowMaterial permits BowMaterialImpl {
    int durability();

    double damageBonus();

    int enchantmentValue();

    int useTime();

    TagKey<Item> repairItems();

    static BowMaterial of(ToolMaterial base, int useTime, double damageBonus) {
        int durability = (int) Math.round(base.durability() * 0.85D);

        return BowMaterialImpl.newBM(
                base,
                durability,
                damageBonus,
                useTime
        );
    }

    /**
     *  The attack damage bonus value of the base material is the damage bonus value of the bow material
     *  when using this method
     */
    static BowMaterial of(ToolMaterial base, int useTime) {
        return of(base, useTime, base.attackDamageBonus());
    }

    default Item.Properties applyProperties(Item.Properties props) {
        return props.repairable(this.repairItems())
                .durability(this.durability())
                .enchantable(this.enchantmentValue())
                .component(MFDataComponentTypes.BOW_DAMAGE_BONUS,  this.damageBonus());
    }

    BowMaterial BISMUTH = of(MFToolMaterial.BISMUTH, 90000, 1.45D);
    BowMaterial CARBON = of(MFToolMaterial.CARBON, 82500, 1.2D);
}
