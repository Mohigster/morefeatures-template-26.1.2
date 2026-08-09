package com.mohigster.morefeatures.data.world.biome;

import com.mohigster.morefeatures.data.references.MFIdentifier;
import com.mohigster.morefeatures.data.world.biome.custom.MFEndBiomes;
import com.mohigster.morefeatures.data.world.biome.custom.MFNetherBiomes;
import com.mohigster.morefeatures.data.world.biome.custom.MFOverworldBiomes;
import com.mohigster.morefeatures.data.world.biome.region.NetherRegion;
import com.mohigster.morefeatures.data.world.biome.region.OverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.EndBiomeRegistry;
import terrablender.api.Regions;

public class MFBiomes {
    public static final ResourceKey<Biome> BLOODWOOD_FOREST = registerBiomeKey("bloodwood_forest");
    public static final ResourceKey<Biome> TAINTED_FOREST = registerBiomeKey("tainted_forest");

    public static final ResourceKey<Biome> ICE_CAVES = registerBiomeKey("ice_caves");

    public static final ResourceKey<Biome> CHARRED_FOREST = registerBiomeKey("charred_forest");

    public static final ResourceKey<Biome> END_ROT = registerBiomeKey("end_rot");
    public static final ResourceKey<Biome> DECREPIT_FOREST = registerBiomeKey("decrepit_forest");
    public static final ResourceKey<Biome> PALLID_FOREST = registerBiomeKey("pallid_forest");

    public static void registerBiomes() {
        // Register regions
        Regions.register(new OverworldRegion(30));
        Regions.register(new NetherRegion(50));

        // Separate EndBiomeRegistry, as end biomes work differently to overworld ones.
        EndBiomeRegistry.registerHighlandsBiome(END_ROT, 10);
        EndBiomeRegistry.registerHighlandsBiome(DECREPIT_FOREST, 7);
        EndBiomeRegistry.registerHighlandsBiome(PALLID_FOREST, 7);
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carver = context.lookup(Registries.CONFIGURED_CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        // Surface Overworld Biomes

        register(context, BLOODWOOD_FOREST, MFOverworldBiomes.bloodwoodForest(placedFeatures, carver));
        register(context, TAINTED_FOREST, MFOverworldBiomes.taintedForest(placedFeatures, carver));

        // Underground Overworld Biomes

        register(context, ICE_CAVES, MFOverworldBiomes.iceCave(placedFeatures, carver));

        // Nether Biomes

        register(context, CHARRED_FOREST, MFNetherBiomes.charredForest(placedFeatures, carver));

        // End Biomes

        register(context, END_ROT, MFEndBiomes.endRot(placedFeatures, carver));
        register(context, DECREPIT_FOREST, MFEndBiomes.decrepitForest(placedFeatures, carver));
        register(context, PALLID_FOREST, MFEndBiomes.pallidForest(placedFeatures, carver));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, MFIdentifier.withMfNamespace(name));
    }
}
