package com.mohigster.morefeatures.entity.custom;

import com.mohigster.morefeatures.entity.goal.IceologerConjureIceGoal;
import com.mohigster.morefeatures.entity.goal.IceologerIcicleRainGoal;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class IceologerEntity extends AbstractIllager {
    public IceologerEntity(EntityType<? extends AbstractIllager> type, Level level) {
        super(type, level);
    }

    private IceologerConjureIceGoal iceAttackGoal;
    private IceologerIcicleRainGoal icicleRainGoal;

    private int attackCooldown = 40; // Ticks before the first attack can occur
    private boolean useIcicleRainNext = false;

    @Override
    protected void registerGoals() {
        this.iceAttackGoal = new IceologerConjureIceGoal(this);
        this.icicleRainGoal = new IceologerIcicleRainGoal(this);

        this.goalSelector.addGoal(2, iceAttackGoal);
        this.goalSelector.addGoal(2, icicleRainGoal);
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(8, new RandomStrollGoal(this, 0.6));

        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true).setUnseenMemoryTicks(300));
    }

    private static final EntityDataAccessor<Boolean> DATA_IS_CASTING_SPELL =
            SynchedEntityData.defineId(IceologerEntity.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Boolean> IS_CELEBRATING =
            SynchedEntityData.defineId(IceologerEntity.class, EntityDataSerializers.BOOLEAN);

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_IS_CASTING_SPELL, false);
        builder.define(IS_CELEBRATING, false);
    }

    public boolean isCastingSpell() {
        return this.entityData.get(DATA_IS_CASTING_SPELL);
    }

    public boolean isCelebrating() {
        return this.entityData.get(IS_CELEBRATING);
    }

    @Override
    public boolean canBeLeader(){
        return false;
    }

    @Override
    public IllagerArmPose getArmPose(){
        if (isCastingSpell()) {
            return IllagerArmPose.SPELLCASTING;
        } else {
            return isCelebrating() ? IllagerArmPose.CELEBRATING : IllagerArmPose.CROSSED;
        }
    }

    public void setIsCastingSpell(boolean castingSpell) {
        this.entityData.set(DATA_IS_CASTING_SPELL, castingSpell);
    }

    public void setCelebrating(boolean celebrating) {
        this.entityData.set(IS_CELEBRATING, celebrating);
    }

    @Override
    public void applyRaidBuffs(ServerLevel serverLevel, int i, boolean b) {

    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.EVOKER_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.EVOKER_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(final DamageSource source) {
        return SoundEvents.EVOKER_HURT;
    }

    @Override
    public SoundEvent getCelebrateSound() {
        return SoundEvents.EVOKER_CELEBRATE;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.ATTACK_DAMAGE, 5.0D)
                .add(Attributes.FOLLOW_RANGE,12.0D);
    }

    public boolean isAttackOnCooldown() {
        return this.attackCooldown > 0;
    }

    public void startAttackCooldown(int ticks) {
        this.attackCooldown = ticks;
    }

    public boolean shouldUseIcicleRain() {
        return this.useIcicleRainNext;
    }

    public void toggleNextAttack() {
        this.useIcicleRainNext = !this.useIcicleRainNext;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide() && this.attackCooldown > 0) {
            this.attackCooldown--;
        }
    }
}
