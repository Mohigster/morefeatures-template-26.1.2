package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFShelfBlockEntity extends ShelfBlockEntity {
    public MFShelfBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(worldPosition, blockState);
    }

    @NullMarked
    @Override
    public BlockEntityType<?> getType() {
        return MFBlockEntities.MF_SHELF_BE.get();
    }
}
