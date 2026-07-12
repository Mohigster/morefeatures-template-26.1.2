package com.mohigster.morefeatures.toolmaterial;


import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import static com.mohigster.morefeatures.tag.MFItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE;

public class MFToolMaterial {
    private MFToolMaterial(){
    }
    public static final ToolMaterial BISMUTH_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2849, 13.5f, 5.5f, 20, BISMUTH_TOOL_MATERIAL_REPAIRABLE);
}
