package com.mohigster.morefeatures.data.generators.custom.providers;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
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

public abstract class EquipmentAssetProvider implements DataProvider {
    private final PackOutput.PathProvider pathProvider;
    private final String modId;

    public EquipmentAssetProvider(PackOutput packOutput, String modId) {
        this.pathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "equipment");
        this.modId = modId;
    }

    protected abstract void bootstrap(BiConsumer<ResourceKey<EquipmentAsset>, EquipmentClientInfo> output);

    @NullMarked
    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<ResourceKey<EquipmentAsset>, EquipmentClientInfo> equipmentAssets = new HashMap<>();
        this.bootstrap((id, asset) -> {
            if(equipmentAssets.putIfAbsent(id, asset) != null){
                throw new IllegalStateException("Tried to register equipment asset twice for the " + id + " id");
            }
        });

        return DataProvider.saveAll(cache, EquipmentClientInfo.CODEC, this.pathProvider::json, equipmentAssets);
    }

    @NullMarked
    @Override
    public String getName() {
        return "Equipment Asset Definitions: " + this.modId;
    }

    protected static EquipmentClientInfo.Layer createLayer(String name){
        return createLayer(name, false);
    }

    protected static EquipmentClientInfo.Layer createLayer(String name, boolean usePlayerTexture){
        return new EquipmentClientInfo.Layer(MFIdentifier.withMfNamespace(name), Optional.empty(), usePlayerTexture);
    }
}
