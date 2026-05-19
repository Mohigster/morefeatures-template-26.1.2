package com.mohigster.morefeatures.toolmaterial;

import com.google.common.collect.Maps;
import com.mohigster.morefeatures.asset.ModEquipmentAssets;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.Map;

public interface ModArmorMaterials {
    ArmorMaterial BISMUTH = new ArmorMaterial(41, makeDefense(3, 6, 8, 3, 23), 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 5.0F, 0.15F, ModItemTags.REPAIRS_BISMUTH_ARMOR, ModEquipmentAssets.BISMUTH);
    ArmorMaterial CARBON = new ArmorMaterial(41, makeDefense(3, 6, 8, 3, 19), 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 5.0F, 0.15F, ModItemTags.REPAIRS_CARBON_ARMOR, ModEquipmentAssets.CARBON);
    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
