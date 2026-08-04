package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.collection.WoodSetType;
import com.mohigster.morefeatures.block.custom.modified.MFFenceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFFlammableFenceBlock extends MFFenceBlock {
    public MFFlammableFenceBlock(WoodSetType wood, Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return true;
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return 20;
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return 5;
    }
}
