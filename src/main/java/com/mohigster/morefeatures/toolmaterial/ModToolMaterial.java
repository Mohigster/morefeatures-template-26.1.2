package com.mohigster.morefeatures.toolmaterial;


import com.mohigster.morefeatures.tag.ModBlockTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ToolMaterial;

import static com.mohigster.morefeatures.tag.ModItemTags.BISMUTH_TOOL_MATERIAL_REPAIRABLE;

public class ModToolMaterial {
    private ModToolMaterial(){

    }

    public static final ToolMaterial BISMUTH_TOOL_MATERIAL = new ToolMaterial(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, 2849, 13.5f, 3.5f, 20, BISMUTH_TOOL_MATERIAL_REPAIRABLE);

}
