package com.mohigster.morefeatures.data.world.noise;

import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class MFNoiseData {
    public static void bootstrap(BootstrapContext<NormalNoise.NoiseParameters> context) {
        register(context, MFNoises.ICE_CAVE_GRADIENT, -5, 1.0, 0.0, 1.0);
    }

    @SuppressWarnings("SameParameterValue")
    private static void register(
            BootstrapContext<NormalNoise.NoiseParameters> context,
            ResourceKey<NormalNoise.NoiseParameters> key,
            int firstOctave,
            double firstAmplitude,
            double... amplitudes
    ) {
        context.register(key, new NormalNoise.NoiseParameters(firstOctave, firstAmplitude, amplitudes));
    }
}
