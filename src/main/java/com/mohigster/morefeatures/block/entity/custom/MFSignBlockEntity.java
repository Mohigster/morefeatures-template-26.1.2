package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFSignBlockEntity extends SignBlockEntity {
    public MFSignBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MFBlockEntities.MF_SIGN_BE.get(), worldPosition, blockState);
    }

    @NullMarked
    @Override
    public BlockEntityType<?> getType() {
        return MFBlockEntities.MF_SIGN_BE.get();
    }
}
