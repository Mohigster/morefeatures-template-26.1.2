package com.mohigster.morefeatures.worldgen;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class MFPlacedFeatures {

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
    public static final ResourceKey<PlacedFeature> FALLEN_BLOODWOOD_PLACED_KEY = registerKey("fallen_bloodwood_placed");
    public static final ResourceKey<PlacedFeature> TAINTED_PLACED_KEY = registerKey("tainted_placed");
    public static final ResourceKey<PlacedFeature> SMALL_TAINTED_PLACED_KEY = registerKey("small_tainted_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_TAINTED_PLACED_KEY = registerKey("fallen_tainted_placed");
    public static final ResourceKey<PlacedFeature> PALM_PLACED_KEY = registerKey("palm_placed");
    public static final ResourceKey<PlacedFeature> FALLEN_PALM_PLACED_KEY = registerKey("fallen_palm_placed");
    public static final ResourceKey<PlacedFeature> DECREPIT_PLACED_KEY = registerKey("decrepit_placed");
    public static final ResourceKey<PlacedFeature> PALLID_PLACED_KEY = registerKey("pallid_placed");

    // Vegetation keys

    public static final ResourceKey<PlacedFeature> DECREPIT_FOREST_VEGETATION_PLACED_KEY = registerKey("decrepit_forest_vegetation_placed");
    public static final ResourceKey<PlacedFeature> DECREPIT_FOREST_VEGETATION_RARE_PLACED_KEY = registerKey("decrepit_forest_vegetation_rare_placed");
    public static final ResourceKey<PlacedFeature> PALLID_FOREST_VEGETATION_PLACED_KEY = registerKey("pallid_forest_vegetation_placed");
    public static final ResourceKey<PlacedFeature> PALLID_FOREST_VEGETATION_RARE_PLACED_KEY = registerKey("pallid_forest_vegetation_rare_placed");

    // Ice cave patch resource keys

    public static final ResourceKey<PlacedFeature> SMALL_ICE_PATCH_PLACED_KEY = registerKey("small_ice_patch_placed");
    public static final ResourceKey<PlacedFeature> ICE_PATCH_PLACED_KEY = registerKey("ice_patch_placed");
    public static final ResourceKey<PlacedFeature> LARGE_ICE_PATCH_PLACED_KEY = registerKey("large_ice_patch_placed");
    public static final ResourceKey<PlacedFeature> SMALL_BLUE_ICE_PATCH_PLACED_KEY = registerKey("small_blue_ice_patch_placed");
    public static final ResourceKey<PlacedFeature> BLUE_ICE_PATCH_PLACED_KEY = registerKey("blue_ice_patch_placed");
    public static final ResourceKey<PlacedFeature> LARGE_BLUE_ICE_PATCH_PLACED_KEY = registerKey("large_blue_ice_patch_placed");
    public static final ResourceKey<PlacedFeature> SMALL_SNOW_PATCH_PLACED_KEY = registerKey("small_snow_patch_placed");
    public static final ResourceKey<PlacedFeature> SNOW_PATCH_PLACED_KEY = registerKey("snow_patch_placed");
    public static final ResourceKey<PlacedFeature> LARGE_SNOW_PATCH_PLACED_KEY = registerKey("large_snow_patch_placed");

    // Other ice cave keys

    public static final ResourceKey<PlacedFeature> EVERFROST_ORE_PLACED_KEY = registerKey("everfrost_ore_placed");

    public static final ResourceKey<PlacedFeature> ICE_SPIRE_PLACED_KEY = registerKey("iced_spire_placed");

    public static final ResourceKey<PlacedFeature> ICICLE_CLUSTER_PLACED_KEY = registerKey("icicle_cluster_placed");

    // Oasis resource key

    public static final ResourceKey<PlacedFeature> OASIS_PLACED_KEY = registerKey("oasis_placed");



    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // PLACE ORES

        register(context, AZURITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.OVERWORLD_AZURITE_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64))));

        register(context, NETHER_AZURITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.NETHER_AZURITE_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, END_AZURITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.END_AZURITE_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.OVERWORLD_FLUORITE_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(64))));

        register(context, NETHER_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.NETHER_FLUORITE_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, END_FLUORITE_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.END_FLUORITE_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(128))));

        register(context, ALUMINIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.ALUMINIUM_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(12, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, MAGNESIUM_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.MAGNESIUM_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(3, HeightRangePlacement.uniform(VerticalAnchor.absolute(-16), VerticalAnchor.absolute(160))));

        register(context, BISMUTH_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.BISMUTH_ORE_KEY),
                MFOrePlacementUtils.extraOrePlacement(1, 0.5f, 1, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));

        register(context, EVERFROST_ORE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.EVERFROST_ORE_KEY),
                MFOrePlacementUtils.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(64))));

        register(context, SMALL_ICE_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.SMALL_ICE_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, ICE_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.ICE_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, LARGE_ICE_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.LARGE_ICE_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(6, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, SMALL_BLUE_ICE_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.SMALL_BLUE_ICE_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, BLUE_ICE_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.BLUE_ICE_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, LARGE_BLUE_ICE_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.LARGE_BLUE_ICE_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(8, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, SMALL_SNOW_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.SMALL_SNOW_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, SNOW_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.SNOW_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));

        register(context, LARGE_SNOW_PATCH_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.LARGE_SNOW_PATCH_KEY),
                MFOrePlacementUtils.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.absolute(-64), VerticalAnchor.absolute(128))));


        // PLACE TREES

        register(context, BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.01f, 1),
                        MFBlocks.BLOODWOOD_SAPLING.get()));

        register(context, SMALL_BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.SMALL_BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.01f, 1),
                        MFBlocks.BLOODWOOD_SAPLING.get()));

        register(context, FALLEN_BLOODWOOD_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.FALLEN_BLOODWOOD_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 1),
                        MFBlocks.BLOODWOOD_SAPLING.get()));

        register(context, TAINTED_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.TAINTED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(1, 0.01f, 1),
                        MFBlocks.TAINTED_SAPLING.get()));

        register(context, SMALL_TAINTED_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.SMALL_TAINTED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(6, 0.01f, 1),
                        MFBlocks.TAINTED_SAPLING.get()));

        register(context, FALLEN_TAINTED_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.FALLEN_TAINTED_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 1),
                        MFBlocks.TAINTED_SAPLING.get()));

        register(context, PALM_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.PALM_TREE_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.05f, 1),
                        MFBlocks.PALM_SAPLING.get()));

        register(context, FALLEN_PALM_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.FALLEN_PALM_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(0, 0.01f, 1),
                        MFBlocks.PALM_SAPLING.get()));

        register(context, DECREPIT_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.DECREPIT_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.02f, 1),
                        MFBlocks.DECREPIT_SAPLING.get()));

        register(context, PALLID_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.PALLID_KEY),
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(8, 0.02f, 1),
                        MFBlocks.PALLID_SAPLING.get()));

        register(context, OASIS_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.OASIS_KEY),
                List.of(
                        RarityFilter.onAverageOnceEvery(200),   // very rare
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );

        // PLACE VEGETATION

        register(context, DECREPIT_FOREST_VEGETATION_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.DECREPIT_ROOTS_KEY),
                List.of(
                        CountPlacement.of(56),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        register(context, DECREPIT_FOREST_VEGETATION_RARE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.PALLID_ROOTS_KEY), // Rare chance for pallid roots to generate in decrepit forest
                List.of(
                        CountPlacement.of(15),
                        InSquarePlacement.spread(),
                        PlacementUtils.FULL_RANGE,
                        BiomeFilter.biome()
                ));

        register(context, PALLID_FOREST_VEGETATION_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.PALLID_ROOTS_KEY),
                List.of(
                        CountPlacement.of(56),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                ));

        register(context, PALLID_FOREST_VEGETATION_RARE_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.DECREPIT_ROOTS_KEY), // Rare chance for decrepit roots in pallid forest
                List.of(
                        CountPlacement.of(15),
                        InSquarePlacement.spread(),
                        PlacementUtils.FULL_RANGE,
                        BiomeFilter.biome()
                ));

        register(context, ICICLE_CLUSTER_PLACED_KEY, configuredFeatures.getOrThrow(MFConfiguredFeatures.ICICLE_CLUSTER_KEY),
                List.of(
                        CountPlacement.of(UniformInt.of(48, 96)),
                        InSquarePlacement.spread(),
                        PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,
                        BiomeFilter.biome()
                ));

        // PLACE SPIRE

        PlacementUtils.register(
                context,
                ICE_SPIRE_PLACED_KEY,
                configuredFeatures.getOrThrow(MFConfiguredFeatures.ICE_SPIRE_KEY),
                RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),

                // Check across vertical heightmap
                PlacementUtils.RANGE_BOTTOM_TO_MAX_TERRAIN_HEIGHT,


                EnvironmentScanPlacement.scanningFor(
                        Direction.UP,
                        BlockPredicate.solid(),               // Target the ice cave ceiling
                        BlockPredicate.ONLY_IN_AIR_PREDICATE, // Only allow for air
                        12
                ),

                BiomeFilter.biome(),
                PlacementUtils.HEIGHTMAP
        );
    }

    private static ResourceKey<PlacedFeature> registerKey(String name){
        return ResourceKey.create(Registries.PLACED_FEATURE, MFIdentifier.withMfNamespace(name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
