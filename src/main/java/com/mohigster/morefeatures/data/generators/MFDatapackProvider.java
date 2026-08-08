package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.sound.MFJukeboxSongs;
import com.mohigster.morefeatures.enchantment.MFEnchantments;
import com.mohigster.morefeatures.item.custom.trim.MFTrimMaterials;
import com.mohigster.morefeatures.data.world.MFBiomeModifiers;
import com.mohigster.morefeatures.data.world.MFConfiguredFeatures;
import com.mohigster.morefeatures.data.world.MFPlacedFeatures;
import com.mohigster.morefeatures.data.world.biome.MFBiomes;
import com.mohigster.morefeatures.data.world.carver.MFCarvers;
import com.mohigster.morefeatures.data.world.dimension.MFDimensions;
import com.mohigster.morefeatures.data.world.noise.MFNoiseData;
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
            .add(Registries.NOISE, MFNoiseData::bootstrap)
            .add(Registries.DIMENSION_TYPE, MFDimensions::bootstrapType)
            .add(Registries.LEVEL_STEM, MFDimensions::bootstrapStem)
            .add(Registries.ENCHANTMENT, MFEnchantments::bootstrap)
            .add(Registries.CONFIGURED_CARVER, MFCarvers::bootstrap)
            .add(Registries.TRIM_MATERIAL, MFTrimMaterials::bootstrap)
            .add(Registries.BIOME, MFBiomes::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, MFConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, MFPlacedFeatures::bootstrap)
            .add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, MFBiomeModifiers::bootstrap);

    public MFDatapackProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries){
        super(output, registries, BUILDER, Set.of(MoreFeatures.MODID));
    }
}
