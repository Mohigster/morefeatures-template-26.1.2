package com.mohigster.morefeatures.particles;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MFParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MoreFeatures.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOODWOOD_LEAVES =
            PARTICLES.register(
                    "bloodwood_leaves",
                    () -> new SimpleParticleType(false)
            );

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TAINTED_LEAVES =
            PARTICLES.register(
                    "tainted_leaves",
                    () -> new SimpleParticleType(false)
            );

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PALM_LEAVES =
            PARTICLES.register(
                    "palm_leaves",
                    () -> new SimpleParticleType(false)
            );

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DECREPIT_LEAVES =
            PARTICLES.register(
                    "decrepit_leaves",
                    () -> new SimpleParticleType(false)
            );

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PALLID_LEAVES =
            PARTICLES.register(
                    "pallid_leaves",
                    () -> new SimpleParticleType(false)
            );

    public static void register(IEventBus bus) {
        PARTICLES.register(bus);
        MoreFeatures.LOGGER.info("Mod Particles registered -> Performed by: " + MoreFeatures.MODID);
    }
}
