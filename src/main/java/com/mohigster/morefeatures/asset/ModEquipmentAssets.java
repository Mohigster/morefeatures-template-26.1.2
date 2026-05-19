package com.mohigster.morefeatures.asset;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import static net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID;

public interface ModEquipmentAssets {
    ResourceKey<EquipmentAsset> BISMUTH = createId("bismuth");
    ResourceKey<EquipmentAsset> CARBON_ELYTRA = createId("carbon_elytra");

    static ResourceKey<EquipmentAsset> createId(String name) {
        return ResourceKey.create(ROOT_ID, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }
}
