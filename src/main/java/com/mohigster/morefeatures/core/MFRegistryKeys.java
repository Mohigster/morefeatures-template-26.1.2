package com.mohigster.morefeatures.core;

import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;

public final class MFRegistryKeys {
    public static final RegistryKey<PortalKeyType<?>> PORTAL_KEY_TYPE = create("portal_key_type");

    @SuppressWarnings("SameParameterValue")
    private static <T> RegistryKey<T> create(String registryName) {
        return RegistryKey.create(MFIdentifier.withMfNamespace(registryName));
    }
}
