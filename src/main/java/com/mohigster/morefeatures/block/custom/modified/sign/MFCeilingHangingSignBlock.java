package com.mohigster.morefeatures.block.custom.modified.sign;

import com.mohigster.morefeatures.block.collection.gemstone.GemstoneType;
import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.CeilingHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MFCeilingHangingSignBlock extends CeilingHangingSignBlock {
    public MFCeilingHangingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    public MFCeilingHangingSignBlock(GemstoneType gem, Properties properties) {
        super(gem.woodType(), properties);
    }

    public MFCeilingHangingSignBlock(WoodSet set, Properties properties) {
        this(set.woodType(), properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return MFBlockEntities.MF_HANGING_SIGN_BE.get().create(pos, state);
    }
}
