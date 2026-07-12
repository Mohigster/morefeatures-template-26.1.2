package com.mohigster.morefeatures.block.custom.blocktype;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class MFWoodType {
    public static final WoodType AZURITE = WoodType.register( // Not *really* a wood type, but it is necessary to add the azurite sign
            new WoodType(
                    MoreFeatures.MODID + ":azurite",
                    MFBlockSetType.AZURITE,
                    SoundType.AMETHYST,
                    SoundType.AMETHYST_CLUSTER,
                    SoundEvents.IRON_TRAPDOOR_CLOSE,
                    SoundEvents.IRON_TRAPDOOR_OPEN
            )
    );

    public static final WoodType PALM = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":palm",
                    MFBlockSetType.PALM
            )
    );

    public static final WoodType BLOODWOOD = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":bloodwood",
                    MFBlockSetType.BLOODWOOD
            )
    );

    public static final WoodType TAINTED = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":tainted",
                    MFBlockSetType.TAINTED
            )
    );

    public static final WoodType DECREPIT = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":decrepit",
                    MFBlockSetType.DECREPIT,
                    SoundType.NETHER_WOOD,
                    SoundType.NETHER_WOOD_HANGING_SIGN,
                    SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
            )
    );

    public static final WoodType PALLID = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":pallid",
                    MFBlockSetType.PALLID,
                    SoundType.NETHER_WOOD,
                    SoundType.NETHER_WOOD_HANGING_SIGN,
                    SoundEvents.NETHER_WOOD_FENCE_GATE_CLOSE,
                    SoundEvents.NETHER_WOOD_FENCE_GATE_OPEN
            )
    );


    public static void init() {}
}
