package com.mohigster.morefeatures.block.collection.vanilla;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public enum VanillaWoodSet implements StringRepresentable {
    OAK("oak", MapColor.WOOD),
    BIRCH("birch", MapColor.SAND),
    SPRUCE("spruce", MapColor.PODZOL),
    JUNGLE("jungle", MapColor.DIRT),
    ACACIA("acacia", MapColor.COLOR_ORANGE),
    DARK_OAK("dark_oak", MapColor.COLOR_BROWN),
    CRIMSON("crimson", MapColor.CRIMSON_STEM, true),
    WARPED("warped", MapColor.WARPED_STEM, true),
    MANGROVE("mangrove", MapColor.COLOR_RED),
    CHERRY("cherry", MapColor.TERRACOTTA_WHITE, SoundType.CHERRY_WOOD),
    BAMBOO("bamboo", MapColor.COLOR_YELLOW, SoundType.BAMBOO),
    MOSAIC("bamboo_mosaic", MapColor.COLOR_YELLOW, SoundType.BAMBOO),
    PALE_OAK("pale_oak", MapColor.QUARTZ);

    private final String name;
    private final MapColor mapColor;
    private final SoundType soundType;
    private final boolean nether;

    VanillaWoodSet(
            String name,
            MapColor mapColor,
            SoundType soundType,
            boolean nether
    ) {
        this.name = name;
        this.mapColor = mapColor;
        this.soundType = soundType;
        this.nether = nether;
    }

    VanillaWoodSet(
            String name,
            MapColor mapColor,
            SoundType soundType
    ) {
        this(name, mapColor, soundType, false);
    }

    VanillaWoodSet(
            String name,
            MapColor mapColor,
            boolean nether
    ) {
        SoundType sound = nether ? SoundType.NETHER_WOOD : SoundType.WOOD;
        this(name, mapColor, sound, nether);
    }

    VanillaWoodSet(
            String name,
            MapColor mapColor
    ) {
        this(name, mapColor, false);
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }

    public boolean isFlammable() {
        return !this.nether;
    }

    public MapColor mapColor() {
        return this.mapColor;
    }

    public SoundType soundType() {
        return this.soundType;
    }
    
    public Supplier<Block> planks() {
        return () -> switch (this) {
            case OAK -> Blocks.OAK_PLANKS;
            case BIRCH -> Blocks.BIRCH_PLANKS;
            case SPRUCE -> Blocks.SPRUCE_PLANKS;
            case JUNGLE -> Blocks.JUNGLE_PLANKS;
            case ACACIA -> Blocks.ACACIA_PLANKS;
            case DARK_OAK -> Blocks.DARK_OAK_PLANKS;
            case CRIMSON -> Blocks.CRIMSON_PLANKS;
            case WARPED -> Blocks.WARPED_PLANKS;
            case MANGROVE -> Blocks.MANGROVE_PLANKS;
            case CHERRY -> Blocks.CHERRY_PLANKS;
            case BAMBOO -> Blocks.BAMBOO_PLANKS;
            case MOSAIC -> Blocks.BAMBOO_MOSAIC;
            case PALE_OAK -> Blocks.PALE_OAK_PLANKS;
        };
    }
}
