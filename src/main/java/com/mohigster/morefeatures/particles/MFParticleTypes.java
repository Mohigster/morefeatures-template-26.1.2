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

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOODWOOD_LEAVES = registerSimple("bloodwood_leaves");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TAINTED_LEAVES = registerSimple("tainted_leaves");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PALM_LEAVES = registerSimple("palm_leaves");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DECREPIT_LEAVES = registerSimple("decrepit_leaves");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> PALLID_LEAVES = registerSimple("pallid_leaves");
    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> CHARRED_SPORE = registerSimple("charred_spore");

    private static DeferredHolder<ParticleType<?>, SimpleParticleType> registerSimple(String name){
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

    public static void register(IEventBus bus) {
        PARTICLES.register(bus);
        MoreFeatures.LOGGER.info("Mod Particles registered -> Performed by: " + MoreFeatures.MODID);
    }
}
