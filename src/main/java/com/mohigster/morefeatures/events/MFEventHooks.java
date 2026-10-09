package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.core.RestrictedTo;
import com.mohigster.morefeatures.mixin.AvatarRendererMixin;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForge;
import org.jspecify.annotations.Nullable;

public final class MFEventHooks {
    public static @Nullable Entity onPortalTrySpawnEntity(ResourceKey<Level> targetLevel, Entity entity) {
        return NeoForge.EVENT_BUS.post(new PortalSpawnsEntityEvent(
                entity,
                targetLevel
        )).isCanceled() ? null : entity;
    }

    public static SetArmPoseEvent onSetArmPose(Avatar avatar, ItemStack itemInHand, InteractionHand hand) {
        return NeoForge.EVENT_BUS.post(new SetArmPoseEvent(
                avatar,
                itemInHand,
                hand
        ));
    }
}
