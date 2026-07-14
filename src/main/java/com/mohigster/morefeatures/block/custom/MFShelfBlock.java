package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ShelfBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SideChainPart;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.redstone.Orientation;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class MFShelfBlock extends ShelfBlock {
    private final boolean isFlammable;

    public MFShelfBlock(boolean isFlammable, Properties properties) {
        super(properties);
        this.isFlammable = isFlammable;
    }

    // The only difference between this and the super method is that it always plays the vanilla shelf sounds
    // Now, it will play gemstone shelf sounds if the shelf is a gemstone shelf
    @NullMarked
    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, @Nullable Orientation orientation, boolean movedByPiston) {
        if (!level.isClientSide()) {
            boolean signal = level.hasNeighborSignal(pos);
            if (state.getValue(POWERED) != signal) {
                BlockState newState = state.setValue(POWERED, signal);
                if (!signal) {
                    newState = newState.setValue(SIDE_CHAIN_PART, SideChainPart.UNCONNECTED);
                }

                level.setBlock(pos, newState, 3);
                this.playSound(level, pos, getRedstoneSound(newState));
                level.gameEvent(signal ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos, GameEvent.Context.of(newState));
            }
        }
    }

    // Identical to the private playSound method within ShelfBlock
    private void playSound(LevelAccessor level, BlockPos pos, SoundEvent sound) {
        level.playSound(null, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
    }

    // New method that checks which sound to use when the shelf is powered by redstone
    private SoundEvent getRedstoneSound(BlockState state){
        if (state.is(MFBlockTags.GEMSTONE_SHELVES)){
            return SoundEvents.AMETHYST_BLOCK_CHIME;
        }

        return state.getValue(POWERED) ? SoundEvents.SHELF_ACTIVATE : SoundEvents.SHELF_DEACTIVATE;
    }

    @Override
    public boolean isConnectable(BlockState state) {
        return (state.is(BlockTags.WOODEN_SHELVES) || state.is(MFBlockTags.GEMSTONE_SHELVES)) && state.hasProperty(POWERED) && state.getValue(POWERED);
    }

    @NullMarked
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return MFBlockEntities.MF_SHELF_BE.get().create(pos, state);
    }

    @NullMarked
    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return isFlammable;
    }

    @NullMarked
    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return isFlammable ? 20 : 0;
    }

    @NullMarked
    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return isFlammable ? 30 : 0;
    }
}
