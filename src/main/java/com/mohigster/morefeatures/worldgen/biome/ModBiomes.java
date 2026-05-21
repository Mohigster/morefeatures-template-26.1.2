package com.mohigster.morefeatures.worldgen.biome;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.worldgen.biome.region.OverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.Regions;

public class ModBiomes {
    public static final ResourceKey<Biome> BLOODWOOD_FOREST = registerBiomeKey("bloodwood_forest");
    public static final ResourceKey<Biome> TAINTED_FOREST = registerBiomeKey("tainted_forest");

    public static void registerBiomes() {
        Regions.register(new OverworldRegion(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures_overworld"), 20));
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carver = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, BLOODWOOD_FOREST, ModOverworldBiomes.bloodwoodForest(placedFeatures, carver));
        register(context, TAINTED_FOREST, ModOverworldBiomes.taintedForest(placedFeatures, carver));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }

}
