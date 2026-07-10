package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.tag.MFBiomeTags;
import com.mohigster.morefeatures.worldgen.biome.MFBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

public class MFBiomeTagProvider extends BiomeTagsProvider {
    public MFBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BiomeTags.IS_OVERWORLD)
                .addOptional(MFBiomes.TAINTED_FOREST)
                .addOptional(MFBiomes.ICE_CAVES)
                .addOptional(MFBiomes.BLOODWOOD_FOREST);

        tag(BiomeTags.IS_END)
                .addOptional(MFBiomes.END_ROT)
                .addOptional(MFBiomes.DECREPIT_FOREST)
                .addOptional(MFBiomes.PALLID_FOREST);

        tag(MFBiomeTags.DESERT)
                .addOptional(Biomes.DESERT);
    }
}
