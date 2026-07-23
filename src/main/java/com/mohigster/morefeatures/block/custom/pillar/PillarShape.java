package com.mohigster.morefeatures.block.custom.pillar;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum PillarShape implements StringRepresentable {
    TOP("top"),
    BOTTOM("bottom"),
    MIDDLE("middle"),
    FULL("full");

    private final String name;

    PillarShape(String name) {
        this.name = name;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }
}
