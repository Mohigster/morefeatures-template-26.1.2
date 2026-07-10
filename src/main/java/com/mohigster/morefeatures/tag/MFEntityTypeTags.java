package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public interface MFEntityTypeTags {
    TagKey<EntityType<?>> WAND_IMMUNE_MOUNTS = morefeaturesTag("wand_immune_mounts");

    private static TagKey<EntityType<?>> morefeaturesTag(String name) {
        return create(MFIdentifier.withMfNamespace(name));
    }

    private static TagKey<EntityType<?>> create(Identifier id) {
        return TagKey.create(Registries.ENTITY_TYPE, id);
    }
}
