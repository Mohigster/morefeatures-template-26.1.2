package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.enchantment.MFEnchantments;
import com.mohigster.morefeatures.worldgen.MFBiomeModifiers;
import com.mohigster.morefeatures.worldgen.MFConfiguredFeatures;
import com.mohigster.morefeatures.worldgen.MFPlacedFeatures;
import com.mohigster.morefeatures.worldgen.biome.MFBiomes;
import com.mohigster.morefeatures.worldgen.carver.MFCarvers;
import com.mohigster.morefeatures.worldgen.dimension.MFDimensions;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class MFDatapackProvider extends DatapackBuiltinEntriesProvider {
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.JUKEBOX_SONG, MFJukeboxSongs::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, MFConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, MFPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MFBiomeModifiers::bootstrap)
            .add(Registries.DIMENSION_TYPE, MFDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, MFDimensions::bootstrapStem)
            .add(Registries.ENCHANTMENT, MFEnchantments::bootstrap)
            .add(Registries.CONFIGURED_CARVER, MFCarvers::bootstrap)
            .add(Registries.BIOME, MFBiomes::bootstrap);

    public MFDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries){
        super(output, registries, BUILDER, Set.of(MoreFeatures.MODID));
    }
}
