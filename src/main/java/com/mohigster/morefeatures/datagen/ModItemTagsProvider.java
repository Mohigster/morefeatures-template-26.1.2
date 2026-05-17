package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
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
                .add(ModItems.BISMUTH_AXE.get())
                .add(ModItems.BISMUTH_HOE.get())
                .add(ModItems.BISMUTH_SHOVEL.get())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ModItemTags.BISMUTH_TOOL_MATERIALS)
                .add(ModItems.BISMUTH.get());

        tag(ItemTags.SHOVELS)
                .add(ModItems.BISMUTH_SHOVEL.get());

        tag(ItemTags.AXES)
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.HOES)
                .add(ModItems.BISMUTH_HOE.get());

        tag(ItemTags.PICKAXES)
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).asItem());

        tag(ItemTags.SWORDS)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem());

        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).asItem())
                .add(ModItems.BISMUTH_AXE.get())
                .add(ModItems.BISMUTH_SHOVEL.get());

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).asItem())
                .add(ModItems.BISMUTH_AXE.get())
                .add(ModItems.BISMUTH_HOE.get())
                .add(ModItems.BISMUTH_SHOVEL.get())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem());

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem());

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem());

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ItemTags.SAPLINGS)
                .add(ModBlocks.BLOODWOOD_SAPLING.get().asItem());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.BLOODWOOD.get().asItem())
                .add(ModBlocks.BLOODWOOD_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_BLOODWOOD.get().asItem())
                .add(ModBlocks.STRIPPED_BLOODWOOD_LOG.get().asItem());
    }
}
