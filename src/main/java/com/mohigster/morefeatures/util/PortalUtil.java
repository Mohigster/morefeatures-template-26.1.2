package com.mohigster.morefeatures.util;

import com.mohigster.morefeatures.portal.PortalDestinationManager;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public final class PortalUtil {
    public static boolean isFrame(BlockState state, BlockGetter level, BlockPos pos) {
        if (!(level instanceof ServerLevel server)) return false;

        BlockState frameState = PortalDestinationManager.getFrame(server, pos);

        return state.is(frameState.getBlock());
    }
}
