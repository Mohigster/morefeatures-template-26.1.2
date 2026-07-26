package com.mohigster.morefeatures.block.entity.custom;

import com.mohigster.morefeatures.block.custom.temporaldilator.TemporalDilatorBlock;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TemporalDilatorBlockEntity extends BlockEntity {
    private static final int RADIUS = 10; // blocks
    private static final int TICKS_PER_ACCELERATION = 5; // how often to force extra ticks

    public TemporalDilatorBlockEntity(BlockPos worldPosition, BlockState blockState) {
        super(MFBlockEntities.TEMPORAL_DILATOR_BE.get(), worldPosition, blockState);
    }

    public static void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide() || !(level instanceof ServerLevel serverLevel)) return;

        boolean slowMode = state.getValue(TemporalDilatorBlock.SLOW_MODE);

        if (!slowMode) {
            // Accelerate blocks
            accelerateNearbyBlocks(serverLevel, pos);
        } else {
            // Slow mobs
            slowNearbyMobs(level, pos);
        }
    }

    private static void accelerateNearbyBlocks(ServerLevel level, BlockPos center) {
        for (int dx = -RADIUS; dx <= RADIUS; dx++) {
            for (int dy = -RADIUS; dy <= RADIUS; dy++) {
                for (int dz = -RADIUS; dz <= RADIUS; dz++) {
                    BlockPos targetPos = center.offset(dx, dy, dz);
                    if (center.distSqr(targetPos) > RADIUS * RADIUS) continue;

                    BlockState targetState = level.getBlockState(targetPos);
                    if (targetState.isRandomlyTicking()) {
                        if (level.getRandom().nextInt(TICKS_PER_ACCELERATION) == 0) {
                            targetState.randomTick(level, targetPos, level.getRandom());
                        }
                    }
                }
            }
        }
    }

    private static void slowNearbyMobs(Level level, BlockPos center) {
        AABB area = new AABB(center).inflate(RADIUS);
        List<Entity> entities = level.getEntities(null, area);

        for (Entity entity : entities) {
            if (entity instanceof Mob mob && !mob.isDeadOrDying()) {
                double distSq = entity.position().distanceToSqr(center.getX() + 0.5, center.getY() + 0.5, center.getZ() + 0.5);
                double maxDistSq = RADIUS * RADIUS;
                if (distSq >= maxDistSq) continue;

                double factor = 1.0 - (distSq / maxDistSq);


                mob.setDeltaMovement(mob.getDeltaMovement().multiply(0.6 * factor, 0.85 * factor, 0.6 * factor));
            }
        }
    }
}
