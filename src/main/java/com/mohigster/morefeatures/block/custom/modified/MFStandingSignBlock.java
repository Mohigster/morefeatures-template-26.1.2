package com.mohigster.morefeatures.block.custom.modified;

import com.mohigster.morefeatures.block.collection.WoodSetType;
import com.mohigster.morefeatures.block.entity.MFBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.jspecify.annotations.NullMarked;

public class MFStandingSignBlock extends StandingSignBlock {
    public MFStandingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }

    public MFStandingSignBlock(WoodSetType type, Properties properties) {
        super(type.getWoodType(), properties);
    }

    @NullMarked
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return MFBlockEntities.MF_SIGN_BE.get().create(pos, state);
    }
}
