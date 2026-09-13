package com.mohigster.morefeatures.mixin;

import com.mohigster.morefeatures.data.tag.MFBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.NetherrackBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NetherrackBlock.class)
public class NetherrackMixin {
    // This method prevents Nullium from triggering Netherrack bonemeal, despite not being able to spread to Netherrack
    @Inject(method = "isValidBonemealTarget", at = @At(value = "RETURN"), cancellable = true)
    private void blockEndNullium(LevelReader level, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        for(BlockPos blockPos : BlockPos.betweenClosed(pos.offset(-1, -1, -1), pos.offset(1, 1, 1))) {
            if (level.getBlockState(blockPos).is(MFBlockTags.NULLIUM)) {
                cir.setReturnValue(false);
            }
        }
    }
}
