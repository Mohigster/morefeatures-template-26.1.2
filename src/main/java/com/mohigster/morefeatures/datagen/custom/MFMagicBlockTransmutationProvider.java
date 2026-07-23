package com.mohigster.morefeatures.datagen.custom;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class MFMagicBlockTransmutationProvider extends MagicBlockTransmutationProvider{
    public MFMagicBlockTransmutationProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void generate() {
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_STONE, Items.STONE);
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_AQUAMARINE_DISC, MFItems.MUSIC_DISC_AQUAMARINE.get());
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_CARBON, MFItems.CARBON_FIBER.get());
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_BISMUTH_SCRAP, MFItems.BISMUTH_SCRAP.get());
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_RAW_BISMUTH_BLOCK, MFBlocks.RAW_BISMUTH_BLOCK.get().asItem());
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_SCRAP, Items.NETHERITE_SCRAP);
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_INGOT, Items.NETHERITE_INGOT);
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_DIAMOND, Items.DIAMOND);
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_LINGERING_POT, Items.LINGERING_POTION, true);
        this.add(MFItemTags.MAGIC_BLOCK_TURNS_TO_BEDROCK, Items.BEDROCK);
    }
}
