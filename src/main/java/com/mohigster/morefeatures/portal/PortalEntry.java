package com.mohigster.morefeatures.portal;


import com.mohigster.morefeatures.portal.frame.PortalFrame;
import com.mohigster.morefeatures.portal.key.PortalKey;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

import java.util.Optional;

public record PortalEntry(
        PortalKey key,
        ResourceKey<Level> targetLevel,
        Optional<EntityType<?>> entitySpawnedByPortal,
        PortalFrame frame
) {
    public static final Codec<PortalEntry> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    PortalKey.CODEC.fieldOf("portal_key")
                            .forGetter(PortalEntry::key),
                    Level.RESOURCE_KEY_CODEC.fieldOf("target_dimension")
                            .forGetter(PortalEntry::targetLevel),
                    BuiltInRegistries.ENTITY_TYPE.byNameCodec().optionalFieldOf("entity_spawned_by_portal")
                            .forGetter(PortalEntry::entitySpawnedByPortal),
                    PortalFrame.CODEC.fieldOf("portal_frame")
                            .forGetter(PortalEntry::frame)
            ).apply(
                    inst,
                    PortalEntry::new
            )
    );
}
