package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.enchantment.ModEnchantments;
import com.mohigster.morefeatures.tag.ModBiomeTags;
import com.mohigster.morefeatures.worldgen.biome.ModBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class ModBiomeTagProvider extends BiomeTagsProvider {
    public ModBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BiomeTags.IS_OVERWORLD)
                .addOptional(ModBiomes.TAINTED_FOREST)
                .addOptional(ModBiomes.ICE_CAVE)
                .addOptional(ModBiomes.BLOODWOOD_FOREST);

        tag(BiomeTags.IS_END)
                .addOptional(ModBiomes.END_ROT)
                .addOptional(ModBiomes.DECREPIT_FOREST)
                .addOptional(ModBiomes.PALLID_FOREST);

        tag(ModBiomeTags.DESERT)
                .addOptional(Biomes.DESERT);
    }
}
