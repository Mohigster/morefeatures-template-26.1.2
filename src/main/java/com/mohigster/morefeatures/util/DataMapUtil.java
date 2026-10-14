package com.mohigster.morefeatures.util;

import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.data.codec.BonemealMorph;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.ParticleUtils;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;

public final class DataMapUtil {
    public static void spawnBonemealParticles(LevelAccessor level, BlockPos pos, int count) {
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();

        // Fallback to vanilla handling if applicable (otherwise particle count will be doubled)
        if (block instanceof BonemealableBlock) return;

        BonemealMorph data = block.defaultBlockState().typeHolder().getData(MFDataMaps.BONEMEAL_MORPHS);

        if (data == null) return;

        if (!data.variants().isEmpty()) {
            BlockPos particlePos = data.particlePos(pos);
            switch (data.bonemealType()) {
                // These mimic vanilla's bonemeal types enum, which can either be neighbour spreader or grower

                // We allow both spellings of "neighbour" here.
                case "neighbour_spreader", "neighbor_spreader" -> ParticleUtils.spawnParticles(level, particlePos, count * 3, 3.0F, 1.0F, false, ParticleTypes.HAPPY_VILLAGER);
                case "grower" -> ParticleUtils.spawnParticleInBlock(level, particlePos, count, ParticleTypes.HAPPY_VILLAGER);
                default -> throw new IllegalStateException("Invalid bonemeal type");
            }
        } else if (state.is(Blocks.WATER)) {
            ParticleUtils.spawnParticles(level, pos, count * 3, 3.0F, 1.0F, false, ParticleTypes.HAPPY_VILLAGER);
        }
    }

    public static <T extends Number> T requirePositive(T data) {
        if (data.intValue() <= 0)
            throw new IndexOutOfBoundsException(data + " must be a positive value");
        return data;
    }
}
