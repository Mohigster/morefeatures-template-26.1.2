package com.mohigster.morefeatures.events;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

public final class SetArmPoseEvent extends Event implements ICancellableEvent {
    private final Avatar avatar;
    private final ItemStack itemInHand;
    private final InteractionHand hand;
    private HumanoidModel.@Nullable ArmPose pose;

    public SetArmPoseEvent(Avatar avatar, ItemStack itemInHand, InteractionHand hand) {
        this.avatar = avatar;
        this.itemInHand = itemInHand;
        this.hand = hand;
        this.pose = null;
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    public ItemStack getItemInHand() {
        return this.itemInHand;
    }

    public InteractionHand getHand() {
        return this.hand;
    }

    public void setPose(HumanoidModel.@NonNull ArmPose pose) {
        this.pose = Objects.requireNonNull(pose);
    }

    public boolean poseIsSet() {
        return this.pose != null;
    }

    public HumanoidModel.@Nullable ArmPose getPose() {
        return this.pose;
    }
}
