package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFHangingSignBlockEntity extends HangingSignBlockEntity {
    public MFHangingSignBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(worldPosition, blockState);
    }

    @NullMarked
    @Override
    public BlockEntityType<?> getType() {
        return MFBlockEntities.MF_HANGING_SIGN_BE.get();
    }
}
