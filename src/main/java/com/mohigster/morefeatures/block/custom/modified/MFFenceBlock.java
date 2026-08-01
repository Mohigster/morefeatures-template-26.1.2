package com.mohigster.morefeatures.block.custom.modified;

import com.mohigster.morefeatures.tag.MFBlockTags;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

public class MFFenceBlock extends FenceBlock {
    public MFFenceBlock(Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    public boolean connectsTo(BlockState state, boolean faceSolid, Direction direction) {
        Block block = state.getBlock();
        boolean validFence = this.isValidFenceToConnect(state);
        boolean gate = block instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
        return !isExceptionForConnection(state) && faceSolid || validFence || gate;
    }

    private boolean isValidFenceToConnect(BlockState state) {
        if (state.is(MFBlockTags.GEMSTONE_FENCES)){
            return true;
        }

        if (state.is(BlockTags.FENCES) && !state.is(BlockTags.WOODEN_FENCES)){
            return this.defaultBlockState().is(MFBlockTags.GEMSTONE_FENCES);
        }

        return state.is(BlockTags.FENCES) && state.is(BlockTags.WOODEN_FENCES) == (this.defaultBlockState().is(BlockTags.WOODEN_FENCES) || this.defaultBlockState().is(MFBlockTags.GEMSTONE_FENCES));
    }
}
