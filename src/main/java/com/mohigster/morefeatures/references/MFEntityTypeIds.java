package com.mohigster.morefeatures.references;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;

public class MFEntityTypeIds {
    public static final ResourceKey<EntityType<?>> CARBON_TRIDENT = createId("carbon_trident");
    public static final ResourceKey<EntityType<?>> BISMUTH_TRIDENT = createId("bismuth_trident");

    private static ResourceKey<EntityType<?>> createId(String name){
        return ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace(name));
    }
}
