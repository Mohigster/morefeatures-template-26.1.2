package com.mohigster.morefeatures.worldgen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.util.valueproviders.FloatProviders;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.IntProviders;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

import java.util.List;

public record MultiBaseSpeleothemClusterConfiguration(List<BlockState> baseBlocks, BlockState pointedBlock, HolderSet<Block> replaceableBlocks, int floorToCeilingSearchRange, IntProvider height, IntProvider radius, int maxStalagmiteStalactiteHeightDiff, int heightDeviation, IntProvider speleothemBlockLayerThickness, FloatProvider density, FloatProvider wetness, float chanceOfSpeleothemAtMaxDistanceFromCenter, int maxDistanceFromEdgeAffectingChanceOfSpeleothem, int maxDistanceFromCenterAffectingHeightBias) implements FeatureConfiguration {

    // 2. Updated CODEC to match the new type
    public static final MapCodec<MultiBaseSpeleothemClusterConfiguration> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            // A list of one or more BlockStates.  At least one entry is required.
            BlockState.CODEC.listOf(1, 64).fieldOf("base_blocks").forGetter(MultiBaseSpeleothemClusterConfiguration::baseBlocks),
            BlockState.CODEC.fieldOf("pointed_block").forGetter(MultiBaseSpeleothemClusterConfiguration::pointedBlock),
            RegistryCodecs.homogeneousList(Registries.BLOCK).fieldOf("replaceable_blocks").forGetter(MultiBaseSpeleothemClusterConfiguration::replaceableBlocks),
            Codec.intRange(1, 512).fieldOf("floor_to_ceiling_search_range").forGetter(MultiBaseSpeleothemClusterConfiguration::floorToCeilingSearchRange),
            IntProviders.codec(1, 128).fieldOf("height").forGetter(MultiBaseSpeleothemClusterConfiguration::height),
            IntProviders.codec(1, 128).fieldOf("radius").forGetter(MultiBaseSpeleothemClusterConfiguration::radius),
            Codec.intRange(0, 64).fieldOf("max_stalagmite_stalactite_height_diff").forGetter(MultiBaseSpeleothemClusterConfiguration::maxStalagmiteStalactiteHeightDiff),
            Codec.intRange(1, 64).fieldOf("height_deviation").forGetter(MultiBaseSpeleothemClusterConfiguration::heightDeviation),
            IntProviders.codec(0, 128).fieldOf("speleothem_block_layer_thickness").forGetter(MultiBaseSpeleothemClusterConfiguration::speleothemBlockLayerThickness),
            FloatProviders.codec(0.0F, 2.0F).fieldOf("density").forGetter(MultiBaseSpeleothemClusterConfiguration::density),
            FloatProviders.codec(0.0F, 2.0F).fieldOf("wetness").forGetter(MultiBaseSpeleothemClusterConfiguration::wetness),
            Codec.floatRange(0.0F, 1.0F)
                    .fieldOf("chance_of_speleothem_at_max_distance_from_center")
                    .forGetter(MultiBaseSpeleothemClusterConfiguration::chanceOfSpeleothemAtMaxDistanceFromCenter),
            Codec.intRange(1, 64)
                    .fieldOf("max_distance_from_edge_affecting_chance_of_speleothem")
                    .forGetter(MultiBaseSpeleothemClusterConfiguration::maxDistanceFromEdgeAffectingChanceOfSpeleothem),
            Codec.intRange(1, 64)
                    .fieldOf("max_distance_from_center_affecting_height_bias")
                    .forGetter(MultiBaseSpeleothemClusterConfiguration::maxDistanceFromCenterAffectingHeightBias)
    ).apply(i, MultiBaseSpeleothemClusterConfiguration::new));

    /**
     * Pick a random base block from the list for each placement decision.
     * Every entry has equal weight; if you want weighted selection, swap this
     * out for a WeightedRandomList in the codec above.
     */
    public BlockState randomBaseBlock(net.minecraft.util.RandomSource random) {
        return baseBlocks.get(random.nextInt(baseBlocks.size()));
    }
}
