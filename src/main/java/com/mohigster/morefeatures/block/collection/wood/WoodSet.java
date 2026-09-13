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
 * as well as environmental flags like flammability and dimension classification ({@code overworld}, {@code nether}, or {@code end}).</p>
 *
 * <p><b>Dimension Functionality:</b>
 * <ul>
 *   <li>If both {@code nether} and {@code overworld} flags are set to {@code true}, {@code nether} behavior takes precedence.</li>
 *   <li>If neither flag is set to {@code true}, the wood set is treated as {@code end} wood.</li>
 * </ul>
 * </p>
 */
public enum WoodSet implements StringRepresentable {
    BLOODWOOD("bloodwood", MFWoodType.BLOODWOOD, MapColor.COLOR_RED, false, true,  true, true, true),
    TAINTED("tainted", MFWoodType.TAINTED, MapColor.COLOR_PURPLE),
    PALM("palm", MFWoodType.PALM, MapColor.COLOR_YELLOW),
    CHARRED("charred", MFWoodType.CHARRED, MapColor.COLOR_BLACK, true),
    DECREPIT("decrepit", MFWoodType.DECREPIT, MapColor.TERRACOTTA_BLUE, false, false),
    PALLID("pallid", MFWoodType.PALLID, MapColor.TERRACOTTA_GREEN, false, false);

    private final String name;
    private final boolean nether;
    private final boolean hasBoat;
    private final WoodType woodType;
    private final MapColor mapColor;
    private final boolean flammable;
    private final boolean overworld;
    private final boolean noWoodSuffix;

    WoodSet(String name, WoodType woodType,
            MapColor mapColor, boolean nether,
            boolean hasBoat, boolean flammable,
            boolean overworld, boolean noWoodSuffix
    ) {
        this.name = name;
        this.nether = nether;
        this.hasBoat = hasBoat;
        this.woodType = woodType;
        this.mapColor = mapColor;
        this.flammable = flammable;
        this.overworld = overworld;
        this.noWoodSuffix = noWoodSuffix;
    }

    WoodSet(String name, WoodType woodType,
            MapColor mapColor, boolean nether
    ) {
        this(name, woodType, mapColor, nether, false,
                false, false, false);
    }

    WoodSet(String name, WoodType woodType,
            MapColor mapColor
    ) {
        this(name, woodType, mapColor, false, true,
                true, true, false);
    }

    WoodSet(String name, WoodType woodType,
            MapColor mapColor, boolean nether, boolean overworld
    ) {
        this(name, woodType, mapColor, nether,
                false, true, overworld, false);
    }

    @Override
    public @NonNull String getSerializedName() {
        return this.name;
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

        if (this.nether && (id.equals("wood") || id.equals("log") || id.equals("sapling"))) {
            String netherSuffix = switch (id) {
                case "wood" -> "hyphae";
                case "sapling" -> "fungus";
                default -> "stem";
            };
            return "_" + netherSuffix;
        }

        return "_" + id;
    }

    public boolean netherOrEndStyled() {
        return this.nether || !this.overworld;
    }

    public SoundType mainSoundType() {
        return this.soundType(SoundType.NETHER_WOOD);
    }

    public SoundType logSoundType() {
        return this.soundType(SoundType.STEM);
    }

    private SoundType soundType(SoundType nonOverworldSound){
        // Nether and End woods have varied sound types depending on if they are a log or not.
        // Overworld woods, in contrast, always use the same sound type for planks, logs, and others.
        return this.netherOrEndStyled() ? nonOverworldSound : SoundType.WOOD;
    }

    public MapColor mapColor() {
        return this.mapColor;
    }

    public boolean isFlammable(){
        return this.flammable;
    }

    public boolean hasBoat(){
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
    public DeferredBlock<Block> leavesOrWart(){
        return switch (this){
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
    public DeferredBlock<Block> saplingOrFungus(){
        return switch (this){
            case BLOODWOOD ->  MFBlocks.BLOODWOOD_SAPLING;
            case TAINTED ->  MFBlocks.TAINTED_SAPLING;
            case PALM ->  MFBlocks.PALM_SAPLING;
            case CHARRED ->  MFBlocks.CHARRED_FUNGUS;
            case DECREPIT ->  MFBlocks.DECREPIT_SAPLING;
            case PALLID ->  MFBlocks.PALLID_SAPLING;
        };
    }

    public @Nullable DeferredItem<Item> getBoat(){
        if (this.hasBoat()) return switch (this){
            case BLOODWOOD -> MFItems.BLOODWOOD_BOAT;
            case TAINTED -> MFItems.TAINTED_BOAT;
            case PALM -> MFItems.PALM_BOAT;
            default -> null;
        };
        return null;
    }

    public @Nullable DeferredItem<Item> getChestBoat(){
        if (this.hasBoat()) return switch (this){
            case BLOODWOOD -> MFItems.BLOODWOOD_CHEST_BOAT;
            case TAINTED -> MFItems.TAINTED_CHEST_BOAT;
            case PALM -> MFItems.PALM_CHEST_BOAT;
            default -> null;
        };
        return null;
    }
}
