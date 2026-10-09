package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.core.MFRegistryKeys;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import net.minecraft.tags.TagKey;

public class MFPortalKeyTypeTags {
    public static final TagKey<PortalKeyType<?>> TEST_TAG = mfTag("test_tag");
    public static final TagKey<PortalKeyType<?>> STABLE_KEYS = mfTag("stable");

    private static TagKey<PortalKeyType<?>> mfTag(String name) {
        return TagKey.create(MFRegistryKeys.PORTAL_KEY_TYPE, MFIdentifier.withMfNamespace(name));
    }
}
