package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericAOEWandItem extends AbstractWandItem {
    private final int ringCount;
    private final int floorScanDistance;

    public GenericAOEWandItem(Properties properties, double radius, int cooldownTicks, int ringCount,
                              int baseDurabilityCost, int manaCost, int floorScanDistance,
                               SoundEvent castSound, float soundVolume, float soundPitch) {
        super(properties, radius, cooldownTicks, baseDurabilityCost,
                0, manaCost, castSound, soundVolume, soundPitch);

        this.ringCount = ringCount;
        this.floorScanDistance = floorScanDistance;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);

        if (!level.isClientSide() && hasEnoughMana(player)){
            List<BlockPos> affectedPositions = calculateRingPositions(level, player);

            for (BlockPos pos : affectedPositions){
                castAOESpell(player, level, pos);
            }

            consumeMana(player);

            applyCastEffects(player, itemStack, baseDurabilityCost, hand, level);

            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.PASS;
    }

    private List<BlockPos> calculateRingPositions(Level level, Player player) {
        List<BlockPos> positions = new ArrayList<>();

        int totalRings = Math.max(1, this.ringCount);

        double radiusStep = this.radius / totalRings; // Distance between each ring. Ensures they are evenly spaced apart.

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
        for (int i = 0; i < floorScanDistance; i++) {
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

    protected abstract void castAOESpell(Player caster, Level level, BlockPos pos);
}
