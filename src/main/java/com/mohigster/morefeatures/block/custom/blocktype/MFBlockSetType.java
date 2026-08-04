package com.mohigster.morefeatures.block.custom.blocktype;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;

public record MFBlockSetType() {

    // Creates the BlockSetTypes for each of my custom Block Sets. Including all of my woods and my gemstone block sets
    // These gemstones have custom signs, hanging signs, doors, trapdoors, pressure plates, etc. so they do need a BlockSetType
    public static final BlockSetType AZURITE = BlockSetType.register(createGemstoneBlockSetType("azurite"));
    public static final BlockSetType FLUORITE = BlockSetType.register(createGemstoneBlockSetType("fluorite"));

    // Only inputting the name causes it to naturally fall back to overworld wooden properties,
    // as the BlockSetType class has a constructor that accepts only a name and falls back on that
    public static final BlockSetType BLOODWOOD = BlockSetType.register(new BlockSetType("bloodwood"));
    public static final BlockSetType TAINTED = BlockSetType.register(new BlockSetType("tainted"));
    public static final BlockSetType PALM = BlockSetType.register(new BlockSetType("palm"));
    public static final BlockSetType CHARRED = BlockSetType.register(createNetherOrEndBlockSetType("charred"));
    public static final BlockSetType DECREPIT = BlockSetType.register(createNetherOrEndBlockSetType("decrepit"));
    public static final BlockSetType PALLID = BlockSetType.register(createNetherOrEndBlockSetType("pallid"));

    private static BlockSetType createNetherOrEndBlockSetType(String name){
        return new BlockSetType(name,
                true,
                true,
                true,
                BlockSetType.PressurePlateSensitivity.EVERYTHING,
                SoundType.NETHER_WOOD,
                SoundEvents.NETHER_WOOD_DOOR_CLOSE,
                SoundEvents.NETHER_WOOD_DOOR_OPEN,
                SoundEvents.NETHER_WOOD_TRAPDOOR_CLOSE,
                SoundEvents.NETHER_WOOD_TRAPDOOR_OPEN,
                SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_OFF,
                SoundEvents.NETHER_WOOD_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.NETHER_WOOD_BUTTON_CLICK_OFF,
                SoundEvents.NETHER_WOOD_BUTTON_CLICK_ON
        );
    }

    private static BlockSetType createGemstoneBlockSetType(String name) {
        return new BlockSetType(name,
                true,
                false,
                false,
                BlockSetType.PressurePlateSensitivity.EVERYTHING,
                SoundType.AMETHYST,
                SoundEvents.IRON_DOOR_CLOSE,
                SoundEvents.IRON_DOOR_OPEN,
                SoundEvents.IRON_TRAPDOOR_CLOSE,
                SoundEvents.IRON_TRAPDOOR_OPEN,
                SoundEvents.METAL_PRESSURE_PLATE_CLICK_OFF,
                SoundEvents.METAL_PRESSURE_PLATE_CLICK_ON,
                SoundEvents.STONE_BUTTON_CLICK_OFF,
                SoundEvents.STONE_BUTTON_CLICK_ON
        );
    }
}
