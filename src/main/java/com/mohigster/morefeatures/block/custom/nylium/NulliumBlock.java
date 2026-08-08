package com.mohigster.morefeatures.block.custom.nylium;

import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class NulliumBlock extends MFNyliumBlock {
    public NulliumBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> feature) {
        super(properties, feature);
    }

    @Override
    protected boolean canGrow(BlockState state) {
        return state.is(MFBlockTags.NULLIUM);
    }
}
