package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;

public class ModBlockItemTags {
    public static final BlockItemTagId WOOL_VERTICAL_SLABS = create("wool_vertical_slab");
    public static final BlockItemTagId CUT_COPPER_VERTICAL_SLABS = create("cut_copper_vertical_slab");

    public static BlockItemTagId create(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, name);
        return BlockItemTagId.create(id, id);
    }
}
