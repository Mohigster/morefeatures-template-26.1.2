package com.mohigster.morefeatures.data.generators.custom.providers;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.magicblock.TransmutationEntry;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NullMarked;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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
        this(output, registries, MoreFeatures.MODID);
    }

    protected abstract void generate(HolderLookup.Provider provider);

    protected void addFromTag(Item output, TagKey<Item> inputTag) {
        this.addFromTag(false, output, inputTag);
    }

    @SuppressWarnings("SameParameterValue")
    protected void addFromTag(int extraAmount, Item output, TagKey<Item> inputTag) {
        this.addFromTag(inputTag, output, extraAmount, false);
    }

    protected void addFromTag(String transmutationName, TagKey<Item> inputTag, Item output) {
        this.addFromTag(transmutationName, inputTag, output, 0);
    }

    @SuppressWarnings("SameParameterValue")
    protected void addFromTag(String transmutationName, TagKey<Item> inputTag, Item output, int extraAmount) {
        this.addFromTag(transmutationName, inputTag, output, extraAmount, false);
    }

    protected void addFromTag(TagKey<Item> inputTag, Item output, int extraAmount, boolean copyComponents) {
        String itemName = BuiltInRegistries.ITEM.getKey(output).getPath();

        this.addFromTag(itemName, inputTag, output, extraAmount, copyComponents);
    }

    protected void addFromTag(boolean copyComponents, Item output, TagKey<Item> inputTag) {
        this.addFromTag(inputTag, output, 0, copyComponents);
    }

    protected void addFromTag(String transmutationName, TagKey<Item> inputTag, Item output, int extraAmount, boolean copyComponents) {
        String descriptionId = transmutationName + "_from_magic_block";

        this.add(Identifier.fromNamespaceAndPath(this.modId, descriptionId), extraAmount, copyComponents, output, inputTag);
    }

    // These methods are private because all they do is convert the TagKey or Item into a HolderSet for the main method

    private void add(Identifier id, int extraAmount, boolean copyComponents, Item output, TagKey<Item> inputTag) {
        try {
            this.add(id, this.registries.get().getOrThrow(inputTag), output, extraAmount, copyComponents);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("deprecation")
    private void add(Identifier id, int extraAmount, boolean copyComponents, Item output, Item input) {
        this.add(id, HolderSet.direct(Item::builtInRegistryHolder, input), output, extraAmount, copyComponents);
    }

    /**
     * Creates and registers a new {@link TransmutationEntry} associated with the given identifier.
     *
     * @param id the unique {@link Identifier} for this entry
     * @param holder the {@link HolderSet} containing input items or tags
     * @param output the target {@link Item} produced by the transmutation
     * @param extraAmount additional items to yield above the base amount (e.g. 0 produces a standard stack of 1)
     * @param copyComponents whether item components (e.g. enchantments, potion effects, or custom data components) should transfer to the output
     *
     * @throws IllegalStateException if an entry with the specified {@code id} is already registered
     * @throws IllegalArgumentException if {@code output} is {@link Items#AIR}
     */
    protected void add(Identifier id, HolderSet<Item> holder, Item output, int extraAmount, boolean copyComponents) {
        if (output == Items.AIR) {
            throw new IllegalArgumentException("The Magic Block cannot transmute to AIR!");
        }

        TransmutationEntry entry = new TransmutationEntry(holder, output, extraAmount, copyComponents);
        TransmutationEntry existing = this.entries.putIfAbsent(id, entry);

        if (existing != null) {
            throw new IllegalStateException("Duplicate magic block transmutation id: " + id);
        }
    }

    @NullMarked
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        this.entries.clear();

        try {
            var lookupProvider = this.registries.get();
            this.generate(lookupProvider);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return this.registries.thenCompose(provider -> {
            Path outputFolder = this.output.getOutputFolder(PackOutput.Target.DATA_PACK);

            return CompletableFuture.allOf(
                    this.entries.entrySet().stream().map(e -> {
                        Path path = outputFolder
                                .resolve(e.getKey().getNamespace())
                                .resolve(Directories.MAGIC_BLOCK_PATH)
                                .resolve(e.getKey().getPath() + ".json");

                        return DataProvider.saveStable(cachedOutput, provider, TransmutationEntry.CODEC, e.getValue(), path);
                    }).toArray(CompletableFuture[]::new)
            );
        });
    }

    @Override
    public String getName() {
        return "Magic Block Transmutations: " + this.modId;
    }
}
