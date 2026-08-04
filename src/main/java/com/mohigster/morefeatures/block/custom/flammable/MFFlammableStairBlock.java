package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.WoodSetType;
import com.mohigster.morefeatures.tag.MFBlockTags;
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

    public MFFlammableStairBlock(BlockState baseState, WoodSetType woodType, Properties properties) {
        this(baseState, woodType.isFlammable(), properties);
    }

    public MFFlammableStairBlock(WoodSetType woodType, Properties properties) {
        this(MFBlocks.PLANKS.pick(woodType).get().defaultBlockState(), woodType, properties);
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
