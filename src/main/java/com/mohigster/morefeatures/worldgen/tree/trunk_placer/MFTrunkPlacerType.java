package com.mohigster.morefeatures.worldgen.tree.trunk_placer;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings("unused")
public class MFTrunkPlacerType<P extends TrunkPlacer>{

    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, MoreFeatures.MODID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<LeaningTrunkPlacer>> LEANING_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("leaning_trunk_placer", () -> new TrunkPlacerType<>(LeaningTrunkPlacer.CODEC));

    public static void register(IEventBus eventBus){
        TRUNK_PLACER_TYPES.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Trunk Placers registered -> Performed by: " + MoreFeatures.MODID);
    }
}
