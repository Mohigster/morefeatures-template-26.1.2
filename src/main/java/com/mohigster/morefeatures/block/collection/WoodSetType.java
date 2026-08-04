package com.mohigster.morefeatures.block.collection;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.blocktype.MFWoodType;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import org.jspecify.annotations.NonNull;

public enum WoodSetType implements StringRepresentable {
    BLOODWOOD("bloodwood", MFWoodType.BLOODWOOD, MapColor.COLOR_RED, MFBlocks.BLOODWOOD_SAPLING, MFBlocks.BLOODWOOD_LEAVES, true, true),
    TAINTED("tainted", MFWoodType.TAINTED, MapColor.COLOR_PURPLE, MFBlocks.TAINTED_SAPLING, MFBlocks.TAINTED_LEAVES, true, true),
    PALM("palm", MFWoodType.PALM, MapColor.COLOR_YELLOW, MFBlocks.PALM_SAPLING, MFBlocks.PALM_LEAVES, true, true),
    CHARRED("charred", MFWoodType.CHARRED, MapColor.COLOR_BLACK, MFBlocks.CHARRED_FUNGUS, MFBlocks.CHARRED_WART_BLOCK, false, false),
    DECREPIT("decrepit", MFWoodType.DECREPIT, MapColor.TERRACOTTA_BLUE, MFBlocks.DECREPIT_SAPLING, MFBlocks.DECREPIT_LEAVES, true, false),
    PALLID("pallid", MFWoodType.PALLID, MapColor.TERRACOTTA_GREEN, MFBlocks.PALLID_SAPLING, MFBlocks.PALLID_LEAVES, true, false);

    private final String name;
    private final WoodType woodType;
    private final MapColor mapColor;
    private final boolean flammable;
    private final boolean overworld;
    private final DeferredBlock<Block> sapling;
    private final DeferredBlock<Block> leaves;

    WoodSetType(String name, WoodType woodType, MapColor mapColor, DeferredBlock<Block> saplingBlock, DeferredBlock<Block> leaves, boolean flammable, boolean overworld) {
        this.name = name;
        this.woodType = woodType;
        this.mapColor = mapColor;
        this.flammable = flammable;
        this.overworld = overworld;
        this.sapling = saplingBlock;
        this.leaves = leaves;
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }

    // Vanilla separates getName from getSerializedName in the DyeColor enum (the enum ColorCollection draws from), so I do the same here.
    public String getName() {
        return this.name;
    }

    public boolean netherOrEndStyled() {
        return !this.overworld;
    }

    public SoundType getMainSoundType() {
        return this.getSoundType(SoundType.NETHER_WOOD);
    }

    public SoundType getLogSoundType() {
        return this.getSoundType(SoundType.STEM);
    }

    private SoundType getSoundType(SoundType nonOverworldSound){
        // Nether and End woods have varied sound types depending on if they are a log or not.
        // Overworld woods, in contrast, always use the same sound type for planks, logs, and others.
        return this.netherOrEndStyled() ? nonOverworldSound : SoundType.WOOD;
    }

    public MapColor getMapColor() {
        return this.mapColor;
    }

    public boolean isFlammable(){
        return this.flammable;
    }

    // This is used to convert the wood set from this Enum to the vanilla WoodType that already exists
    public WoodType getWoodType(){
        return this.woodType;
    }

    // Returns the BlockSetType passed in when registering the WoodType
    public BlockSetType getBlockSetType(){
        return this.woodType.setType();
    }

    // Leaves have to be registered separately because not every wood type is registered
    // as an MFLeavesBlock. Charred wood has CHARRED_WART_BLOCK registered as a plain Block
    public DeferredBlock<Block> getLeavesOrWart(){
        return this.leaves;
    }

    // Same goes for saplings, but it's even more diverse. Bloodwood and Tainted are
    // registered with plain SaplingBlock, but Palm, Decrepit, and Pallid are registered
    // with PlantedOffGrassSaplingBlock, and Charred with NetherFungusBlock.
    public DeferredBlock<Block> getSaplingOrFungus(){
        return this.sapling;
    }
}
