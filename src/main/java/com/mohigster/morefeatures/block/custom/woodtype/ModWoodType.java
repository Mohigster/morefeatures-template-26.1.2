package com.mohigster.morefeatures.block.custom.woodtype;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodType {
    public static final WoodType PALM = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":palm",
                    BlockSetType.OAK
            )
    );

    public static final WoodType BLOODWOOD = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":bloodwood",
                    BlockSetType.OAK
            )
    );

    public static final WoodType TAINTED = WoodType.register(
            new WoodType(
                    MoreFeatures.MODID + ":tainted",
                    BlockSetType.OAK
            )
    );


    public static void init() {}
}
