package com.mohigster.morefeatures.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.function.Supplier;

public class PlantedOffGrassSaplingBlock extends SaplingBlock {

    private final Supplier<Block> blockToSurviveOn;

    public PlantedOffGrassSaplingBlock(TreeGrower treeGrower, Properties properties, Supplier<Block> blockToSurviveOn) {
        super(treeGrower, properties);
        this.blockToSurviveOn = blockToSurviveOn;
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(blockToSurviveOn.get());
    }
//    public static final DeferredBlock<Block> PALM_SIGN = registerBlock("palm_sign",
//            properties -> new StandingSignBlock(WoodType.ACACIA,
//                    properties
//                            .sound(SoundType.WOOD)
//                            .strength(2f, 2f)
//                            .ignitedByLava()
//            ));
//
//    public static final DeferredBlock<Block> PALM_WALL_SIGN = registerBlock("palm_wall_sign",
//            properties -> new WallSignBlock(WoodType.ACACIA, properties
//                    .strength(2f, 2f)
//                    .sound(SoundType.WOOD)
//                    .ignitedByLava()
//            ));
}
