package com.mohigster.morefeatures.block.custom;

import com.mohigster.morefeatures.item.ModItems;
import com.mohigster.morefeatures.tag.ModItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;


public class MagicBlock extends Block {

    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                0, 1, 0);

        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 2f, 1f);
        return InteractionResult.SUCCESS;
    }

    public void stepOn(Level level, BlockPos pos, BlockState onState, Entity entity) {
        if(entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 300));
        }

        if(entity instanceof ItemEntity itemEntity) {
            ItemStack currentItem = itemEntity.getItem();
            ItemStack result = getTransmutationResult(currentItem);

            if(!result.isEmpty()){

                itemEntity.setItem(result);

                level.addParticle(ParticleTypes.END_ROD, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5,
                        0, 1, 0);

                level.playSound(null, itemEntity,
                        SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS, 1.5f, 1f);

            }
        }

        super.stepOn(level, pos, onState, entity);
    }

    private ItemStack getTransmutationResult(ItemStack item) {
        int count = item.getCount();

        if (isCarbonItem(item)) {
            return new ItemStack(ModItems.CARBON_FIBER.get(), count);
        }
        if (isMetalItem(item)) {
            return new ItemStack(ModItems.BISMUTH_SCRAP.get(), count);
        }
        if (isGemstoneItem(item)) {
            return new ItemStack(Items.DIAMOND, count);
        }
        if (isPotionItem(item)) {
            ItemStack lingeringPotion = new ItemStack(Items.LINGERING_POTION, count);

            lingeringPotion.applyComponents(item.getComponents());

            return lingeringPotion;
        }

        return ItemStack.EMPTY; // Returns an empty stack if no match is found
    }

    private boolean isCarbonItem(ItemStack item) {
        return item.is(Items.COAL) || item.is(ItemTags.SAPLINGS) ||
                item.is(ItemTags.FLOWERS) || item.is(ItemTags.LOGS_THAT_BURN) ||
                item.is(ItemTags.LOGS) || item.is(ModItemTags.IS_FOOD) ||
                item.is(Items.REDSTONE) || item.is(Items.STICK) ||
                item.is(ItemTags.WOOL) || item.is(ItemTags.PLANKS);
    }
    private boolean isMetalItem(ItemStack item) {
        return item.is(Items.IRON_INGOT) || item.is(ModItems.MAGNESIUM_INGOT) ||
                item.is(ModItems.ALUMINIUM_INGOT) || item.is(Items.COPPER_INGOT) ||
                item.is(Items.GOLD_INGOT);
    }
    private boolean isGemstoneItem(ItemStack item) {
        return item.is(Items.EMERALD) || item.is(ModItems.AZURITE)
                || item.is(ModItems.FLUORITE);
    }
    private boolean isPotionItem(ItemStack item) {
        return item.is(Items.POTION) || item.is(Items.SPLASH_POTION);
    }
}
