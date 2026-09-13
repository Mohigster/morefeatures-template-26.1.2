package com.mohigster.morefeatures.block.custom.data;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodCollection;
import com.mohigster.morefeatures.block.collection.vanilla.VanillaWoodSet;
import com.mohigster.morefeatures.block.collection.wood.WoodSet;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlammableHelper {
    public record DefaultEntry(Block block, int flammability, int fireSpreadSpeed) {}

    private static final List<DefaultEntry> DEFAULTS = new ArrayList<>();

    public static final FlammableHelper INSTANCE = new FlammableHelper();

    protected void bootstrap() {
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.LOG.pick(set).get(), 5, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOOD.pick(set).get(), 5, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.STRIPPED_LOG.pick(set).get(), 5, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.STRIPPED_WOOD.pick(set).get(), 5, 5));

        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.PLANKS.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOODEN_STAIRS.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOODEN_SLAB.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOODEN_VERTICAL_SLAB.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOODEN_FENCE.pick(set).get(), 20, 5));
        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOODEN_FENCE_GATE.pick(set).get(), 20, 5));

        ColorCollection.VALUES.forEach(color ->
                this.add(MFBlocks.WOOL_VERTICAL_SLAB.pick(color).get(), 20, 5));

        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.WOODEN_SHELF.pick(set).get(), 20, 30));

        WoodTypeCollection.SETS.forEach(set ->
                this.add(set, set.leavesOrWart().get(), 60, 30));

        VanillaWoodCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.VANILLA_WOOD_VERTICAL_SLAB.pick(set).get(), 20, 5));

        VanillaWoodCollection.SETS.forEach(set ->
                this.add(set, MFBlocks.VANILLA_WOOD_PILLAR.pick(set).get(), 15, 3));
    }

    protected void add(Block block, int flammability, int fireSpreadSpeed) {
        DEFAULTS.add(new DefaultEntry(block, flammability, fireSpreadSpeed));
    }

    protected void add(WoodSet set, Block block, int flammability, int fireSpreadSpeed) {
        if (set.isFlammable()) this.add(block, flammability, fireSpreadSpeed);
    }

    protected void add(VanillaWoodSet set, Block block, int flammability, int fireSpreadSpeed) {
        if (set.isFlammable()) this.add(block, flammability, fireSpreadSpeed);
    }

    public List<DefaultEntry> getDefaults() {
        this.bootstrap();
        return Collections.unmodifiableList(DEFAULTS);
    }
}
