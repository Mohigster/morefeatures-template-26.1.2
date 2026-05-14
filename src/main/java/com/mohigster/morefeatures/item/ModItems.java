package com.mohigster.morefeatures.item;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MoreFeatures.MODID);

    // Item registration. JSON files are generated with DataGen. See MoreFeaturesDataGen and classes in the datagen package.

    // Texture files and language data still must be manually added.

    // This mod has no theme and therefore may add a large number of items. For convenience, Items should be
    // split into categories headed by comments, e.g. // Aluminium items.

    // Aluminium items
    public static final DeferredItem<Item> RAW_ALUMINIUM = ITEMS.registerSimpleItem("raw_aluminium");
    public static final DeferredItem<Item> ALUMINIUM_INGOT = ITEMS.registerSimpleItem("aluminium_ingot");

    // Magnesium items
    public static final DeferredItem<Item> RAW_MAGNESIUM = ITEMS.registerSimpleItem("raw_magnesium");
    public static final DeferredItem<Item> MAGNESIUM_INGOT = ITEMS.registerSimpleItem("magnesium_ingot");

    // Azurite items
    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");

    // Fluorite items
    public static final DeferredItem<Item> FLUORITE = ITEMS.registerSimpleItem("fluorite");
    public static final DeferredItem<Item> RAW_FLUORITE = ITEMS.registerSimpleItem("raw_fluorite");

    // Bismuth items
    public static final DeferredItem<Item> BISMUTH_INGOT = ITEMS.registerSimpleItem("bismuth_ingot");
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerSimpleItem("raw_bismuth");

    // Elemental rods
    public static final DeferredItem<Item> BRINE_ROD = ITEMS.registerSimpleItem("brine_rod");


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Items registered -> Performed by: " + MoreFeatures.MODID);
    }
}
