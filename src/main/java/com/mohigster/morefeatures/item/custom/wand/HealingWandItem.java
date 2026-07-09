package com.mohigster.morefeatures.item.custom.wand;

import com.mohigster.morefeatures.item.custom.wand.type.SelfInflictingAndTargetingWandItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class HealingWandItem extends SelfInflictingAndTargetingWandItem {
    private static final double RADIUS = 20.0D;

    private static final int COOLDOWN = 30;
    private static final int BASE_DURABILITY_COST = 1;
    private static final int SCALING_FACTOR = 2;
    private static final int SELF_INFLICTING_ADDITIONAL_COST = 1;
    private static final int MANA_COST = 3;
    private static final int MAX_TARGETS = 1;

    private static final float PROXIMITY_LIMIT = 0.0F;
    private static final float VOLUME = 0.9F;
    private static final float PITCH = 0.2F;

    private static final float SELF_HEALING_AMOUNT = 3.5F;
    private static final float TARGET_HEALING_AMOUNT = 6.5F;

    public HealingWandItem(Properties properties) {
        super(properties, RADIUS, COOLDOWN, BASE_DURABILITY_COST, SELF_INFLICTING_ADDITIONAL_COST, SCALING_FACTOR,
                MANA_COST, MAX_TARGETS, PROXIMITY_LIMIT, SoundEvents.AMETHYST_BLOCK_RESONATE, VOLUME, PITCH);
    }

    @Override
    protected Predicate<LivingEntity> getTargetPredicate(Player caster) {
        return entity -> entity != caster &&
                (entity instanceof Player ||
                        (entity instanceof TamableAnimal animal && animal.getOwner() == caster))
                && entity.isAlive() && entity.getMaxHealth() > entity.getHealth();
    }

    @Override
    protected boolean canCastSpellOnSelf(Player caster) {
        return caster.getHealth() < caster.getMaxHealth(); // Only cast if the casters health is not full.
    }

    @Override
    protected void castSelfInflictingSpell(Player caster, Level level) {
        caster.heal(SELF_HEALING_AMOUNT);
    }

    @Override
    protected void castTargetedSpell(LivingEntity target, Player caster, Level level) {
        target.heal(TARGET_HEALING_AMOUNT);
    }
}
