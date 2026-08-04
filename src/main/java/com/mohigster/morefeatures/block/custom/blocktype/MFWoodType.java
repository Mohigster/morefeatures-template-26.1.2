package com.mohigster.morefeatures.block.custom.blocktype;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MFWoodType {
    // Not *really* a wood type, but it is necessary to add the azurite signs, fence gates, etc.
    public static final WoodType AZURITE = WoodType.register(
            createGemstoneType(
                    "azurite",
                    MFBlockSetType.AZURITE
            )
    );

    // Same as above
    public static final WoodType FLUORITE = WoodType.register(
            createGemstoneType(
                    "fluorite",
                    MFBlockSetType.FLUORITE
            )
    );

    public static final WoodType PALM = WoodType.register(
            createWoodType(
                    "palm",
                    MFBlockSetType.PALM
            )
    );

    public static final WoodType BLOODWOOD = WoodType.register(
            createWoodType(
                    "bloodwood",
                    MFBlockSetType.BLOODWOOD
            )
    );

    public static final WoodType TAINTED = WoodType.register(
            createWoodType(
                    "tainted",
                    MFBlockSetType.TAINTED
            )
    );

    public static final WoodType CHARRED = WoodType.register(
            createNetherOrEndWoodType(
                    "charred",
                    MFBlockSetType.CHARRED
            )
    );

    public static final WoodType DECREPIT = WoodType.register(
            createNetherOrEndWoodType(
                    "decrepit",
                    MFBlockSetType.DECREPIT
            )
    );

    public static final WoodType PALLID = WoodType.register(
            createNetherOrEndWoodType(
                    "pallid",
                    MFBlockSetType.PALLID
            )
    );

    private static WoodType createNetherOrEndWoodType(String name, BlockSetType blockSet){
        return new WoodType(
                MoreFeatures.MODID + ":" + name,
                blockSet,
                SoundType.NETHER_WOOD,
                SoundType.NETHER_WOOD_HANGING_SIGN,
                SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
                SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
        );
    }

    private static WoodType createGemstoneType(String name, BlockSetType blockSet){
        return new WoodType(
                MoreFeatures.MODID + ":" + name,
                blockSet,
                SoundType.AMETHYST,
                SoundType.AMETHYST_CLUSTER,
                SoundEvents.IRON_TRAPDOOR_CLOSE,
                SoundEvents.IRON_TRAPDOOR_OPEN
        );
    }

    private static WoodType createWoodType(String name, BlockSetType blockSet){
        return new WoodType(
                MoreFeatures.MODID + ":" + name,
                blockSet
        );
    }


    public static void init() {}
}
