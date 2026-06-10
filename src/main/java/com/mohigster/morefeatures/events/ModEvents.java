package com.mohigster.morefeatures.events;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.entity.ModBlockEntities;
import com.mohigster.morefeatures.block.entity.custom.CompressorBlockEntity;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.BonemealEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.ArrayList;
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

        if (player.level().isClientSide()) return;

        if (!player.level().getBiome(player.blockPosition()).is(ICE_CAVES)) {
            return;
        }

        if(!player.gameMode().isSurvival()) return;

        if (!player.isInWater()) {
            return;
        }

        int currentFrozen = player.getTicksFrozen();
        int required = player.getTicksRequiredToFreeze();

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
    public static void onBonemeal(BonemealEvent event) {

        final int SCAN_RADIUS = 1;

        RandomSource random = RandomSource.create();
        // Only act on End Stone
        BlockState state = event.getState();
        if (!state.is(Blocks.END_STONE)) {
            return;
        }

        // Only run server-side
        if (!(event.getLevel() instanceof ServerLevel serverLevel)) {
            return;
        }

        BlockPos center = event.getPos();

        boolean foundPallid  = false;
        boolean foundDecrepit = false;

        // Scan the 3x3x3 cube centred on the bone-mealed End Stone
        for (int dx = -SCAN_RADIUS; dx <= SCAN_RADIUS; dx++) {
            for (int dy = -SCAN_RADIUS; dy <= SCAN_RADIUS; dy++) {
                for (int dz = -SCAN_RADIUS; dz <= SCAN_RADIUS; dz++) {
                    BlockState neighbour = serverLevel.getBlockState(center.offset(dx, dy, dz));

                    if (neighbour.is(ModBlocks.PALLID_NULLIUM.get())) {
                        foundPallid = true;
                    }
                    if (neighbour.is(ModBlocks.DECREPIT_NULLIUM.get())) {
                        foundDecrepit = true;
                    }

                    // Early-exit once both variants are found
                    if (foundPallid && foundDecrepit) break;
                }
                if (foundPallid && foundDecrepit) break;
            }
            if (foundPallid && foundDecrepit) break;
        }

        // Nothing nearby — don't consume bone meal, let default behaviour run
        if (!foundPallid && !foundDecrepit) {
            return;
        }

        // Build the candidate list exactly as vanilla does:
        // if both variants are present the game picks one at random.
        List<Block> candidates = new ArrayList<>();
        if (foundPallid)   candidates.add(ModBlocks.PALLID_NULLIUM.get());
        if (foundDecrepit) candidates.add(ModBlocks.DECREPIT_NULLIUM.get());

        Block chosen = candidates.get(random.nextInt(candidates.size()));

        // Replace only the exact bone-mealed End Stone block
        serverLevel.setBlock(center, chosen.defaultBlockState(), Block.UPDATE_ALL);

        // Tell NeoForge we handled the event — this consumes the bone meal
        // and prevents other handlers (including vanilla) from also firing.
        event.setSuccessful(true);
        event.setCanceled(true);
    }

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.Item.BLOCK, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getItemHandler);

        event.registerBlockEntity(Capabilities.Energy.BLOCK, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getEnergyStorage);

        event.registerBlockEntity(Capabilities.Fluid.BLOCK, ModBlockEntities.COMPRESSOR_BE.get(), CompressorBlockEntity::getFluidTank);
    }
}
