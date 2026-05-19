package com.mohigster.morefeatures.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {
    public static final TagKey<Item> BISMUTH_TOOL_MATERIAL_REPAIRABLE = bind("bismuth_tool_materials");
    public static final TagKey<Item> REPAIRS_BISMUTH_ARMOR = bind("repairs_bismuth_armor");
    public static final TagKey<Item> REPAIRS_CARBON_ARMOR = bind("repairs_carbon_armor");
    public static final TagKey<Item> BISMUTH_TOOL_MATERIALS = bind("bismuth_tools_material");
    public static final TagKey<Item> IS_FOOD = bind("is_food");
    public static final TagKey<Item> IS_POTION = bind("is_potion");
    public static final TagKey<Item> IS_GOLD = bind("is_gold");
    public static final TagKey<Item> IS_GOLD_ARMOR = bind("is_gold_armor");
    public static final TagKey<Item> IS_NON_GOLD_INGOT = bind("is_non_gold_ingot");
    public static final TagKey<Item> IS_NON_GOLD_RAW_METAL = bind("is_non_gold_raw_metal");
    public static final TagKey<Item> IS_NON_GOLD_METAL_BLOCK = bind("is_non_gold_metal_block");

    private ModItemTags() {
    }

    private static TagKey<Item> bind(String name) {
        return TagKey.create(Registries.ITEM, Identifier.withDefaultNamespace(name));
    }

    public static TagKey<Item> create(Identifier name) {
        return TagKey.create(Registries.ITEM, name);
    }
}
