package com.mohigster.morefeatures.data.world.feature;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.world.feature.config.MultiBaseSpeleothemClusterConfiguration;
import com.mohigster.morefeatures.data.world.feature.config.OasisConfiguration;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MFFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, MoreFeatures.MODID);

    public static final DeferredHolder<Feature<?>, Feature<OasisConfiguration>> OASIS =
            FEATURES.register("oasis", () -> new OasisFeature(OasisConfiguration.CODEC.codec()));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ICE_SPIRE =
            FEATURES.register("ice_spire", () -> new IceSpireFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<MultiBaseSpeleothemClusterConfiguration>> MULTI_BASE_SPELEOTHEM_CLUSTER =
            FEATURES.register("multi_base_speleothem_cluster",
                    () -> new MultiBaseSpeleothemClusterFeature(
                            MultiBaseSpeleothemClusterConfiguration.CODEC.codec()
                    ));

    public static void register(IEventBus eventBus){
        FEATURES.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Features registered -> Performed by: " + MoreFeatures.MODID);
    }
}
