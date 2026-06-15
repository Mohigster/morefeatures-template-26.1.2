package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class DecrepitHangingSignBlockEntity extends HangingSignBlockEntity {
    public DecrepitHangingSignBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(worldPosition, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.DECREPIT_HANGING_SIGN_BE.get();
    }
}
