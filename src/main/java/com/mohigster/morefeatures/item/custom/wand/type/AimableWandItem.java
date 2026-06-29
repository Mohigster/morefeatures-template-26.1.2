package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public abstract class AimableWandItem extends AbstractWandItem {
    private final double spreadRadius; // Whilst I could just
    private final boolean hasVariableSpread; // Controls if the spread has slight variation or not
    private final int projectileCount;

    public AimableWandItem(Properties properties, double spreadRadius, boolean hasVariableSpread,
                           int projectileCount, int cooldownTicks, int baseDurabilityCost,
                           SoundEvent castSound, float soundVolume, float soundPitch) {
        // Doesn't accept durabilityScalingFactor as a parameter and hardcodes the value as zero. This is because the durability for Aimable wands doesn't scale. Wands extending this class do not define a scaling factor.
        // calculateDurabilityCost in AbstractWandItem checks if the scaling factor is zero before performing division and simply sets extraCost to zero instead of performing division if so. Setting durabilityScalingFactor to 0 is safe.

        super(properties, 0.0D, // This could be used as spreadRadius, but the separate variable is used for clarity since spreadRadius fits what it's actually doing more accurately.
                cooldownTicks, baseDurabilityCost, 0, castSound, soundVolume, soundPitch);
        this.spreadRadius = spreadRadius;
        this.hasVariableSpread = hasVariableSpread;
        this.projectileCount = projectileCount;
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide()){
            Vec3 aim = player.getLookAngle();
            List<Vec3> spreadDirections = calculateSpreadDirections(aim, level.getRandom());

            castAimedSpell(player, level, spreadDirections);

            applyCastEffects(player, stack, baseDurabilityCost, hand, level);

            return InteractionResult.SUCCESS_SERVER;
        }

        return InteractionResult.PASS;
    }

    protected List<Vec3> calculateSpreadDirections(Vec3 aim, RandomSource random) {
        int count = getProjectileCount();
        List<Vec3> directions = new ArrayList<>();

        if (count <= 0) {
            return directions;
        }

        if (count == 1 || spreadRadius <= 0) {
            directions.add(aim);
            return directions;
        }

        double effectiveSpread = hasVariableSpread
                ? random.nextDouble() * spreadRadius // If the wand has variable spread, use the randomSource to calculate spread radius
                : spreadRadius; // Otherwise, use spread radius directly and do not perform any math

        Vec3 right = aim.cross(new Vec3(0, 1, 0));
        if (right.lengthSqr() < 1e-6) {

            right = aim.cross(new Vec3(1, 0, 0));
        }

        right = right.normalize();
        Vec3 up = right.cross(aim).normalize();

        for (int i = 0; i < count; i++) {
            double angle = 2 * Math.PI * i / count;
            Vec3 offset = right.scale(Math.cos(angle)).add(up.scale(Math.sin(angle)));

            Vec3 direction = aim.add(offset.scale(effectiveSpread)).normalize();
            directions.add(direction);
        }

        return directions;
    }

    protected int getProjectileCount() {
        if (projectileCount == 0) return 1; // Don't allow wands to have no projectiles.
        return projectileCount; // Otherwise return the specified value. This way, wands don't need to override this method
    }

    protected abstract void castAimedSpell(Player caster, Level level, List<Vec3> spreadDirections);
}