package com.mohigster.morefeatures.worldgen;

import com.google.common.collect.ImmutableList;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.worldgen.feature.MFFeatures;
import com.mohigster.morefeatures.worldgen.feature.config.MultiBaseSpeleothemClusterConfiguration;
import com.mohigster.morefeatures.worldgen.feature.config.OasisConfiguration;
import com.mohigster.morefeatures.worldgen.tree.decorator.TrunkLightDecorator;
import com.mohigster.morefeatures.worldgen.tree.foliage_placer.PalmFoliagePlacer;
import com.mohigster.morefeatures.worldgen.tree.trunk_placer.LeaningTrunkPlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ClampedNormalFloat;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.DarkOakFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.AttachedToLogsDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.DarkOakTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;
import java.util.OptionalInt;

public class MFConfiguredFeatures {

    // Ore resource keys

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_AZURITE_ORE_KEY = registerKey("azurite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_AZURITE_ORE_KEY = registerKey("nether_azurite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_AZURITE_ORE_KEY = registerKey("end_azurite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_FLUORITE_ORE_KEY = registerKey("fluorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_FLUORITE_ORE_KEY = registerKey("nether_fluorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_FLUORITE_ORE_KEY = registerKey("end_fluorite_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ALUMINIUM_ORE_KEY = registerKey("aluminium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGNESIUM_ORE_KEY = registerKey("magnesium_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BISMUTH_ORE_KEY = registerKey("bismuth_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EVERFROST_ORE_KEY = registerKey("everfrost_ore");

    // Tree resource keys

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_KEY = registerKey("bloodwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BLOODWOOD_KEY = registerKey("small_bloodwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BLOODWOOD_KEY = registerKey("fallen_bloodwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TAINTED_KEY = registerKey("tainted");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_TAINTED_KEY = registerKey("small_tainted");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_TAINTED_KEY = registerKey("fallen_tainted");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE_KEY = registerKey("palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_PALM_KEY = registerKey("fallen_palm");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DECREPIT_KEY = registerKey("decrepit");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALLID_KEY = registerKey("pallid");

    // Vegetation keys

    public static final ResourceKey<ConfiguredFeature<?, ?>> DECREPIT_ROOTS_KEY = registerKey("decrepit_forest_vegetation");

    public static final ResourceKey<ConfiguredFeature<?, ?>> PALLID_ROOTS_KEY = registerKey("pallid_forest_vegetation");

    // Frozen resource keys

    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_ICE_PATCH_KEY = registerKey("small_ice_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_PATCH_KEY = registerKey("ice_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_ICE_PATCH_KEY = registerKey("large_ice_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BLUE_ICE_PATCH_KEY = registerKey("small_blue_ice_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLUE_ICE_PATCH_KEY = registerKey("blue_ice_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_BLUE_ICE_PATCH_KEY = registerKey("large_blue_ice_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_SNOW_PATCH_KEY = registerKey("small_snow_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOW_PATCH_KEY = registerKey("snow_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_SNOW_PATCH_KEY = registerKey("large_snow_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ICE_SPIRE_KEY = registerKey("ice_spire");

    public static final ResourceKey<ConfiguredFeature<?, ?>> ICICLE_CLUSTER_KEY = registerKey("icicle_cluster");

    // Oasis

    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_KEY = registerKey("oasis");




    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endStoneReplaceables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest packedIceReplaceables = new BlockMatchTest(Blocks.PACKED_ICE);
        RuleTest blueIceReplaceables = new BlockMatchTest(Blocks.BLUE_ICE);

        List<OreConfiguration.TargetBlockState> overworldAzuriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, MFBlocks.AZURITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, MFBlocks.DEEPSLATE_AZURITE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldFluoriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, MFBlocks.FLUORITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, MFBlocks.DEEPSLATE_FLUORITE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> aluminiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, MFBlocks.ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, MFBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> magnesiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, MFBlocks.MAGNESIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, MFBlocks.DEEPSLATE_MAGNESIUM_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> everfrostOres = List.of(
                OreConfiguration.target(packedIceReplaceables, MFBlocks.EVERFROST_PACKED_ICE_ORE.get().defaultBlockState()),
                OreConfiguration.target(blueIceReplaceables, MFBlocks.EVERFROST_BLUE_ICE_ORE.get().defaultBlockState())
        );

        // Registering ore configured features
        register(context, OVERWORLD_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAzuriteOres, 9, 0.65f));
        register(context, NETHER_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, MFBlocks.NETHER_AZURITE_ORE.get().defaultBlockState(), 9, 0.85f));
        register(context, END_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, MFBlocks.END_AZURITE_ORE.get().defaultBlockState(), 9, 0.80f));
        register(context, OVERWORLD_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldFluoriteOres, 9, 0.65f));
        register(context, NETHER_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, MFBlocks.NETHER_FLUORITE_ORE.get().defaultBlockState(), 9, 0.85f));
        register(context, END_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, MFBlocks.END_FLUORITE_ORE.get().defaultBlockState(), 9, 0.80f));
        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(aluminiumOres, 9));
        register(context, MAGNESIUM_ORE_KEY, Feature.ORE, new OreConfiguration(magnesiumOres, 9));
        register(context, BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, MFBlocks.BISMUTH_ORE.get().defaultBlockState(), 3, 1.0f));
        register(context, SMALL_ICE_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.ICE.defaultBlockState(), 10));
        register(context, ICE_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.ICE.defaultBlockState(), 20));
        register(context, LARGE_ICE_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.ICE.defaultBlockState(), 30));
        register(context, SMALL_BLUE_ICE_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.BLUE_ICE.defaultBlockState(), 10));
        register(context, BLUE_ICE_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.BLUE_ICE.defaultBlockState(), 20));
        register(context, LARGE_BLUE_ICE_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.BLUE_ICE.defaultBlockState(), 30));
        register(context, SMALL_SNOW_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.SNOW_BLOCK.defaultBlockState(), 10));
        register(context, SNOW_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.SNOW_BLOCK.defaultBlockState(), 20));
        register(context, LARGE_SNOW_PATCH_KEY, Feature.ORE, new OreConfiguration(packedIceReplaceables, Blocks.SNOW_BLOCK.defaultBlockState(), 30));
        register(context, EVERFROST_ORE_KEY, Feature.ORE, new OreConfiguration(everfrostOres, 9));

        // Registering ice spire
        register(context, ICE_SPIRE_KEY, MFFeatures.ICE_SPIRE.get(), FeatureConfiguration.NONE);

        register(context, ICICLE_CLUSTER_KEY, MFFeatures.MULTI_BASE_SPELEOTHEM_CLUSTER.get(),
                new MultiBaseSpeleothemClusterConfiguration(
                        List.of(
                                Blocks.PACKED_ICE.defaultBlockState(),
                                Blocks.BLUE_ICE.defaultBlockState()
                        ),
                        MFBlocks.ICICLE.get().defaultBlockState(),
                        blocks.getOrThrow(BlockTags.DRIPSTONE_REPLACEABLE),
                        12,
                        UniformInt.of(3, 6),
                        UniformInt.of(2, 8),
                        1,
                        3,
                        UniformInt.of(2, 4),
                        UniformFloat.of(0.3F, 0.7F),
                        ClampedNormalFloat.of(0.1F, 0.3F, 0.1F, 0.9F),
                        0.1F,
                        3,
                        8
                ));


        // Registering tree configured features
        register(context, BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MFBlocks.BLOODWOOD_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(MFBlocks.BLOODWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT))
                .ignoreVines()
                .build()
        );
        register(context, SMALL_BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MFBlocks.BLOODWOOD_LOG.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(MFBlocks.BLOODWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                BlockStateProvider.simple(Blocks.DIRT))
                .ignoreVines()
                .build()
        );
        register(context, FALLEN_BLOODWOOD_KEY, Feature.FALLEN_TREE,
                createFallenTree(
                        MFBlocks.BLOODWOOD_LOG.get(),
                        4,
                        9,
                        true)
                .build()
        );
        register(context, TAINTED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MFBlocks.TAINTED_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(MFBlocks.TAINTED_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.DIRT))
                .ignoreVines()
                .build()
        );
        register(context, SMALL_TAINTED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MFBlocks.TAINTED_LOG.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(MFBlocks.TAINTED_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1),
                BlockStateProvider.simple(Blocks.DIRT))
                .ignoreVines()
                .build()
        );
        register(context, FALLEN_TAINTED_KEY, Feature.FALLEN_TREE,
                createFallenTree(
                        MFBlocks.TAINTED_LOG.get(),
                        4,
                        9,
                        true)
                        .build()
        );
        register(context, PALM_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(MFBlocks.PALM_LOG.get()),
                new LeaningTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(MFBlocks.PALM_LEAVES.get()),
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2),
                BlockStateProvider.simple(Blocks.SAND))
                .ignoreVines()
                .build()
        );
        register(context, FALLEN_PALM_KEY, Feature.FALLEN_TREE,
                createFallenTree(
                        MFBlocks.PALM_LOG.get(),
                        4,
                        9,
                        false)
                        .build()
        );
        register(context, DECREPIT_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(MFBlocks.DECREPIT_LOG.get()),
                        new DarkOakTrunkPlacer(6, 2, 1),
                        BlockStateProvider.simple(MFBlocks.DECREPIT_LEAVES.get()),
                        new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()),
                        BlockStateProvider.simple(MFBlocks.DECREPIT_NULLIUM.get()))
                        .ignoreVines()
                        .decorators(List.of(
                                new TrunkLightDecorator(0.03f,
                                        BlockStateProvider.simple(Blocks.PEARLESCENT_FROGLIGHT))
                        ))
                        .build()
        );
        register(context, PALLID_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(MFBlocks.PALLID_LOG.get()),
                        new DarkOakTrunkPlacer(6, 2, 1),
                        BlockStateProvider.simple(MFBlocks.PALLID_LEAVES.get()),
                        new DarkOakFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0)),
                        new ThreeLayersFeatureSize(1, 1, 0, 1, 2, OptionalInt.empty()),
                        BlockStateProvider.simple(MFBlocks.PALLID_NULLIUM.get()))
                        .ignoreVines()
                        .decorators(List.of(
                                new TrunkLightDecorator(0.03f,
                                        BlockStateProvider.simple(Blocks.VERDANT_FROGLIGHT))
                        ))
                        .build()
        );

        // Registering forest vegetation (Roots)
        register(context, DECREPIT_ROOTS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(MFBlocks.DECREPIT_ROOTS.get()))
        );

        register(context, PALLID_ROOTS_KEY, Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(MFBlocks.PALLID_ROOTS.get()))
        );

        // Registering oasis
        register(context, OASIS_KEY, MFFeatures.OASIS.get(),
                new OasisConfiguration(context.lookup(Registries.CONFIGURED_FEATURE)
                        .getOrThrow(MFConfiguredFeatures.PALM_TREE_KEY)));
    }

    @SuppressWarnings("SameParameterValue")
    private static FallenTreeConfiguration.FallenTreeConfigurationBuilder createFallenTree(Block logBlock, final int minLength, final int maxLength, boolean hasVines){
        FallenTreeConfiguration.FallenTreeConfigurationBuilder builder = new FallenTreeConfiguration.FallenTreeConfigurationBuilder(BlockStateProvider.simple(logBlock), UniformInt.of(minLength, maxLength))
                .logDecorators(
                        ImmutableList.of(
                                new AttachedToLogsDecorator(
                                        0.1F,
                                        new WeightedStateProvider(
                                                WeightedList.<BlockState>builder().add(Blocks.RED_MUSHROOM.defaultBlockState(), 2).add(Blocks.BROWN_MUSHROOM.defaultBlockState(), 1)
                                        ),
                                        List.of(Direction.UP)
                                )
                        )
                );

        if (hasVines) {
            builder.stumpDecorators(ImmutableList.of(TrunkVineDecorator.INSTANCE));
        }

        return builder;
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, MFIdentifier.withMfNamespace(name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
