package com.mohigster.morefeatures.mixin;

import com.mohigster.morefeatures.block.custom.portal.datadriven.CustomPortalShape;
import com.mohigster.morefeatures.block.custom.portal.datadriven.PortalBlock;
import com.mohigster.morefeatures.block.custom.portal.datadriven.PortalDestinations;
import com.mohigster.morefeatures.block.custom.portal.key.BlockKey;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
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
public class PortalMixin {
    @Inject(method = "onPlace", at = @At(value = "HEAD"))
    public void createPortalIfBlockIsKey(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston, CallbackInfo ci) {
        if (!oldState.is(state.getBlock())) {
            ResourceKey<Level> target = PortalDestinations.getTargetLevel(PortalBlock.scanForFrameState(level, pos));

            if (this.portal$shouldIgnite(state, level, target)) {
                // CustomPortalShape has behaviours that scans for the correct frame based on JSON data, unlike the vanilla PortalShape which always uses Obsidian
                Optional<CustomPortalShape> optionalShape = CustomPortalShape.findEmptyCustomShape(level, pos, Direction.Axis.X);
                optionalShape.ifPresent(customShape -> {

                    // Convert the custom shape to a vanilla shape so that we can fire the NeoForge event
                    Optional<PortalShape> vanillaShape = Optional.of(customShape);

                    vanillaShape = EventHooks.onTrySpawnPortal(level, pos, vanillaShape);

                    // If everything goes well, light the portal
                    if (vanillaShape.isPresent()) {
                        customShape.createPortalBlocks(level);
                    }
                });
            }
        }
    }

    @Unique
    private boolean portal$shouldIgnite(BlockState state, Level level, ResourceKey<Level> target) {
        return this.portal$blockIsKey(state, target) && this.portal$inValidDim(level, target);
    }

    @Unique
    private boolean portal$blockIsKey(BlockState state, ResourceKey<Level> targetLevel) {
        Optional<BlockKey> optionalKey = PortalDestinations.getBlockKey(targetLevel);
        return optionalKey.filter(key -> state.is(key.block())).isPresent();
    }

    @Unique
    private boolean portal$inValidDim(Level level, ResourceKey<Level> target) {
        return level.dimension().equals(target) || level.dimension().equals(Level.OVERWORLD);
    }
}
