package com.mohigster.morefeatures.data.generators.tag;

import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.data.resources.references.MFBlockItemIds;
import com.mohigster.morefeatures.data.tag.MFBlockItemTags;
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
        this.tag(MFBlockItemTags.CUT_COPPER_VERTICAL_SLABS).addAll(MFBlockItemIds.CUT_COPPER_VERTICAL_SLAB.asList());
        this.tag(MFBlockItemTags.CUT_COPPER_PILLARS).addAll(MFBlockItemIds.CUT_COPPER_PILLAR.asList());
        this.tag(MFBlockItemTags.CONCRETE_PILLARS).addAll(MFBlockItemIds.CONCRETE_PILLAR.asList());
        this.tag(MFBlockItemTags.MODDED_PLANKS).addAll(MFBlockItemIds.PLANKS.asList());
        this.tag(MFBlockItemTags.WOODEN_STAIRS).addAll(MFBlockItemIds.WOODEN_STAIRS.asList());
        this.tag(MFBlockItemTags.WOODEN_SLABS).addAll(MFBlockItemIds.WOODEN_SLAB.asList());
        this.tag(MFBlockItemTags.CUSTOM_WOODEN_FENCES).addAll(MFBlockItemIds.WOODEN_FENCE.asList());
        this.tag(MFBlockItemTags.WOODEN_FENCE_GATES).addAll(MFBlockItemIds.WOODEN_FENCE_GATE.asList());
        WoodTypeCollection.SETS.forEach(set ->
                this.tag(MFBlockItemTags.LOGS.pick(set))
                        .add(MFBlockItemIds.LOG.pick(set))
                        .add(MFBlockItemIds.WOOD.pick(set))
                        .add(MFBlockItemIds.STRIPPED_LOG.pick(set))
                        .add(MFBlockItemIds.STRIPPED_WOOD.pick(set))
        );

        WoodTypeCollection.SETS.forEach(set ->
                this.tag(MFBlockItemTags.WOODEN.pick(set))
                        .addTag(MFBlockItemTags.LOGS.pick(set))
                        .add(MFBlockItemIds.PLANKS.pick(set))
                        .add(MFBlockItemIds.WOODEN_STAIRS.pick(set))
                        .add(MFBlockItemIds.WOODEN_SLAB.pick(set))
                        .add(MFBlockItemIds.WOODEN_BUTTON.pick(set))
                        .add(MFBlockItemIds.WOODEN_PRESSURE_PLATE.pick(set))
                        .add(MFBlockItemIds.WOODEN_FENCE.pick(set))
                        .add(MFBlockItemIds.WOODEN_FENCE_GATE.pick(set))
                        .add(MFBlockItemIds.WOODEN_TRAPDOOR.pick(set))
                        .add(MFBlockItemIds.WOODEN_DOOR.pick(set))
                        .add(MFBlockItemIds.WOODEN_SHELF.pick(set))
        );
        this.tag(MFBlockItemTags.MODDED_LOGS)
                .addAll(MFBlockItemIds.LOG.asList())
                .addAll(MFBlockItemIds.WOOD.asList())
                .addAll(MFBlockItemIds.STRIPPED_LOG.asList())
                .addAll(MFBlockItemIds.STRIPPED_WOOD.asList());
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
