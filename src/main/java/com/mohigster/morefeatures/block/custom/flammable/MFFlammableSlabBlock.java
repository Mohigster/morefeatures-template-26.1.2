package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.collection.WoodSetType;
import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFFlammableSlabBlock extends SlabBlock {
    private final WoodSetType woodType;

    public MFFlammableSlabBlock(WoodSetType type, Properties properties) {
        super(properties);
        this.woodType = type;
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return (this.woodType.isFlammable() || state.is(MFBlockTags.FLAMMABLE_WOOD));
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return (this.woodType.isFlammable() || state.is(MFBlockTags.FLAMMABLE_WOOD)) ? 20 : super.getFlammability(state, level, pos, direction);
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return (this.woodType.isFlammable() || state.is(MFBlockTags.FLAMMABLE_WOOD)) ? 5 : super.getFireSpreadSpeed(state, level, pos, direction);
    }
}
