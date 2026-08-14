package com.mohigster.morefeatures.data.generators.custom;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.data.generators.custom.providers.MagicBlockTransmutationProvider;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFMagicBlockTransmutationProvider extends MagicBlockTransmutationProvider {
    public MFMagicBlockTransmutationProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void generate(HolderLookup.@NonNull Provider provider) {
        this.addFromTag(MFItems.MUSIC_DISC_AQUAMARINE.get(), MFItemTags.MAGIC_BLOCK_TURNS_TO_AQUAMARINE_DISC);
        // Since only the ingredients in the MAGIC_BLOCK_MULTIPLIES_RESULTS tag get the extra amount applied, adding the extra amount here won't make all ingredients passed in duplicate.
        this.addFromTag(1, MFItems.CARBON_FIBER.get(), MFItemTags.MAGIC_BLOCK_TURNS_TO_CARBON);
        this.addFromTag(MFItems.BISMUTH_SCRAP.get(), MFItemTags.MAGIC_BLOCK_TURNS_TO_BISMUTH_SCRAP);
        this.addFromTag(MFBlocks.RAW_BISMUTH_BLOCK.asItem(), MFItemTags.MAGIC_BLOCK_TURNS_TO_RAW_BISMUTH_BLOCK);
        this.addFromTag(Items.NETHERITE_SCRAP, MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_SCRAP);
        this.addFromTag(Items.NETHERITE_INGOT, MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_INGOT);
        this.addFromTag(Items.DIAMOND, MFItemTags.MAGIC_BLOCK_TURNS_TO_DIAMOND);
        this.addFromTag(true, Items.LINGERING_POTION, MFItemTags.MAGIC_BLOCK_TURNS_TO_LINGERING_POT);
        this.addFromTag(Items.BEDROCK, MFItemTags.MAGIC_BLOCK_TURNS_TO_BEDROCK);
        this.addFromTag(Items.STONE, MFItemTags.MAGIC_BLOCK_TURNS_TO_STONE);
    }
}
