package com.mohigster.morefeatures.mixin;

import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FenceBlock.class)
public class FenceConnectionMixin {
    @Inject(method = "connectsTo", at = @At(
            value = "HEAD",
            target = "Lnet/minecraft/world/level/block/FenceBlock;isSameFence(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/state/BlockState;"
    ), cancellable = true)
    private void allowGemstoneConnections(BlockState state, boolean faceSolid, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        FenceBlock thisBlock = (FenceBlock) (Object) this;
        Block targetBlock = state.getBlock();

        if (thisBlock.defaultBlockState().is(MFBlockTags.GEMSTONE_FENCES) || state.is(MFBlockTags.GEMSTONE_FENCES)) {
            boolean isGate = targetBlock instanceof FenceGateBlock && FenceGateBlock.connectsToDirection(state, direction);
            boolean isAnyFence = state.is(BlockTags.FENCES);

            if (isAnyFence || isGate) {
                cir.setReturnValue(true);
            }
        }
    }
}
