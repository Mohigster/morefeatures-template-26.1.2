package com.mohigster.morefeatures.item.custom.metaldetector;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.data.tag.MFBlockTags;
import com.mohigster.morefeatures.util.Directories;
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
        super(DetectorCostEntry.CODEC, FileToIdConverter.json(Directories.METAL_DETECTOR_PATH));
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
            if (state.is(entry.inputValues())) {
                // Just a lil Easter egg
                if (entry.inputValues().unwrapKey().get().equals(MFBlockTags.METAL_DETECTOR_BISMUTH_COST) && !state.is(MFBlocks.BISMUTH_BLOCK)) {
                    MoreFeatures.LOGGER.debug("wait... that's not bismuth in the bismuth cost tag... isn't that ILLEGAL???!!!");
                }

                // This is the actual cost input
                return entry.durabilityCost();
            }
        }
        return 1; // Default fallback cost
    }
}
