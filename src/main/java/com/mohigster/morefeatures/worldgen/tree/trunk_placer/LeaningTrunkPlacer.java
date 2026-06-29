package com.mohigster.morefeatures.worldgen.tree.trunk_placer;

import com.google.common.collect.Lists;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Plane;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class LeaningTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<LeaningTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(
            instance -> trunkPlacerParts(instance).apply(instance, LeaningTrunkPlacer::new)
    );

    public LeaningTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerType.LEANING_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(
            WorldGenLevel level,
            BiConsumer<BlockPos, BlockState> trunkSetter,
            RandomSource random,
            int treeHeight,
            BlockPos origin,
            TreeConfiguration config
    ){
        // Some trunk placers call this (e.g. mangrove), it's safe to keep
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);

        List<FoliagePlacer.FoliageAttachment> foliageAttachments = Lists.newArrayList();

        Direction leanDir = Plane.HORIZONTAL.getRandomDirection(random);

        int leanStartHeight = treeHeight - random.nextInt(3) - 1;   // when the lean begins
        int leanSteps = 1 + random.nextInt(2);                      // how many blocks it shifts

        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        int x = origin.getX();
        int z = origin.getZ();

        OptionalInt topY = OptionalInt.empty();

        for (int yOffset = 0; yOffset < treeHeight; ++yOffset) {
            int y = origin.getY() + yOffset;

            // Apply lean after leanStartHeight
            if (yOffset >= leanStartHeight && leanSteps > 0) {
                x += leanDir.getStepX();
                z += leanDir.getStepZ();
                leanSteps--;
            }

            if (placeLog(level, trunkSetter, random, pos.set(x, y, z), config)) {
                topY = OptionalInt.of(y + 1);
            }
        }

        // Add foliage attachment at the top of the trunk
        if (topY.isPresent()) {
            foliageAttachments.add(new FoliagePlacer.FoliageAttachment(
                    new BlockPos(x, topY.getAsInt(), z),
                    1,      // radius
                    false   // not hanging
            ));
        }

        return foliageAttachments;
    }
}
