package com.mohigster.morefeatures.block.custom.nylium;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jspecify.annotations.NullMarked;

public class MFNyliumBlock extends NyliumBlock {
    private final ResourceKey<ConfiguredFeature<?, ?>> feature;

    public MFNyliumBlock(Properties properties, ResourceKey<ConfiguredFeature<?, ?>> feature) {
        super(properties);
        this.feature = feature;
    }

    @NullMarked
    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        BlockState blockState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        ChunkGenerator generator = level.getChunkSource().getGenerator();
        this.placeFeature(level, generator, random, abovePos, blockState);
    }

    protected void placeFeature(ServerLevel level, ChunkGenerator generator, RandomSource random, BlockPos pos, BlockState state) {
        Registry<ConfiguredFeature<?, ?>> configuredFeatures =
                level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        if (canGrow(state)) {
            this.place(configuredFeatures, this.getFeature(), level, generator, random, pos);
        }
    }

    protected boolean canGrow(BlockState state) {
        return state.is(BlockTags.NYLIUM);
    }

    protected ResourceKey<ConfiguredFeature<?, ?>> getFeature() {
        return this.feature;
    }
}