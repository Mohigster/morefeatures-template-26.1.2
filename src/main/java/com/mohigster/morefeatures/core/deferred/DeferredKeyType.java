package com.mohigster.morefeatures.core.deferred;

import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.registries.DeferredHolder;

public final class DeferredKeyType<P extends PortalKeyType<?>> extends DeferredHolder<PortalKeyType<?>, P> {
    /**
     * Creates a new DeferredKeyType with a ResourceKey.
     *
     * <p>Attempts to bind immediately if possible.
     *
     * @param key The resource key of the target object.
     * @see #create(ResourceKey, Identifier)
     * @see #create(Identifier, Identifier)
     * @see #create(ResourceKey)
     */
    private DeferredKeyType(ResourceKey<PortalKeyType<?>> key) {
        super(key);
    }

    public static <P extends PortalKeyType<?>> DeferredKeyType<P> createKey(ResourceKey<PortalKeyType<?>> key) {
        return new DeferredKeyType<>(key);
    }

    @Override
    public String getRegisteredName() {
        return this.getName();
    }

    @Override
    public int hashCode() {
        return this.get().hashCode();
    }

    @SuppressWarnings("unused")
    public Holder<PortalKeyType<?>> getRegistryHolder() {
        return this.get().builtInRegistryHolder();
    }

    public String getName() {
        return this.get().name();
    }
}
