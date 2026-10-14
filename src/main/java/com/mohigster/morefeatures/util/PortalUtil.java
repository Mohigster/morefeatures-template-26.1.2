package com.mohigster.morefeatures.util;

import com.mohigster.morefeatures.portal.PortalDestinationManager;
import com.mohigster.morefeatures.portal.frame.PortalFrame;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;

public final class PortalUtil {
    public static boolean isFrame(BlockState state, BlockGetter level, BlockPos pos) {
        if (!(level instanceof ServerLevel server)) return false;

        PortalFrame frame = PortalDestinationManager.getFrame(server, pos);

        return frameIsValid(frame, state);
    }

    public static boolean frameIsValid(PortalFrame portalFrame, BlockState state) {
        if (portalFrame instanceof PortalFrame.StateSensitive frame) {
            return state.equals(frame.state());
        } else if (portalFrame instanceof PortalFrame.Simple frame) {
            return state.is(frame.block());
        } else {
            return !portalFrame.isEmpty();
        }
    }
}
