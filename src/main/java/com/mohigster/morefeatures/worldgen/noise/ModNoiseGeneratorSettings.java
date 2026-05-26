package com.mohigster.morefeatures.worldgen.noise;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class ModNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLANDS_MULTI = ResourceKey.create(
            Registries.NOISE_SETTINGS,
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "floating_islands_multi")
    );
}
