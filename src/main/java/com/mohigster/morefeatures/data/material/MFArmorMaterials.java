package com.mohigster.morefeatures.data.material;

import com.google.common.collect.Maps;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class MFArmorMaterials {
    public static final ArmorMaterial BISMUTH = createArmor(41, 3, 6, 8, 3, 23, 19, 5.0F, 0.15F, MFItemTags.REPAIRS_BISMUTH_ARMOR, MFEquipmentAssets.BISMUTH);
    public static final ArmorMaterial CARBON = createArmor(36, 2, 5, 7, 2, 19, 15, 3.0F, 0.10F, MFItemTags.REPAIRS_CARBON_ARMOR, MFEquipmentAssets.CARBON);

    private static ArmorMaterial createArmor(
            int durability, int boots, int legs,
            int chest, int helm, int body,
            int enchantmentValue, float toughness,
            float knockbackResistance,
            TagKey<Item> repairTag,
            ResourceKey<EquipmentAsset> asset
    ) {
        return new ArmorMaterial(
                durability,
                makeDefense(
                        boots,
                        legs,
                        chest,
                        helm,
                        body
                ),
                enchantmentValue,
                SoundEvents.ARMOR_EQUIP_DIAMOND,
                toughness,
                knockbackResistance,
                repairTag,
                asset
        );
    }

    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
