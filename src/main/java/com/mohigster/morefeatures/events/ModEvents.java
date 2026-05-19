package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber(modid = MoreFeatures.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {

        Player player = event.getEntity();

        if (!player.isFallFlying()) return;

        ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);

        if (!chest.is(ModItems.CARBON_ELYTRA.get())) return;

        List<FireworkRocketEntity> rockets =
                player.level().getEntitiesOfClass(
                        FireworkRocketEntity.class,
                        player.getBoundingBox().inflate(3)
                );

        for (FireworkRocketEntity rocket : rockets) {

            if (rocket.getOwner() == player) {
                double maxSpeed = 8.5;
                Vec3 movement = player.getDeltaMovement();

                if (movement.length() <= maxSpeed) {
                    player.push(
                            movement.x * 0.185,
                            movement.y,
                            movement.z * 0.185
                    );
                }
            }
        }
    }
}
