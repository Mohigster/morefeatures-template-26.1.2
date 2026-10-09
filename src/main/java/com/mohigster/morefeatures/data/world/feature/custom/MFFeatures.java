package com.mohigster.morefeatures.data.world.feature.custom;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.core.RestrictedTo;
import com.mohigster.morefeatures.data.world.feature.custom.config.MultiBaseSpeleothemClusterConfiguration;
import com.mohigster.morefeatures.data.world.feature.custom.config.OasisConfiguration;
import com.mohigster.morefeatures.data.world.feature.custom.config.nethervine.CeilingVinesConfiguration;
import com.mohigster.morefeatures.data.world.feature.custom.config.nethervine.FloorVinesConfiguration;
import com.mohigster.morefeatures.data.world.feature.custom.nethervine.CeilingVinesFeature;
import com.mohigster.morefeatures.data.world.feature.custom.nethervine.FloorVinesFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MFFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, MoreFeatures.MODID);

    public static final Supplier<Feature<OasisConfiguration>> OASIS =
            FEATURES.register("oasis", () -> new OasisFeature(OasisConfiguration.CODEC));

    public static final Supplier<Feature<NoneFeatureConfiguration>> ICE_SPIRE =
            FEATURES.register("ice_spire", () -> new IceSpireFeature(NoneFeatureConfiguration.CODEC));

    public static final Supplier<Feature<MultiBaseSpeleothemClusterConfiguration>> MULTI_BASE_SPELEOTHEM_CLUSTER =
            FEATURES.register("multi_base_speleothem_cluster",
                    () -> new MultiBaseSpeleothemClusterFeature(
                            MultiBaseSpeleothemClusterConfiguration.CODEC.codec()));

    public static final Supplier<Feature<FloorVinesConfiguration>> FLOOR_VINES =
            FEATURES.register("floor_vines", () -> new FloorVinesFeature(FloorVinesConfiguration.CODEC));

    public static final Supplier<Feature<CeilingVinesConfiguration>> CEILING_VINES =
            FEATURES.register("ceiling_vines", () -> new CeilingVinesFeature(CeilingVinesConfiguration.CODEC));

    @RestrictedTo(MoreFeatures.class)
    public static void register(IEventBus eventBus){
        FEATURES.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Features registered -> Performed by: " + MoreFeatures.MODID);
    }
}
