package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.equine.AbstractHorse;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.function.Predicate;

public abstract class TargetingWandItem extends AbstractWandItem implements Targeting {
    protected final int maxTargets;
    protected final float proximityLimit; // In blocks

    public TargetingWandItem(
            Properties properties,
            double radius,
            int cooldownTicks,
            int baseDurabilityCost,
            int durabilityScalingFactor,
            int manaCost,
            int maxTargets,
            float proximityLimit,
            SoundEvent castSound,
            float soundVolume,
            float soundPitch
    ) {
        super(
                properties, radius, cooldownTicks,
                baseDurabilityCost, durabilityScalingFactor,
                manaCost, castSound, soundVolume, soundPitch
        );

        this.maxTargets = maxTargets;
        this.proximityLimit = proximityLimit;
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide() && hasEnoughMana(player)) {
            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(this.radius),
                    this.getTargetPredicate(player)
            );

            if (!targets.isEmpty()) {
                int targetCount = 0;
                for (LivingEntity target : targets) {
                    if (targetCount >= this.maxTargets){
                        break;
                    }

                    this.castTargetedSpell(target, player, level);
                    targetCount++;
                }
                int durabilityCost = totalDurabilityCost(targets.size());

                this.consumeMana(player);

                this.applyCastEffects(player, stack, durabilityCost, hand, level);
                return InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public float proximityLimit() {
        return this.proximityLimit;
    }
}
