package com.mohigster.morefeatures.item.custom.metaldetector;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.tag.MFBlockTags;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Map;

public class MetalDetectorCosts extends SimpleJsonResourceReloadListener<DetectorCostEntry> {
    public static final MetalDetectorCosts INSTANCE = new MetalDetectorCosts();

    private List<DetectorCostEntry> entries = List.of();

    protected MetalDetectorCosts() {
        super(DetectorCostEntry.CODEC, FileToIdConverter.json("metal_detector_costs"));
    }

    @NullMarked
    @Override
    protected void apply(Map<Identifier, DetectorCostEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.entries = map.values().stream()
                .sorted((a, b) -> Integer.compare(b.durabilityCost(), a.durabilityCost()))
                .toList();
    }

    public int getCost(BlockState state) {
        for (DetectorCostEntry entry : entries) {
            // Just a bit of a joke here. The tag will still work and give them the desired cost, it will just poke a bit of fun in the console.

            // The joke is that all the other tags have generic names e.g. high or low. Bismuth is exceptionally rare, so it got its own tag with
            // a higher cost than any other in the base mod. This will only fire if another modder or datapack creator adds a different block to this tag
            if (entry.inputTag().equals(MFBlockTags.METAL_DETECTOR_BISMUTH_COST) && !state.is(MFBlocks.BISMUTH_ORE.get())){
                MoreFeatures.LOGGER.info("Wait... there's something that's not bismuth in the bismuth cost tag... that's illegal!");
            }

            if (state.is(entry.inputTag())) {
                return entry.durabilityCost();
            }
        }
        return 1; // Default fallback cost
    }
}
