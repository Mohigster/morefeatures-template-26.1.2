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
import net.minecraft.world.item.Items;
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
                .add(ModItems.BISMUTH_SPEAR.get())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ModItemTags.IS_FOOD)
                .add(Items.APPLE.asItem())
                .add(Items.BAKED_POTATO.asItem())
                .add(Items.POTATO.asItem())
                .add(Items.POISONOUS_POTATO.asItem())
                .add(Items.MUSHROOM_STEW.asItem())
                .add(Items.SUSPICIOUS_STEW.asItem())
                .add(Items.BREAD.asItem())
                .add(Items.CAKE.asItem())
                .add(Items.WHEAT.asItem())
                .add(Items.CARROT.asItem())
                .add(Items.COOKIE.asItem())
                .add(Items.BEETROOT.asItem())
                .add(Items.BEETROOT_SOUP.asItem())
                .add(Items.GOLDEN_APPLE.asItem())
                .add(Items.ENCHANTED_GOLDEN_APPLE.asItem())
                .add(Items.GOLDEN_CARROT.asItem())
                .add(Items.GLOW_BERRIES.asItem())
                .add(Items.MELON_SLICE.asItem())
                .add(Items.MELON.asItem())
                .add(Items.PUMPKIN.asItem())
                .add(Items.PUMPKIN_PIE.asItem())
                .add(Items.CARVED_PUMPKIN.asItem())
                .add(Items.ROTTEN_FLESH.asItem())
                .add(Items.SWEET_BERRIES.asItem())
                .add(Items.SPIDER_EYE.asItem())
                .add(Items.TROPICAL_FISH.asItem())
                .add(Items.CHICKEN.asItem())
                .add(Items.BEEF.asItem())
                .add(Items.MUTTON.asItem())
                .add(Items.PORKCHOP.asItem())
                .add(Items.RABBIT.asItem())
                .add(Items.COD.asItem())
                .add(Items.SALMON.asItem())
                .add(Items.COOKED_CHICKEN.asItem())
                .add(Items.COOKED_BEEF.asItem())
                .add(Items.COOKED_MUTTON.asItem())
                .add(Items.COOKED_PORKCHOP.asItem())
                .add(Items.COOKED_RABBIT.asItem())
                .add(Items.COOKED_COD.asItem())
                .add(Items.COOKED_SALMON.asItem());

        tag(ModItemTags.IS_POTION)
                .add(Items.SPLASH_POTION)
                .add(Items.LINGERING_POTION)
                .add(Items.POTION);

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.CARBON_BOW.get());

        tag(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ModItems.CARBON_CROSSBOW.get());

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

        tag(ItemTags.SPEARS)
                .add(ModItems.BISMUTH_SPEAR.get());

        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_SPEAR.get())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.SWEEPING_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem());

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
                .add(ModItems.CARBON_CROSSBOW.get())
                .add(ModItems.CARBON_BOW.get())
                .add(ModItems.BISMUTH_SPEAR.get())
                .add(ModItems.CARBON_ELYTRA.get())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).asItem())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());

        tag(ItemTags.LUNGE_ENCHANTABLE)
                .add(ModItems.BISMUTH_SPEAR.get());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_SPEAR.get())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_SPEAR.get())
                .add(ModItems.BISMUTH_AXE.get());

        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())
                .add(ModItems.BISMUTH_SPEAR.get());

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

        tag(ItemTags.PLANKS)
                .add(ModBlocks.BLOODWOOD_PLANKS.get().asItem());
    }
}
