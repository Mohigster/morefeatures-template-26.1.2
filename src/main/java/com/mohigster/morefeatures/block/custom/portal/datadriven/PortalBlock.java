package com.mohigster.morefeatures.block.custom.portal.datadriven;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySpawnReason;
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

public class PortalBlock extends NetherPortalBlock {
    public PortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (level.isSpawningMonsters()
                && level.getLevelData().getDifficulty() != Difficulty.PEACEFUL
                && level.environmentAttributes().getValue(
                        EnvironmentAttributes.NETHER_PORTAL_SPAWNS_PIGLINS, pos
        ) && random.nextInt(2000) < level.getDifficulty().getId()
                && level.anyPlayerCloseEnoughForSpawning(pos)
        ) {
            while(level.getBlockState(pos).is(this)) {
                pos = pos.below();
            }

            ResourceKey<Level> targetLevel = PortalDestinations.getTargetLevel(scanForFrameState(level, pos));

            if (level.getBlockState(pos).isValidSpawn(level, pos, PortalDestinations.getEntity(targetLevel))) {
                Entity entity = PortalDestinations.getEntity(targetLevel).spawn(level, pos.above(), EntitySpawnReason.STRUCTURE);
                if (entity != null) {
                    entity.setPortalCooldown();
                    Entity vehicle = entity.getVehicle();
                    if (vehicle != null) {
                        vehicle.setPortalCooldown();
                    }
                }
            }
        }
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos) {
        ResourceKey<Level> targetLevel = PortalDestinations.getTargetLevel(scanForFrameState(currentLevel, portalEntryPos));

        ResourceKey<Level> newDimension = currentLevel.dimension() == targetLevel ? Level.OVERWORLD : targetLevel;
        ServerLevel newLevel = currentLevel.getServer().getLevel(newDimension);

        if (newLevel == null) {
            return null;
        } else {
            boolean leavingOverworld = newLevel.dimension() == targetLevel;
            WorldBorder newWorldBorder = newLevel.getWorldBorder();

            double teleportationScale = DimensionType.getTeleportationScale(currentLevel.dimensionType(), newLevel.dimensionType());

            BlockPos approximateExitPos = newWorldBorder.clampToBounds(
                    entity.getX() * teleportationScale, entity.getY(),
                    entity.getZ() * teleportationScale
            );

            return this.getExitPortal(newLevel, entity, portalEntryPos,
                    approximateExitPos, leavingOverworld, newWorldBorder);
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess ticks, BlockPos pos, Direction directionToNeighbour, BlockPos neighbourPos, BlockState neighbourState, RandomSource random) {
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

    public static BlockState scanForFrameState(Level level, BlockPos startPos) {
        BlockPos.MutableBlockPos scanPos = startPos.mutable();

        int scanHeight = 0;

        while (level.isInsideBuildHeight(scanPos) && scanHeight <= CustomPortalShape.MAX_HEIGHT) {
            BlockState state = level.getBlockState(scanPos);

            if (PortalDestinations.isFrame(state)) {
                return state;
            }

            scanHeight++;

            scanPos.move(Direction.UP);
        }

        return Blocks.AIR.defaultBlockState();
    }
}
