package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.block.MFBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jspecify.annotations.NullMarked;

@SuppressWarnings("unused")
public class MFNyliumBlock extends NyliumBlock {
    public MFNyliumBlock(Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState blockState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        ChunkGenerator generator = level.getChunkSource().getGenerator();
        Registry<ConfiguredFeature<?, ?>> configuredFeatures = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        if (blockState.is(MFBlocks.CHARRED_NYLIUM.get())) {
            this.place(configuredFeatures, NetherFeatures.CRIMSON_FOREST_VEGETATION_BONEMEAL, level, generator, random, abovePos);
        }
    }

    @SuppressWarnings("SameParameterValue")
    private void place(Registry<ConfiguredFeature<?, ?>> configuredFeatures, ResourceKey<ConfiguredFeature<?, ?>> id, ServerLevel level, ChunkGenerator generator, RandomSource random, BlockPos pos) {
        if (level.isInsideBuildHeight(pos)) {
            configuredFeatures.get(id).ifPresent((h) -> (h.value()).place(level, generator, random, pos));
        }
    }
}
