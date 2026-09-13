package com.mohigster.morefeatures.block.custom.modified.sign;

import com.mohigster.morefeatures.block.collection.gemstone.GemstoneType;
import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MFStandingSignBlock extends StandingSignBlock {
    public MFStandingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    public MFStandingSignBlock(GemstoneType gem, Properties properties) {
        this(gem.woodType(), properties);
    }

    public MFStandingSignBlock(WoodSet set, Properties properties) {
        this(set.woodType(), properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return MFBlockEntities.MF_SIGN_BE.get().create(pos, state);
    }
}
