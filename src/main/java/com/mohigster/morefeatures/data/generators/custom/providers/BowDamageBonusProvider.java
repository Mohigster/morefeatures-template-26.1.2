package com.mohigster.morefeatures.data.generators.custom.providers;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.events.data.BowDamageEntry;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SuppressWarnings("deprecation")
public abstract class BowDamageBonusProvider implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String modId;
    private final Map<Identifier, BowDamageEntry> entries = new HashMap<>();

    protected BowDamageBonusProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries,
            String modId
    ) {
        this.output = output;
        this.registries = registries;
        this.modId = modId;
    }

    protected BowDamageBonusProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        this(output, registries, MoreFeatures.MODID);
    }

    protected abstract void generate();

    protected void add(Item item, double damageBonus){
        String bowItemName = BuiltInRegistries.ITEM.getKey(item).getPath();

        this.add(bowItemName, item, damageBonus);
    }

    protected void add(String bowItemName, Item item, double damageBonus) {
        this.add(Identifier.fromNamespaceAndPath(modId, bowItemName), item, damageBonus);
    }

    protected void add(double damageBonus, Item... items){
        String bowItemName = BuiltInRegistries.ITEM.getKey(Arrays.stream(items).findFirst().get()).getPath();

        this.add(bowItemName, damageBonus, items);
    }

    protected void add(String path, double damageBonus, Item... items) {
        this.add(Identifier.fromNamespaceAndPath(modId, path), damageBonus, items);
    }

    protected void add(TagKey<Item> itemTag, double damageBonus){
        String itemTagName = BuiltInRegistries.ITEM.get(itemTag).get().toString();

        this.add(itemTagName, itemTag, damageBonus);
    }

    protected void add(String path, TagKey<Item> itemTag, double damageBonus){
        this.add(Identifier.fromNamespaceAndPath(modId, path), itemTag, damageBonus);
    }

    protected void add(Identifier id, Item item, double damageBonus) {
        HolderSet<Item> holderSet = HolderSet.direct(item.builtInRegistryHolder());
        entries.put(id, new BowDamageEntry(holderSet, damageBonus));
    }

    protected void add(Identifier id, double damageBonus, Item... items) {
        List<Holder<Item>> holders = Arrays.stream(items)
                .map(Item::builtInRegistryHolder)
                .map(h -> (Holder<Item>) h)
                .toList();
        HolderSet<Item> holderSet = HolderSet.direct(holders);
        entries.put(id, new BowDamageEntry(holderSet, damageBonus));
    }

    protected void add(Identifier id, TagKey<Item> tag, double damageBonus) {
        try {
            HolderSet<Item> holderSet = this.registries.get().getOrThrow(tag);
            entries.put(id, new BowDamageEntry(holderSet, damageBonus));
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
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
                                .resolve(Directories.BOW_PATH)
                                .resolve(e.getKey().getPath() + ".json");

                        return DataProvider.saveStable(cachedOutput, provider, BowDamageEntry.CODEC, e.getValue(), path);
                    }).toArray(CompletableFuture[]::new)
            );
        });
    }

    @Override
    public @NonNull String getName() {
        return "Bow Damage Bonuses: " + modId;
    }
}
