package com.mohigster.morefeatures.block.custom.portal.datadriven;

import com.mohigster.morefeatures.block.custom.portal.key.PortalKey;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public record PortalDataEntry(
        PortalKey portalKey,
        ResourceKey<Level> targetLevel,
        EntityType<?> entitySpawnedByPortal,
        BlockState portalFrame
) {
    public static final Codec<PortalDataEntry> CODEC = RecordCodecBuilder.create(
            inst -> inst.group(
                    PortalKey.CODEC.fieldOf("portal_key")
                            .forGetter(PortalDataEntry::portalKey),
                    Level.RESOURCE_KEY_CODEC.fieldOf("target_dimension")
                            .forGetter(PortalDataEntry::targetLevel),
                    BuiltInRegistries.ENTITY_TYPE.byNameCodec().optionalFieldOf("entity_spawned_by_portal", EntityTypes.ZOMBIFIED_PIGLIN)
                            .forGetter(PortalDataEntry::entitySpawnedByPortal),
                    BlockState.CODEC.fieldOf("portal_frame")
                            .forGetter(PortalDataEntry::portalFrame)
            ).apply(
                    inst,
                    PortalDataEntry::new
            )
    );
}
