package com.mohigster.morefeatures.entity.renderstate;


import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.illager.AbstractIllager;

public class IceologerRenderState extends IllagerRenderState {
    public HumanoidArm mainArm;
    public AbstractIllager.IllagerArmPose armPose;
    private boolean isCastingSpell;

    public IceologerRenderState() {
        this.mainArm = HumanoidArm.RIGHT;
        this.armPose = AbstractIllager.IllagerArmPose.CROSSED;
    }

    public boolean isCastingSpell() {
        return this.isCastingSpell;
    }

    public void setCastingSpell(boolean isCastingSpell) {
        this.isCastingSpell = isCastingSpell;
    }
}
