package com.mohigster.morefeatures.entity.renderstate;


import net.minecraft.client.renderer.entity.state.IllagerRenderState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.illager.AbstractIllager;

public class IceologerRenderState extends IllagerRenderState {
    public HumanoidArm mainArm;
    public AbstractIllager.IllagerArmPose armPose;
    public boolean isCastingSpell;
    public IceologerRenderState() {
        mainArm = HumanoidArm.RIGHT;
        armPose = AbstractIllager.IllagerArmPose.CROSSED;
    }
}
