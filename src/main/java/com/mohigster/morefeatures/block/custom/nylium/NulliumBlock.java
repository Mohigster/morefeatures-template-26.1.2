package com.mohigster.morefeatures.block.custom.nylium;

import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NulliumBlock extends MFNyliumBlock {
    public NulliumBlock(ResourceKey<ConfiguredFeature<?, ?>> feature, Properties properties) {
        super(feature, properties);
    }

    @SuppressWarnings("unused")
    public NulliumBlock(
            ResourceKey<ConfiguredFeature<?, ?>> feature,
            ResourceKey<ConfiguredFeature<?, ?>> rareFeature,
            int chance,
            Properties properties
    ) {
        super(feature, rareFeature, chance, properties);
    }

    @Override
    protected boolean canGrow(BlockState state) {
        return state.is(MFBlockTags.NULLIUM);
    }
}
