package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
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
                            movement.y * 0.185,
                            movement.z * 0.185
                    );
                }
            }
        }
    }



    @SubscribeEvent
    public static void onArrowSpawn(EntityJoinLevelEvent event) {

        if (!(event.getEntity() instanceof AbstractArrow arrow)) {
            return;
        }

        if (!(arrow.getOwner() instanceof Player player)) {
            MoreFeatures.LOGGER.info("Shooter was not a player");
            return;
        }

        Vec3 movement = arrow.getDeltaMovement();

        double velBonus = movement.length();

        double baseDamage = 2.0 * velBonus;

        ItemStack weapon = player.getUseItem();


        if (weapon.is(ModItems.CARBON_BOW.get())) { // Carbon Bow damage boost
            MoreFeatures.LOGGER.debug("Carbon damage multiplier applied");
            MoreFeatures.LOGGER.debug("Weapon instance of: {}", weapon);
            arrow.setBaseDamage(baseDamage * 0.75);
        } else if (weapon.is(ModItems.BISMUTH_BOW.get())) { // Bismuth Bow damage boost
            MoreFeatures.LOGGER.debug("Bismuth damage multiplier applied");
            MoreFeatures.LOGGER.debug("Weapon instance of: {}", weapon);
            arrow.setBaseDamage(baseDamage * 1.05);
        }
    }
}
