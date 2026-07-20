package com.mohigster.morefeatures.block.custom.flammable;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.jspecify.annotations.NullMarked;

import javax.annotation.Nullable;

public class MFFlammableRotatedPillarBlock extends RotatedPillarBlock {

    public MFFlammableRotatedPillarBlock(Properties properties) {
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
        return 5;
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction){
        return 5;
    }

    @NullMarked
    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context,
                                                     ItemAbility itemAbility, boolean simulate){
        if (context.getItemInHand().getItem() instanceof AxeItem){
            if (state.is(MFBlocks.BLOODWOOD_LOG)){
                return MFBlocks.STRIPPED_BLOODWOOD_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.BLOODWOOD)){
                return MFBlocks.STRIPPED_BLOODWOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.TAINTED_LOG)){
                return MFBlocks.STRIPPED_TAINTED_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.TAINTED_WOOD)){
                return MFBlocks.STRIPPED_TAINTED_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.PALM_LOG)){
                return MFBlocks.STRIPPED_PALM_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.PALM_WOOD)){
                return MFBlocks.STRIPPED_PALM_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.DECREPIT_LOG)){
                return MFBlocks.STRIPPED_DECREPIT_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.DECREPIT_WOOD)){
                return MFBlocks.STRIPPED_DECREPIT_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.PALLID_LOG)){
                return MFBlocks.STRIPPED_PALLID_LOG.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
            if (state.is(MFBlocks.PALLID_WOOD)){
                return MFBlocks.STRIPPED_PALLID_WOOD.get().defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return super.getToolModifiedState(state, context, itemAbility, simulate);
    }
}
