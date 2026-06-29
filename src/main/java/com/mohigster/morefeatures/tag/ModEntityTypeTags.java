package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public interface ModEntityTypeTags {
    TagKey<EntityType<?>> WAND_IMMUNE_MOUNTS = morefeaturesTag("wand_immune_mounts");

    private static TagKey<EntityType<?>> morefeaturesTag(String name) {
        return TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name));
    }
}
