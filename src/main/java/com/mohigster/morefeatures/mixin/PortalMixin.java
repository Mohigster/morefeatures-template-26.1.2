package com.mohigster.morefeatures.mixin;

import com.mohigster.morefeatures.portal.frame.CustomPortalShape;
import com.mohigster.morefeatures.portal.PortalDestinationManager;
import com.mohigster.morefeatures.portal.key.BlockKey;
import com.mohigster.morefeatures.portal.key.PortalKey;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.neoforge.event.EventHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

@Mixin(BlockBehaviour.class)
public final class PortalMixin {
    @Inject(method = "onPlace", at = @At(value = "HEAD"))
    public void createPortalIfBlockIsKey(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston, CallbackInfo ci) {
        if (!oldState.is(state.getBlock())) {
            ResourceKey<Level> target = PortalDestinationManager.getTargetLevel(level, pos);

            if (portalMixin$shouldIgnite(state, level, target)) {
                // CustomPortalShape has behaviours that scans for the correct frame based on JSON data, unlike the vanilla PortalShape which always uses Obsidian
                Optional<PortalShape> optionalShape = CustomPortalShape.findEmptyShape(level, pos, Direction.Axis.X);
                Optional<PortalShape> eventFired = EventHooks.onTrySpawnPortal(level, pos, optionalShape);
                eventFired.ifPresent(shape -> shape.createPortalBlocks(level));
            }
        }
    }

    @Unique
    private static boolean portalMixin$shouldIgnite(BlockState state, Level level, ResourceKey<Level> target) {
        return portalMixin$blockIsKey(state, target) && portalMixin$inValidDim(level, target);
    }

    @Unique
    private static boolean portalMixin$blockIsKey(BlockState state, ResourceKey<Level> target) {
        Optional<PortalKey> optionalKey = PortalDestinationManager.getPortalKey(target);

        if (optionalKey.isEmpty()) return false;

        if (!(optionalKey.get() instanceof BlockKey(Block block))) return false;

        return state.is(block);
    }

    @Unique
    private static boolean portalMixin$inValidDim(Level level, ResourceKey<Level> target) {
        return level.dimension().equals(target) || level.dimension().equals(Level.OVERWORLD);
    }
}
