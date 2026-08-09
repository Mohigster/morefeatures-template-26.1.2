package com.mohigster.morefeatures.data.world.feature.custom.config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record OasisConfiguration(
        Holder<ConfiguredFeature<?, ?>> treeFeature,
        BlockState mainBaseBlock,
        BlockState exposedBaseBlock) implements FeatureConfiguration {
    public static final MapCodec<OasisConfiguration> CODEC = RecordCodecBuilder.mapCodec(
            inst -> inst.group(
                    ConfiguredFeature.CODEC.fieldOf("tree_feature")
                            .forGetter(OasisConfiguration::treeFeature),
                    BlockState.CODEC.fieldOf("main_base_block")
                            .forGetter(OasisConfiguration::mainBaseBlock),
                    BlockState.CODEC.fieldOf("exposed_base_block")
                            .forGetter(OasisConfiguration::exposedBaseBlock)
            ).apply(
                    inst,
                    OasisConfiguration::new
            )
    );
}
