package com.mohigster.morefeatures.item.custom.metaldetector;

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
            if (state.is(entry.inputTag())) {
                return entry.durabilityCost();
            }
        }
        return 1; // Default fallback cost
    }
}
