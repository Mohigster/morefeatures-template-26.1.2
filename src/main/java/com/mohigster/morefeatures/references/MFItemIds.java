package com.mohigster.morefeatures.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MFItemIds {
    public static final ResourceKey<Item> RAW_ALUMINIUM = createId("raw_aluminium");
    public static final ResourceKey<Item> ALUMINIUM_INGOT = createId("aluminium_ingot");

    public static final ResourceKey<Item> RAW_MAGNESIUM = createId("raw_magnesium");
    public static final ResourceKey<Item> MAGNESIUM_INGOT = createId("magnesium_ingot");

    public static final ResourceKey<Item> RAW_AZURITE = createId("raw_azurite");
    public static final ResourceKey<Item> AZURITE = createId("azurite");

    public static final ResourceKey<Item> RAW_FLUORITE = createId("raw_fluorite");
    public static final ResourceKey<Item> FLUORITE = createId("fluorite");

    public static final ResourceKey<Item> RAW_EVERFROST = createId("raw_everfrost");
    public static final ResourceKey<Item> EVERFROST = createId("everfrost");

    public static final ResourceKey<Item> METAL_DETECTOR = createId("metal_detector");

    public static final ResourceKey<Item> AZURITE_SIGN = createId("azurite_sign");
    public static final ResourceKey<Item> FLUORITE_SIGN = createId("fluorite_sign");
    public static final ResourceKey<Item> BLOODWOOD_SIGN = createId("bloodwood_sign");
    public static final ResourceKey<Item> TAINTED_SIGN = createId("tainted_sign");
    public static final ResourceKey<Item> PALM_SIGN = createId("palm_sign");
    public static final ResourceKey<Item> DECREPIT_SIGN = createId("decrepit_sign");
    public static final ResourceKey<Item> PALLID_SIGN = createId("pallid_sign");

    public static final ResourceKey<Item> AZURITE_HANGING_SIGN = createId("azurite_hanging_sign");
    public static final ResourceKey<Item> FLUORITE_HANGING_SIGN = createId("fluorite_hanging_sign");
    public static final ResourceKey<Item> BLOODWOOD_HANGING_SIGN = createId("bloodwood_hanging_sign");
    public static final ResourceKey<Item> TAINTED_HANGING_SIGN = createId("tainted_hanging_sign");
    public static final ResourceKey<Item> PALM_HANGING_SIGN = createId("palm_hanging_sign");
    public static final ResourceKey<Item> DECREPIT_HANGING_SIGN = createId("decrepit_hanging_sign");
    public static final ResourceKey<Item> PALLID_HANGING_SIGN = createId("pallid_hanging_sign");

    public static final ResourceKey<Item> BLOODWOOD_BOAT = createId("bloodwood_boat");
    public static final ResourceKey<Item> BLOODWOOD_CHEST_BOAT = createId("bloodwood_chest_boat");
    public static final ResourceKey<Item> TAINTED_BOAT = createId("tainted_boat");
    public static final ResourceKey<Item> TAINTED_CHEST_BOAT = createId("tainted_chest_boat");
    public static final ResourceKey<Item> PALM_BOAT = createId("palm_boat");
    public static final ResourceKey<Item> PALM_CHEST_BOAT = createId("palm_chest_boat");

    public static final ResourceKey<Item> CARBON_FIBER = createId("carbon_fiber");

    public static final ResourceKey<Item> RAW_BISMUTH = createId("raw_bismuth");
    public static final ResourceKey<Item> BISMUTH = createId("bismuth");
    public static final ResourceKey<Item> BISMUTH_SCRAP = createId("bismuth_scrap");
    public static final ResourceKey<Item> BISMUTH_HELMET = createId("bismuth_helmet");
    public static final ResourceKey<Item> BISMUTH_CHESTPLATE = createId("bismuth_chestplate");
    public static final ResourceKey<Item> BISMUTH_LEGGINGS = createId("bismuth_leggings");
    public static final ResourceKey<Item> BISMUTH_BOOTS = createId("bismuth_boots");
    public static final ResourceKey<Item> BISMUTH_AXE = createId("bismuth_axe");
    public static final ResourceKey<Item> BISMUTH_HOE = createId("bismuth_hoe");
    public static final ResourceKey<Item> BISMUTH_SHOVEL = createId("bismuth_shovel");
    public static final ResourceKey<Item> BISMUTH_SPEAR = createId("bismuth_spear");

    public static final ResourceKey<Item> MUSIC_DISC_AQUAMARINE = createId("music_disc_aquamarine");
    public static final ResourceKey<Item> MUSIC_DISC_SNOW_QUEEN = createId("music_disc_snow_queen");

    private static ResourceKey<Item> createId(String name) {
        return ResourceKey.create(Registries.ITEM, MFIdentifier.withMfNamespace(name));
    }
}
