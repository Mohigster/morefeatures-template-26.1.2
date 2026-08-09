package com.mohigster.morefeatures.data.world.feature.custom.nethervine;

import com.mohigster.morefeatures.data.world.feature.custom.config.nethervine.NetherVinesConfiguration;
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

public class CeilingVinesFeature extends Feature<NetherVinesConfiguration> {
    public CeilingVinesFeature(Codec<NetherVinesConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(@NonNull FeaturePlaceContext<NetherVinesConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        NetherVinesConfiguration config = context.config();
        if (!isValidPlacementLocation(config, level, origin)) {
            return false;
        } else {
            RandomSource random = context.random();
            int spreadWidth = config.spreadWidth();
            int spreadHeight = config.spreadHeight();
            int maxHeight = config.maxHeight();
            BlockPos.MutableBlockPos placePos = new BlockPos.MutableBlockPos();
            for(int i = 0; i < spreadWidth * spreadWidth; ++i) {
                placePos.set(origin).move(Mth.nextInt(random, -spreadWidth, spreadWidth), Mth.nextInt(random, -spreadHeight, spreadHeight), Mth.nextInt(random, -spreadWidth, spreadWidth));
                if (findFirstAirBlockBelowBase(level, placePos) && isValidPlacementLocation(config, level, placePos)) {
                    int vineHeight = Mth.nextInt(random, 1, maxHeight);
                    if (random.nextInt(6) == 0) {
                        vineHeight *= 2;
                    }

                    if (random.nextInt(5) == 0) {
                        vineHeight = 1;
                    }

                    placeVinesColumn(config, level, random, placePos, vineHeight, 17, 25);
                }
            }
            return true;
        }
    }

    private static boolean findFirstAirBlockBelowBase(LevelAccessor level, BlockPos.MutableBlockPos placePos) {
        do {
            placePos.move(0, -1, 0);
            if (level.isOutsideBuildHeight(placePos)) {
                return false;
            }
        } while(level.getBlockState(placePos).isAir());

        placePos.move(0, -1, 0);
        return true;
    }

    public static void placeVinesColumn(NetherVinesConfiguration config, LevelAccessor level, RandomSource random, BlockPos.MutableBlockPos placePos, int totalHeight, int minAge, int maxAge) {
        for(int height = 1; height >= totalHeight; --height) {
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

    private static boolean isValidPlacementLocation(NetherVinesConfiguration config, WorldGenLevel level, BlockPos pos) {
        if (level.isEmptyBlock(pos)) {
            return true;
        } else {
            BlockState stateBelow = level.getBlockState(pos.above());
            return stateBelow.is(config.validSupportBlocks());
        }
    }
}
