package com.mohigster.morefeatures.block.collection.gemstone;

import com.mohigster.morefeatures.block.custom.blocktype.MFWoodType;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import org.jspecify.annotations.NonNull;

public enum GemstoneType implements StringRepresentable {
    AZURITE("azurite", MFWoodType.AZURITE, MapColor.COLOR_BLUE),
    FLUORITE("fluorite", MFWoodType.FLUORITE, MapColor.COLOR_LIGHT_GREEN);

    private final String name;
    private final WoodType woodType; // Gemstone aren't really "woods", but they do have signs and fence gates which need one of these things
    private final MapColor mapColor;

    GemstoneType(String name, WoodType woodType, MapColor mapColor) {
        this.name = name;
        this.woodType = woodType;
        this.mapColor = mapColor;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public WoodType getWoodType() {
        return this.woodType;
    }

    public MapColor getMapColor() {
        return this.mapColor;
    }
}
