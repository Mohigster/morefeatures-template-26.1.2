package com.mohigster.morefeatures.block.custom.modified.sign;

import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jspecify.annotations.NullMarked;

public class MFWallHangingSignBlock extends WallHangingSignBlock {
    public MFWallHangingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    public MFWallHangingSignBlock(WoodSet set, Properties properties) {
        super(set.getWoodType(), properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return MFBlockEntities.MF_HANGING_SIGN_BE.get().create(pos, state);
    }
}
