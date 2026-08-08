package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.data.BonemealMorph;
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
    public MFDataMapsProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void gather(HolderLookup.@NonNull Provider provider) {
        final var cutCopperVSList = MFBlocks.CUT_COPPER_VERTICAL_SLAB.asList();
        final var cutCopperPillarList = MFBlocks.CUT_COPPER_PILLAR.asList();

        final var logList = MFBlocks.LOG.asList();
        final var woodList =  MFBlocks.WOOD.asList();
        final var strippedLogList = MFBlocks.STRIPPED_LOG.asList();
        final var strippedWoodList = MFBlocks.STRIPPED_WOOD.asList();

        final var bonemealMorphs = builder(MFDataMaps.BONEMEAL_MORPHS);
        bonemealMorphs.add(Blocks.NETHERRACK.builtInRegistryHolder(), new BonemealMorph(List.of(MFBlocks.CHARRED_NYLIUM.get(), Blocks.CRIMSON_NYLIUM, Blocks.WARPED_NYLIUM)), false);
        bonemealMorphs.add(Blocks.END_STONE.builtInRegistryHolder(), new BonemealMorph(List.of(MFBlocks.DECREPIT_NULLIUM.get(), MFBlocks.PALLID_NULLIUM.get())), false);

        final var oxidizables = builder(NeoForgeDataMaps.OXIDIZABLES);
        oxidizables.add(cutCopperVSList.getFirst(), new Oxidizable(cutCopperVSList.get(1).get()), false);
        oxidizables.add(cutCopperVSList.get(1), new Oxidizable(cutCopperVSList.get(2).get()), false);
        oxidizables.add(cutCopperVSList.get(2), new Oxidizable(cutCopperVSList.get(3).get()), false);

        oxidizables.add(cutCopperPillarList.getFirst(), new Oxidizable(cutCopperPillarList.get(1).get()), false);
        oxidizables.add(cutCopperPillarList.get(1), new Oxidizable(cutCopperPillarList.get(2).get()), false);
        oxidizables.add(cutCopperPillarList.get(2), new Oxidizable(cutCopperPillarList.get(3).get()), false);

        final var waxables = builder(NeoForgeDataMaps.WAXABLES);
        waxables.add(cutCopperVSList.getFirst(), new Waxable(cutCopperVSList.get(4).get()), false);
        waxables.add(cutCopperVSList.get(1), new Waxable(cutCopperVSList.get(5).get()), false);
        waxables.add(cutCopperVSList.get(2), new Waxable(cutCopperVSList.get(6).get()), false);
        waxables.add(cutCopperVSList.get(3), new Waxable(cutCopperVSList.getLast().get()), false);

        waxables.add(cutCopperPillarList.getFirst(), new Waxable(cutCopperPillarList.get(4).get()), false);
        waxables.add(cutCopperPillarList.get(1), new Waxable(cutCopperPillarList.get(5).get()), false);
        waxables.add(cutCopperPillarList.get(2), new Waxable(cutCopperPillarList.get(6).get()), false);
        waxables.add(cutCopperPillarList.get(3), new Waxable(cutCopperPillarList.getLast().get()), false);

        final var strippables = builder(NeoForgeDataMaps.STRIPPABLES);

        strippables.add(logList.getFirst(), new Strippable(strippedLogList.getFirst().get()), false);
        strippables.add(woodList.getFirst(), new Strippable(strippedWoodList.getFirst().get()), false);

        strippables.add(logList.get(1), new Strippable(strippedLogList.get(1).get()), false);
        strippables.add(woodList.get(1), new Strippable(strippedWoodList.get(1).get()), false);

        strippables.add(logList.get(2), new Strippable(strippedLogList.get(2).get()), false);
        strippables.add(woodList.get(2), new Strippable(strippedWoodList.get(2).get()), false);

        strippables.add(logList.get(3), new Strippable(strippedLogList.get(3).get()), false);
        strippables.add(woodList.get(3), new Strippable(strippedWoodList.get(3).get()), false);

        strippables.add(logList.get(4), new Strippable(strippedLogList.get(4).get()), false);
        strippables.add(woodList.get(4), new Strippable(strippedWoodList.get(4).get()), false);

        strippables.add(logList.getLast(), new Strippable(strippedLogList.getLast().get()), false);
        strippables.add(woodList.getLast(), new Strippable(strippedWoodList.getLast().get()), false);
    }
}
