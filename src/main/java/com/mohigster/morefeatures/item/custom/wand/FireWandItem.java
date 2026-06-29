package com.mohigster.morefeatures.item.custom.wand;

import com.mohigster.morefeatures.item.custom.wand.type.AimableWandItem;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class FireWandItem extends AimableWandItem {
    private static final double SPREAD_RADIUS = 0.0F;
    private static final boolean VARIABLE_SPREAD = false; // Determines if the amount of spread is randomised as any value between zero and SPREAD_RADIUS, instead of being directly set to SPREAD_RADIUS
    private static final int COOLDOWN = 20;
    private static final int DURABILITY_COST = 1; // Unlike with other wands, durability in the Fire Wand (and other Aimable wands) doesn't scale. Since that means this value is always the final durability cost, I have decided not to call this constant BASE_DURABILITY_COST
    private static final int PROJECTILE_COUNT = 1;

    private static final float VOLUME = 1.0F;
    private static final float PITCH = 0.5F;

    public FireWandItem(Properties properties) {
        super(properties, SPREAD_RADIUS, VARIABLE_SPREAD, PROJECTILE_COUNT, COOLDOWN, DURABILITY_COST, SoundEvents.FIRECHARGE_USE, VOLUME, PITCH);
    }

    @Override
    protected void castAimedSpell(Player caster, Level level, List<Vec3> spreadDirections){
        if (!(level instanceof ServerLevel serverLevel)) return;

        for (Vec3 direction : spreadDirections) {
            SmallFireball fireball = new SmallFireball(
                    serverLevel,
                    caster.getX(),
                    caster.getEyeY() - 0.1,
                    caster.getZ(),
                    direction
            );

            fireball.setOwner(caster); // Forgot this at first... yeah... I accidentally hit myself and died during testing

            fireball.setDeltaMovement(direction.scale(1.8));

            serverLevel.addFreshEntity(fireball);
        }
    }
}
