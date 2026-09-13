package com.mohigster.morefeatures.block.custom.portal.key;

import com.mohigster.morefeatures.core.MFRegistries;
import com.mojang.serialization.Codec;

public abstract class PortalKey {
    public static final Codec<PortalKey> CODEC =
            MFRegistries.PORTAL_KEY_TYPES.byNameCodec()
                    .dispatch("type", PortalKey::getType,
                            PortalKeyType::codec);

    @Override
    public String toString() {
        return this.keyName() + "Key[" + this.getType().getId() + "]";
    }

    public abstract String keyName();

    public abstract PortalKeyType<?> getType();
}
