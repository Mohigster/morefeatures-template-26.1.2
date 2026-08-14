package com.mohigster.morefeatures.data.material;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import static net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID;

public class MFEquipmentAssets {
    public static final ResourceKey<EquipmentAsset> BISMUTH = createId("bismuth");
    public static final ResourceKey<EquipmentAsset> CARBON = createId("carbon");

    private static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(ROOT_ID, MFIdentifier.withMfNamespace(name));
    }
}