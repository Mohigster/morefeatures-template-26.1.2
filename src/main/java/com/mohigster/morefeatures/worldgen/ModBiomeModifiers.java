package com.mohigster.morefeatures.worldgen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.common.world.BiomeModifiers;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModBiomeModifiers {
    public static final ResourceKey<BiomeModifier> ADD_AZURITE_ORE = registerKey("add_azurite_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_AZURITE_ORE = registerKey("add_nether_azurite_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_AZURITE_ORE = registerKey("add_end_azurite_ore");
    public static final ResourceKey<BiomeModifier> ADD_FLUORITE_ORE = registerKey("add_fluorite_ore");
    public static final ResourceKey<BiomeModifier> ADD_NETHER_FLUORITE_ORE = registerKey("add_nether_fluorite_ore");
    public static final ResourceKey<BiomeModifier> ADD_END_FLUORITE_ORE = registerKey("add_end_fluorite_ore");
    public static final ResourceKey<BiomeModifier> ADD_ALUMINIUM_ORE = registerKey("add_aluminium_ore");
    public static final ResourceKey<BiomeModifier> ADD_MAGNESIUM_ORE = registerKey("add_magnesium_ore");
    public static final ResourceKey<BiomeModifier> ADD_BISMUTH_ORE = registerKey("add_bismuth_ore");
    public static final ResourceKey<BiomeModifier> ADD_SMALL_ICE_PATCH = registerKey("add_small_ice_patch");
    public static final ResourceKey<BiomeModifier> ADD_ICE_PATCH = registerKey("add_ice_patch");
    public static final ResourceKey<BiomeModifier> ADD_LARGE_ICE_PATCH = registerKey("add_large_ice_patch");
    public static final ResourceKey<BiomeModifier> ADD_SMALL_BLUE_ICE_PATCH = registerKey("add_small_blue_ice_patch");
    public static final ResourceKey<BiomeModifier> ADD_BLUE_ICE_PATCH = registerKey("add_blue_ice_patch");
    public static final ResourceKey<BiomeModifier> ADD_LARGE_BLUE_ICE_PATCH = registerKey("add_large_blue_ice_patch");
    public static final ResourceKey<BiomeModifier> ADD_EVERFROST_ORE = registerKey("add_everfrost_ore");
    public static final ResourceKey<BiomeModifier> ADD_OASIS = registerKey("add_oasis");
    public static final ResourceKey<BiomeModifier> ADD_PALM_TREE = registerKey("add_palm_tree");
    public static final ResourceKey<BiomeModifier> ADD_FALLEN_PALM = registerKey("add_fallen_palm");
    public static final ResourceKey<BiomeModifier> ADD_ICE_SPIRE = registerKey("add_ice_spire");

    public static void bootstrap(BootstrapContext<BiomeModifier> context){
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);
        var biomes = context.lookup(Registries.BIOME);

        context.register(ADD_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.AZURITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_NETHER_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_AZURITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_END_AZURITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_AZURITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_FLUORITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FLUORITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_NETHER_FLUORITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_NETHER),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.NETHER_FLUORITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_END_FLUORITE_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.END_FLUORITE_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_ALUMINIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ALUMINIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_MAGNESIUM_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.MAGNESIUM_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_BISMUTH_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_END),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BISMUTH_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SMALL_ICE_PATCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SMALL_ICE_PATCH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_ICE_PATCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ICE_PATCH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_LARGE_ICE_PATCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LARGE_ICE_PATCH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_SMALL_BLUE_ICE_PATCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.SMALL_BLUE_ICE_PATCH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_BLUE_ICE_PATCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.BLUE_ICE_PATCH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_LARGE_BLUE_ICE_PATCH, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.LARGE_BLUE_ICE_PATCH_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_EVERFROST_ORE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.EVERFROST_ORE_PLACED_KEY)),
                GenerationStep.Decoration.UNDERGROUND_ORES
        ));
        context.register(ADD_PALM_TREE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.PALM_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_FALLEN_PALM, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.FALLEN_PALM_PLACED_KEY)),
                GenerationStep.Decoration.VEGETAL_DECORATION
        ));
        context.register(ADD_OASIS, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(Biomes.DESERT)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.OASIS_PLACED_KEY)),
                GenerationStep.Decoration.FLUID_SPRINGS
        ));
        context.register(ADD_ICE_SPIRE, new BiomeModifiers.AddFeaturesBiomeModifier(
                HolderSet.direct(biomes.getOrThrow(ModBiomes.ICE_CAVE)),
                HolderSet.direct(placedFeatures.getOrThrow(ModPlacedFeatures.ICE_SPIRE_PLACED_KEY)),
                GenerationStep.Decoration.SURFACE_STRUCTURES
        ));
    }

    public static ResourceKey<BiomeModifier> registerKey(String name){
        return ResourceKey.create(NeoForgeRegistries.Keys.BIOME_MODIFIERS, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }
}
