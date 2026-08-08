package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jspecify.annotations.NullMarked;

public class MFFlammableFenceGateBlock extends FenceGateBlock {
    private final boolean isFlammable;

    public MFFlammableFenceGateBlock(WoodSet set, Properties properties) {
        this(set.getWoodType(), set.isFlammable(), properties);
    }

    public MFFlammableFenceGateBlock(WoodType type, boolean isFlammable, Properties properties) {
        super(type, properties);
        this.isFlammable = isFlammable;
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (this.isFlammable || state.is(MFBlockTags.FLAMMABLE_WOOD));
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (this.isFlammable || state.is(MFBlockTags.FLAMMABLE_WOOD) ? 20 : super.getFlammability(state, level, pos, direction));
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (this.isFlammable || state.is(MFBlockTags.FLAMMABLE_WOOD) ? 5 : super.getFireSpreadSpeed(state, level, pos, direction));
    }
}
