package com.mohigster.morefeatures.block.custom.portal;

import com.mohigster.morefeatures.events.MFEventHooks;
import com.mohigster.morefeatures.portal.frame.CustomPortalShape;
import com.mohigster.morefeatures.portal.PortalDestinationManager;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NetherPortalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.TeleportTransition;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class PortalBlock extends NetherPortalBlock {
    private static final int RAND_BOUND = 2000;

    public PortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(
            BlockState state,
            ServerLevel server,
            BlockPos pos,
            RandomSource random
    ) {
        if (shouldSpawnEntity(server, random, pos)) {
            while(server.getBlockState(pos).is(this)) {
                pos = pos.below();
            }

            ResourceKey<Level> targetLevel = PortalDestinationManager.getTargetLevel(server, pos);
            Optional<EntityType<?>> optionalType = PortalDestinationManager.getEntity(targetLevel);

            if (optionalType.isEmpty()) return;

            EntityType<?> type = optionalType.get();

            if (!server.getBlockState(pos).isValidSpawn(server, pos, type)) return;

            // Fire an event when attempting to spawn an entity
            Entity entity = MFEventHooks.onPortalTrySpawnEntity(targetLevel, type.spawn(server, pos, EntitySpawnReason.STRUCTURE));

            if (entity == null) return;

            Entity vehicle = entity.getVehicle();

            if (vehicle == null) return;

            vehicle.setPortalCooldown();
        }
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(
            ServerLevel currentLevel,
            Entity entity,
            BlockPos portalEntryPos
    ) {
        ResourceKey<Level> targetLevel = PortalDestinationManager.getTargetLevel(currentLevel, portalEntryPos);

        ResourceKey<Level> newDimension = currentLevel.dimension() == targetLevel ? Level.OVERWORLD : targetLevel;
        ServerLevel newLevel = currentLevel.getServer().getLevel(newDimension);

        if (newLevel == null) {
            return null;
        } else {
            boolean leavingOverworld = newLevel.dimension() == targetLevel;
            WorldBorder newWorldBorder = newLevel.getWorldBorder();

            double teleportationScale = DimensionType.getTeleportationScale(
                    currentLevel.dimensionType(),
                    newLevel.dimensionType()
            );

            BlockPos approximateExitPos = newWorldBorder.clampToBounds(
                    entity.getX() * teleportationScale, entity.getY(),
                    entity.getZ() * teleportationScale
            );

            return this.getExitPortal(newLevel, entity, portalEntryPos,
                    approximateExitPos, leavingOverworld, newWorldBorder);
        }
    }

    @Override
    protected BlockState updateShape(
            BlockState state,
            LevelReader level,
            ScheduledTickAccess ticks,
            BlockPos pos,
            Direction directionToNeighbour,
            BlockPos neighbourPos,
            BlockState neighbourState,
            RandomSource random
    ) {
        Direction.Axis updateAxis = directionToNeighbour.getAxis();
        Direction.Axis axis = state.getValue(AXIS);
        boolean wrongAxis = axis != updateAxis && updateAxis.isHorizontal();
        return !wrongAxis && !neighbourState.is(this)
                && !CustomPortalShape.findAnyCustomShape(level, pos, axis).isComplete()
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(
                        state, level,
                        ticks, pos,
                        directionToNeighbour,
                        neighbourPos,
                        neighbourState,
                        random
                );
    }

    private static boolean shouldSpawnEntity(ServerLevel server, RandomSource random, BlockPos pos) {
        return server.isSpawningMonsters()
                && server.getLevelData().getDifficulty() != Difficulty.PEACEFUL
                && server.environmentAttributes().getValue(
                        EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, pos)
                && random.nextInt(RAND_BOUND) < server.getDifficulty().getId()
                && server.anyPlayerCloseEnoughForSpawning(pos);
    }
}
