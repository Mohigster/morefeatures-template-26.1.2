package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public final class MFEntityTypeTags {
    public static final TagKey<EntityType<?>> WAND_IMMUNE_MOUNTS = morefeaturesTag("wand_immune_mounts");
    public static final TagKey<EntityType<?>> ICEOLOGER_FRIENDS = morefeaturesTag("iceologer_friends");

    private static TagKey<EntityType<?>> morefeaturesTag(String name) {
        return create(MFIdentifier.withMfNamespace(name));
    }

    private static TagKey<EntityType<?>> create(Identifier id) {
        return TagKey.create(Registries.ENTITY_TYPE, id);
    }
}
