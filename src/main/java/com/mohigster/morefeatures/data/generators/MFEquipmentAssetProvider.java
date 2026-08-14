package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.data.material.MFArmorMaterials;
import net.minecraft.client.resources.model.EquipmentClientInfo;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class MFEquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;

    public MFEquipmentAssetProvider(PackOutput packOutput) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
    }

    private static void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output){
        output.accept(MFArmorMaterials.BISMUTH_KEY, EquipmentClientInfo.builder()
                        .addHumanoidLayers(MFIdentifier.withMfNamespace("bismuth"), false)
                        .addLayers(EquipmentClientInfo.LayerType.HORSE_BODY, createLayer("bismuth"))
                        .addLayers(EquipmentClientInfo.LayerType.NAUTILUS_BODY, createLayer("bismuth"))
                .build());
        output.accept(MFArmorMaterials.CARBON_KEY, EquipmentClientInfo.builder()
                .addLayers(EquipmentClientInfo.LayerType.WOLF_BODY, createLayer("carbon"))
                .addLayers(EquipmentClientInfo.LayerType.WINGS, createLayer("carbon_elytra", true))
                .build());
    }

    @NullMarked
    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
        bootstrap((id, asset) -> {
            if(equipmentAssets.putIfAbsent(id, asset) != null){
                throw new IllegalStateException("Tried to register equipment asset twice for the " + id + " id");
            }
        });

        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @NullMarked
    @Override
    public String getName() {
        return "More Features Equipment Definitions";
    }

    private static EquipmentClientInfo.Layer createLayer(String name){
        return new EquipmentClientInfo.Layer(MFIdentifier.withMfNamespace(name));
    }

    @SuppressWarnings("SameParameterValue")
    private static EquipmentClientInfo.Layer createLayer(String name, boolean usePlayerTexture){
        return new EquipmentClientInfo.Layer(MFIdentifier.withMfNamespace(name), Optional.empty(), usePlayerTexture);
    }
}
