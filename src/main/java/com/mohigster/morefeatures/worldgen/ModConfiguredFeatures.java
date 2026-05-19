package com.mohigster.morefeatures.worldgen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

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

    // Tree resource keys

    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOODWOOD_KEY = registerKey("bloodwood");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BLOODWOOD_KEY = registerKey("small_bloodwood");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context){

        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endStoneReplaceables = new BlockMatchTest(Blocks.END_STONE);

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

        // Registering ore configured features
        register(context, OVERWORLD_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldAzuriteOres, 9));
        register(context, NETHER_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_AZURITE_ORE.get().defaultBlockState(), 9));
        register(context, END_AZURITE_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, ModBlocks.END_AZURITE_ORE.get().defaultBlockState(), 9));
        register(context, OVERWORLD_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(overworldFluoriteOres, 9));
        register(context, NETHER_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceables, ModBlocks.NETHER_FLUORITE_ORE.get().defaultBlockState(), 9));
        register(context, END_FLUORITE_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, ModBlocks.END_FLUORITE_ORE.get().defaultBlockState(), 9));
        register(context, ALUMINIUM_ORE_KEY, Feature.ORE, new OreConfiguration(aluminiumOres, 9));
        register(context, MAGNESIUM_ORE_KEY, Feature.ORE, new OreConfiguration(magnesiumOres, 9));
        register(context, BISMUTH_ORE_KEY, Feature.ORE, new OreConfiguration(endStoneReplaceables, ModBlocks.BISMUTH_ORE.get().defaultBlockState(), 3, 1.0f));

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
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name){
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
