package com.mohigster.morefeatures.datagen.custom;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.magicblock.TransmutationEntry;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unused")
public abstract class MagicBlockTransmutationProvider implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String modId;
    private final Map<Identifier, TransmutationEntry> entries = new HashMap<>();

    protected MagicBlockTransmutationProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries,
            String modId
    ) {
        this.output = output;
        this.registries = registries;
        this.modId = modId;
    }

    protected MagicBlockTransmutationProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        this.output = output;
        this.registries = registries;
        this.modId = MoreFeatures.MODID;
    }

    protected abstract void generate();

    protected void add(TagKey<Item> inputTag, Item output) {
        this.add(inputTag, output, false);
    }

    protected void add(String transmutationName, TagKey<Item> inputTag, Item output) {
        this.add(transmutationName, inputTag, output, false);
    }

    protected void add(TagKey<Item> inputTag, Item output, boolean copyComponents) {
        String itemName = BuiltInRegistries.ITEM.getKey(output).getPath();

        this.add(itemName, inputTag, output, copyComponents);
    }

    protected void add(String transmutationName, TagKey<Item> inputTag, Item output, boolean copyComponents) {
        String descriptionId = transmutationName + "_from_magic_block";

        this.add(Identifier.fromNamespaceAndPath(modId, descriptionId), inputTag, output, copyComponents);
    }

    // This method is private because the Identifier is already defined by the other add methods
    private void add(Identifier id, TagKey<Item> inputTag, Item output, boolean copyComponents) {
        if (output == Items.AIR) {
            throw new IllegalArgumentException("Cannot transmute to AIR!");
        }

        TransmutationEntry entry = new TransmutationEntry(inputTag, output, copyComponents);
        TransmutationEntry existing = entries.putIfAbsent(id, entry);

        if (existing != null) {
            throw new IllegalStateException("Duplicate magic block transmutation id: " + id);
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
                                .resolve("magic_block_transmutations")
                                .resolve(e.getKey().getPath() + ".json");

                        return DataProvider.saveStable(cachedOutput, provider, TransmutationEntry.CODEC, e.getValue(), path);
                    }).toArray(CompletableFuture[]::new)
            );
        });
    }

    @Override
    public @NonNull String getName() {
        return "Magic Block Transmutations: " + modId;
    }
}
