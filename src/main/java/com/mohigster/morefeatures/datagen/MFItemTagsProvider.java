package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.references.MFBlockItemIds;
import com.mohigster.morefeatures.references.MFItemIds;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.tag.MFBlockItemTags;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class MFItemTagsProvider extends ItemTagsProvider {

    public MFItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, MoreFeatures.MODID);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        new MFBlockItemTagsProvider(tagId -> BlockItemTagsProvider.wrapForItems(this.tag(tagId.item()))).run();

        tag(MFItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(MFItems.BISMUTH_EQUIPMENT.get(1).getKey())
                .add(MFItemIds.BISMUTH_AXE)
                .add(MFItemIds.BISMUTH_HOE)
                .add(MFItemIds.BISMUTH_SHOVEL)
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_HELMET)
                .add(MFItemIds.BISMUTH_CHESTPLATE)
                .add(MFItemIds.BISMUTH_LEGGINGS)
                .add(MFItemIds.BISMUTH_BOOTS);

        tag(MFItemTags.CARBON_INGREDIENTS)
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

        tag(MFItemTags.THUNDERBOLT_ENCHANTABLE)
                .add(MFItems.BISMUTH_BOW.getKey());

        tag(MFItemTags.THUNDERING_ENCHANTABLE)
                .add(MFItems.BISMUTH_AXE.getKey())
                .add(MFItems.BISMUTH_EQUIPMENT.getFirst().getKey());

        tag(ItemTags.TRIDENT_ENCHANTABLE)
                .add(MFItems.BISMUTH_TRIDENT.getKey())
                .add(MFItems.CARBON_TRIDENT.getKey());

        tag(MFItemTags.BLOODWOOD_LOGS)
                .add(MFBlockItemIds.BLOODWOOD.item())
                .add(MFBlockItemIds.BLOODWOOD_LOG.item())
                .add(MFBlockItemIds.STRIPPED_BLOODWOOD.item())
                .add(MFBlockItemIds.STRIPPED_BLOODWOOD_LOG.item());

        tag(MFItemTags.TAINTED_LOGS)
                .add(MFBlockItemIds.TAINTED_LOG.item())
                .add(MFBlockItemIds.TAINTED_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_TAINTED_LOG.item())
                .add(MFBlockItemIds.STRIPPED_TAINTED_WOOD.item());

        tag(MFItemTags.PALM_LOGS)
                .add(MFBlockItemIds.PALM_LOG.item())
                .add(MFBlockItemIds.PALM_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_PALM_LOG.item())
                .add(MFBlockItemIds.STRIPPED_PALM_WOOD.item());

        tag(MFItemTags.DECREPIT_LOGS)
                .add(MFBlockItemIds.DECREPIT_LOG.item())
                .add(MFBlockItemIds.DECREPIT_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_DECREPIT_LOG.item())
                .add(MFBlockItemIds.STRIPPED_DECREPIT_WOOD.item());

        tag(MFItemTags.PALLID_LOGS)
                .add(MFBlockItemIds.PALLID_LOG.item())
                .add(MFBlockItemIds.PALLID_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_PALLID_LOG.item())
                .add(MFBlockItemIds.STRIPPED_PALLID_WOOD.item());

        tag(MFItemTags.IS_FOOD)
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

        tag(MFItemTags.IS_POTION)
                .add(ItemIds.SPLASH_POTION)
                .add(ItemIds.LINGERING_POTION)
                .add(ItemIds.POTION);

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(MFItems.BISMUTH_BOW.getKey())
                .add(MFItems.CARBON_BOW.getKey());

        tag(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(MFItems.CARBON_CROSSBOW.getKey());

        tag(MFItemTags.BISMUTH_TOOL_MATERIALS)
                .add(MFItems.BISMUTH.getKey());

        tag(MFItemTags.IS_GOLD_ARMOR)
                .add(ItemIds.GOLDEN_CHESTPLATE)
                .add(ItemIds.GOLDEN_HELMET)
                .add(ItemIds.GOLDEN_LEGGINGS)
                .add(ItemIds.GOLDEN_BOOTS);

        tag(MFItemTags.IS_NON_GOLD_RAW_METAL)
                .add(ItemIds.RAW_IRON)
                .add(MFItemIds.RAW_ALUMINIUM)
                .add(MFItemIds.RAW_MAGNESIUM)
                .add(ItemIds.RAW_COPPER);

        tag(MFItemTags.IS_NON_GOLD_METAL_BLOCK)
                .add(MFBlockItemIds.ALUMINIUM_BLOCK.item())
                .add(MFBlockItemIds.MAGNESIUM_BLOCK.item())
                .add(BlockItemIds.RAW_IRON_BLOCK.item())
                .add(MFBlockItemIds.RAW_ALUMINIUM_BLOCK.item())
                .add(MFBlockItemIds.RAW_MAGNESIUM_BLOCK.item())
                .add(BlockItemIds.RAW_COPPER_BLOCK.item());

        tag(MFItemTags.IS_NON_GOLD_INGOT)
                .add(ItemIds.COPPER_INGOT)
                .add(MFItemIds.MAGNESIUM_INGOT)
                .add(MFItemIds.ALUMINIUM_INGOT)
                .add(ItemIds.IRON_INGOT);

        tag(MFItemTags.IS_GOLD)
                .add(ItemIds.RAW_GOLD)
                .add(ItemIds.GOLD_INGOT);

        tag(ItemTags.SHOVELS)
                .add(MFItems.BISMUTH_SHOVEL.getKey());

        tag(ItemTags.AXES)
                .add(MFItems.BISMUTH_AXE.getKey());

        tag(ItemTags.HOES)
                .add(MFItems.BISMUTH_HOE.getKey());

        tag(ItemTags.PICKAXES)
                .add(MFItems.BISMUTH_EQUIPMENT.get(1).getKey());

        tag(ItemTags.SWORDS)
                .add(MFItems.BISMUTH_EQUIPMENT.getFirst().getKey());

        tag(ItemTags.SPEARS)
                .add(MFItems.BISMUTH_SPEAR.getKey());

        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.getFirst().getKey())
                .add(MFItems.BISMUTH_SPEAR.getKey())
                .add(MFItems.BISMUTH_AXE.getKey());

        tag(ItemTags.SWEEPING_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.getFirst().getKey());

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.get(1).getKey())
                .add(MFItems.BISMUTH_AXE.getKey())
                .add(MFItems.BISMUTH_SHOVEL.getKey());

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(MFItems.BISMUTH_EQUIPMENT.get(1).getKey())
                .add(MFItems.BISMUTH_AXE.getKey())
                .add(MFItems.BISMUTH_HOE.getKey())
                .add(MFItems.BISMUTH_SHOVEL.getKey())
                .add(MFItems.CARBON_CROSSBOW.getKey())
                .add(MFItems.CARBON_BOW.getKey())
                .add(MFItems.BISMUTH_BOW.getKey())
                .add(MFItems.BISMUTH_SPEAR.getKey())
                .add(MFItems.CARBON_ELYTRA.getKey())
                .add(MFItemIds.BISMUTH_HELMET)
                .add(MFItemIds.BISMUTH_CHESTPLATE)
                .add(MFItemIds.BISMUTH_LEGGINGS)
                .add(MFItemIds.BISMUTH_BOOTS);

        tag(ItemTags.LUNGE_ENCHANTABLE)
                .add(MFItems.BISMUTH_SPEAR.getKey());

        tag(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_AXE);

        tag(ItemTags.WEAPON_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(MFItemIds.BISMUTH_SPEAR)
                .add(MFItemIds.BISMUTH_AXE);

        tag(ItemTags.FIRE_ASPECT_ENCHANTABLE)
                .add(MFItems.BISMUTH_EQUIPMENT.get(0).getKey())
                .add(MFItemIds.BISMUTH_SPEAR);

        tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_HELMET)
                .add(MFItemIds.BISMUTH_CHESTPLATE)
                .add(MFItemIds.BISMUTH_LEGGINGS)
                .add(MFItemIds.BISMUTH_BOOTS);

        tag(ItemTags.HEAD_ARMOR)
                .add(MFItemIds.BISMUTH_HELMET);

        tag(ItemTags.CHEST_ARMOR)
                .add(MFItemIds.BISMUTH_CHESTPLATE);

        tag(ItemTags.LEG_ARMOR)
                .add(MFItemIds.BISMUTH_LEGGINGS);

        tag(ItemTags.FOOT_ARMOR)
                .add(MFItemIds.BISMUTH_BOOTS);

        tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_HELMET);

        tag(ItemTags.CHEST_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_CHESTPLATE);

        tag(ItemTags.LEG_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_LEGGINGS);

        tag(ItemTags.FOOT_ARMOR_ENCHANTABLE)
                .add(MFItemIds.BISMUTH_BOOTS);

        tag(ItemTags.SAPLINGS)
                .add(MFBlockItemIds.TAINTED_SAPLING.item())
                .add(MFBlockItemIds.BLOODWOOD_SAPLING.item())
                .add(MFBlockItemIds.PALM_SAPLING.item())
                .add(MFBlockItemIds.DECREPIT_SAPLING.item())
                .add(MFBlockItemIds.PALLID_SAPLING.item());

        tag(MFItemTags.COMPRESSOR_FUEL)
                .add(MFItems.AZURITE.getKey())
                .add(MFItems.FLUORITE.getKey())
                .add(MFItems.EVERFROST.getKey());

        tag(ItemTags.LOGS_THAT_BURN)
                .add(MFBlockItemIds.TAINTED_WOOD.item())
                .add(MFBlockItemIds.TAINTED_LOG.item())
                .add(MFBlockItemIds.STRIPPED_TAINTED_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_TAINTED_LOG.item())
                .add(MFBlockItemIds.BLOODWOOD.item())
                .add(MFBlockItemIds.BLOODWOOD_LOG.item())
                .add(MFBlockItemIds.STRIPPED_BLOODWOOD.item())
                .add(MFBlockItemIds.STRIPPED_BLOODWOOD_LOG.item())
                .add(MFBlockItemIds.PALM_WOOD.item())
                .add(MFBlockItemIds.PALM_LOG.item())
                .add(MFBlockItemIds.STRIPPED_PALM_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_PALM_LOG.item())
                .add(MFBlockItemIds.DECREPIT_WOOD.item())
                .add(MFBlockItemIds.DECREPIT_LOG.item())
                .add(MFBlockItemIds.STRIPPED_DECREPIT_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_DECREPIT_LOG.item())
                .add(MFBlockItemIds.PALLID_WOOD.item())
                .add(MFBlockItemIds.PALLID_LOG.item())
                .add(MFBlockItemIds.STRIPPED_PALLID_WOOD.item())
                .add(MFBlockItemIds.STRIPPED_PALLID_LOG.item());

        tag(ItemTags.PLANKS)
                .add(MFBlockItemIds.TAINTED_PLANKS.item())
                .add(MFBlockItemIds.BLOODWOOD_PLANKS.item())
                .add(MFBlockItemIds.PALM_PLANKS.item())
                .add(MFBlockItemIds.DECREPIT_PLANKS.item())
                .add(MFBlockItemIds.PALLID_PLANKS.item());

        tag(MFItemTags.MUSIC_DISCS)
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

        tag(ItemTags.BEACON_PAYMENT_ITEMS)
                .add(MFItems.BISMUTH.getKey())
                .add(MFItems.ALUMINIUM_INGOT.getKey())
                .add(MFItems.MAGNESIUM_INGOT.getKey())
                .add(MFItems.AZURITE.getKey())
                .add(MFItems.FLUORITE.getKey());

        tag(MFItemTags.AIMABLE_WANDS)
                .add(MFItems.FIRE_WAND.getKey());

        tag(MFItemTags.TARGETING_WANDS)
                .add(MFItems.ICE_WAND.getKey());

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_CARBON)
                .add(ItemIds.COAL)
                .addTag(ItemTags.SAPLINGS)
                .addTag(ItemTags.LOGS)
                .addTag(ItemTags.LOGS_THAT_BURN)
                .addTag(ItemTags.WOOL)
                .addTag(MFBlockItemTags.WOOL_VERTICAL_SLABS.item())
                .addTag(ItemTags.PLANKS)
                .addTag(MFItemTags.IS_FOOD)
                .add(ItemIds.STICK)
                .add(BlockItemIds.REDSTONE_DUST.item());

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_BISMUTH_SCRAP)
                .addTag(MFItemTags.IS_NON_GOLD_RAW_METAL)
                .addTag(MFItemTags.IS_NON_GOLD_INGOT);

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_RAW_BISMUTH_BLOCK)
                .addTag(MFItemTags.IS_NON_GOLD_METAL_BLOCK);

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_SCRAP)
                .addTag(ItemTags.GOLD_TOOL_MATERIALS)
                .addTag(ItemTags.GOLD_ORES)
                .addTag(MFItemTags.IS_GOLD)
                .addTag(MFItemTags.IS_GOLD_ARMOR);

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_NETHERITE_INGOT)
                .add(BlockItemIds.RAW_GOLD_BLOCK.item())
                .add(BlockItemIds.GOLD_BLOCK.item());

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_DIAMOND)
                .add(ItemIds.EMERALD)
                .add(ItemIds.AMETHYST_SHARD)
                .add(MFItems.AZURITE.getKey())
                .add(MFItems.FLUORITE.getKey());

        tag(MFItemTags.MAGIC_BLOCK_TURNS_TO_LINGERING_POT)
                .add(ItemIds.POTION)
                .add(ItemIds.SPLASH_POTION);

        tag(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULT)
                .add(MFItems.CARBON_FIBER.getKey())
                .add(MFItems.BISMUTH_SCRAP.getKey())
                .add(MFBlockItemIds.RAW_BISMUTH_BLOCK.item())
                .add(ItemIds.NETHERITE_SCRAP)
                .add(ItemIds.NETHERITE_INGOT)
                .add(ItemIds.DIAMOND)
                .add(ItemIds.LINGERING_POTION)
                .add(BlockItemIds.BEDROCK.item());

        tag(ItemTags.TRIM_MATERIALS)
                .add(MFItemIds.AZURITE)
                .add(MFItemIds.BISMUTH);
    }
}
