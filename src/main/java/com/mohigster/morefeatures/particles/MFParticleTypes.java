package com.mohigster.morefeatures.particles;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.RestrictedTo;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public final class MFParticleTypes {

    public static final DeferredRegister<ParticleType<?>> PARTICLES =
            DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MoreFeatures.MODID);

    public static final Supplier<SimpleParticleType> BLOODWOOD_LEAVES = registerSimple("bloodwood_leaves");
    public static final Supplier<SimpleParticleType> TAINTED_LEAVES = registerSimple("tainted_leaves");
    public static final Supplier<SimpleParticleType> PALM_LEAVES = registerSimple("palm_leaves");
    public static final Supplier<SimpleParticleType> DECREPIT_LEAVES = registerSimple("decrepit_leaves");
    public static final Supplier<SimpleParticleType> PALLID_LEAVES = registerSimple("pallid_leaves");

    public static final Supplier<SimpleParticleType> CHARRED_SPORE = registerSimple("charred_spore");
    public static final Supplier<SimpleParticleType> DECREPIT_SPORE = registerSimple("decrepit_spore");
    public static final Supplier<SimpleParticleType> PALLID_SPORE = registerSimple("pallid_spore");

    private static Supplier<SimpleParticleType> registerSimple(String name){
        return PARTICLES.register(name, () -> new SimpleParticleType(false));
    }

    @RestrictedTo(MoreFeatures.class)
    public static void register(IEventBus bus) {
        PARTICLES.register(bus);
        MoreFeatures.LOGGER.info("Mod Particles registered -> Performed by: " + MoreFeatures.MODID);
    }
}
