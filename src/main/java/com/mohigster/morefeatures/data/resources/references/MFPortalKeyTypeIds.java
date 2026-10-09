package com.mohigster.morefeatures.data.resources.references;

import com.mohigster.morefeatures.core.MFRegistryKeys;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.resources.ResourceKey;

public class MFPortalKeyTypeIds {
    public static final ResourceKey<PortalKeyType<?>> BLOCK = createId("block");
    public static final ResourceKey<PortalKeyType<?>> ITEM = createId("item");

    private static ResourceKey<PortalKeyType<?>> createId(String name) {
        return ResourceKey.create(MFRegistryKeys.PORTAL_KEY_TYPE, MFIdentifier.withMfNamespace(name));
    }
}
