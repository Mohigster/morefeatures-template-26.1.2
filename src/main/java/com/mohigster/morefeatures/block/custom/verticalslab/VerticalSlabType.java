package com.mohigster.morefeatures.block.custom.verticalslab;

import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NullMarked;

public enum VerticalSlabType implements StringRepresentable {
    NORTH("north"),
    EAST("east"),
    SOUTH("south"),
    WEST("west"),
    DOUBLE("double");

    private final String name;

    VerticalSlabType(String name) {
        this.name = name;
    }

    public static VerticalSlabType fromDirection(Direction direction) {
        return switch (direction) {
            case NORTH -> NORTH;
            case EAST -> EAST;
            case SOUTH -> SOUTH;
            case WEST -> WEST;
            default -> throw new IllegalArgumentException("No vertical slab type for direction " + direction);
        };
    }

    public Direction toDirection() {
        return switch (this) {
            case NORTH -> Direction.NORTH;
            case EAST -> Direction.EAST;
            case SOUTH -> Direction.SOUTH;
            case WEST -> Direction.WEST;
            case DOUBLE -> throw new IllegalStateException("DOUBLE vertical slab has no single facing");
        };
    }

    @NullMarked
    @Override
    public String getSerializedName() {
        return this.name;
    }
}
