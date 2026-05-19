package com.mohigster.morefeatures.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;

public class ModBiomes {
    public static final ResourceKey<Biome> BLOODWOOD_FOREST = ResourceKey.create(
            Registries.BIOME, Identifier.fromNamespaceAndPath("morefeatures", "bloodwood_forest")
    );
    public static void bootstrap(BootstrapContext<Biome> context) {
        context.register(BLOODWOOD_FOREST, createMyBiome(context));
    }

    private static Biome createMyBiome(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        // Use vanilla helper methods or add your own custom spawns here
        BiomeDefaultFeatures.farmAnimals(spawnSettings);
        BiomeDefaultFeatures.commonSpawns(spawnSettings);

        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE),
                context.lookup(Registries.CONFIGURED_CARVER)
        );
        // Add things like grass, trees, and default features
        BiomeDefaultFeatures.addDefaultOres(generationSettings);
        BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
        BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
        BiomeDefaultFeatures.addDefaultGrass(generationSettings);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.7F)
                .downfall(0.8F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(4159204)
                        .grassColorOverride(9302111)
                        .build())
                .mobSpawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
