package com.mohigster.morefeatures.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

public class MFItemIds {
    public static final ResourceKey<Item> RAW_ALUMINIUM = createId("raw_aluminium");
    public static final ResourceKey<Item> ALUMINIUM_INGOT = createId("aluminium_ingot");

    public static final ResourceKey<Item> RAW_MAGNESIUM = createId("raw_magnesium");
    public static final ResourceKey<Item> MAGNESIUM_INGOT = createId("magnesium_ingot");

    public static final ResourceKey<Item> CARBON_FIBER = createId("carbon_fiber");

    public static final ResourceKey<Item> BISMUTH_AXE = createId("bismuth_axe");

    public static final ResourceKey<Item> MUSIC_DISC_AQUAMARINE = createId("music_disc_aquamarine");

    private static ResourceKey<Item> createId(String name) {
        return ResourceKey.create(Registries.ITEM, MFIdentifier.withMfNamespace(name));
    }
}
