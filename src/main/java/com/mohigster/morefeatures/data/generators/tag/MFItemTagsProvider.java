package com.mohigster.morefeatures.data.generators.tag;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.resources.references.MFBlockItemIds;
import com.mohigster.morefeatures.data.resources.references.MFItemIds;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.tag.MFBlockItemTags;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class MFItemTagsProvider extends ItemTagsProvider {

    public MFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider provider) {
        new MFBlockItemTagsProvider(tagId -> BlockItemTagsProvider.wrapForItems(this.tag(tagId.item()))).run();

        this.tag(MFItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE)
                .add(MFItemIds.BISMUTH_SWORD)
                .add(MFItemIds.BISMUTH_PICKAXE)
                .add(MFItemIds.BISMUTH_AXE)
                .add(MFItemIds.BISMUTH_HOE)
                .add(MFItemIds.BISMUTH_SHOVEL)
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_HELMET)
                .add(MFItemIds.BISMUTH_CHESTPLATE)
                .add(MFItemIds.BISMUTH_LEGGINGS)
                .add(MFItemIds.BISMUTH_BOOTS);

        this.tag(MFItemTags.CARBON_INGREDIENTS)
                .add(ItemIds.STICK)
                .add(ItemIds.COAL)
                .add(BlockItemIds.REDSTONE_DUST.item())
                .add(ItemIds.DIAMOND)
                .addTag(ItemTags.LOGS_THAT_BURN)
                .addTag(ItemTags.LOGS)
                .addTag(ItemTags.SAPLINGS)
                .addTag(ItemTags.PLANKS)
                .addTag(ItemTags.WOOL)
                .addTag(MFItemTags.IS_FOOD);

        this.tag(MFItemTags.THUNDERBOLT_ENCHANTABLE)
                .add(MFItems.BISMUTH_BOW.getKey());

        this.tag(MFItemTags.THUNDERING_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_AXE)
                .add(MFItemIds.BISMUTH_SWORD);

        this.tag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_TRIDENT)
                .add(MFItemIds.CARBON_TRIDENT);

        this.tag(MFItemTags.IS_FOOD)
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
                .add(ItemIds.COOKED_SALMON)
                .add(BlockItemIds.SWEET_BERRY_CROP.item());

        this.tag(MFItemTags.IS_POTION)
                .add(ItemIds.SPLASH_POTION)
                .add(ItemIds.LINGERING_POTION)
                .add(ItemIds.POTION);

        this.tag(ItemTags.BOW_ENCHANTABLE)
                .add(MFItems.BISMUTH_BOW.getKey())
                .add(MFItems.CARBON_BOW.getKey());

        this.tag(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(MFItems.CARBON_CROSSBOW.getKey());

        this.tag(MFItemTags.BISMUTH_TOOL_MATERIALS)
                .add(MFItemIds.BISMUTH);

        this.tag(MFItemTags.IS_GOLD_ARMOR)
                .add(ItemIds.GOLDEN_CHESTPLATE)
                .add(ItemIds.GOLDEN_HELMET)
                .add(ItemIds.GOLDEN_LEGGINGS)
                .add(ItemIds.GOLDEN_BOOTS);

        this.tag(MFItemTags.IS_NON_GOLD_RAW_METAL)
                .add(ItemIds.RAW_IRON)
                .add(MFItemIds.RAW_ALUMINIUM)
                .add(MFItemIds.RAW_MAGNESIUM)
                .add(ItemIds.RAW_COPPER);

        this.tag(MFItemTags.IS_NON_GOLD_METAL_BLOCK)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.item())
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.item())
                .add(BlockItemIds.RAW_IRON_BLOCK.item())
                .add(MFBlockItemIds.RAW_ALUMINIUM_BLOCK.item())
                .add(MFBlockItemIds.RAW_MAGNESIUM_BLOCK.item())
                .add(BlockItemIds.RAW_COPPER_BLOCK.item());

        this.tag(MFItemTags.IS_NON_GOLD_INGOT)
                .add(ItemIds.COPPER_INGOT)
                .add(MFItemIds.MAGNESIUM_INGOT)
                .add(MFItemIds.ALUMINIUM_INGOT)
                .add(ItemIds.IRON_INGOT);

        this.tag(MFItemTags.IS_GOLD)
                .add(ItemIds.RAW_GOLD)
                .add(ItemIds.GOLD_INGOT);

        this.tag(ItemTags.SHOVELS)
                .add(MFItems.BISMUTH_SHOVEL.getKey());

        this.tag(ItemTags.AXES)
                .add(MFItems.BISMUTH_AXE.getKey());

        this.tag(ItemTags.HOES)
                .add(MFItems.BISMUTH_HOE.getKey());

        this.tag(ItemTags.PICKAXES)
                .add(MFItemIds.BISMUTH_PICKAXE);

        this.tag(ItemTags.SWORDS)
                .add(MFItemIds.BISMUTH_SWORD);

        this.tag(ItemTags.SPEARS)
                .add(MFItemIds.BISMUTH_SPEAR);

        this.tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SWORD)
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_AXE);

        this.tag(ItemTags.SWEEPING_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SWORD);

        this.tag(ItemTags.MINING_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_PICKAXE)
                .add(MFItemIds.BISMUTH_AXE)
                .add(MFItemIds.BISMUTH_SHOVEL);

        this.tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SWORD)
                .add(MFItemIds.BISMUTH_PICKAXE)
                .add(MFItemIds.BISMUTH_AXE)
                .add(MFItemIds.BISMUTH_HOE)
                .add(MFItemIds.BISMUTH_SHOVEL)
                .add(MFItems.CARBON_CROSSBOW.getKey())
                .add(MFItems.CARBON_BOW.getKey())
                .add(MFItemIds.BISMUTH_BOW)
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItems.CARBON_ELYTRA.getKey())
                .add(MFItemIds.BISMUTH_HELMET)
                .add(MFItemIds.BISMUTH_CHESTPLATE)
                .add(MFItemIds.BISMUTH_LEGGINGS)
                .add(MFItemIds.BISMUTH_BOOTS);

        this.tag(ItemTags.LUNGE_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SPEAR);

        this.tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SWORD)
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_AXE);

        this.tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SWORD)
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_AXE);

        this.tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_SWORD)
                .add(MFItemIds.BISMUTH_SPEAR);

        this.tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_HELMET)
                .add(MFItemIds.BISMUTH_CHESTPLATE)
                .add(MFItemIds.BISMUTH_LEGGINGS)
                .add(MFItemIds.BISMUTH_BOOTS);

        this.tag(ItemTags.HEAD_ARMOR)
                .add(MFItemIds.BISMUTH_HELMET);

        this.tag(ItemTags.CHEST_ARMOR)
                .add(MFItemIds.BISMUTH_CHESTPLATE);

        this.tag(ItemTags.LEG_ARMOR)
                .add(MFItemIds.BISMUTH_LEGGINGS);

        this.tag(ItemTags.FOOT_ARMOR)
                .add(MFItemIds.BISMUTH_BOOTS);

        this.tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_HELMET);

        this.tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_CHESTPLATE);

        this.tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_LEGGINGS);

        this.tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_BOOTS);

        this.tag(ItemTags.SAPLINGS)
                .add(MFBlockItemIds.TAINTED_SAPLING.item())
                .add(MFBlockItemIds.BLOODWOOD_SAPLING.item())
                .add(MFBlockItemIds.PALM_SAPLING.item())
                .add(MFBlockItemIds.DECREPIT_SAPLING.item())
                .add(MFBlockItemIds.PALLID_SAPLING.item());

        this.tag(MFItemTags.COMPRESSOR_FUEL)
                .add(MFItemIds.AZURITE)
                .add(MFItemIds.FLUORITE)
                .add(MFItemIds.EVERFROST);

        this.tag(ItemTags.LOGS_THAT_BURN)
                .addTag(MFBlockItemTags.LOGS.bloodwood().item())
                .addTag(MFBlockItemTags.LOGS.tainted().item())
                .addTag(MFBlockItemTags.LOGS.palm().item())
                .addTag(MFBlockItemTags.LOGS.decrepit().item())
                .addTag(MFBlockItemTags.LOGS.pallid().item());

        this.tag(ItemTags.LOGS).addTag(MFItemTags.MODDED_LOGS);

        this.tag(ItemTags.PLANKS)
                .add(MFBlockItemIds.PLANKS.bloodwood().item())
                .add(MFBlockItemIds.PLANKS.tainted().item())
                .add(MFBlockItemIds.PLANKS.palm().item())
                .add(MFBlockItemIds.PLANKS.decrepit().item())
                .add(MFBlockItemIds.PLANKS.pallid().item());

        this.tag(MFItemTags.MUSIC_DISCS)
                .addTag(ItemTags.CREEPER_DROP_MUSIC_DISCS)
                .add(ItemIds.MUSIC_DISC_PIGSTEP)
                .add(ItemIds.MUSIC_DISC_OTHERSIDE)
                .add(ItemIds.MUSIC_DISC_PRECIPICE)
                .add(ItemIds.MUSIC_DISC_5)
                .add(ItemIds.MUSIC_DISC_CREATOR)
                .add(ItemIds.MUSIC_DISC_CREATOR_MUSIC_BOX)
                .add(ItemIds.MUSIC_DISC_RELIC)
                .add(ItemIds.MUSIC_DISC_TEARS)
                .add(ItemIds.MUSIC_DISC_LAVA_CHICKEN)
                .add(ItemIds.MUSIC_DISC_BOUNCE)
                .add(MFItemIds.MUSIC_DISC_AQUAMARINE);

        this.tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(MFItemIds.BISMUTH)
                .add(MFItemIds.ALUMINIUM_INGOT)
                .add(MFItemIds.MAGNESIUM_INGOT)
                .add(MFItemIds.AZURITE)
                .add(MFItemIds.FLUORITE);

        this.tag(MFItemTags.AIMABLE_WANDS)
                .add(MFItems.FIRE_WAND.getKey());

        this.tag(MFItemTags.TARGETING_ENTITY_WANDS)
                .add(MFItemIds.ICE_WAND)
                .add(MFItemIds.HEALING_WAND);

        this.tag(MFItemTags.AOE_WANDS)
                .add(MFItemIds.LIGHTNING_WAND)
                .add(MFItemIds.EARTH_WAND);

        this.tag(MFItemTags.TARGETING_BLOCK_WANDS)
                .add(MFItemIds.TIME_WAND);

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_CARBON)
                .add(ItemIds.COAL)
                .addTag(ItemTags.SAPLINGS)
                .addTag(ItemTags.LOGS)
                .addTag(ItemTags.LOGS_THAT_BURN)
                .addTag(ItemTags.WOOL)
                .addTag(MFItemTags.WOODEN_VERTICAL_SLABS)
                .addTag(ItemTags.WOODEN_SLABS)
                .add(BlockItemIds.BAMBOO_MOSAIC_SLAB.item())
                .addTag(ItemTags.WOODEN_STAIRS)
                .add(BlockItemIds.BAMBOO_MOSAIC_STAIRS.item())
                .addTag(MFBlockItemTags.WOOL_VERTICAL_SLABS.item())
                .addTag(ItemTags.PLANKS)
                .addTag(MFItemTags.IS_FOOD)
                .add(ItemIds.STICK)
                .add(BlockItemIds.REDSTONE_DUST.item());

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_BISMUTH_SCRAP)
                .addTag(MFItemTags.IS_NON_GOLD_RAW_METAL)
                .addTag(MFItemTags.IS_NON_GOLD_INGOT);

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_RAW_BISMUTH_BLOCK)
                .addTag(MFItemTags.IS_NON_GOLD_METAL_BLOCK);

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_SCRAP)
                .addTag(ItemTags.GOLD_TOOL_MATERIALS)
                .addTag(ItemTags.GOLD_ORES)
                .addTag(MFItemTags.IS_GOLD)
                .addTag(MFItemTags.IS_GOLD_ARMOR);

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_INGOT)
                .add(BlockItemIds.RAW_GOLD_BLOCK.item())
                .add(BlockItemIds.GOLD_BLOCK.item());

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_DIAMOND)
                .add(ItemIds.EMERALD)
                .add(ItemIds.AMETHYST_SHARD)
                .add(MFItems.AZURITE.getKey())
                .add(MFItems.FLUORITE.getKey());

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_STONE)
                .add(BlockItemIds.GRANITE.item())
                .add(BlockItemIds.DIORITE.item())
                .add(BlockItemIds.ANDESITE.item());

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_LINGERING_POT)
                .add(ItemIds.POTION)
                .add(ItemIds.SPLASH_POTION);

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_BEDROCK)
                .add(MFBlockItemIds.MAGIC_BLOCK.item());

        this.tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_AQUAMARINE_DISC)
                .add(ItemIds.MUSIC_DISC_BOUNCE);

        this.tag(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULTS)
                .add(MFItems.CARBON_FIBER.getKey())
                .add(MFItemIds.BISMUTH_SCRAP)
                .add(MFBlockItemIds.RAW_BISMUTH_BLOCK.item())
                .add(ItemIds.NETHERITE_SCRAP)
                .add(ItemIds.NETHERITE_INGOT)
                .add(ItemIds.DIAMOND)
                .add(BlockItemIds.STONE.item())
                .add(ItemIds.LINGERING_POTION)
                .add(MFItemIds.MUSIC_DISC_AQUAMARINE)
                .add(BlockItemIds.BEDROCK.item());

        this.tag(MFItemTags.METAL_DETECTOR_FINDABLE)
                .add(MFBlockItemIds.ALUMINIUM_ORE.item())
                .add(MFBlockItemIds.MAGNESIUM_ORE.item())
                .add(MFBlockItemIds.DEEPSLATE_ALUMINIUM_ORE.item())
                .add(MFBlockItemIds.DEEPSLATE_MAGNESIUM_ORE.item())
                .add(MFBlockItemIds.BISMUTH_ORE.item())
                .add(BlockItemIds.IRON_ORE.item())
                .add(BlockItemIds.COPPER_ORE.item())
                .add(BlockItemIds.GOLD_ORE.item())
                .add(BlockItemIds.DEEPSLATE_IRON_ORE.item())
                .add(BlockItemIds.DEEPSLATE_COPPER_ORE.item())
                .add(BlockItemIds.DEEPSLATE_GOLD_ORE.item())
                .add(BlockItemIds.ANCIENT_DEBRIS.item())
                .add(BlockItemIds.NETHER_GOLD_ORE.item());

        this.tag(MFItemTags.WOODEN_VERTICAL_SLABS)
                .add(MFBlockItemIds.OAK_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.SPRUCE_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.BIRCH_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.JUNGLE_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.ACACIA_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.DARK_OAK_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.CRIMSON_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.WARPED_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.MANGROVE_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.CHERRY_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.BAMBOO_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.BAMBOO_MOSAIC_VERTICAL_SLAB.item())
                .add(MFBlockItemIds.PALE_OAK_VERTICAL_SLAB.item())
                .addTag(MFBlockItemTags.CUSTOM_WOODEN_VERTICAL_SLABS.item());

        this.tag(ItemTags.WOODEN_STAIRS)
                .addTag(MFBlockItemTags.WOODEN_STAIRS.item());

        this.tag(ItemTags.WOODEN_SLABS)
                .addTag(MFBlockItemTags.WOODEN_SLABS.item());

        this.tag(ItemTags.TRIM_MATERIALS)
                .add(MFItemIds.ALUMINIUM_INGOT)
                .add(MFItemIds.MAGNESIUM_INGOT)
                .add(MFItemIds.AZURITE)
                .add(MFItemIds.FLUORITE)
                .add(MFItemIds.CARBON_FIBER)
                .add(MFItemIds.BISMUTH);

        this.tag(MFItemTags.MAGIC_BLOCK_MULTIPLIES_RESULT)
                .addTag(ItemTags.PLANKS);
    }
}
