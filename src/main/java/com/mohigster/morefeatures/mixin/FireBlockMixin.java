package com.mohigster.morefeatures.mixin;

import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.data.codec.FlammabilityEntry;
import com.mohigster.morefeatures.util.DataMapUtil;
import net.minecraft.world.level.block.FireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * This {@code Mixin} injects the data within the custom Flammability datamap into
 * the methods within {@link FireBlock} that handle an objects flammability
 * AND fire spread speed. Doing this allows the datamap to apply to all blocks, vanilla or modded
 */
@Mixin(FireBlock.class)
public final class FireBlockMixin {
    @Inject(method = "getBurnOdds", at = @At("HEAD"), cancellable = true)
    private void customDataMapBurnOdds(BlockState state, CallbackInfoReturnable<Integer> cir) {
        // Waterlogged check preserves standard vanilla behaviour
        if (fireMixin$isWaterlogged(state)) {
            cir.setReturnValue(0);
            return;
        }
        FlammabilityEntry data = fireMixin$getEntry(state);

        if (data != null) {
            cir.setReturnValue(DataMapUtil.requirePositive(data.flammability()));
        }
    }

    @Inject(
            method = "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private void customDataMapIgniteOdds(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if (fireMixin$isWaterlogged(state)) {
            cir.setReturnValue(0);
            return;
        }

        FlammabilityEntry data = fireMixin$getEntry(state);

        if (data != null) {
            cir.setReturnValue(DataMapUtil.requirePositive(data.fireSpreadSpeed()));
        }
    }

    @Unique
    private static boolean fireMixin$isWaterlogged(BlockState state) {
        return state.hasProperty(BlockStateProperties.WATERLOGGED)
                && state.getValue(BlockStateProperties.WATERLOGGED);
    }

    @Unique
    private static FlammabilityEntry fireMixin$getEntry(BlockState state) {
        return state.typeHolder().getData(MFDataMaps.FLAMMABLES);
    }
}
