package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class MFItemTags {
    public static final TagKey<Item> BISMUTH_TOOL_MATERIAL_REPAIRABLE = morefeaturesTag("bismuth_tool_material_repairable");
    public static final TagKey<Item> REPAIRS_BISMUTH_ARMOR = morefeaturesTag("repairs_bismuth_armor");
    public static final TagKey<Item> REPAIRS_CARBON_ARMOR = morefeaturesTag("repairs_carbon_armor");
    public static final TagKey<Item> BISMUTH_TOOL_MATERIALS = morefeaturesTag("bismuth_tool_materials");
    public static final TagKey<Item> IS_FOOD = morefeaturesTag("is_food");
    public static final TagKey<Item> IS_POTION = morefeaturesTag("is_potion");
    public static final TagKey<Item> IS_GOLD = morefeaturesTag("is_gold");
    public static final TagKey<Item> IS_GOLD_ARMOR = morefeaturesTag("is_gold_armor");
    public static final TagKey<Item> IS_NON_GOLD_INGOT = morefeaturesTag("is_non_gold_ingot");
    public static final TagKey<Item> IS_NON_GOLD_RAW_METAL = morefeaturesTag("is_non_gold_raw_metal");
    public static final TagKey<Item> IS_NON_GOLD_METAL_BLOCK = morefeaturesTag("is_non_gold_metal_block");
    public static final TagKey<Item> COMPRESSOR_FUEL = morefeaturesTag("compressor_fuel");
    public static final TagKey<Item> THUNDERBOLT_ENCHANTABLE = morefeaturesTag("thunderbolt_enchantable");
    public static final TagKey<Item> THUNDERING_ENCHANTABLE = morefeaturesTag("thundering_enchantable");
    public static final TagKey<Item> CARBON_INGREDIENTS = morefeaturesTag("carbon_ingredients");
    public static final TagKey<Item> BLOODWOOD_LOGS = morefeaturesTag("bloodwood_logs");
    public static final TagKey<Item> TAINTED_LOGS = morefeaturesTag("tainted_logs");
    public static final TagKey<Item> PALM_LOGS = morefeaturesTag("palm_logs");
    public static final TagKey<Item> CHARRED_STEMS = morefeaturesTag("charred_stems");
    public static final TagKey<Item> DECREPIT_LOGS = morefeaturesTag("decrepit_logs");
    public static final TagKey<Item> PALLID_LOGS = morefeaturesTag("pallid_logs");
    public static final TagKey<Item> MUSIC_DISCS = morefeaturesTag("music_discs");
    public static final TagKey<Item> AIMABLE_WANDS = morefeaturesTag("aimable_wands");
    public static final TagKey<Item> TARGETING_WANDS = morefeaturesTag("targeting_wands");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_AQUAMARINE_DISC = morefeaturesTag("magic_block_turns_to_aquamarine_disc");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_CARBON = morefeaturesTag("magic_block_turns_to_carbon");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_BISMUTH_SCRAP = morefeaturesTag("magic_block_turns_to_bismuth_scrap");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_RAW_BISMUTH_BLOCK = morefeaturesTag("magic_block_turns_to_raw_bismuth_block");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_NETHERITE_SCRAP = morefeaturesTag("magic_block_turns_to_netherite_scrap");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_NETHERITE_INGOT = morefeaturesTag("magic_block_turns_to_netherite_ingot");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_DIAMOND = morefeaturesTag("magic_block_turns_to_diamond");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_STONE = morefeaturesTag("magic_block_turns_to_stone");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_LINGERING_POT = morefeaturesTag("magic_block_turns_to_lingering_pot");
    public static final TagKey<Item> MAGIC_BLOCK_TURNS_TO_BEDROCK = morefeaturesTag("magic_block_turns_to_bedrock");
    public static final TagKey<Item> MAGIC_BLOCK_TRANSMUTATION_RESULTS = morefeaturesTag("magic_block_transmutation_results");
    public static final TagKey<Item> METAL_DETECTOR_FINDABLE = morefeaturesTag("metal_detector_findable");

    private static TagKey<Item> morefeaturesTag(String name) {
        return TagKey.create(Registries.ITEM, MFIdentifier.withMfNamespace(name));
    }
}
