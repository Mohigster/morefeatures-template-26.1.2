package com.mohigster.morefeatures.events;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

@SuppressWarnings("unused")
public final class PortalSpawnsEntityEvent extends Event implements ICancellableEvent {
    private final Entity entity;
    private final ResourceKey<Level> portalDestination;

    public PortalSpawnsEntityEvent(Entity entity, ResourceKey<Level> portalDestination) {
        this.entity = entity;
        this.portalDestination = portalDestination;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public ResourceKey<Level> getPortalDestination() {
        return this.portalDestination;
    }
}
