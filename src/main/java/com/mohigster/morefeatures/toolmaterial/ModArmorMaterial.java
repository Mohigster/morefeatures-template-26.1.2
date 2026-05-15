package com.mohigster.morefeatures.toolmaterial;

import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.EnumMap;

import static com.mohigster.morefeatures.MoreFeatures.MODID;


public class ModArmorMaterial {
    public interface Armor {
        ArmorMaterial BISMUTH = new ArmorMaterial(
                20,
                createMap(new int[]{3, 8, 6, 3, 8}),
                22,
                SoundEvents.ARMOR_EQUIP_DIAMOND,
                5f,
                0.1f,
                ModItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE,
                createAsset("bismuth"));
    }
    private static ResourceKey<EquipmentAsset> createAsset(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(MODID, name));
    }
    private static EnumMap<ArmorType, Integer> createMap(int[] values) {
        EnumMap<ArmorType, Integer> enumMap = new EnumMap<>(ArmorType.class);
        for (int i = 0; i < values.length; i++) enumMap.put(ArmorType.values()[i], values[i]);
        return enumMap;
    }
}
