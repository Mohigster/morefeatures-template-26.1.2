package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(6).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(7).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(8).asItem());

        tag(ItemTags.SHOVELS)
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem());

        tag(ItemTags.AXES)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem());

        tag(ItemTags.HOES)
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem());

        tag(ItemTags.PICKAXES)
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).asItem());

        tag(ItemTags.SWORDS)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem());
    }
}
