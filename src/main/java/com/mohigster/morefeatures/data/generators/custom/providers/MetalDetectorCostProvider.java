package com.mohigster.morefeatures.data.generators.custom.providers;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.custom.metaldetector.DetectorCostEntry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public abstract class MetalDetectorCostProvider implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String modId;
    private final Map<Identifier, DetectorCostEntry> entries = new HashMap<>();

    protected MetalDetectorCostProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, String modId) {
        this.output = output;
        this.registries = registries;
        this.modId = modId;
    }

    protected MetalDetectorCostProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        this(output, registries, MoreFeatures.MODID);
    }

    protected abstract void generate();

    protected void add(TagKey<Block> tag, int cost) {
        String costName = "durability_cost_of_" + cost;

        add(Identifier.fromNamespaceAndPath(modId, costName), tag, cost);
    }

    protected void add(Identifier id, TagKey<Block> tag, int cost) {
        DetectorCostEntry entry = new DetectorCostEntry(tag, cost);
        if (entries.putIfAbsent(id, entry) != null) {
            throw new IllegalStateException("Duplicate cost entry ID: " + id);
        }
    }

    @NullMarked
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        entries.clear();
        generate();

        return registries.thenCompose(provider -> {
            Path outputFolder = output.getOutputFolder(PackOutput.Target.DATA_PACK);

            return CompletableFuture.allOf(
                    entries.entrySet().stream().map(e -> {
                        Path path = outputFolder
                                .resolve(e.getKey().getNamespace())
                                .resolve("metal_detector_costs")
                                .resolve(e.getKey().getPath() + ".json");

                        return DataProvider.saveStable(cachedOutput, provider, DetectorCostEntry.CODEC, e.getValue(), path);
                    }).toArray(CompletableFuture[]::new)
            );
        });
    }

    @Override
    public @NonNull String getName() {
        return "Metal Detector Costs: " + modId;
    }
}
