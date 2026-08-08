package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFFlammableRotatedPillarBlock extends RotatedPillarBlock {
    private final boolean isFlammable;

    public MFFlammableRotatedPillarBlock(boolean isFlammable, Properties properties) {
        super(properties);
        this.isFlammable = isFlammable;
    }

    public MFFlammableRotatedPillarBlock(WoodSet set, Properties properties) {
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
        return (state.is(MFBlockTags.FLAMMABLE_WOOD) || this.isFlammable) ? 5 : super.getFlammability(state, level, pos, direction);
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return (state.is(MFBlockTags.FLAMMABLE_WOOD) || this.isFlammable) ? 5 : super.getFireSpreadSpeed(state, level, pos, direction);
    }
}
