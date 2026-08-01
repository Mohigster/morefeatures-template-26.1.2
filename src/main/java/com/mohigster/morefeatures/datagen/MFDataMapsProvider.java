package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.data.BonemealMorphData;
import com.mohigster.morefeatures.references.MFBlockItemIds;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;
import net.neoforged.neoforge.registries.datamaps.builtin.Oxidizable;
import net.neoforged.neoforge.registries.datamaps.builtin.Strippable;
import net.neoforged.neoforge.registries.datamaps.builtin.Waxable;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("deprecation")
public class MFDataMapsProvider extends DataMapProvider {

    /**
     * Create a new provider.
     *
     * @param packOutput     the output location
     * @param lookupProvider a {@linkplain CompletableFuture} supplying the registries
     */
    public MFDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NonNull Provider provider) {
        final var cutCopperVSList = MFBlocks.CUT_COPPER_VERTICAL_SLAB.asList();
        final var cutCopperPillarList = MFBlocks.CUT_COPPER_PILLAR.asList();

        final var bonemealMorphs = builder(MFDataMaps.BONEMEAL_MORPHS);
        bonemealMorphs.add(Blocks.NETHERRACK.builtInRegistryHolder(), new BonemealMorphData(List.of(MFBlocks.CHARRED_NYLIUM.get(), Blocks.CRIMSON_NYLIUM, Blocks.WARPED_NYLIUM)), false);
        bonemealMorphs.add(Blocks.END_STONE.builtInRegistryHolder(), new BonemealMorphData(List.of(MFBlocks.DECREPIT_NULLIUM.get(), MFBlocks.PALLID_NULLIUM.get())), false);

        final var oxidizables = builder(NeoForgeDataMaps.OXIDIZABLES);
        oxidizables.add(cutCopperVSList.getFirst().getKey(), new Oxidizable(cutCopperVSList.get(1).get()), false);
        oxidizables.add(cutCopperVSList.get(1).getKey(), new Oxidizable(cutCopperVSList.get(2).get()), false);
        oxidizables.add(cutCopperVSList.get(2).getKey(), new Oxidizable(cutCopperVSList.get(3).get()), false);

        oxidizables.add(cutCopperPillarList.getFirst().getKey(), new Oxidizable(cutCopperPillarList.get(1).get()), false);
        oxidizables.add(cutCopperPillarList.get(1).getKey(), new Oxidizable(cutCopperPillarList.get(2).get()), false);
        oxidizables.add(cutCopperPillarList.get(2).getKey(), new Oxidizable(cutCopperPillarList.get(3).get()), false);

        final var waxables = builder(NeoForgeDataMaps.WAXABLES);
        waxables.add(cutCopperVSList.getFirst().getKey(), new Waxable(cutCopperVSList.get(4).get()), false);
        waxables.add(cutCopperVSList.get(1).getKey(), new Waxable(cutCopperVSList.get(5).get()), false);
        waxables.add(cutCopperVSList.get(2).getKey(), new Waxable(cutCopperVSList.get(6).get()), false);
        waxables.add(cutCopperVSList.get(3).getKey(), new Waxable(cutCopperVSList.getLast().get()), false);

        waxables.add(cutCopperPillarList.getFirst().getKey(), new Waxable(cutCopperPillarList.get(4).get()), false);
        waxables.add(cutCopperPillarList.get(1).getKey(), new Waxable(cutCopperPillarList.get(5).get()), false);
        waxables.add(cutCopperPillarList.get(2).getKey(), new Waxable(cutCopperPillarList.get(6).get()), false);
        waxables.add(cutCopperPillarList.get(3).getKey(), new Waxable(cutCopperPillarList.getLast().get()), false);

        final var strippables = builder(NeoForgeDataMaps.STRIPPABLES);

        strippables.add(MFBlockItemIds.BLOODWOOD_LOG.block(), new Strippable(MFBlocks.STRIPPED_BLOODWOOD_LOG.get()), false);
        strippables.add(MFBlockItemIds.BLOODWOOD.block(), new Strippable(MFBlocks.STRIPPED_BLOODWOOD.get()), false);

        strippables.add(MFBlockItemIds.TAINTED_LOG.block(), new Strippable(MFBlocks.STRIPPED_TAINTED_LOG.get()), false);
        strippables.add(MFBlockItemIds.TAINTED_WOOD.block(), new Strippable(MFBlocks.STRIPPED_TAINTED_WOOD.get()), false);

        strippables.add(MFBlockItemIds.PALM_LOG.block(), new Strippable(MFBlocks.STRIPPED_PALM_LOG.get()), false);
        strippables.add(MFBlockItemIds.PALM_WOOD.block(), new Strippable(MFBlocks.STRIPPED_PALM_WOOD.get()), false);

        strippables.add(MFBlockItemIds.CHARRED_STEM.block(), new Strippable(MFBlocks.STRIPPED_CHARRED_STEM.get()), false);
        strippables.add(MFBlockItemIds.CHARRED_HYPHAE.block(), new Strippable(MFBlocks.STRIPPED_CHARRED_HYPHAE.get()), false);

        strippables.add(MFBlockItemIds.DECREPIT_LOG.block(), new Strippable(MFBlocks.STRIPPED_DECREPIT_LOG.get()), false);
        strippables.add(MFBlockItemIds.DECREPIT_WOOD.block(), new Strippable(MFBlocks.STRIPPED_DECREPIT_WOOD.get()), false);

        strippables.add(MFBlockItemIds.PALLID_LOG.block(), new Strippable(MFBlocks.STRIPPED_PALLID_LOG.get()), false);
        strippables.add(MFBlockItemIds.PALLID_WOOD.block(), new Strippable(MFBlocks.STRIPPED_PALLID_WOOD.get()), false);
    }
}
