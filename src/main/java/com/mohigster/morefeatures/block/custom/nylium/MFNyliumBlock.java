package com.mohigster.morefeatures.block.custom.nylium;

import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NyliumBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.lighting.LightEngine;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class MFNyliumBlock extends NyliumBlock {
    private final List<ResourceKey<ConfiguredFeature<?, ?>>> primaryFeatures;
    private final ResourceKey<ConfiguredFeature<?, ?>> rareFeature;
    private final int chance;

    public MFNyliumBlock(
            @NonNull List<ResourceKey<ConfiguredFeature<?, ?>>> features,
            @Nullable ResourceKey<ConfiguredFeature<?, ?>> rareFeature,
            int chanceBound,
            Properties properties
    ) {
        super(properties);
        this.primaryFeatures = features;
        this.rareFeature = rareFeature;
        this.chance = chanceBound;
    }

    public MFNyliumBlock(
            @NonNull ResourceKey<ConfiguredFeature<?, ?>> feature,
            @Nullable ResourceKey<ConfiguredFeature<?, ?>> rareFeature,
            int chanceBound,
            Properties properties
    ) {
        this(List.of(feature), rareFeature, chanceBound, properties);
    }

    public MFNyliumBlock(
            @NonNull List<ResourceKey<ConfiguredFeature<?, ?>>> features,
            Properties properties
    ) {
        this(features, null, 1, properties);
    }

    public MFNyliumBlock(
            @NonNull ResourceKey<ConfiguredFeature<?, ?>> feature,
            Properties properties
    ) {
        this(List.of(feature), properties);
    }

    @Override
    public void performBonemeal(
            @NonNull ServerLevel server,
            @NonNull RandomSource random,
            @NonNull BlockPos pos,
            @NonNull BlockState state
    ) {
        BlockPos abovePos = pos.above();
        ChunkGenerator generator = server.getChunkSource().getGenerator();
        this.placeFeature(server, generator, random, abovePos, state);
    }

    protected final void placeFeature(
            @NonNull ServerLevel server,
            ChunkGenerator generator,
            RandomSource random,
            BlockPos pos,
            BlockState state
    ) {
        Registry<ConfiguredFeature<?, ?>> configuredFeatures =
                server.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        if (this.canGrow(state)) {
            this.getPrimaryFeatures().forEach(feature ->
                    this.place(configuredFeatures, feature, server, generator, random, pos));
            if(this.rareFeatureShouldPlace(random)) {
                this.getRareFeature().ifPresent(feature ->
                        this.place(configuredFeatures, feature,
                                server, generator, random, pos));
            }
        }
    }

    protected boolean canGrow(BlockState state) {
        return state.is(BlockTags.NYLIUM) && !state.is(MFBlockTags.NULLIUM);
    }

    protected List<ResourceKey<ConfiguredFeature<?, ?>>> getPrimaryFeatures() {
        return this.primaryFeatures;
    }

    protected Optional<ResourceKey<ConfiguredFeature<?, ?>>> getRareFeature() {
        return Optional.ofNullable(this.rareFeature);
    }

    protected int getChance(){
        return Math.max(this.chance, 1);
    }

    protected boolean rareFeatureShouldPlace(RandomSource random) {
        return random.nextInt(this.getChance()) == 0;
    }

    @NullMarked
    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (!canBeNylium(state, level, pos)) {
            level.setBlockAndUpdate(pos, this.nyliumBaseBlock().defaultBlockState());
        }
    }

    protected Block nyliumBaseBlock() {
        return Blocks.NETHERRACK;
    }

    private static boolean canBeNylium(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos above = pos.above();
        BlockState aboveState = level.getBlockState(above);
        int lightDampeningTopFace = LightEngine.getLightDampeningInto(state, aboveState, Direction.UP, aboveState.getLightDampening());
        return lightDampeningTopFace < 15;
    }
}