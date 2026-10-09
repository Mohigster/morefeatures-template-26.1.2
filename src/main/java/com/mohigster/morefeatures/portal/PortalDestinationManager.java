package com.mohigster.morefeatures.portal;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.core.RestrictedTo;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import com.mohigster.morefeatures.data.resources.references.MFBlockIds;
import com.mohigster.morefeatures.portal.frame.CustomPortalShape;
import com.mohigster.morefeatures.portal.key.PortalKey;
import com.mohigster.morefeatures.util.Directories;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.AddServerReloadListenersEvent;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public final class PortalDestinationManager extends SimpleJsonResourceReloadListener<PortalEntry> {
    private static final PortalDestinationManager INSTANCE = new PortalDestinationManager();

    private List<PortalEntry> entries = List.of();

    private PortalDestinationManager() {
        super(PortalEntry.CODEC, FileToIdConverter.json(Directories.PORTAL_PATH));
    }

    @Override
    protected void apply(Map<Identifier, PortalEntry> map, ResourceManager resourceManager, ProfilerFiller profiler) {
        this.entries = map.values().stream()
                .sorted(Comparator.comparingInt(Record::hashCode))
                .toList();
    }

    public static Optional<EntityType<?>> getEntity(ResourceKey<Level> level) {
        for (PortalEntry entry : getEntries()) {
            if (entry.targetLevel().equals(level)) {
                return entry.entitySpawnedByPortal();
            }
        }

        return Optional.empty();
    }

    public static Optional<PortalKey> getPortalKey(ResourceKey<Level> level) {
        for (PortalEntry entry : getEntries()) {
            if (entry.targetLevel().equals(level)) {
                MoreFeatures.LOGGER.debug("Found Portal Key: {}", entry.portalKey());

                return Optional.of(entry.portalKey());
            }
        }

        return Optional.empty();
    }

    public static Optional<PortalKey> getPortalKey(BlockState state) {
        return getPortalKey(getTargetLevel(state));
    }

    public static ResourceKey<Level> getTargetLevel(Level level, BlockPos pos) {
        return getTargetLevel(scanForPotentialFrame(level, pos));
    }

    public static ResourceKey<Level> getTargetLevel(BlockState state) {
        for (PortalEntry entry : getEntries()) {
            if (entry.frame().blockState().is(state.getBlock())){
                return entry.targetLevel();
            }
        }

        return Level.NETHER; // Fallback to the nether if all else fails
    }

    public static BlockState getFrame(Level level, BlockPos pos) {
        return validateFrame(scanForPotentialFrame(level, pos));
    }

    private static BlockState validateFrame(BlockState state) {
        for (PortalEntry entry : getEntries()) {
            if (entry.frame().blockState().isAir()) {
                throw new IllegalStateException("Air portal frames are not allowed!");
            } else if (frameIsValid(entry, state)) {
                MoreFeatures.LOGGER.debug("Validated frame state {} for: {}", state, entry.targetLevel());
                return state;
            }
        }

        return Blocks.AIR.defaultBlockState(); // Air means that the frame was invalid
    }

    private static BlockState scanForPotentialFrame(Level level, BlockPos startPos) {
        BlockPos.MutableBlockPos scanPos = startPos.mutable();

        MutableInt scanHeight = new MutableInt();

        while (level.isInsideBuildHeight(scanPos) && scanHeight.intValue() < CustomPortalShape.MAX_HEIGHT) {
            scanPos.move(Direction.UP);

            BlockState state = level.getBlockState(scanPos);

            // No point scanning through solid blocks that aren't frames, as they would block the portal anyway
            if (!state.isAir() && !state.is(MFBlockIds.PORTAL_BLOCK)) {
                return state;
            }

            scanHeight.increment();
        }

        return Blocks.AIR.defaultBlockState();
    }

    private static boolean frameIsValid(PortalEntry entry, BlockState state) {
        if (entry.frame().stateSensitive()) {
            return state.equals(entry.frame().blockState());
        } else {
            return state.is(entry.frame().blockState().getBlock());
        }
    }

    private List<PortalEntry> entries() {
        return this.entries;
    }

    public static List<PortalEntry> getEntries() {
        return INSTANCE.entries();
    }

    @RestrictedTo(MoreFeatures.class)
    public static void init(AddServerReloadListenersEvent event) {
        event.addListener(
                MFIdentifier.withMfNamespace(Directories.PORTAL_PATH),
                INSTANCE
        );
    }
}
