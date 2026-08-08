package com.mohigster.morefeatures.data.generators.tag;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.tag.MFBiomeTags;
import com.mohigster.morefeatures.data.world.biome.MFBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFBiomeTagProvider extends TagsProvider<Biome> {
    public MFBiomeTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.BIOME, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        this.tag(BiomeTags.IS_OVERWORLD)
                .addOptional(MFBiomes.TAINTED_FOREST)
                .addOptional(MFBiomes.ICE_CAVES)
                .addOptional(MFBiomes.BLOODWOOD_FOREST);

        this.tag(BiomeTags.IS_END)
                .addOptional(MFBiomes.END_ROT)
                .addOptional(MFBiomes.DECREPIT_FOREST)
                .addOptional(MFBiomes.PALLID_FOREST);

        this.tag(BiomeTags.IS_NETHER)
                .addOptional(MFBiomes.CHARRED_FOREST);

        this.tag(MFBiomeTags.NETHER_FOREST)
                .addOptional(MFBiomes.CHARRED_FOREST)
                .addOptional(Biomes.WARPED_FOREST)
                .addOptional(Biomes.CRIMSON_FOREST);

        this.tag(MFBiomeTags.EVIL_FOREST)
                .addOptional(MFBiomes.BLOODWOOD_FOREST)
                .addOptional(MFBiomes.TAINTED_FOREST);

        this.tag(MFBiomeTags.END_FOREST)
                .addOptional(MFBiomes.DECREPIT_FOREST)
                .addOptional(MFBiomes.PALLID_FOREST);

        this.tag(MFBiomeTags.DESERT)
                .addOptional(Biomes.DESERT);

        this.tag(MFBiomeTags.OCEAN_CITADEL_BIOMES)
                .addOptionalTag(BiomeTags.IS_OCEAN);
    }
}
