package com.mohigster.morefeatures.entity.custom;

import com.mohigster.morefeatures.entity.goal.BrineAttackGoal;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BrineEntity extends Monster {

    public BrineEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        // Makes it immune to drowning and allows it to swim properly
        this.setAirSupply(getMaxAirSupply());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this)); // Keep from sinking helplessly
        // Custom Ranged Attack Goal (We will define the attack behavior or use a custom goal)
        this.goalSelector.addGoal(2, new BrineAttackGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 20.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 4.0D)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

//    @Override
//    public boolean checkSpawnObstacle(net.minecraft.world.level.LevelReader level) {
//        return super.checkSpawnObstacle(level);
//    }

    // Custom movement mechanics to simulate a "floating" fluid elemental
    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isInWater()) {
            // Give it a buoyant, gliding feel in water
            Vec3 delta = this.getDeltaMovement();
            this.setDeltaMovement(delta.x, delta.y * 0.05D + 0.02D, delta.z);
        }

        // Add custom bubble/splash particles here to mimic the Blaze's smoke or Breeze's wind particles
        if (this.level().isClientSide()) {
            // Spawn splash/bubble particles around its body
        }
    }
}
