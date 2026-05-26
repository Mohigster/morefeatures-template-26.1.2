package com.mohigster.morefeatures.item.custom;

import com.mohigster.morefeatures.entity.custom.trident.ThrownBismuthTrident;
import com.mohigster.morefeatures.entity.custom.trident.ThrownCarbonTrident;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;

import java.util.List;

public class BismuthTridentItem extends TridentItem {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float PROJECTILE_SHOOT_POWER = 2.5F;

    public BismuthTridentItem(Properties properties) {
        super(properties);
    }

    @Override
    public Projectile asProjectile(final Level level, final Position position, final ItemStack itemStack, final Direction direction) {
        ThrownBismuthTrident bismuthTrident = new ThrownBismuthTrident(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1));
        bismuthTrident.pickup = AbstractArrow.Pickup.ALLOWED;
        return bismuthTrident;
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 11.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.1F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int useTime = this.getUseDuration(stack, entity) - timeLeft;
            if (useTime < THROW_THRESHOLD_TIME) {
                return false;
            }



            // 1. Play sound on both client and server safely via level.playSound
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW.value(), SoundSource.PLAYERS, 1.0F, 1.0F);

            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {

                // Hurt the item stack
                stack.hurtWithoutBreaking(1, player);

                // 2. Spawn your custom entity on the server
                ThrownBismuthTrident bismuthTrident = new ThrownBismuthTrident(level, player, stack);
                bismuthTrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, PROJECTILE_SHOOT_POWER, 1.0F);

                if (player.hasInfiniteMaterials()) {
                    bismuthTrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                // Add to level
                serverLevel.addFreshEntity(bismuthTrident);

                // Track stats
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            // 3. Consume the item from the inventory if they aren't in creative
            if (!player.hasInfiniteMaterials()) {
                stack.shrink(1);
            }

            return true;
        }
        return false;
    }
}
