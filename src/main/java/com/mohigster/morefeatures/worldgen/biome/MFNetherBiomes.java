package com.mohigster.morefeatures.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class MFNetherBiomes {
    public static Biome charredForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter){
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 3, new MobSpawnSettings.SpawnerData(EntityTypes.GHAST, 1, 1))
                .addSpawn(MobCategory.MONSTER, 8, new MobSpawnSettings.SpawnerData(EntityTypes.MAGMA_CUBE, 1, 3))
                .addSpawn(MobCategory.MONSTER, 13, new MobSpawnSettings.SpawnerData(EntityTypes.ENDERMAN, 1, 2))
                .addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityTypes.BLAZE, 1, 1));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addAncientDebris(biomeBuilder);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false)
                .temperature(3.0F)
                .downfall(0.0F)
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, ARGB.color(105, 24, 26, 25))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.MUSIC_BIOME_CRIMSON_FOREST))
                .setAttribute(
                        EnvironmentAttributes.AMBIENT_SOUNDS,
                        new AmbientSounds(
                                Optional.of(SoundEvents.AMBIENT_CRIMSON_FOREST_LOOP),
                                Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0)),
                                List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111))
                        )
                )
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(108350).build())
                .build();
    }
}
