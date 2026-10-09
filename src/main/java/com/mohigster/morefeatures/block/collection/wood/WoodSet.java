package com.mohigster.morefeatures.block.collection.wood;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.blocktype.MFWoodType;
import com.mohigster.morefeatures.item.MFItems;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

/**
 * Represents every wood set introduced by this mod and holds its core physical and environmental properties.
 *
 * <p>Each entry defines direct parameters such as the associated {@link WoodType} and {@link MapColor},
 * as well as environmental flags like flammability and dimension classification.</p>
 *
 * Dimension is determined by
 */
public enum WoodSet implements StringRepresentable {
    BLOODWOOD("bloodwood", MFWoodType.BLOODWOOD, MapColor.COLOR_RED, Dimension.OVERWORLD, true),
    TAINTED("tainted", MFWoodType.TAINTED, MapColor.COLOR_PURPLE, Dimension.OVERWORLD),
    PALM("palm", MFWoodType.PALM, MapColor.COLOR_YELLOW, Dimension.OVERWORLD),
    CHARRED("charred", MFWoodType.CHARRED, MapColor.COLOR_BLACK, Dimension.NETHER),
    DECREPIT("decrepit", MFWoodType.DECREPIT, MapColor.TERRACOTTA_BLUE, Dimension.END),
    PALLID("pallid", MFWoodType.PALLID, MapColor.TERRACOTTA_GREEN, Dimension.END);

    private final String name;
    private final boolean hasBoat;
    private final WoodType woodType;
    private final MapColor mapColor;
    private final Dimension dimension;
    private final boolean flammable;
    private final boolean noWoodSuffix;

    WoodSet(String name, WoodType woodType,
            MapColor mapColor, Dimension dim,
            boolean hasBoat, boolean flammable,
            boolean noWoodSuffix
    ) {
        this.dimension = dim;
        this.name = name;
        this.hasBoat = hasBoat;
        this.woodType = woodType;
        this.mapColor = mapColor;
        this.flammable = flammable;
        this.noWoodSuffix = noWoodSuffix;
    }

    WoodSet(String name, WoodType woodType,
            MapColor mapColor, Dimension dim,
            boolean noWoodSuffix
    ) {
        this(name, woodType, mapColor, dim,
                true, true, noWoodSuffix);
    }

    WoodSet(String name, WoodType woodType,
            MapColor mapColor, Dimension dim
    ) {
        boolean hasBoat = dim == Dimension.OVERWORLD;
        boolean flammable = dim != Dimension.NETHER;

        this(name, woodType, mapColor, dim,
                hasBoat, flammable, false);
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
    }

    public Dimension dimension() {
        return this.dimension;
    }

    // Vanilla separates getName from getSerializedName in the DyeColor enum (the enum ColorCollection draws from), so I do the same here.
    public String getName() {
        return this.name;
    }

    String getFormattedId(String prefix, String id) {
        return prefix + this.getName() + this.getFormattedSuffix(id);
    }

    private String getFormattedSuffix(String id) {
        if (id.isEmpty()) return id;

        if (this.noWoodSuffix && id.equals("wood")) {
            return "";
        }

        if (this.dimension() == Dimension.NETHER && (id.equals("wood") || id.equals("log") || id.equals("sapling"))) {
            String netherSuffix = switch (id) {
                case "wood" -> "hyphae";
                case "sapling" -> "fungus";
                default -> "stem";
            };
            return "_" + netherSuffix;
        }

        return "_" + id;
    }

    public boolean nonOverworld() {
        return this.dimension() != Dimension.OVERWORLD;
    }

    public SoundType mainSoundType() {
        return this.soundType(SoundType.NETHER_WOOD);
    }

    public SoundType logSoundType() {
        return this.soundType(SoundType.STEM);
    }

    private SoundType soundType(SoundType nonOverworldSound) {
        // Nether and End woods have varied sound types depending on if they are a log or not.
        // Overworld woods, in contrast, always use the same sound type for planks, logs, and others.
        return this.nonOverworld() ? nonOverworldSound : SoundType.WOOD;
    }

    public MapColor mapColor() {
        return this.mapColor;
    }

    public boolean isFlammable() {
        return this.flammable;
    }

    public boolean hasBoat() {
        return this.hasBoat;
    }

    // This is used to convert the WoodSet from this Enum to the vanilla WoodType that already exists
    public WoodType woodType() {
        return this.woodType;
    }

    // Returns the BlockSetType passed in when registering the WoodType
    public BlockSetType blockSetType() {
        return this.woodType.setType();
    }

    // Leaves have to be registered separately because not every wood type is registered
    // as an MFLeavesBlock. Charred wood has CHARRED_WART_BLOCK registered as a plain Block
    public DeferredBlock<Block> leavesOrWart() {
        return switch (this) {
            case BLOODWOOD -> MFBlocks.BLOODWOOD_LEAVES;
            case TAINTED -> MFBlocks.TAINTED_LEAVES;
            case PALM -> MFBlocks.PALM_LEAVES;
            case CHARRED -> MFBlocks.CHARRED_WART_BLOCK;
            case DECREPIT -> MFBlocks.DECREPIT_LEAVES;
            case PALLID -> MFBlocks.PALLID_LEAVES;
        };
    }

    // Same goes for saplings, but it's even more diverse. Bloodwood and Tainted are
    // registered with plain SaplingBlock, but Palm, Decrepit, and Pallid are registered
    // with PlantedOffGrassSaplingBlock, and Charred with NetherFungusBlock.
    public DeferredBlock<Block> saplingOrFungus() {
        return switch (this) {
            case BLOODWOOD ->  MFBlocks.BLOODWOOD_SAPLING;
            case TAINTED ->  MFBlocks.TAINTED_SAPLING;
            case PALM ->  MFBlocks.PALM_SAPLING;
            case CHARRED ->  MFBlocks.CHARRED_FUNGUS;
            case DECREPIT ->  MFBlocks.DECREPIT_SAPLING;
            case PALLID ->  MFBlocks.PALLID_SAPLING;
        };
    }

    public @Nullable DeferredItem<Item> getBoat() {
        return switch (this) {
            case BLOODWOOD -> MFItems.BLOODWOOD_BOAT;
            case TAINTED -> MFItems.TAINTED_BOAT;
            case PALM -> MFItems.PALM_BOAT;
            default -> null;
        };
    }

    public @Nullable DeferredItem<Item> getChestBoat() {
        return switch (this) {
            case BLOODWOOD -> MFItems.BLOODWOOD_CHEST_BOAT;
            case TAINTED -> MFItems.TAINTED_CHEST_BOAT;
            case PALM -> MFItems.PALM_CHEST_BOAT;
            default -> null;
        };
    }

    public enum Dimension {
        OVERWORLD,
        NETHER,
        BOREALIS, // Currently unused, but will be used when I add Frigid Wood
        END
    }
}
