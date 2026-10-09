package com.mohigster.morefeatures.portal.frame;

import com.mohigster.morefeatures.core.MFRegistries;
import com.mojang.serialization.Codec;

public interface PortalFrame {
    Codec<PortalFrame> CODEC = MFRegistries.PORTAL_FRAME.byNameCodec()
            .dispatch(PortalFrame::getType, PortalFrameType::codec);

    PortalFrameType<?> getType();
}
