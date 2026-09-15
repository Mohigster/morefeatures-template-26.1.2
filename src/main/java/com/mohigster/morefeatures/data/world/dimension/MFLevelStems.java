package com.mohigster.morefeatures.data.world.dimension;

import com.mohigster.morefeatures.data.resources.references.dimension.MFDimensionTypeIds;
import com.mohigster.morefeatures.data.resources.references.dimension.MFLevelStemIds;
import com.mohigster.morefeatures.data.world.biome.MFBiomes;
import com.mohigster.morefeatures.data.world.noise.MFNoiseGeneratorSettings;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;

import java.util.List;

public class MFLevelStems {
    public static void bootstrap(BootstrapContext<LevelStem> context) {
        var biomes = context.lookup(Registries.BIOME);
        var dimensionTypes = context.lookup(Registries.DIMENSION_TYPE);
        var noiseGenSettings = context.lookup(Registries.NOISE_SETTINGS);

        NoiseBasedChunkGenerator multiBiomeGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(MFBiomes.BLOODWOOD_FOREST)),
                                Pair.of(Climate.parameters(-0.1f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f), biomes.getOrThrow(MFBiomes.TAINTED_FOREST))
                        ))
                ),
                noiseGenSettings.getOrThrow(MFNoiseGeneratorSettings.FLOATING_ISLANDS_MULTI)
        );

        NoiseBasedChunkGenerator borealisGenerator = new NoiseBasedChunkGenerator(
                MultiNoiseBiomeSource.createFromList(
                        new Climate.ParameterList<>(List.of(
                                Pair.of(Climate.parameters(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F), biomes.getOrThrow(MFBiomes.ICE_CAVES))
                        ))
                ),
                noiseGenSettings.getOrThrow(MFNoiseGeneratorSettings.BOREALIS)
        );

        context.register(MFLevelStemIds.EVILDIM, new LevelStem(
                dimensionTypes.getOrThrow(MFDimensionTypeIds.EVILDIM), multiBiomeGenerator));

        context.register(MFLevelStemIds.BOREALIS, new LevelStem(
                dimensionTypes.getOrThrow(MFDimensionTypeIds.BOREALIS), borealisGenerator));
    }
}
