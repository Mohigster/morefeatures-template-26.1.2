package com.mohigster.morefeatures.datagen.custom;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class MFMagicBlockTransmutationProvider extends MagicBlockTransmutationProvider{
    public MFMagicBlockTransmutationProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, MoreFeatures.MODID);
    }

    @Override
    protected void generate() {
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_AQUAMARINE_DISC, MFItems.MUSIC_DISC_AQUAMARINE.get());
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_CARBON, MFItems.CARBON_FIBER.get());
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_BISMUTH_SCRAP, MFItems.BISMUTH_SCRAP.get());
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_RAW_BISMUTH_BLOCK, MFBlocks.RAW_BISMUTH_BLOCK.get().asItem());
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_SCRAP, Items.NETHERITE_SCRAP);
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_INGOT, Items.NETHERITE_INGOT);
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_DIAMOND, Items.DIAMOND);
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_LINGERING_POT, Items.LINGERING_POTION, true);
        add(MFItemTags.MAGIC_BLOCK_TURNS_TO_BEDROCK, Items.BEDROCK);
    }
}
