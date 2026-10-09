package com.mohigster.morefeatures.portal.key;

import com.mohigster.morefeatures.core.MFRegistries;
import com.mohigster.morefeatures.portal.key.type.PortalKeyType;
import com.mojang.serialization.Codec;

public interface PortalKey {
    Codec<PortalKey> CODEC = MFRegistries.PORTAL_KEY_TYPE.byNameCodec()
            .dispatch(PortalKey::type, PortalKeyType::codec);

    PortalKeyType<?> type();
}
