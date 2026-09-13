package com.mohigster.morefeatures.data.world.feature.custom.config;

import com.mohigster.morefeatures.util.MFExtraCodecs;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record OasisConfiguration(
        Holder<ConfiguredFeature<?, ?>> treeFeature,
        BlockState mainBaseBlock,
        BlockState exposedBaseBlock,
        HolderSet<Block> shouldBeReplacedWhenClearing,
        int treeAttempts,
        int treeRingMin,
        int treeRingMax,
        int requiredDistanceInBlocksBetweenTrees
) implements FeatureConfiguration {
    public static final Codec<OasisConfiguration> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    ConfiguredFeature.CODEC.fieldOf("tree_feature")
                            .forGetter(OasisConfiguration::treeFeature),
                    BlockState.CODEC.fieldOf("main_base_block")
                            .forGetter(OasisConfiguration::mainBaseBlock),
                    BlockState.CODEC.fieldOf("exposed_base_block")
                            .forGetter(OasisConfiguration::exposedBaseBlock),
                    MFExtraCodecs.BLOCK_SET.fieldOf("blocks_that_should_be_replaced_when_clearing")
                                    .forGetter(OasisConfiguration::shouldBeReplacedWhenClearing),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("tree_attempts")
                            .forGetter(OasisConfiguration::treeAttempts),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("tree_ring_min_distance")
                            .forGetter(OasisConfiguration::treeRingMin),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("tree_ring_max_distance")
                            .forGetter(OasisConfiguration::treeRingMax),
                    ExtraCodecs.NON_NEGATIVE_INT.fieldOf("required_distance_in_blocks_between_trees")
                            .forGetter(OasisConfiguration::requiredDistanceInBlocksBetweenTrees)
            ).apply(
                    inst,
                    OasisConfiguration::new
            )
    );
}
