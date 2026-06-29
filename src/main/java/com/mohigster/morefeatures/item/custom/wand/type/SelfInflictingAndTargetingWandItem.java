package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public abstract class SelfInflictingAndTargetingWandItem extends TargetingWandItem{
    protected final int selfInflictingExtraDurabilityCost;
    protected final boolean canUse;

    public SelfInflictingAndTargetingWandItem(Properties properties, double radius, int cooldownTicks,
                                              int baseDurabilityCost, int selfInflictingExtraDurabilityCost,
                                              int durabilityScalingFactor, int maxTargets, float proximityLimit,
                                              SoundEvent castSound, float soundVolume, float soundPitch) {
        super(properties, radius, cooldownTicks, baseDurabilityCost, durabilityScalingFactor, maxTargets, proximityLimit, castSound, soundVolume, soundPitch);
        this.selfInflictingExtraDurabilityCost = selfInflictingExtraDurabilityCost;
        this.canUse = true;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            if (player.isShiftKeyDown() && canCastSpellOnSelf(player)){
                castSelfInflictingSpell(player, level);
                int durabilityCost = baseDurabilityCost + selfInflictingExtraDurabilityCost;
                applyCastEffects(player, stack, durabilityCost, hand, level);
                return InteractionResult.SUCCESS_SERVER;
            } else {
                List<LivingEntity> targets = level.getEntitiesOfClass(
                        LivingEntity.class,
                        player.getBoundingBox().inflate(radius),
                        getTargetPredicate(player)
                );

                if (!targets.isEmpty()) {
                    for (LivingEntity target : targets) {
                        castTargetedSpell(target, player, level);
                    }
                    int durabilityCost = calculateDurabilityCost(targets.size());
                    applyCastEffects(player, stack, durabilityCost, hand, level);
                    return InteractionResult.SUCCESS_SERVER;
                }
            }
        }
        return InteractionResult.PASS;
    }

    protected abstract boolean canCastSpellOnSelf(Player caster); // Whether the target can have the spell cast on them is easily defined in the target predicate. However, the same is not said for when casting on yourself, hence this abstract method.

    protected abstract void castSelfInflictingSpell(Player caster, Level level); // To make the self inflicting spell the same as the targeting spell, it is as simple as calling castTargetedSpell(caster, caster, level); in this method. Otherwise, write your own spell logic in here.
}
