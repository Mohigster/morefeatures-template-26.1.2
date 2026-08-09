package com.mohigster.morefeatures.data.world.biome.custom;

import com.mohigster.morefeatures.entity.MFEntityTypes;
import com.mohigster.morefeatures.data.sound.MFSoundEvents;
import com.mohigster.morefeatures.data.world.feature.MFPlacedFeatures;
import com.mohigster.morefeatures.data.world.carver.MFCarvers;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class MFOverworldBiomes {
    public static Biome bloodwoodForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 30);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(EntityTypes.ARMADILLO, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(EntityTypes.CAMEL, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS_2);
        BiomeDefaultFeatures.addBushes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.BLOODWOOD_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.SMALL_BLOODWOOD_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.FALLEN_BLOODWOOD_PLACED_KEY);


        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(4.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0xf13123).grassColorOverride(0xf26231)).build())
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, -13432824)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, -2354116)
                .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(155, 200, 31, 25))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.CRIMSON_SPORE, 0.00625F))
                .build();
    }

    public static Biome taintedForest(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 30);
        spawnBuilder.addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(EntityTypes.ARMADILLO, 2, 3));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(EntityTypes.CAMEL, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_TALL_GRASS_2);
        BiomeDefaultFeatures.addBushes(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);

        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.TAINTED_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.SMALL_TAINTED_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.FALLEN_TAINTED_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(4.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0x8121a3).grassColorOverride(0x8242a1)).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, -14152110)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, -5614132)
                .setAttribute(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(155, 160, 31, 185))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_BADLANDS))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.WARPED_SPORE, 0.00625F))
                .build();
    }

    public static Biome iceCave(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        // Spawns tailored for a cold cave environment
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 30);
        spawnBuilder.addSpawn(MobCategory.MONSTER, 80, new MobSpawnSettings.SpawnerData(MFEntityTypes.ICEOLOGER.get(), 1, 1));
        spawnBuilder.addSpawn(MobCategory.MONSTER, 95, new MobSpawnSettings.SpawnerData(EntityTypes.STRAY, 4, 4));
        spawnBuilder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.POLAR_BEAR, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addCarver(MFCarvers.ICE_CAVE);
        biomeBuilder.addCarver(MFCarvers.ICE_CAVE_EXTRA_UNDERGROUND);
        biomeBuilder.addCarver(MFCarvers.ICE_CANYON);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.SMALL_SNOW_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.SNOW_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.LARGE_SNOW_PATCH_PLACED_KEY);

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.SMALL_ICE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.ICE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.LARGE_ICE_PATCH_PLACED_KEY);

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.SMALL_BLUE_ICE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.BLUE_ICE_PATCH_PLACED_KEY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.LARGE_BLUE_ICE_PATCH_PLACED_KEY);

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, MFPlacedFeatures.EVERFROST_ORE_PLACED_KEY);

        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, MFPlacedFeatures.ICICLE_CLUSTER_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(-0.5F) // Below freezing point
                .downfall(0.5F)
                .specialEffects((new BiomeSpecialEffects.Builder()
                        .waterColor(0x3f76e4)
                        .build()))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, -4138753)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, -8871425)
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(MFSoundEvents.MUSIC_BIOME_ICE_CAVES))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.WHITE_ASH, 0.01F))
                .build();
    }
}

