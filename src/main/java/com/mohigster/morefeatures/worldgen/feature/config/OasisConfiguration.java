package com.mohigster.morefeatures.worldgen.feature.config;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public record OasisConfiguration(Holder<ConfiguredFeature<?, ?>> palmFeature) implements FeatureConfiguration {
    public static final MapCodec<OasisConfiguration> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            ConfiguredFeature.CODEC.fieldOf("palm_feature").forGetter(OasisConfiguration::palmFeature)
    ).apply(i, OasisConfiguration::new));
}
