package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.lighting.LightEngine;

public class NulliumBlock extends Block implements BonemealableBlock {
    public NulliumBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
        if (!canBeNullium(state, level, pos)) {
            // Replace this block with End Stone (or your custom Ens Stone)
            level.setBlockAndUpdate(pos, Blocks.END_STONE.defaultBlockState());
        }
    }

    private static boolean canBeNullium(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos blockPos = pos.above();
        BlockState blockState = level.getBlockState(blockPos);

        // If it's covered by liquid (like water) and it blocks light, it dies
        if (blockState.getFluidState().getAmount() == 8) {
            return false;
        } else {
            // Gets the light blocking value of the block above.
            // If it blocks too much light (like dirt, stone, etc.), the nullium dies.
            int lightBlocking = LightEngine.getLightBlockInto(state, blockState, Direction.UP, blockState.getLightDampening());
            return lightBlocking < 15;
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader levelReader, BlockPos blockPos, BlockState blockState) {
        return levelReader.getBlockState(blockPos.above()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel serverLevel, RandomSource randomSource, BlockPos blockPos, BlockState blockState) {

        BlockPos targetPos = blockPos.above();

        // Loop through a small 3x3x3 area around the clicked block to find End Stone
        for (int i = 0; i < 40; ++i) {
            BlockPos spreadPos = blockPos.offset(
                    randomSource.nextInt(3) - 1,
                    randomSource.nextInt(3) - 1,
                    randomSource.nextInt(3) - 1
            );

            // Check if the target block is standard End Stone and has air above it
            if (serverLevel.getBlockState(spreadPos).is(Blocks.END_STONE) && serverLevel.getBlockState(spreadPos.above()).isAir()) {
                // Replace End Stone with your custom Nylium block
                serverLevel.setBlockAndUpdate(spreadPos, this.defaultBlockState());
            }
        }
    }
}
