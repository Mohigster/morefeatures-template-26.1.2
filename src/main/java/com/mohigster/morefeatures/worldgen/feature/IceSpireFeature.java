package com.mohigster.morefeatures.worldgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class IceSpireFeature extends Feature<NoneFeatureConfiguration> {

    public IceSpireFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        int height = Mth.nextInt(random, 6, 10);
        int baseRadius = Mth.nextInt(random, 3, 4);

        if (level.isEmptyBlock(origin.below()) || level.isWaterAt(origin.below())) {
            return false;
        }

        BlockState blueIce = Blocks.BLUE_ICE.defaultBlockState();
        BlockState packedIce = Blocks.PACKED_ICE.defaultBlockState();
        BlockState coreState = Blocks.AIR.defaultBlockState();


        // Generate the actual spire
        generateSpire(level, blueIce, packedIce, coreState, random, baseRadius, height, origin);

        // Place a single glowing block at the bottom center of the geyser
        this.setBlock(level, origin.above(1), Blocks.MAGMA_BLOCK.defaultBlockState());

        // Extend the foundation so the spire isn't left floating
        extendFoundation(level, blueIce, baseRadius, origin);

        return true;
    }

    private void generateSpire(WorldGenLevel level, BlockState blueIce, BlockState packedIce, BlockState coreState, RandomSource random, int baseRadius, int height, BlockPos origin) {
        for (int y = 0; y < height; y++) {
            double currentRadius = baseRadius * (1.0 - ((double) y / height));

            for (int x = (int) -currentRadius - 1; x <= currentRadius + 1; x++) {
                for (int z = (int) -currentRadius - 1; z <= currentRadius + 1; z++) {
                    double distanceSq = (x * x) + (z * z);

                    if (distanceSq <= currentRadius * currentRadius) {
                        BlockPos targetPos = origin.offset(x, y, z);


                        if (y >= height - 2) {
                            // Blend the cap blocks randomly
                            this.setBlock(level, targetPos, random.nextFloat() < 0.30f ? packedIce : blueIce);
                        } else if (distanceSq <= (currentRadius - 1.2) * (currentRadius - 1.2) && y > 1) {
                            this.setBlock(level, targetPos, coreState);
                        } else {
                            BlockState chosenWallState = random.nextFloat() < 0.30f ? packedIce : blueIce;
                            this.setBlock(level, targetPos, chosenWallState);
                        }
                    }
                }
            }
        }
    }

    private void extendFoundation(WorldGenLevel level, BlockState wallState, int baseRadius, BlockPos origin){
        int maxDepth = 12;
        for (int x = -baseRadius; x <= baseRadius; x++) {
            for (int z = -baseRadius; z <= baseRadius; z++) {
                // Only anchor the parts that fall within the circle footprint of the base
                if ((x * x) + (z * z) <= baseRadius * baseRadius) {
                    BlockPos foundationPos = origin.offset(x, -1, z);

                    // Max depth check so it doesn't drill forever
                    int depth = 0;
                    while (depth < maxDepth) {
                        BlockState currentState = level.getBlockState(foundationPos);
                        // Replace the air/snow with solid Blue Ice
                        if (!currentState.isAir() &&
                                !currentState.getFluidState().isSource() &&
                                !currentState.is(Blocks.SNOW) &&
                                !currentState.canBeReplaced()) {
                            break;
                        }

                        this.setBlock(level, foundationPos, wallState);

                        // Move one block deeper
                        foundationPos = foundationPos.below();
                        depth++;
                    }
                }
            }
        }
    }
}
