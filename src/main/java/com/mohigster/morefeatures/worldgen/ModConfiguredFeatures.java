package com.mohigster.morefeatures.worldgen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.worldgen.feature.ModFeatures;
import com.mohigster.morefeatures.worldgen.feature.config.OasisConfiguration;
import com.mohigster.morefeatures.worldgen.tree.foliage_placer.PalmFoliagePlacer;
import com.mohigster.morefeatures.worldgen.tree.trunk_placer.LeaningTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {

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

    // Oasis

    public static final ResourceKey<ConfiguredFeature<?, ?>> OASIS_KEY = registerKey("oasis");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE_KEY = registerKey("palm_tree");



    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endStoneReplaceables = new BlockMatchTest(Blocks.END_STONE);
        RuleTest packedIceReplaceables = new BlockMatchTest(Blocks.PACKED_ICE);
        RuleTest blueIceReplaceables = new BlockMatchTest(Blocks.BLUE_ICE);

        List<OreConfiguration.TargetBlockState> overworldAzuriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.AZURITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_AZURITE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> overworldFluoriteOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.FLUORITE_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_FLUORITE_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> aluminiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.ALUMINIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> magnesiumOres = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.MAGNESIUM_ORE.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get().defaultBlockState())
        );
        List<OreConfiguration.TargetBlockState> everfrostOres = List.of(
                OreConfiguration.target(packedIceReplaceables, ModBlocks.EVERFROST_PACKED_ICE_ORE.get().defaultBlockState()),
                OreConfiguration.target(blueIceReplaceables, ModBlocks.EVERFROST_BLUE_ICE_ORE.get().defaultBlockState())
        );

        // Registering ore configured features
        register(context, OVERWORLD_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAzuriteOres, 9, 0.65f));
        register(context, NETHER_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_AZURITE_ORE.get().defaultBlockState(), 9, 0.85f));
        register(context, END_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, ModBlocks.END_AZURITE_ORE.get().defaultBlockState(), 9, 0.80f));
        register(context, OVERWORLD_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldFluoriteOres, 9, 0.65f));
        register(context, NETHER_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_FLUORITE_ORE.get().defaultBlockState(), 9, 0.85f));
        register(context, END_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, ModBlocks.END_FLUORITE_ORE.get().defaultBlockState(), 9, 0.80f));
        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(aluminiumOres, 9));
        register(context, MAGNESIUM_ORE_KEY, Feature.ORE, new OreConfiguration(magnesiumOres, 9));
        register(context, BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, ModBlocks.BISMUTH_ORE.get().defaultBlockState(), 3, 1.0f));
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

        register(context, ICE_SPIRE_KEY, ModFeatures.ICE_SPIRE.get(), FeatureConfiguration.NONE);

        // Registering tree configured features
        register(context, BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines()
                .build()
        );
        register(context, SMALL_BLOODWOOD_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .build()
        );
        register(context, FALLEN_BLOODWOOD_KEY, Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.BLOODWOOD_LOG.get()),
                UniformInt.of(4, 9))
                .build()
        );
        register(context, TAINTED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.TAINTED_LOG.get()),
                new ForkingTrunkPlacer(4, 4, 3),
                BlockStateProvider.simple(ModBlocks.TAINTED_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(2), 3),
                new TwoLayersFeatureSize(1, 0, 2))
                .ignoreVines()
                .build()
        );
        register(context, SMALL_TAINTED_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.TAINTED_LOG.get()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.simple(ModBlocks.TAINTED_LEAVES.get()),
                new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                new TwoLayersFeatureSize(1, 0, 1))
                .ignoreVines()
                .build()
        );
        register(context, FALLEN_TAINTED_KEY, Feature.FALLEN_TREE, new FallenTreeConfiguration.FallenTreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.TAINTED_LOG.get()),
                UniformInt.of(4, 9))
                .build()
        );
        register(context, PALM_TREE_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.PALM_LOG.get()),
                new LeaningTrunkPlacer(5, 2, 2),
                BlockStateProvider.simple(Blocks.AZALEA_LEAVES), // Placeholder. Will replace with palm leaves when added
                new PalmFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0)),
                new TwoLayersFeatureSize(1, 0, 2))
                .belowTrunkProvider(BlockStateProvider.simple(Blocks.SAND))
                .ignoreVines()
                .build()
        );

        // Register oasis

        register(context, OASIS_KEY, ModFeatures.OASIS.get(),
                new OasisConfiguration(context.lookup(Registries.CONFIGURED_FEATURE)
                        .getOrThrow(ModConfiguredFeatures.PALM_TREE_KEY)));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
