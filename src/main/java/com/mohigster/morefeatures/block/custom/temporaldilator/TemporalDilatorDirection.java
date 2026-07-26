package com.mohigster.morefeatures.block.custom.temporaldilator;

import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

public enum TemporalDilatorDirection implements StringRepresentable {
    UP("up"),
    DOWN("down");

    private final String name;

    TemporalDilatorDirection(String name) {
        this.name = name;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }
}
