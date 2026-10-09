package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public sealed interface AreaOfEffect permits GenericAOEWandItem {
    int ringCount();

    double radius();

    int floorScanDistance();

    default List<BlockPos> calculateRingPositions(Level level, Player player) {
        List<BlockPos> positions = new ArrayList<>();

        int totalRings = Math.max(1, this.ringCount());

        double radiusStep = this.radius() / totalRings; // Distance between each ring. Ensures they are evenly spaced apart.

        int playerY = player.getBlockY();

        for (int ringIndex = 1; ringIndex <= totalRings; ringIndex++) {
            double currentRadius = radiusStep * ringIndex;

            int pointCount = Math.max(4, Mth.floor(currentRadius * 4));

            for (int i = 0; i < pointCount; i++) {
                float angle = (float) (i * 2 * Math.PI / pointCount);

                double xOffset = Mth.cos(angle) * currentRadius;
                double zOffset = Mth.sin(angle) * currentRadius;

                int targetX = Mth.floor(player.getX() + xOffset);
                int targetZ = Mth.floor(player.getZ() + zOffset);

                BlockPos.MutableBlockPos mutablePos = new BlockPos.MutableBlockPos(targetX, playerY, targetZ);
                int groundY = findLocalFloor(level, mutablePos);

                positions.add(new BlockPos(targetX, groundY, targetZ));
            }
        }
        return positions;
    }

    private int findLocalFloor(Level level, BlockPos.MutableBlockPos pos) {
        int startY = pos.getY();

        if (level.getBlockState(pos).isCollisionShapeFullBlock(level, pos)) {
            for (int i = 0; i < 5; i++) {
                pos.move(0, 1, 0);
                if (level.getBlockState(pos).isAir()) {
                    break;
                }
            }
        }

        // Scan downward to find a solid floor to plant the spike
        // floorScanDistance is the maximum distance IN BLOCKS that it will scan
        for (int i = 0; i < this.floorScanDistance(); i++) {
            // Check the block directly beneath our current position
            if (level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos.below())) {
                return pos.getY(); // Found the floor!
            }
            pos.move(0, -1, 0); // Move down one block
        }

        // Fallback: If we are hanging over a massive ravine or cliff that is deeper than floorScanDistance,
        // use the standard heightmap or the player's current Y so the spike isn't lost.
        return startY;
    }

    void castAOESpell(Player caster, Level level, BlockPos pos);
}
