package com.mohigster.morefeatures.worldgen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    // Ore resource keys

    public static final ResourceKey<PlacedFeature> AZURITE_ORE_PLACED_KEY = registerKey("azurite_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_AZURITE_ORE_PLACED_KEY = registerKey("nether_azurite_ore_placed");
    public static final ResourceKey<PlacedFeature> END_AZURITE_ORE_PLACED_KEY = registerKey("end_azurite_ore_placed");
    public static final ResourceKey<PlacedFeature> FLUORITE_ORE_PLACED_KEY = registerKey("fluorite_ore_placed");
    public static final ResourceKey<PlacedFeature> NETHER_FLUORITE_ORE_PLACED_KEY = registerKey("nether_fluorite_ore_placed");
    public static final ResourceKey<PlacedFeature> END_FLUORITE_ORE_PLACED_KEY = registerKey("end_fluorite_ore_placed");
    public static final ResourceKey<PlacedFeature> ALUMINIUM_ORE_PLACED_KEY = registerKey("aluminium_ore_placed");
    public static final ResourceKey<PlacedFeature> MAGNESIUM_ORE_PLACED_KEY = registerKey("magnesium_ore_placed");
    public static final ResourceKey<PlacedFeature> BISMUTH_ORE_PLACED_KEY = registerKey("bismuth_ore_placed");

    // Tree resource keys

    public static final ResourceKey<PlacedFeature> BLOODWOOD_PLACED_KEY = registerKey("bloodwood_placed");
    public static final ResourceKey<PlacedFeature> SMALL_BLOODWOOD_PLACED_KEY = registerKey("small_bloodwood_placed");


    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // PLACE ORES

        register(context, AZURITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_AZURITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64))));

        register(context, NETHER_AZURITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_AZURITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, END_AZURITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_AZURITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OVERWORLD_FLUORITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64))));

        register(context, NETHER_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NETHER_FLUORITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, END_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.END_FLUORITE_ORE_KEY),
                ModOrePlacement.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, ALUMINIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, MAGNESIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MAGNESIUM_ORE_KEY),
                ModOrePlacement.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(160))));

        register(context, BISMUTH_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BISMUTH_ORE_KEY),
                ModOrePlacement.commonOrePlacement(1, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));

        // PLACE TREES

        register(context, BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.01f, 1),
                        ModBlocks.BLOODWOOD_SAPLING.get()));

        register(context, SMALL_BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SMALL_BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(2, 0.01f, 1),
                        ModBlocks.BLOODWOOD_SAPLING.get()));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
