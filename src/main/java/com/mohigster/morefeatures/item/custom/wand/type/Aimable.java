package com.mohigster.morefeatures.item.custom.wand.type;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.List;

public interface Aimable {
    double spreadRadius();
    boolean hasVariableSpread();
    int bonusShiftingProjCount();
    int projectileCount();

    default List<Vec3> calculateSpreadDirections(Vec3 aim, RandomSource random, Player caster) {
        int count = this.getProjectileCount(caster);
        List<Vec3> directions = new ArrayList<>();

        if (count <= 0) {
            return directions;
        }

        if (count == 1 || spreadRadius() <= 0) {
            directions.add(aim);
            return directions;
        }

        double effectiveSpread = this.hasVariableSpread()
                ? random.nextDouble() * this.spreadRadius() // If the wand has variable spread, use randomness to calculate spread radius
                : this.spreadRadius(); // Otherwise, use spread radius directly and do not perform any math

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

    default int getProjectileCount(Player caster) {
        int extraProjectile = caster.isShiftKeyDown() // Allow the player to shoot more projectiles if holding shift
                ? this.bonusShiftingProjCount()
                : 0;

        if (this.projectileCount() == 0) return 1 + extraProjectile; // Don't allow wands to have no projectiles.

        return this.projectileCount() + extraProjectile; // Otherwise return the specified value. This way, wands don't need to override this method
    }

    void castAimedSpell(Player caster, Level level, List<Vec3> spreadDirections);
}
