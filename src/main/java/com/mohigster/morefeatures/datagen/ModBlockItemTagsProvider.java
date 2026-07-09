package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.block.references.ModBlockItemIds;
import com.mohigster.morefeatures.tag.ModBlockItemTags;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.tags.BlockItemTagId;

import java.util.function.Function;

public class ModBlockItemTagsProvider extends BlockItemTagsProvider {
    protected ModBlockItemTagsProvider(Function<BlockItemTagId, CombinedAppender> tagSupplier) {
        super(tagSupplier);
    }

    @Override
    protected void run() {
        tag(ModBlockItemTags.WOOL_VERTICAL_SLABS).addAll(ModBlockItemIds.WOOL_VERTICAL_SLAB.asList());
        tag(ModBlockItemTags.CUT_COPPER_VERTICAL_SLABS).addAll(ModBlockItemIds.CUT_COPPER_VERTICAL_SLAB.asList());
    }
}
