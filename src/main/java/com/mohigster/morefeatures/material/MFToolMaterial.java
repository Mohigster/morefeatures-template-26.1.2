package com.mohigster.morefeatures.material;


import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import static com.mohigster.morefeatures.tag.MFItemTags.*;

public class MFToolMaterial {

    private MFToolMaterial(){}

    public static final ToolMaterial BISMUTH_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2849, 13.5f, 5.5f, 20, BISMUTH_TOOL_MATERIALS);
    public static final ToolMaterial CARBON_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_DIAMOND_TOOL, 2031, 9.0F, 4.0F, 15, CARBON_TOOL_MATERIALS);
}
