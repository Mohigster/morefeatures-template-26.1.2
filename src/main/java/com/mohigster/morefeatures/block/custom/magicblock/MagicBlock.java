package com.mohigster.morefeatures.block.custom.magicblock;

import com.mohigster.morefeatures.sound.MFSounds;
import com.mohigster.morefeatures.tag.MFItemTags;
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

    public MagicBlock(Properties properties) {
        super(properties);
    }

    @NullMarked
    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                                        Player player, BlockHitResult hitResult) {
        level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                0, 1, 0);

        level.playSound(player, pos, MFSounds.MAGIC_BLOCK_FALL.get(), SoundSource.BLOCKS, 2f, 1f);
        return InteractionResult.SUCCESS;
    }

    @NullMarked
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if(entity instanceof Player player) {
            // To save performance, the glowing effect is only applied if the player does not have the effect, or is running low.
            if (!player.hasEffect(MobEffects.GLOWING) || Objects.requireNonNull(player.getEffect(MobEffects.GLOWING)).getDuration() < 50) {
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
            }
        }

        if(entity instanceof ItemEntity itemEntity) {
            ItemStack currentItem = itemEntity.getItem();
            if (!currentItem.is(MFItemTags.MAGIC_BLOCK_TRANSMUTATION_RESULT)) { // A failsafe. If an item is a possible result of a magic block transmutation, it shouldn't even attempt to find a transmutation result
                ItemStack result = getTransmutationResult(currentItem);

                if (!result.isEmpty()) {

                    itemEntity.setItem(result);

                    level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                            0, 1, 0);

                    level.playSound(null, itemEntity,
                            MFSounds.MAGIC_BLOCK_FALL.get(), SoundSource.BLOCKS, 1.5f, 1f);
                }
            }
        }

        super.stepOn(level, pos, onState, entity);
    }


    private ItemStack getTransmutationResult(ItemStack item) {
        ItemStack dataDrivenResult = MagicBlockTransmutations.INSTANCE.getResult(item);
        if (!dataDrivenResult.isEmpty()) {
            return dataDrivenResult;
        }
        return ItemStack.EMPTY;
    }
}
