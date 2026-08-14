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
import org.jspecify.annotations.NonNull;
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
            int chance,
            Properties properties
    ) {
        super(properties);
        this.primaryFeatures = features;
        this.rareFeature = rareFeature;
        this.chance = chance;
    }

    public MFNyliumBlock(
            @NonNull ResourceKey<ConfiguredFeature<?, ?>> feature,
            @Nullable ResourceKey<ConfiguredFeature<?, ?>> rareFeature,
            int chance,
            Properties properties
    ) {
        this(List.of(feature), rareFeature, chance, properties);
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
            ServerLevel level,
            @NonNull RandomSource random,
            @NonNull BlockPos pos,
            @NonNull BlockState state
    ) {
        BlockState blockState = level.getBlockState(pos);
        BlockPos abovePos = pos.above();
        ChunkGenerator generator = level.getChunkSource().getGenerator();
        this.placeFeature(level, generator, random, abovePos, blockState);
    }

    protected void placeFeature(
            ServerLevel level,
            ChunkGenerator generator,
            RandomSource random,
            BlockPos pos,
            BlockState state
    ) {
        Registry<ConfiguredFeature<?, ?>> configuredFeatures =
                level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
        if (canGrow(state)) {
            this.getPrimaryFeatures().forEach(feature ->
                    this.place(configuredFeatures, feature, level, generator, random, pos));
            if(random.nextInt(this.getChance()) == 0) {
                this.getRareFeature().ifPresent(configuredFeature ->
                        this.place(configuredFeatures, configuredFeature,
                                level, generator, random, pos));
            }
        }
    }

    protected boolean canGrow(BlockState state) {
        return state.is(BlockTags.NYLIUM);
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
}