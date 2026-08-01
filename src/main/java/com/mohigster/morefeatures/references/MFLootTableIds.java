package com.mohigster.morefeatures.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class MFLootTableIds {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();

    public static final ResourceKey<LootTable> HARVEST_BLUE_BERRY_BUSH = register("harvest/blue_berry_bush");

    private static ResourceKey<LootTable> register(String location) {
        ResourceKey<LootTable> id = ResourceKey.create(Registries.LOOT_TABLE, MFIdentifier.withMfNamespace(location));
        if (LOCATIONS.add(id)) {
            return id;
        } else {
            throw new IllegalStateException("Attempted to register a loot table with id " + location + " when such a loot table already exists.");
        }
    }
}
