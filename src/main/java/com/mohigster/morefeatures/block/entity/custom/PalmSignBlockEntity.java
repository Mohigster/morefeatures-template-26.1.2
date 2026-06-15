package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PalmSignBlockEntity extends SignBlockEntity{
    public PalmSignBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(ModBlockEntities.PALM_SIGN_BE.get(), worldPosition, blockState);
    }

    @Override
    public BlockEntityType<?> getType() {
        return ModBlockEntities.PALM_SIGN_BE.get();
    }
}
