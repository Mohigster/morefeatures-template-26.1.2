package com.mohigster.morefeatures.data.resources.references;

import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
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

    public static final ResourceKey<Item> AZURITE_HANGING_SIGN = createId("azurite_hanging_sign");
    public static final ResourceKey<Item> FLUORITE_HANGING_SIGN = createId("fluorite_hanging_sign");

    public static final ResourceKey<Item> BLOODWOOD_BOAT = createId("bloodwood_boat");
    public static final ResourceKey<Item> BLOODWOOD_CHEST_BOAT = createId("bloodwood_chest_boat");
    public static final ResourceKey<Item> TAINTED_BOAT = createId("tainted_boat");
    public static final ResourceKey<Item> TAINTED_CHEST_BOAT = createId("tainted_chest_boat");
    public static final ResourceKey<Item> PALM_BOAT = createId("palm_boat");
    public static final ResourceKey<Item> PALM_CHEST_BOAT = createId("palm_chest_boat");

    public static final ResourceKey<Item> BRINE_ROD = createId("brine_rod");
    public static final ResourceKey<Item> CARBON_FIBER = createId("carbon_fiber");
    public static final ResourceKey<Item> FROSTED_CORE = createId("frosted_core");

    public static final ResourceKey<Item> ICEOLOGER_SPAWN_EGG = createId("iceologer_spawn_egg");

    public static final ResourceKey<Item> ICE_WAND = createId("ice_wand");
    public static final ResourceKey<Item> FIRE_WAND = createId("fire_wand");
    public static final ResourceKey<Item> HEALING_WAND = createId("healing_wand");
    public static final ResourceKey<Item> EARTH_WAND = createId("earth_wand");
    public static final ResourceKey<Item> LIGHTNING_WAND = createId("lightning_wand");
    public static final ResourceKey<Item> TIME_WAND = createId("time_wand");

    public static final ResourceKey<Item> RAW_BISMUTH = createId("raw_bismuth");
    public static final ResourceKey<Item> BISMUTH = createId("bismuth");
    public static final ResourceKey<Item> BISMUTH_SCRAP = createBismuthId("scrap");
    public static final ResourceKey<Item> BISMUTH_HELMET = createBismuthId("helmet");
    public static final ResourceKey<Item> BISMUTH_CHESTPLATE = createBismuthId("chestplate");
    public static final ResourceKey<Item> BISMUTH_LEGGINGS = createBismuthId("leggings");
    public static final ResourceKey<Item> BISMUTH_BOOTS = createBismuthId("boots");
    public static final ResourceKey<Item> BISMUTH_NAUTILUS_ARMOR = createBismuthId("nautilus_armor");
    public static final ResourceKey<Item> BISMUTH_HORSE_ARMOR = createBismuthId("horse_armor");
    public static final ResourceKey<Item> BISMUTH_SWORD = createBismuthId("sword");
    public static final ResourceKey<Item> BISMUTH_PICKAXE = createBismuthId("pickaxe");
    public static final ResourceKey<Item> BISMUTH_AXE = createBismuthId("axe");
    public static final ResourceKey<Item> BISMUTH_HOE = createBismuthId("hoe");
    public static final ResourceKey<Item> BISMUTH_SHOVEL = createBismuthId("shovel");
    public static final ResourceKey<Item> BISMUTH_SPEAR = createBismuthId("spear");
    public static final ResourceKey<Item> BISMUTH_BOW = createBismuthId("bow");
    public static final ResourceKey<Item> BISMUTH_SHIELD = createBismuthId("shield");
    public static final ResourceKey<Item> BISMUTH_TRIDENT = createBismuthId("trident");
    public static final ResourceKey<Item> BISMUTH_ELYTRA = createBismuthId("elytra");

    public static final ResourceKey<Item> CARBON_WOLF_ARMOR =  createCarbonId("wolf_armor");
    public static final ResourceKey<Item> CARBON_BOW = createCarbonId("bow");
    public static final ResourceKey<Item> CARBON_CROSSBOW = createCarbonId("crossbow");
    public static final ResourceKey<Item> CARBON_SHIELD =  createCarbonId("shield");
    public static final ResourceKey<Item> CARBON_TRIDENT = createCarbonId("trident");
    public static final ResourceKey<Item> CARBON_ELYTRA = createCarbonId("elytra");

    public static final ResourceKey<Item> MUSIC_DISC_AQUAMARINE = createId("music_disc_aquamarine");
    public static final ResourceKey<Item> MUSIC_DISC_SNOW_QUEEN = createId("music_disc_snow_queen");

    public static final ResourceKey<Item> BLUE_BERRY = createId("blue_berry");

    public static final WoodTypeCollection<ResourceKey<Item>> WOODEN_SIGN = createSimpleWoodId("sign");
    public static final WoodTypeCollection<ResourceKey<Item>> WOODEN_HANGING_SIGN = createSimpleWoodId("hanging_sign");

    public static final GemstoneCollection<ResourceKey<Item>> GEMSTONE = createSimpleGemstoneId("");
    public static final GemstoneCollection<ResourceKey<Item>> RAW_GEM = createSuffixGemstoneId("raw");

    private static ResourceKey<Item> createId(String name) {
        return ResourceKey.create(Registries.ITEM, MFIdentifier.withMfNamespace(name));
    }

    private static ResourceKey<Item> createBismuthId(String name) {
        return createId("bismuth_" + name);
    }

    private static ResourceKey<Item> createCarbonId(String name) {
        return createId("carbon_" + name);
    }

    private static WoodTypeCollection<ResourceKey<Item>> createSimpleWoodId(String name) {
        return WoodTypeCollection.prefixWithSet(WoodTypeCollection.create(name)).map(MFItemIds::createId);
    }

    private static GemstoneCollection<ResourceKey<Item>> createSimpleGemstoneId(String name) {
        return GemstoneCollection.prefixWithGem(GemstoneCollection.create(name)).map(MFItemIds::createId);
    }

    private static GemstoneCollection<ResourceKey<Item>> createSuffixGemstoneId(String name) {
        return GemstoneCollection.suffixWithGem(GemstoneCollection.create(name)).map(MFItemIds::createId);
    }
}
