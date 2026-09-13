package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class AbstractWandItem extends Item implements Wand {
    protected final double radius;
    protected final int cooldownTicks;
    protected final int baseDurabilityCost;
    protected final int durabilityScalingFactor; // targets.size() / this = extra cost
    protected final int manaCost;
    protected final SoundEvent castSound;
    protected final float soundVolume;
    protected final float soundPitch;

    public AbstractWandItem(
            Properties properties, double radius, int cooldownTicks,
            int baseDurabilityCost, int durabilityScalingFactor, int manaCost,
            SoundEvent castSound, float soundVolume, float soundPitch
    ) {
        super(properties);
        this.radius = radius;
        this.cooldownTicks = cooldownTicks;
        this.baseDurabilityCost = baseDurabilityCost;
        this.durabilityScalingFactor = durabilityScalingFactor;
        this.manaCost = manaCost;
        this.castSound = castSound;
        this.soundVolume = soundVolume;
        this.soundPitch = soundPitch;
    }

    protected void applyCastEffects(Player player, ItemStack stack, int durabilityCost, InteractionHand hand, Level level) {
        player.getCooldowns().addCooldown(stack, this.cooldownTicks);

        stack.hurtAndBreak(durabilityCost, player, hand);

        level.playSound(null, player.getX(), player.getY(), player.getZ(),
                this.castSound, SoundSource.PLAYERS, this.soundVolume, this.soundPitch);
    }

    protected int totalDurabilityCost(int targetCount) {
        int extraCost = this.durabilityScalingFactor == 0
                ? 0 // If durabilityScalingFactor is zero, do NOT attempt division. We don't want to break the universe here. Simply set extraCost to zero.
                : (int) Math.floor((double) targetCount / this.durabilityScalingFactor); // Else, perform calculation as normal. targetCount / durabilityScalingFactor = extra durability added. E.g. durabilityScalingFactor set to 2 = one additional durability cost for every two entities

        return this.baseDurabilityCost + extraCost;
    }

    @Override
    public int manaCost() {
        return this.manaCost;
    }
}
