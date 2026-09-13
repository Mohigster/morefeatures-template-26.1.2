package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.generators.custom.providers.EquipmentAssetProvider;
import com.mohigster.morefeatures.data.material.MFEquipmentAssets;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jspecify.annotations.NonNull;

import java.util.function.BiConsumer;

public class MFEquipmentAssetProvider extends EquipmentAssetProvider {
    public MFEquipmentAssetProvider(PackOutput packOutput) {
        super(packOutput, MoreFeatures.MODID);
    }

    @Override
    public void bootstrap(@NonNull BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output) {
        output.accept(MFEquipmentAssets.BISMUTH, EquipmentClientInfo.builder()
                .addHumanoidLayers(MFIdentifier.withMfNamespace("bismuth"), false)
                .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, createLayer("bismuth"))
                .addLayers(EquipmentClientInfo.LayerType.NAUTILUS_BODY, createLayer("bismuth"))
                .build());

        // Bismuth elytra must be separate because it has humanoid layers. This causes the elytra to also show the bismuth chestplate alongside the elytra layer when put together
        output.accept(MFEquipmentAssets.BISMUTH_ELYTRA, EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.WINGS, createLayer("bismuth_elytra", true))
                .build());

        // Carbon lacks the above problem because it does not have humanoid layers
        output.accept(MFEquipmentAssets.CARBON, EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.WOLF_BODY, createLayer("carbon"))
                .addLayers(EquipmentClientInfo.LayerType.WINGS, createLayer("carbon_elytra", true))
                .build());
    }
}
