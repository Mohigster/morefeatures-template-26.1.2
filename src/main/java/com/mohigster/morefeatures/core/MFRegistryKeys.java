package com.mohigster.morefeatures.core;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.portal.frame.PortalFrameType;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public final class MFRegistryKeys {
    public static final ResourceKey<Registry<PortalKeyType<?>>> PORTAL_KEY_TYPE = create("portal_key_type");
    public static final ResourceKey<Registry<PortalFrameType<?>>> PORTAL_FRAME = create("portal_frame");

    private static <T> ResourceKey<Registry<T>> create(String registryName) {
        return ResourceKey.createRegistryKey(MFIdentifier.withMfNamespace(registryName));
    }
}
