package com.mohigster.morefeatures.portal.frame;

import com.mojang.serialization.MapCodec;

public class PortalFrameType<F extends PortalFrame> {
    public MapCodec<F> codec;

    public MapCodec<F> codec() {
        return codec;
    }
}
