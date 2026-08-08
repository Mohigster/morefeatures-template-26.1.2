package com.mohigster.morefeatures.data.world.noise;

import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class MFNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLANDS_MULTI = ResourceKey.create(
            Registries.NOISE_SETTINGS,
            MFIdentifier.withMfNamespace("floating_islands_multi")
    );
}
