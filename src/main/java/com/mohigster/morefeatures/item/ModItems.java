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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Blocks registered -> Performed by: " + MoreFeatures.MODID);
    }
}
