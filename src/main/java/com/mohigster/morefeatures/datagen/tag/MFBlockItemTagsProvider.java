package com.mohigster.morefeatures.datagen.tag;

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
        this.tag(MFBlockItemTags.WOOL_VERTICAL_SLABS).addAll(MFBlockItemIds.WOOL_VERTICAL_SLAB.asList());
        this.tag(MFBlockItemTags.CONCRETE_VERTICAL_SLABS).addAll(MFBlockItemIds.CONCRETE_VERTICAL_SLAB.asList());
        this.tag(MFBlockItemTags.CUSTOM_WOODEN_VERTICAL_SLABS).addAll(MFBlockItemIds.WOODEN_VERTICAL_SLAB.asList());
        this.tag(MFBlockItemTags.CONCRETE_SLABS).addAll(MFBlockItemIds.CONCRETE_SLAB.asList());
        this.tag(MFBlockItemTags.CONCRETE_STAIRS).addAll(MFBlockItemIds.CONCRETE_STAIRS.asList());
        this.tag(MFBlockItemTags.CUT_COPPER_VERTICAL_SLABS).addAll(MFBlockItemIds.CUT_COPPER_VERTICAL_SLAB.asList());
        this.tag(MFBlockItemTags.CUT_COPPER_PILLARS).addAll(MFBlockItemIds.CUT_COPPER_PILLAR.asList());
        this.tag(MFBlockItemTags.CONCRETE_PILLARS).addAll(MFBlockItemIds.CONCRETE_PILLAR.asList());
        this.tag(MFBlockItemTags.MODDED_PLANKS).addAll(MFBlockItemIds.PLANKS.asList());
        this.tag(MFBlockItemTags.WOODEN_STAIRS).addAll(MFBlockItemIds.WOODEN_STAIRS.asList());
        this.tag(MFBlockItemTags.WOODEN_SLABS).addAll(MFBlockItemIds.WOODEN_SLAB.asList());
        this.tag(MFBlockItemTags.CUSTOM_WOODEN_FENCES).addAll(MFBlockItemIds.WOODEN_FENCE.asList());
        this.tag(MFBlockItemTags.WOODEN_FENCE_GATES).addAll(MFBlockItemIds.WOODEN_FENCE_GATE.asList());
        this.tag(MFBlockItemTags.BLOODWOOD_LOGS).add(
                MFBlockItemIds.LOG.bloodwood(),
                MFBlockItemIds.WOOD.bloodwood(),
                MFBlockItemIds.STRIPPED_LOG.bloodwood(),
                MFBlockItemIds.STRIPPED_WOOD.bloodwood()
        );
        this.tag(MFBlockItemTags.TAINTED_LOGS).add(
                MFBlockItemIds.LOG.tainted(),
                MFBlockItemIds.WOOD.tainted(),
                MFBlockItemIds.STRIPPED_LOG.tainted(),
                MFBlockItemIds.STRIPPED_WOOD.tainted()
        );
        this.tag(MFBlockItemTags.PALM_LOGS).add(
                MFBlockItemIds.LOG.palm(),
                MFBlockItemIds.WOOD.palm(),
                MFBlockItemIds.STRIPPED_LOG.palm(),
                MFBlockItemIds.STRIPPED_WOOD.palm()
        );
        this.tag(MFBlockItemTags.CHARRED_STEMS).add(
                MFBlockItemIds.LOG.charred(),
                MFBlockItemIds.WOOD.charred(),
                MFBlockItemIds.STRIPPED_LOG.charred(),
                MFBlockItemIds.STRIPPED_WOOD.charred()
        );
        this.tag(MFBlockItemTags.DECREPIT_LOGS).add(
                MFBlockItemIds.LOG.decrepit(),
                MFBlockItemIds.WOOD.decrepit(),
                MFBlockItemIds.STRIPPED_LOG.decrepit(),
                MFBlockItemIds.STRIPPED_WOOD.decrepit()
        );
        this.tag(MFBlockItemTags.PALLID_LOGS).add(
                MFBlockItemIds.LOG.pallid(),
                MFBlockItemIds.WOOD.pallid(),
                MFBlockItemIds.STRIPPED_LOG.pallid(),
                MFBlockItemIds.STRIPPED_WOOD.pallid()
        );
        this.tag(BlockItemTags.COMPLETES_FIND_TREE_TUTORIAL).addAll(MFBlockItemIds.LOG.asList());
        this.tag(BlockItemTags.SAPLINGS).add(
                MFBlockItemIds.BLOODWOOD_SAPLING,
                MFBlockItemIds.TAINTED_SAPLING,
                MFBlockItemIds.PALM_SAPLING,
                MFBlockItemIds.DECREPIT_SAPLING,
                MFBlockItemIds.PALLID_SAPLING
        );
    }
}
