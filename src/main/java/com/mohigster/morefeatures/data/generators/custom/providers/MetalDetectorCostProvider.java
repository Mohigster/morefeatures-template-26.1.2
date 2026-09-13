package com.mohigster.morefeatures.data.generators.custom.providers;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.custom.metaldetector.DetectorCostEntry;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
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
import java.util.concurrent.ExecutionException;

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
        try {
            HolderSet<Block> value = this.registries.get().getOrThrow(tag);
            DetectorCostEntry entry = new DetectorCostEntry(value, cost);
            if (entries.putIfAbsent(id, entry) != null) {
                throw new IllegalStateException("Duplicate cost entry ID: " + id);
            }
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @NullMarked
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        this.entries.clear();
        this.generate();

        return this.registries.thenCompose(provider -> {
            Path outputFolder = this.output.getOutputFolder(PackOutput.Target.DATA_PACK);

            return CompletableFuture.allOf(
                    this.entries.entrySet().stream().map(e -> {
                        Path path = outputFolder
                                .resolve(e.getKey().getNamespace())
                                .resolve(Directories.METAL_DETECTOR_PATH)
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
