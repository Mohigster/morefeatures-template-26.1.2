package com.mohigster.morefeatures.data.world.feature.custom.nethervine;

import com.mohigster.morefeatures.data.world.feature.custom.config.nethervine.CeilingVinesConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.GrowingPlantHeadBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import org.jspecify.annotations.NonNull;

public class CeilingVinesFeature extends Feature<CeilingVinesConfiguration> {
    public CeilingVinesFeature(Codec<CeilingVinesConfiguration> codec) {
        super(codec);
    }

    private static final Direction[] DIRECTIONS = Direction.values();

    @Override
    public boolean place(@NonNull FeaturePlaceContext<CeilingVinesConfiguration> context) {
        var config = context.config();
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();
        if (!level.isEmptyBlock(origin)) {
            return false;
        } else {
            BlockState stateAbove = level.getBlockState(origin.above());
            if (!stateAbove.is(config.validSupportBlocks())) {
                return false;
            } else {
                this.placeRoofNetherWart(config, level, random, origin);
                this.placeRoofWeepingVines(config, level, random, origin);
                return true;
            }
        }
    }

    private void placeRoofNetherWart(CeilingVinesConfiguration config, LevelAccessor level, RandomSource random, BlockPos origin) {
        level.setBlock(origin, config.roofBlockState(), 2);
        BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos neighbourPos = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 200; ++i) {
            placePos.setWithOffset(origin, random.nextInt(6) - random.nextInt(6), random.nextInt(2) - random.nextInt(5), random.nextInt(6) - random.nextInt(6));
            if (level.isEmptyBlock(placePos)) {
                int neighbours = 0;

                for(Direction direction : DIRECTIONS) {
                    BlockState neighbourBlockState = level.getBlockState(neighbourPos.setWithOffset(placePos, direction));
                    if (neighbourBlockState.is(config.validSupportBlocks()) || neighbourBlockState.is(config.roofBlockState().getBlock())) {
                        ++neighbours;
                    }

                    if (neighbours > 1) {
                        break;
                    }
                }

                if (neighbours == 1) {
                    level.setBlock(placePos, config.roofBlockState(), 2);
                }
            }
        }

    }

    private void placeRoofWeepingVines(CeilingVinesConfiguration config, LevelAccessor level, RandomSource random, BlockPos origin) {
        BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();

        for(int i = 0; i < 100; ++i) {
            placePos.setWithOffset(origin, random.nextInt(8) - random.nextInt(8), random.nextInt(2) - random.nextInt(7), random.nextInt(8) - random.nextInt(8));
            if (level.isEmptyBlock(placePos)) {
                BlockState stateAbove = level.getBlockState(placePos.above());
                if (stateAbove.is(config.validSupportBlocks()) || stateAbove.is(config.roofBlockState().getBlock())) {
                    int vineHeight = Mth.nextInt(random, 1, 8);
                    if (random.nextInt(6) == 0) {
                        vineHeight *= 2;
                    }

                    if (random.nextInt(5) == 0) {
                        vineHeight = 1;
                    }

                    placeWeepingVinesColumn(config, level, random, placePos, vineHeight, 17, 25);
                }
            }
        }

    }

    public static void placeWeepingVinesColumn(CeilingVinesConfiguration config, LevelAccessor level, RandomSource random, BlockPos.MutableBlockPos placePos, int totalHeight, int minAge, int maxAge) {
        for(int height = 0; height <= totalHeight; ++height) {
            if(!config.vinesState().hasProperty(GrowingPlantHeadBlock.AGE)) {
                throw new IllegalStateException("The vine block in a ceiling vines feature configuration does not have an age property from GrowingPlantHeadBlock!" +
                        " Ensure the class that is instantiated by this vine block extends GrowingPlantHeadBlock or one of its subclasses somewhere to inherit this property.");
            }

            if (level.isEmptyBlock(placePos)) {
                if (height == totalHeight || !level.isEmptyBlock(placePos.below())) {
                    level.setBlock(placePos, config.vinesState().setValue(GrowingPlantHeadBlock.AGE, Mth.nextInt(random, minAge, maxAge)), 2);
                    break;
                }

                level.setBlock(placePos, config.vinesPlantState(), 2);
            }

            placePos.move(Direction.DOWN);
        }
    }
}
