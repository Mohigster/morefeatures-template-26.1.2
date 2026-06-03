package com.mohigster.morefeatures.worldgen.tree.trunk_placer;

import com.mohigster.morefeatures.MoreFeatures;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTrunkPlacerType<P extends TrunkPlacer>{

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MoreFeatures.MODID);

    // Register your specific custom type
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<LeaningTrunkPlacer>> LEANING_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("leaning_trunk_placer", () -> new TrunkPlacerType<>(LeaningTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus){
        TRUNK_PLACER_TYPES.register(eventBus);
    }
}
