package com.mohigster.morefeatures.data.world.noise;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;

public class MFNoiseGeneratorSettings {
    public static final ResourceKey<NoiseGeneratorSettings> FLOATING_ISLANDS_MULTI = create("floating_islands_multi");
    public static final ResourceKey<NoiseGeneratorSettings> BOREALIS = create("borealis");

    private static ResourceKey<NoiseGeneratorSettings> create(String id) {
        return ResourceKey.create(Registries.NOISE_SETTINGS, MFIdentifier.withMfNamespace(id));
    }
}
