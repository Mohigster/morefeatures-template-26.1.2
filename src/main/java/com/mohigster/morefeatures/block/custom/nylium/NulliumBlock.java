package com.mohigster.morefeatures.block.custom.nylium;

import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.List;

@SuppressWarnings("unused")
public class NulliumBlock extends MFNyliumBlock {

    public NulliumBlock(
            List<ResourceKey<ConfiguredFeature<?, ?>>> features,
            ResourceKey<ConfiguredFeature<?, ?>> rareFeature,
            int chance,
            Properties properties
    ) {
        super(features, rareFeature, chance, properties);
    }

    public NulliumBlock(
            ResourceKey<ConfiguredFeature<?, ?>> feature,
            ResourceKey<ConfiguredFeature<?, ?>> rareFeature,
            int chance,
            Properties properties
    ) {
        super(feature, rareFeature, chance, properties);
    }

    public NulliumBlock(
            List<ResourceKey<ConfiguredFeature<?, ?>>> feature,
            Properties properties
    ) {
        this(feature, null, 1, properties);
    }

    public NulliumBlock(ResourceKey<ConfiguredFeature<?, ?>> feature, Properties properties) {
        this(List.of(feature), properties);
    }

    @Override
    protected boolean canGrow(BlockState state) {
        return state.is(MFBlockTags.NULLIUM);
    }
}
