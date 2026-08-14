package com.mohigster.morefeatures.block.custom.data;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FlammableHelper {
    public record FlammabilityEntry(Block block, int flammability, int fireSpreadSpeed) {}

    private static final List<FlammabilityEntry> DEFAULTS = new ArrayList<>();

    public static void bootstrap() {
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.LOG.pick(set).get(), 5, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOOD.pick(set).get(), 5, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.STRIPPED_LOG.pick(set).get(), 5, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.STRIPPED_WOOD.pick(set).get(), 5, 5));

        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.PLANKS.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOODEN_STAIRS.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOODEN_SLAB.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOODEN_VERTICAL_SLAB.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOODEN_FENCE.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOODEN_FENCE_GATE.pick(set).get(), 20, 5));

        ColorCollection.VALUES.forEach(color ->
                add(MFBlocks.WOOL_VERTICAL_SLAB.pick(color).get(), 20, 5));

        WoodTypeCollection.SETS.forEach(set ->
                add(set, MFBlocks.WOODEN_SHELF.pick(set).get(), 20, 30));

        WoodTypeCollection.SETS.forEach(set ->
                add(set, set.getLeavesOrWart().get(), 60, 30));

        add(MFBlocks.OAK_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.SPRUCE_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.BIRCH_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.ACACIA_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.JUNGLE_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.DARK_OAK_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.MANGROVE_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.CHERRY_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.BAMBOO_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.get(), 20, 5);
        add(MFBlocks.PALE_OAK_VERTICAL_SLAB.get(), 20, 5);
    }

    public static void add(Block block, int flammability, int fireSpreadSpeed) {
        DEFAULTS.add(new FlammabilityEntry(block, flammability, fireSpreadSpeed));
    }

    public static void add(WoodSet set, Block block, int flammability, int fireSpreadSpeed) {
        if (set.isFlammable()) add(block, flammability, fireSpreadSpeed);
    }

    public static List<FlammabilityEntry> getDefaults() {
        bootstrap();
        return Collections.unmodifiableList(DEFAULTS);
    }
}
