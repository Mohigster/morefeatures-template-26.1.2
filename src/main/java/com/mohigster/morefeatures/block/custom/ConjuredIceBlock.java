package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FrostedIceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public class ConjuredIceBlock extends FrostedIceBlock {

    public static BlockState meltsInto(Level level, BlockPos pos) {
        if (!neighboursWater(level, pos)) return Blocks.AIR.defaultBlockState();
        else return Blocks.WATER.defaultBlockState();
    }

    public static boolean neighboursWater(LevelAccessor level, BlockPos pos){
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            BlockPos neighborPos = pos.relative(direction);
            // Check if the fluid state at the neighboring position is water
            if (level.getFluidState(neighborPos).is(Fluids.WATER)) {
                return true;
            }
        }
        return false;
    }

    public ConjuredIceBlock(Properties properties) {
        super(properties);
        this.registerDefaultState((this.stateDefinition.any()).setValue(AGE, 0));
    }

    @NullMarked
    @Override
    @SuppressWarnings("deprecation")
    public void playerDestroy(Level level, Player player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack destroyedWith) {
        super.playerDestroy(level, player, pos, state, blockEntity, destroyedWith);
        if (!EnchantmentHelper.hasTag(destroyedWith, EnchantmentTags.PREVENTS_ICE_MELTING)) {
            if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
                level.removeBlock(pos, false);
                return;
            }

            BlockState belowState = level.getBlockState(pos.below());
            if (belowState.blocksMotion() || belowState.liquid()) {
                level.setBlockAndUpdate(pos, meltsInto(level, pos));
            }
        }
    }

    @NullMarked
    @Override
    protected void melt(BlockState state, Level level, BlockPos pos) {
        if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
            level.removeBlock(pos, false);
        } else {
            level.setBlockAndUpdate(pos, meltsInto(level, pos));
            level.neighborChanged(pos, meltsInto(level, pos).getBlock(), null);
        }
    }
}
