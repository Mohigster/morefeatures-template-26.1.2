package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.id.ModBlockItemIds;
import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).getKey())
                .add(ModItems.BISMUTH_AXE.getKey())
                .add(ModItems.BISMUTH_HOE.getKey())
                .add(ModItems.BISMUTH_SHOVEL.getKey())
                .add(ModItems.BISMUTH_SPEAR.getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).getKey());

        tag(ModItemTags.CARBON_INGREDIENTS)
                .add(ItemIds.STICK)
                .add(ItemIds.COAL)
                .add(BlockItemIds.REDSTONE_DUST.item())
                .add(ItemIds.DIAMOND)
                .addTag(ItemTags.LOGS_THAT_BURN)
                .addTag(ItemTags.LOGS)
                .addTag(ItemTags.SAPLINGS)
                .addTag(ItemTags.PLANKS)
                .addTag(ItemTags.WOOL)
                .addTag(ModItemTags.IS_FOOD);

        tag(ModItemTags.BOW_UPGRADE_ENCHANTABLE)
                .add(ModItems.BISMUTH_BOW.getKey());

        tag(ModItemTags.MELEE_WEAPON_UPGRADE_ENCHANTABLE)
                .add(ModItems.BISMUTH_AXE.getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey());

        tag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(ModItems.BISMUTH_TRIDENT.getKey())
                .add(ModItems.CARBON_TRIDENT.getKey());

        tag(ModItemTags.BLOODWOOD_LOGS)
                .add(ModBlockItemIds.BLOODWOOD.item())
                .add(ModBlockItemIds.BLOODWOOD_LOG.item())
                .add(ModBlockItemIds.STRIPPED_BLOODWOOD.item())
                .add(ModBlockItemIds.STRIPPED_BLOODWOOD_LOG.item());

        tag(ModItemTags.TAINTED_LOGS)
                .add(ModBlockItemIds.TAINTED_LOG.item())
                .add(ModBlockItemIds.TAINTED_WOOD.item())
                .add(ModBlockItemIds.STRIPPED_TAINTED_LOG.item())
                .add(ModBlockItemIds.STRIPPED_TAINTED_WOOD.item());

        tag(ModItemTags.PALM_LOGS)
                .add(ModBlockItemIds.PALM_LOG.item())
                .add(ModBlockItemIds.PALM_WOOD.item())
                .add(ModBlockItemIds.STRIPPED_PALM_LOG.item())
                .add(ModBlockItemIds.STRIPPED_PALM_WOOD.item());

        tag(ModItemTags.DECREPIT_LOGS)
                .add(ModBlockItemIds.DECREPIT_LOG.item())
                .add(ModBlockItemIds.DECREPIT_WOOD.item())
                .add(ModBlockItemIds.STRIPPED_DECREPIT_LOG.item())
                .add(ModBlockItemIds.STRIPPED_DECREPIT_WOOD.item());

        tag(ModItemTags.PALLID_LOGS)
                .add(ModBlockItemIds.PALLID_LOG.item())
                .add(ModBlockItemIds.PALLID_WOOD.item())
                .add(ModBlockItemIds.STRIPPED_PALLID_LOG.item())
                .add(ModBlockItemIds.STRIPPED_PALLID_WOOD.item());

        tag(ModItemTags.IS_FOOD)
                .add(ItemIds.APPLE)
                .add(ItemIds.BAKED_POTATO)
                .add(ItemIds.POISONOUS_POTATO)
                .add(ItemIds.MUSHROOM_STEW)
                .add(ItemIds.SUSPICIOUS_STEW)
                .add(BlockItemIds.PUMPKIN.item())
                .add(BlockItemIds.CAKE.item())
                .add(BlockItemIds.CARVED_PUMPKIN.item())
                .add(ItemIds.BREAD)
                .add(ItemIds.WHEAT)
                .add(ItemIds.COOKIE)
                .add(ItemIds.BEETROOT)
                .add(ItemIds.BEETROOT_SOUP)
                .add(ItemIds.GOLDEN_APPLE)
                .add(ItemIds.ENCHANTED_GOLDEN_APPLE)
                .add(ItemIds.GOLDEN_CARROT)
                .add(ItemIds.MELON_SLICE)
                .add(ItemIds.PUMPKIN_PIE)
                .add(ItemIds.ROTTEN_FLESH)
                .add(ItemIds.SPIDER_EYE)
                .add(ItemIds.TROPICAL_FISH)
                .add(ItemIds.CHICKEN)
                .add(ItemIds.BEEF)
                .add(ItemIds.MUTTON)
                .add(ItemIds.PORKCHOP)
                .add(ItemIds.RABBIT)
                .add(ItemIds.COD)
                .add(ItemIds.SALMON)
                .add(ItemIds.COOKED_CHICKEN)
                .add(ItemIds.COOKED_BEEF)
                .add(ItemIds.COOKED_MUTTON)
                .add(ItemIds.COOKED_PORKCHOP)
                .add(ItemIds.COOKED_RABBIT)
                .add(ItemIds.COOKED_COD)
                .add(ItemIds.COOKED_SALMON);

        tag(ModItemTags.IS_POTION)
                .add(ItemIds.SPLASH_POTION)
                .add(ItemIds.LINGERING_POTION)
                .add(ItemIds.POTION);

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.BISMUTH_BOW.getKey())
                .add(ModItems.CARBON_BOW.getKey());

        tag(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ModItems.CARBON_CROSSBOW.getKey());

        tag(ModItemTags.BISMUTH_TOOL_MATERIALS)
                .add(ModItems.BISMUTH.getKey());

        tag(ModItemTags.IS_GOLD_ARMOR)
                .add(ItemIds.GOLDEN_CHESTPLATE)
                .add(ItemIds.GOLDEN_HELMET)
                .add(ItemIds.GOLDEN_LEGGINGS)
                .add(ItemIds.GOLDEN_BOOTS);

        tag(ModItemTags.IS_NON_GOLD_RAW_METAL)
                .add(ItemIds.RAW_IRON)
                .add(ModItems.RAW_ALUMINIUM.getKey())
                .add(ModItems.RAW_MAGNESIUM.getKey())
                .add(ItemIds.RAW_COPPER);

        tag(ModItemTags.IS_NON_GOLD_METAL_BLOCK)
                .add(ModBlockItemIds.ALUMINIUM_BLOCK.item())
                .add(ModBlockItemIds.MAGNESIUM_BLOCK.item())
                .add(BlockItemIds.RAW_IRON_BLOCK.item())
                .add(ModBlockItemIds.RAW_ALUMINIUM_BLOCK.item())
                .add(ModBlockItemIds.RAW_MAGNESIUM_BLOCK.item())
                .add(BlockItemIds.RAW_COPPER_BLOCK.item());

        tag(ModItemTags.IS_NON_GOLD_INGOT)
                .add(ItemIds.COPPER_INGOT)
                .add(ModItems.MAGNESIUM_INGOT.getKey())
                .add(ModItems.ALUMINIUM_INGOT.getKey())
                .add(ItemIds.IRON_INGOT);

        tag(ModItemTags.IS_GOLD)
                .add(ItemIds.RAW_GOLD)
                .add(ItemIds.GOLD_INGOT);

        tag(ItemTags.SHOVELS)
                .add(ModItems.BISMUTH_SHOVEL.getKey());

        tag(ItemTags.AXES)
                .add(ModItems.BISMUTH_AXE.getKey());

        tag(ItemTags.HOES)
                .add(ModItems.BISMUTH_HOE.getKey());

        tag(ItemTags.PICKAXES)
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).getKey());

        tag(ItemTags.SWORDS)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey());

        tag(ItemTags.SPEARS)
                .add(ModItems.BISMUTH_SPEAR.getKey());

        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(ModItems.BISMUTH_SPEAR.getKey())
                .add(ModItems.BISMUTH_AXE.getKey());

        tag(ItemTags.SWEEPING_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey());

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).getKey())
                .add(ModItems.BISMUTH_AXE.getKey())
                .add(ModItems.BISMUTH_SHOVEL.getKey());

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(1).getKey())
                .add(ModItems.BISMUTH_AXE.getKey())
                .add(ModItems.BISMUTH_HOE.getKey())
                .add(ModItems.BISMUTH_SHOVEL.getKey())
                .add(ModItems.CARBON_CROSSBOW.getKey())
                .add(ModItems.CARBON_BOW.getKey())
                .add(ModItems.BISMUTH_BOW.getKey())
                .add(ModItems.BISMUTH_SPEAR.getKey())
                .add(ModItems.CARBON_ELYTRA.getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).getKey());

        tag(ItemTags.LUNGE_ENCHANTABLE)
                .add(ModItems.BISMUTH_SPEAR.getKey());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(ModItems.BISMUTH_SPEAR.getKey())
                .add(ModItems.BISMUTH_AXE.getKey());

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(ModItems.BISMUTH_SPEAR.getKey())
                .add(ModItems.BISMUTH_AXE.getKey());

        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(ModItems.BISMUTH_SPEAR.getKey());

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).getKey())
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).getKey());

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).getKey());

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).getKey());

        tag(ItemTags.LEG_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).getKey());

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).getKey());

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(2).getKey());

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(3).getKey());

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(4).getKey());

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(ModItems.BISMUTH_EQUIPMENT.get(5).getKey());

        tag(ItemTags.SAPLINGS)
                .add(ModBlockItemIds.TAINTED_SAPLING.item())
                .add(ModBlockItemIds.BLOODWOOD_SAPLING.item())
                .add(ModBlockItemIds.PALM_SAPLING.item())
                .add(ModBlockItemIds.DECREPIT_SAPLING.item())
                .add(ModBlockItemIds.PALLID_SAPLING.item());

        tag(ModItemTags.COMPRESSOR_FUEL)
                .add(ModItems.AZURITE.getKey())
                .add(ModItems.FLUORITE.getKey())
                .add(ModItems.EVERFROST.getKey());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlockItemIds.TAINTED_WOOD.item())
                .add(ModBlockItemIds.TAINTED_LOG.item())
                .add(ModBlockItemIds.STRIPPED_TAINTED_WOOD.item())
                .add(ModBlockItemIds.STRIPPED_TAINTED_LOG.item())
                .add(ModBlockItemIds.BLOODWOOD.item())
                .add(ModBlockItemIds.BLOODWOOD_LOG.item())
                .add(ModBlockItemIds.STRIPPED_BLOODWOOD.item())
                .add(ModBlockItemIds.STRIPPED_BLOODWOOD_LOG.item());

        tag(ItemTags.PLANKS)
                .add(ModBlockItemIds.TAINTED_PLANKS.item())
                .add(ModBlockItemIds.BLOODWOOD_PLANKS.item())
                .add(ModBlockItemIds.PALM_PLANKS.item())
                .add(ModBlockItemIds.DECREPIT_PLANKS.item())
                .add(ModBlockItemIds.PALLID_PLANKS.item());

        tag(ModItemTags.MUSIC_DISCS)
                .addTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ItemIds.MUSIC_DISC_PIGSTEP)
                .add(ItemIds.MUSIC_DISC_OTHERSIDE)
                .add(ItemIds.MUSIC_DISC_PRECIPICE)
                .add(ItemIds.MUSIC_DISC_5)
                .add(ItemIds.MUSIC_DISC_CREATOR)
                .add(ItemIds.MUSIC_DISC_CREATOR_MUSIC_BOX)
                .add(ItemIds.MUSIC_DISC_RELIC)
                .add(ItemIds.MUSIC_DISC_TEARS)
                .add(ItemIds.MUSIC_DISC_LAVA_CHICKEN);

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(ModItems.BISMUTH.getKey())
                .add(ModItems.ALUMINIUM_INGOT.getKey())
                .add(ModItems.MAGNESIUM_INGOT.getKey())
                .add(ModItems.AZURITE.getKey())
                .add(ModItems.FLUORITE.getKey());

        tag(ModItemTags.AIMABLE_WANDS)
                .add(ModItems.FIRE_WAND.getKey());

        tag(ModItemTags.TARGETING_WANDS)
                .add(ModItems.ICE_WAND.getKey());
    }
}
