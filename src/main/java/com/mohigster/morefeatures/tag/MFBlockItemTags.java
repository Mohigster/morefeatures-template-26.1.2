package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;

public class MFBlockItemTags {
    public static final BlockItemTagId WOOL_VERTICAL_SLABS = morefeaturesTag("wool_vertical_slab");
    public static final BlockItemTagId CUT_COPPER_VERTICAL_SLABS = morefeaturesTag("cut_copper_vertical_slab");

    private static BlockItemTagId morefeaturesTag(String name) {
        Identifier id = MFIdentifier.withMfNamespace(name);
        return create(id);
    }

    public static BlockItemTagId create(Identifier id) {
        return new BlockItemTagId(TagKey.create(Registries.BLOCK, id), TagKey.create(Registries.ITEM, id));
    }
}
