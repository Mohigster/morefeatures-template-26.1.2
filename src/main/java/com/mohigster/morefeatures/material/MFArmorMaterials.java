package com.mohigster.morefeatures.material;

import com.google.common.collect.Maps;
import com.mohigster.morefeatures.asset.MFEquipmentAssets;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.Map;

public class MFArmorMaterials {
    private static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.withDefaultNamespace("equipment_asset"));

    public static final ResourceKey<EquipmentAsset> BISMUTH_KEY = createId("bismuth");
    public static final ResourceKey<EquipmentAsset> CARBON_KEY = createId("carbon");

    public static final ArmorMaterial BISMUTH = new ArmorMaterial(41, makeDefense(3, 6, 8, 3, 23), 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 5.0F, 0.15F, MFItemTags.REPAIRS_BISMUTH_ARMOR, MFEquipmentAssets.BISMUTH);
    public static final ArmorMaterial CARBON = new ArmorMaterial(36, makeDefense(3, 6, 8, 3, 19), 15, SoundEvents.ARMOR_EQUIP_DIAMOND, 3.0F, 0.10F, MFItemTags.REPAIRS_CARBON_ARMOR, MFEquipmentAssets.CARBON);

    private static ResourceKey<EquipmentAsset> createId(String name){
        return ResourceKey.create(ROOT_ID, MFIdentifier.withMfNamespace(name));
    }

    @SuppressWarnings("SameParameterValue")
    private static Map<ArmorType, Integer> makeDefense(int boots, int legs, int chest, int helm, int body) {
        return Maps.newEnumMap(Map.of(ArmorType.BOOTS, boots, ArmorType.LEGGINGS, legs, ArmorType.CHESTPLATE, chest, ArmorType.HELMET, helm, ArmorType.BODY, body));
    }
}
