package com.mohigster.morefeatures.block.custom.magicblock;

import com.mohigster.morefeatures.data.sound.MFSoundEvents;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;


public class MagicBlock extends Block {
    private static final double HORIZONTAL_OFFSET = 0.5D;
    private static final double VERTICAL_OFFSET = 1.0D;
    private static final float VOLUME = 2F;
    private static final float PITCH = 1F;

    private static final int GLOWING_DURATION = 300;

    public MagicBlock(Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    protected InteractionResult useWithoutItem(
            BlockState state,
            Level level,
            BlockPos pos,
            Player player,
            BlockHitResult hitResult
    ) {
        level.addParticle(ParticleTypes.END_ROD, pos.getX() + HORIZONTAL_OFFSET, pos.getY() + VERTICAL_OFFSET, pos.getZ() + HORIZONTAL_OFFSET,
                0, 1, 0);

        level.playSound(null, pos,
                MFSoundEvents.MAGIC_BLOCK_FALL.get(), SoundSource.BLOCKS, VOLUME, PITCH);
        return InteractionResult.SUCCESS;
    }

    @NullMarked
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if(entity instanceof Player player) {
            // To save performance, the glowing effect is only applied if the player does not have the effect, or is running low.
            if (!player.hasEffect(MobEffects.GLOWING) || Objects.requireNonNull(
                    player.getEffect(MobEffects.GLOWING)).getDuration() < 50
            ) {
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, GLOWING_DURATION));
            }
        }

        if(entity instanceof ItemEntity itemEntity) {
            ItemStack currentItem = itemEntity.getItem();
            if (!currentItem.is(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULTS)) { // A failsafe. If an item is a possible result of a magic block transmutation, it shouldn't even attempt to find a transmutation result
                ItemStack result = this.getTransmutationResult(currentItem);
                this.performTransmutationEffects(itemEntity, result, level, pos);
            }
        }

        super.stepOn(level, pos, onState, entity);
    }

    protected ItemStack getTransmutationResult(ItemStack item) {
        ItemStack result = MagicBlockTransmutationManager.getResult(item);

        return result.isEmpty() ? ItemStack.EMPTY : result;
    }

    private void performTransmutationEffects(
            ItemEntity entity,
            ItemStack result,
            Level level,
            BlockPos pos
    ){
        Objects.requireNonNull(pos);

        ItemStack checkedResult = Objects.requireNonNullElse(result, ItemStack.EMPTY);
        if (!checkedResult.isEmpty()) {

            Objects.requireNonNull(entity).setItem(checkedResult);

            Objects.requireNonNull(level).addParticle(ParticleTypes.END_ROD, pos.getX() + HORIZONTAL_OFFSET, pos.getY() + VERTICAL_OFFSET, pos.getZ() + HORIZONTAL_OFFSET,
                    0, 1, 0);

            level.playSound(null, entity,
                    MFSoundEvents.MAGIC_BLOCK_FALL.get(), SoundSource.BLOCKS, VOLUME, PITCH);
        }
    }
}
