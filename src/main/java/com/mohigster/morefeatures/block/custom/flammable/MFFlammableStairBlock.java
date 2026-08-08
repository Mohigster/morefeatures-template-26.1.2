package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFFlammableStairBlock extends StairBlock {
    private final boolean isFlammable;

    public MFFlammableStairBlock(BlockState baseState, boolean isFlammable, Properties properties) {
        super(baseState, properties);
        this.isFlammable = isFlammable;
    }

    public MFFlammableStairBlock(BlockState baseState, WoodSet set, Properties properties) {
        this(baseState, set.isFlammable(), properties);
    }

    public MFFlammableStairBlock(WoodSet set, Properties properties) {
        this(MFBlocks.PLANKS.pick(set).get().defaultBlockState(), set, properties);
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return this.isFlammable || state.is(MFBlockTags.FLAMMABLE_WOOD);
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return (this.isFlammable || state.is(MFBlockTags.FLAMMABLE_WOOD)) ? 20 : super.getFlammability(state, level, pos, direction);
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return (this.isFlammable || state.is(MFBlockTags.FLAMMABLE_WOOD)) ? 5 : super.getFireSpreadSpeed(state, level, pos, direction);
    }
}
