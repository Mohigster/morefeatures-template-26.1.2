package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFFlammableBlock extends Block {
    private final boolean isFlammable;

    public MFFlammableBlock(boolean isFlammable, Properties properties) {
        super(properties);
        this.isFlammable = isFlammable;
    }

    public MFFlammableBlock(WoodSet set, Properties properties) {
        this(set.isFlammable(), properties);
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (state.is(MFBlockTags.FLAMMABLE_WOOD) || this.isFlammable);
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (state.is(MFBlockTags.FLAMMABLE_WOOD) || this.isFlammable) ? 20 : super.getFlammability(state, level, pos, direction);
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (state.is(MFBlockTags.FLAMMABLE_WOOD) || this.isFlammable) ? 5 :  super.getFireSpreadSpeed(state, level, pos, direction);
    }
}
