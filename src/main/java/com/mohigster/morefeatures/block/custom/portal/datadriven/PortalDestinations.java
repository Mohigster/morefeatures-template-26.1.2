package com.mohigster.morefeatures.block.custom.portal.datadriven;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.custom.portal.key.BlockKey;
import com.mohigster.morefeatures.block.custom.portal.key.PortalKey;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class PortalDestinations extends SimpleJsonResourceReloadListener<PortalDataEntry> {
    public static final PortalDestinations INSTANCE = new PortalDestinations();

    protected List<PortalDataEntry> entries = List.of();

    private PortalDestinations() {
        super(PortalDataEntry.CODEC, FileToIdConverter.json(Directories.PORTAL_PATH));
    }

    @Override
    protected void apply(Map<Identifier, PortalDataEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.entries = map.values().stream()
                .sorted(Comparator.comparingInt(Record::hashCode))
                .toList();
    }

    public static EntityType<?> getEntity(ResourceKey<Level> level) {
        for (PortalDataEntry entry : INSTANCE.entries) {
            if (entry.targetLevel().equals(level)) {
                return entry.entitySpawnedByPortal();
            }
        }

        return EntityTypes.ZOMBIFIED_PIGLIN;
    }

    public static Optional<PortalKey> getPortalKey(ResourceKey<Level> level) {
        for (PortalDataEntry entry : INSTANCE.entries) {
            if (entry.targetLevel().equals(level)) {
                return Optional.of(entry.portalKey());
            }
        }

        return Optional.empty();
    }

    public static Optional<BlockKey> getBlockKey(ResourceKey<Level> level) {
        for (PortalDataEntry entry : INSTANCE.entries) {
            if (entry.targetLevel().equals(level)) {
                if (entry.portalKey() instanceof BlockKey key) {
                    MoreFeatures.LOGGER.debug("Found block key {} for: {} ", key, entry.targetLevel());

                    return Optional.of(key);
                }
            }
        }

        return Optional.empty();
    }

    public static ResourceKey<Level> getTargetLevel(BlockState state) {
        for (PortalDataEntry entry : INSTANCE.entries) {
            if (entry.portalFrame().is(state.getBlock())){
                return entry.targetLevel();
            }
        }

        return Level.NETHER; // Fallback to the nether if all else fails
    }

    public static BlockState getFrameState(BlockState state) {
        for (PortalDataEntry entry : INSTANCE.entries) {
            if (entry.portalFrame().isAir()) {
                throw new IllegalStateException("Air portal frames are not allowed!");
            }
            else if (state.is(entry.portalFrame().getBlock())) {
                return entry.portalFrame();
            }
        }

        return Blocks.OBSIDIAN.defaultBlockState(); // Fallback if nothing is found
    }

    public static boolean isFrame(BlockState state) {
        if (INSTANCE.entries.isEmpty()) return false;

        AtomicBoolean toReturn = new AtomicBoolean(false);

        INSTANCE.entries.forEach(entry -> {
            // Check the block instead of the block state so that true is returned regardless of state
            if (state.is(entry.portalFrame().getBlock())) {
                toReturn.set(true);
            }
        });

        return toReturn.get();
    }
}
