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

import java.util.List;
import java.util.function.Predicate;

public abstract class TargetingWandItem extends AbstractWandItem {
    protected final int maxTargets;
    protected final float proximityLimit; // In blocks

    public TargetingWandItem(Properties properties, double radius, int cooldownTicks,
                             int baseDurabilityCost, int durabilityScalingFactor, int maxTargets, float proximityLimit,
                             SoundEvent castSound, float soundVolume, float soundPitch) {
        super(properties, radius, cooldownTicks, baseDurabilityCost, durabilityScalingFactor, castSound, soundVolume, soundPitch);
        this.maxTargets = maxTargets;
        this.proximityLimit = proximityLimit;
    }

    protected Predicate<LivingEntity> getTargetPredicate(Player caster) {
        return entity -> {

            if (entity instanceof ArmorStand){
                return false;
            }

            if (entity instanceof TamableAnimal pet && pet.getOwner() != null) { // Prevents pets from being targeted! No accidentally killing your own pets OR stooping low. We do not support the petty killing of beloved pets here. Also, this covers the Nautilus. Wild and untamed wolves / cats are fair game, though.
                return false;
            }

            if (entity.getVehicle() == caster){
                return false;
            }

            if (caster.getVehicle() == entity){
                return false;
            }

            if (entity instanceof AbstractHorse horse && horse.isTamed()){ // Don't allow accidental killing of mounts! AbstractNautilus extends TamableAnimal and so the Nautilus is already protected, no separate logic necessary.
                return false;
            }

            if (caster.distanceToSqr(entity) < proximityLimit * proximityLimit) { // Squaring the proximity limit ensures the limit value set will be in blocks. This makes it easier to set appropriate proximity limits in new wands!
                return false;
            }

            boolean isFriendly = entity instanceof Animal // This boolean controls entities that can only be targeted when holding down the shift key.
                    || entity instanceof Villager
                    || entity instanceof Player;

            return !isFriendly || caster.isShiftKeyDown();
        };
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide()) {
            List<LivingEntity> targets = level.getEntitiesOfClass(
                    LivingEntity.class,
                    player.getBoundingBox().inflate(radius),
                    getTargetPredicate(player)
            );

            if (!targets.isEmpty()) {
                int targetCount = 0;
                for (LivingEntity target : targets) {
                    if (targetCount >= maxTargets){
                        break;
                    }

                    castTargetedSpell(target, player, level);
                    targetCount++;
                }
                int durabilityCost = calculateDurabilityCost(targets.size());

                applyCastEffects(player, stack, durabilityCost, hand, level);
                return InteractionResult.SUCCESS_SERVER;
            }
        }
        return InteractionResult.PASS;
    }

    protected abstract void castTargetedSpell(LivingEntity target, Player caster, Level level);
}
