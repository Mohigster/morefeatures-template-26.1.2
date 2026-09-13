package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

public abstract class AimableWandItem extends AbstractWandItem implements Aimable {
    private final double spreadRadius;
    private final boolean hasVariableSpread; // Controls if the spread has slight variation or not
    private final int projectileCount;
    private final int bonusShiftingProjCount;

    public AimableWandItem(
            Properties properties, double spreadRadius, boolean hasVariableSpread,
            int projectileCount, int bonusShiftingProjCount, int cooldownTicks, int baseDurabilityCost,
            int manaCost, SoundEvent castSound, float soundVolume, float soundPitch
    ) {
        // Doesn't accept durabilityScalingFactor as a parameter and hardcodes the value as zero. This is because the durability for Aimable wands doesn't scale. Wands extending this class do not define a scaling factor.
        // calculateDurabilityCost in AbstractWandItem checks if the scaling factor is zero before performing division and simply sets extraCost to zero instead of performing division if so. Setting durabilityScalingFactor to 0 is safe.

        super(properties, 0.0D, // This could be used as spreadRadius, but the separate variable is used for clarity since spreadRadius fits what it's actually doing more accurately.
                cooldownTicks, baseDurabilityCost, 0,
                manaCost, castSound, soundVolume, soundPitch);
        this.spreadRadius = spreadRadius;
        this.hasVariableSpread = hasVariableSpread;
        this.projectileCount = projectileCount;
        this.bonusShiftingProjCount = bonusShiftingProjCount;
    }

    @NullMarked
    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide() && hasEnoughMana(player)){
            Vec3 aim = player.getLookAngle();
            List<Vec3> spreadDirections = this.calculateSpreadDirections(aim, level.getRandom(), player);

            this.castAimedSpell(player, level, spreadDirections);

            this.consumeMana(player);

            this.applyCastEffects(player, stack, this.baseDurabilityCost, hand, level);

            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.PASS;
    }

    @Override
    public double spreadRadius() {
        return this.spreadRadius;
    }

    @Override
    public boolean hasVariableSpread() {
        return this.hasVariableSpread;
    }

    @Override
    public int projectileCount() {
        return this.projectileCount;
    }

    @Override
    public int bonusShiftingProjCount() {
        return this.bonusShiftingProjCount;
    }
}