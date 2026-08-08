package com.mohigster.morefeatures.data.world.noise;

import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class MFNoises {
    private MFNoises(){}

    public static final ResourceKey<NormalNoise.NoiseParameters> ICE_CAVE_GRADIENT = createKey("ice_cave_gradient");

    @SuppressWarnings("SameParameterValue")
    private static ResourceKey<NormalNoise.NoiseParameters> createKey(String name){
        return ResourceKey.create(Registries.NOISE, MFIdentifier.withMfNamespace(name));
    }
}
