package com.mohigster.morefeatures.mixin;

import com.mohigster.morefeatures.block.custom.data.MFDataMaps;
import com.mohigster.morefeatures.block.custom.data.codec.FlammabilityEntry;
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
@SuppressWarnings("deprecation")
@Mixin(FireBlock.class)
public class FireBlockMixin {
    @Inject(method = "getBurnOdds", at = @At("HEAD"), cancellable = true)
    private void customDataMapBurnOdds(BlockState state, CallbackInfoReturnable<Integer> cir) {
        // Waterlogged check preserves standard vanilla behavior
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            cir.setReturnValue(0);
            return;
        }

        FlammabilityEntry data = flammabilityEntry$get(state);

        if (data != null) {
            if (data.flammability() == 0) throw new IllegalStateException("Cannot have an entry with a flammability value of zero!");
            cir.setReturnValue(data.flammability());
        }
    }

    @Inject(
            method = "getBurnOdds(Lnet/minecraft/world/level/block/state/BlockState;)I",
            at = @At("HEAD"),
            cancellable = true
    )
    private void customDataMapIgniteOdds(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if (state.hasProperty(BlockStateProperties.WATERLOGGED) && state.getValue(BlockStateProperties.WATERLOGGED)) {
            cir.setReturnValue(0);
            return;
        }

        FlammabilityEntry data = this.flammabilityEntry$get(state);

        if (data != null) {
            if (data.fireSpreadSpeed() == 0) throw new IllegalStateException("Cannot have an entry with a fire spread speed value of zero!");
            cir.setReturnValue(data.fireSpreadSpeed());
        }
    }

    @Unique
    public FlammabilityEntry flammabilityEntry$get(BlockState state) {
        return state.getBlock().builtInRegistryHolder().getData(MFDataMaps.FLAMMABLES);
    }
}
