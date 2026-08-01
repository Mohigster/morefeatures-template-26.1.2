package com.mohigster.morefeatures.block.custom.nylium;

import com.mohigster.morefeatures.tag.MFBlockTags;
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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.function.Supplier;

public class NulliumBlock extends Block implements BonemealableBlock {
    // Using a supplier delays the call and prevents the holder from being empty at call.

    // It also is just very useful because DeferredBlock<?> Is a supplier, so I don't have to
    // use a supplier reference and can just reference the DeferredBlock directly.
    private final Supplier<Block> rootBlock;

    public NulliumBlock(Properties properties, Supplier<Block> rootBlock) {
        super(properties);
        this.rootBlock = rootBlock;
    }

    @NullMarked
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
            int lightBlocking = LightEngine.getLightDampeningInto(state, blockState, Direction.UP, blockState.getLightDampening());
            return lightBlocking < 15;
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, @NonNull BlockState state) {
        return level.getBlockState(pos.above()).isAir();
    }

    @NullMarked
    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @NullMarked
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos blockPos, BlockState blockState) {

        // Place a root directly above the bonemealed block
        BlockPos abovePos = blockPos.above();
        if (level.getBlockState(abovePos).isAir()) {
            this.growRootAbove(level, abovePos);
        }

        // Spread roots to surrounding Nullium blocks in a 5x3x5 area
        for (int i = 0; i < 40; ++i) {
            BlockPos spreadPos = blockPos.offset(
                    random.nextInt(5) - 2,
                    random.nextInt(3) - 1,
                    random.nextInt(5) - 2
            );

            BlockState spreadState = level.getBlockState(spreadPos);
            BlockPos aboveSpread = spreadPos.above();

            // Only place roots on top of Nullium blocks that have air above them
            if ((spreadState.is(MFBlockTags.NULLIUM))
                    && level.getBlockState(aboveSpread).isAir()) {
                this.growRootAbove(level, aboveSpread);
            }
        }
    }

    private void growRootAbove(ServerLevel serverLevel, BlockPos pos) {
        serverLevel.setBlockAndUpdate(pos, this.getRootBlock().defaultBlockState());
    }

    /**
     * @return the root block as a block, rather than a supplier. This method is deliberately
     * declared as protected in order to allow subclasses to override this method and add custom
     * behaviour. While such a thing isn't planned for this mod, it is good practice to future-proof
     */
    protected Block getRootBlock(){
        return this.rootBlock.get();
    }
}
