package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

import java.util.List;

public abstract class SelfInflictingAndTargetingWandItem extends AbstractWandItem implements SelfInflicting, Targeting {
    protected final int selfInflictingExtraDurabilityCost;
    private final int maxTargets;
    private final float proximityLimit;

    public SelfInflictingAndTargetingWandItem(
            Properties properties, double radius, int cooldownTicks,
            int baseDurabilityCost, int selfInflictingExtraDurabilityCost,
            int durabilityScalingFactor, int manaCost, int maxTargets, float proximityLimit,
            SoundEvent castSound, float soundVolume, float soundPitch
    ) {
        super(properties, radius, cooldownTicks, baseDurabilityCost, durabilityScalingFactor, manaCost, castSound, soundVolume, soundPitch);
        this.selfInflictingExtraDurabilityCost = selfInflictingExtraDurabilityCost;
        this.maxTargets = maxTargets;
        this.proximityLimit = proximityLimit;
    }

    @Override
    public @NonNull InteractionResult use(Level level, Player player, @NonNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide() && this.hasEnoughMana(player)) {
            if (player.isShiftKeyDown() && this.canCastSpellOnSelf(player)){
                this.castSelfInflictingSpell(player, level);
                int durabilityCost = this.baseDurabilityCost + this.selfInflictingExtraDurabilityCost;
                this.applyCastEffects(player, stack, durabilityCost, hand, level);
                return InteractionResult.SUCCESS_SERVER;
            } else {
                List<LivingEntity> targets = level.getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(this.radius),
                        getTargetPredicate(player)
                );

                if (!targets.isEmpty()) {
                    int targetCount = 0;

                    for (LivingEntity target : targets) {

                        if (targetCount < this.maxTargets) {
                            this.castTargetedSpell(target, player, level);
                        }

                        targetCount++;
                    }

                    int durabilityCost = this.totalDurabilityCost(targets.size());

                    this.applyCastEffects(player, stack, durabilityCost, hand, level);
                    return InteractionResult.SUCCESS_SERVER;
                }
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public float proximityLimit() {
        return this.proximityLimit;
    }
}
