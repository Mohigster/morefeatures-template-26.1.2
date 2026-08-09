package com.mohigster.morefeatures.data.world.biome.custom;

import com.mohigster.morefeatures.particles.MFParticleTypes;
import com.mohigster.morefeatures.data.world.feature.MFPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
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

import java.util.List;
import java.util.Optional;

public class MFNetherBiomes {
    public static Biome charredForest(HolderGetter<PlacedFeature> featureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter){
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder()
                .addSpawn(MobCategory.MONSTER, 1, new MobSpawnSettings.SpawnerData(EntityTypes.GHAST, 1, 1))
                .addSpawn(MobCategory.MONSTER, 3, new MobSpawnSettings.SpawnerData(EntityTypes.MAGMA_CUBE, 1, 2))
                .addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityTypes.ENDERMAN, 1, 2))
                .addSpawn(MobCategory.MONSTER, 4, new MobSpawnSettings.SpawnerData(EntityTypes.BLAZE, 1, 2))
                .addSpawn(MobCategory.MONSTER, 2, new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIFIED_PIGLIN, 1, 2))
                .addSpawn(MobCategory.CREATURE, 25, new MobSpawnSettings.SpawnerData(EntityTypes.STRIDER, 1, 2));

        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(featureGetter, carverGetter)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.PATCH_SOUL_FIRE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.SPRING_CLOSED)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.CHARRED_PLACED_KEY)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.CHARRED_FOREST_VEGETATION_PLACED_KEY)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.SMOLDERED_VINES_PLACED_KEY)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, MFPlacedFeatures.SCORCHED_VINES_PLACED_KEY);
        BiomeDefaultFeatures.addNetherDefaultOres(biomeBuilder);

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
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES,
                        AmbientParticle.of(MFParticleTypes.CHARRED_SPORE.get(), 0.02F))
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(108350).grassColorOverride(4671303).build())
                .build();
    }
}
