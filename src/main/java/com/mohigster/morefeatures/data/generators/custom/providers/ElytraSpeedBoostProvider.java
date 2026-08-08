package com.mohigster.morefeatures.data.generators.custom.providers;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.events.data.ElytraSpeedEntry;
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

@SuppressWarnings("deprecation")
public abstract class ElytraSpeedBoostProvider implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> registries;
    private final String modId;
    private final Map<Identifier, ElytraSpeedEntry> entries = new HashMap<>();

    protected ElytraSpeedBoostProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries,
            String modId
    ) {
        this.output = output;
        this.registries = registries;
        this.modId = modId;
    }

    protected ElytraSpeedBoostProvider(
            PackOutput output,
            CompletableFuture<HolderLookup.Provider> registries
    ) {
        this(output, registries, MoreFeatures.MODID);
    }

    protected abstract void generate();

    protected void add(Item elytra, double percentSpeedBoost){
        String elytraName = getElytraName(elytra);

        this.add(elytraName, elytra, percentSpeedBoost);
    }

    protected void add(Item elytra, double percentSpeedBoost, double maxSpeed){ // This method automatically sets the item name
        String elytraName = getElytraName(elytra);

        this.add(elytraName, elytra, percentSpeedBoost, maxSpeed);
    }

    protected void add(String elytraName, Item elytra, double percentSpeedBoost){
        this.add(elytraName, elytra, percentSpeedBoost, 0.0D);
    }

    protected void add(String elytraName, Item elytra, double percentSpeedBoost, double maxSpeed){ // Allows manually setting the item name.
        String jsonFileName = elytraName + "_speed_boost";

        this.add(Identifier.fromNamespaceAndPath(modId, jsonFileName), elytra, percentSpeedBoost, maxSpeed);
    }

    private String getElytraName(Item elytra){
        return BuiltInRegistries.ITEM.getKey(elytra).getPath();
    }

    protected void add(Identifier id, Item item, double percentSpeedBoost, double maxSpeed) {
        HolderSet<Item> holderSet = HolderSet.direct(item.builtInRegistryHolder());
        this.entries.put(id, new ElytraSpeedEntry(holderSet, percentSpeedBoost, maxSpeed));
    }

    protected void add(Identifier id, double percentSpeedBoost, double maxSpeed, Item... items) {
        List<Holder<Item>> holders = Arrays.stream(items)
                .map(Item::builtInRegistryHolder)
                .map(h -> (Holder<Item>) h)
                .toList();
        HolderSet<Item> holderSet = HolderSet.direct(holders);
        this.entries.put(id, new ElytraSpeedEntry(holderSet, percentSpeedBoost, maxSpeed));
    }

    protected void add(Identifier id, TagKey<Item> tag, double percentSpeedBoost, double maxSpeed) {
        // TagKey can be resolved via the registry lookup
        HolderSet<Item> holderSet = BuiltInRegistries.ITEM.getOrThrow(tag);
        this.entries.put(id, new ElytraSpeedEntry(holderSet, percentSpeedBoost, maxSpeed));
    }

    @NullMarked
    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        this.entries.clear();
        generate();

        return this.registries.thenCompose(provider -> {
            Path outputFolder = this.output.getOutputFolder(PackOutput.Target.DATA_PACK);

            return CompletableFuture.allOf(
                    this.entries.entrySet().stream().map(e -> {
                        Path path = outputFolder
                                .resolve(e.getKey().getNamespace())
                                .resolve("elytra_speed_boosts")
                                .resolve(e.getKey().getPath() + ".json");

                        return DataProvider.saveStable(cachedOutput, provider, ElytraSpeedEntry.CODEC, e.getValue(), path);
                    }).toArray(CompletableFuture[]::new)
            );
        });
    }

    @Override
    public @NonNull String getName() {
        return "Elytra Speed Boosts: " + modId;
    }
}
