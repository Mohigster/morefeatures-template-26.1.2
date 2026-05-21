package com.mohigster.morefeatures.worldgen.dimension;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.worldgen.biome.ModBiomes;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TimelineTags;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.clock.WorldClocks;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

import java.util.List;
import java.util.Optional;

public class ModDimensions {
    public static final ResourceKey<LevelStem> EVILDIM_KEY = ResourceKey.create(Registries.LEVEL_STEM,
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "evildim"));
    public static final ResourceKey<Level> EVILDIM_LEVEL_KEY = ResourceKey.create(Registries.DIMENSION,
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "evildim"));
    public static final ResourceKey<DimensionType> EVIL_DIM_TYPE_KEY = ResourceKey.create(Registries.DIMENSION_TYPE,
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "evildim_type"));


    public static void bootstrapType(BootstrapContext<DimensionType> context) {
        var timelines = context.lookup(Registries.TIMELINE);
        var clocks = context.lookup(Registries.WORLD_CLOCK);

        context.register(EVIL_DIM_TYPE_KEY, new DimensionType(
                false,
                true,
                false,
                false,
                0.5,
                0,
                256,
                256,
                BlockTags.INFINIBURN_OVERWORLD,
                1.0f,
                new DimensionType.MonsterSettings(ConstantInt.of(0), 0),
                DimensionType.Skybox.OVERWORLD,
                CardinalLighting.Type.DEFAULT,
                EnvironmentAttributeMap.builder()
                        .set(EnvironmentAttributes.FOG_COLOR, ARGB.color(255, 225, 20, 10))
                        .set(EnvironmentAttributes.SKY_COLOR, ARGB.color(255, 225, 20, 10))
                        .set(EnvironmentAttributes.AMBIENT_LIGHT_COLOR, -4212331)
                        .set(EnvironmentAttributes.CLOUD_COLOR, ARGB.color(155, 200, 31, 25))
                        .build(),
                timelines.getOrThrow(TimelineTags.IN_OVERWORLD),
                Optional.of(clocks.getOrThrow(WorldClocks.OVERWORLD))));
    }


    public static void bootstrapStem(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

//        var bloodwoodKey = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "bloodwood_forest"));
//        var ebonKey = ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "ebon_forest"));

        NoiseBasedChunkGenerator multiBiomeGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.5f, 0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(ModBiomes.BLOODWOOD_FOREST)),
                                Pair.of(Climate.parameters(-0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(ModBiomes.TAINTED_FOREST))
                        ))),
                noiseGenSettings.getOrThrow(NoiseGeneratorSettings.FLOATING_ISLANDS));

        context.register(EVILDIM_KEY, new LevelStem(dimensionTypes.getOrThrow(ModDimensions.EVIL_DIM_TYPE_KEY), multiBiomeGenerator));
    }
}
