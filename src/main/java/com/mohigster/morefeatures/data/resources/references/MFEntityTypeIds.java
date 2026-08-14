package com.mohigster.morefeatures.data.resources.references;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class MFEntityTypeIds {
    public static final ResourceKey<EntityType<?>> CARBON_TRIDENT = createId("carbon_trident");
    public static final ResourceKey<EntityType<?>> BISMUTH_TRIDENT = createId("bismuth_trident");

    public static final ResourceKey<EntityType<?>> BLOODWOOD_BOAT = createId("bloodwood_boat");
    public static final ResourceKey<EntityType<?>> BLOODWOOD_CHEST_BOAT = createId("bloodwood_chest_boat");
    public static final ResourceKey<EntityType<?>> TAINTED_BOAT = createId("tainted_boat");
    public static final ResourceKey<EntityType<?>> TAINTED_CHEST_BOAT = createId("tainted_chest_boat");
    public static final ResourceKey<EntityType<?>> PALM_BOAT = createId("palm_boat");
    public static final ResourceKey<EntityType<?>> PALM_CHEST_BOAT = createId("palm_chest_boat");

    public static final ResourceKey<EntityType<?>> BRINE = createId("brine");

    public static final ResourceKey<EntityType<?>> ICEOLOGER = createId("iceologer");

    private static ResourceKey<EntityType<?>> createId(String name){
        return ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace(name));
    }
}
