package com.mohigster.morefeatures.block.custom.nylium;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public class MFNyliumBlock extends NyliumBlock {
    // For the same reasons as NulliumBlock, rootBlock is a Supplier, not a block directly
    private final Supplier<Block> rootBlock;

    public MFNyliumBlock(Properties properties, Supplier<Block> rootBlock) {
        super(properties);
        this.rootBlock = rootBlock;
    }

    @NullMarked
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState blockState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        if (level.getBlockState(abovePos).isAir()) {
            this.growRootAbove(level, abovePos);
        }

        for (int i = 0; i < 40; ++i) {
            BlockPos spreadPos = pos.offset(
                    random.nextInt(5) - 2,
                    random.nextInt(3) - 1,
                    random.nextInt(5) - 2
            );

            BlockState spreadState = level.getBlockState(spreadPos);
            BlockPos aboveSpread = spreadPos.above();

            // Only place roots on top of Nylium
            if ((spreadState.is(BlockTags.NYLIUM))
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
