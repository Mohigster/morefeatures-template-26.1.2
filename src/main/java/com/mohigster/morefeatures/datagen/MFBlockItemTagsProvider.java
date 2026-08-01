package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.references.MFBlockItemIds;
import com.mohigster.morefeatures.tag.MFBlockItemTags;
import net.minecraft.data.tags.BlockItemTagsProvider;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.BlockItemTags;

import java.util.function.Function;

public class MFBlockItemTagsProvider extends BlockItemTagsProvider {
    protected MFBlockItemTagsProvider(Function<BlockItemTagId, CombinedAppender> tagSupplier) {
        super(tagSupplier);
    }

    @Override
    protected void run() {
        tag(MFBlockItemTags.WOOL_VERTICAL_SLABS).addAll(MFBlockItemIds.WOOL_VERTICAL_SLAB.asList());
        tag(MFBlockItemTags.CONCRETE_VERTICAL_SLABS).addAll(MFBlockItemIds.CONCRETE_VERTICAL_SLAB.asList());
        tag(MFBlockItemTags.CONCRETE_SLABS).addAll(MFBlockItemIds.CONCRETE_SLAB.asList());
        tag(MFBlockItemTags.CONCRETE_STAIRS).addAll(MFBlockItemIds.CONCRETE_STAIRS.asList());
        tag(MFBlockItemTags.CUT_COPPER_VERTICAL_SLABS).addAll(MFBlockItemIds.CUT_COPPER_VERTICAL_SLAB.asList());
        tag(MFBlockItemTags.CUT_COPPER_PILLARS).addAll(MFBlockItemIds.CUT_COPPER_PILLAR.asList());
        tag(MFBlockItemTags.CONCRETE_PILLARS).addAll(MFBlockItemIds.CONCRETE_PILLAR.asList());
        tag(BlockItemTags.COMPLETES_FIND_TREE_TUTORIAL).add(
                MFBlockItemIds.BLOODWOOD_LOG,
                MFBlockItemIds.TAINTED_LOG,
                MFBlockItemIds.PALM_LOG,
                MFBlockItemIds.DECREPIT_LOG,
                MFBlockItemIds.PALLID_LOG
        );
        tag(BlockItemTags.SAPLINGS).add(
                MFBlockItemIds.BLOODWOOD_SAPLING,
                MFBlockItemIds.TAINTED_SAPLING,
                MFBlockItemIds.PALM_SAPLING,
                MFBlockItemIds.DECREPIT_SAPLING,
                MFBlockItemIds.PALLID_SAPLING
        );
    }
}
