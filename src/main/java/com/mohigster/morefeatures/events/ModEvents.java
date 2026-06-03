package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import com.mohigster.morefeatures.block.entity.custom.CompressorBlockEntity;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.List;

@EventBusSubscriber(modid = MoreFeatures.MODID)
public class ModEvents {

    private static final ResourceKey<Biome> ICE_CAVES = ResourceKey.create(
            Registries.BIOME,
            Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "ice_caves")
    );

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
    public static void onPlayerTickIceCaves(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) return; // Server side only

        // Check biome
        if (!player.level().getBiome(player.blockPosition()).is(ICE_CAVES)) {
            return;
        }

        if(!player.gameMode().isSurvival()) return;

        // Check if player is in water
        if (!player.isInWater()) {
            return;
        }

        // Apply freezing
        int currentFrozen = player.getTicksFrozen();
        int required = player.getTicksRequiredToFreeze();

        // Freeze faster in your ice caves
        player.setTicksFrozen(Math.min(currentFrozen + 3, required + 20));
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
            arrow.setBaseDamage(baseDamage * 0.75);
        } else if (weapon.is(ModItems.BISMUTH_BOW.get())) { // Bismuth Bow damage boost
            MoreFeatures.LOGGER.debug("Bismuth damage multiplier applied");
            arrow.setBaseDamage(baseDamage * 1.05);
        }
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {
        if(event.getEntity() instanceof Sheep sheep && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == Items.END_ROD) {
                player.sendSystemMessage(Component.literal(player.getName().getString() + " just hit this sheep with an End Rod? YOU SICK FUCK!"));
                player.getMainHandItem().shrink(1);
                sheep.addEffect(new MobEffectInstance(MobEffects.POISON, 600, 6));
            }
        }
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.Energy.BLOCK, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getFluidTank);
    }
}
