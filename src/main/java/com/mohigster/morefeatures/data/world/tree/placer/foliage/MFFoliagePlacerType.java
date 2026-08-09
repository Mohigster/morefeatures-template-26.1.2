package com.mohigster.morefeatures.data.world.tree.placer.foliage;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MFFoliagePlacerType {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, MoreFeatures.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>,
            FoliagePlacerType<QuadFrongedFoliagePlacer>> QUAD_FRONGED_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("quad_fronged_foliage_placer",
                    () -> new FoliagePlacerType<>(QuadFrongedFoliagePlacer.CODEC));

    public static void register(IEventBus eventBus){
        FOLIAGE_PLACER_TYPES.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Foliage Placer registered -> Performed by: " + MoreFeatures.MODID);
    }
}
