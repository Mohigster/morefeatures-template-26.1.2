package com.mohigster.morefeatures.item.custom;

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
import net.minecraft.world.entity.projectile.arrow.ThrownTrident;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CarbonTridentItem extends TridentItem {
    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float BASE_DAMAGE = 8.0F;
    public static final float PROJECTILE_SHOOT_POWER = 2.5F;

    public CarbonTridentItem(Properties properties) {
        super(properties);
    }

    @Override
    public Projectile asProjectile(final Level level, final Position position, final ItemStack itemStack, final Direction direction) {
        ThrownCarbonTrident carbonTrident = new ThrownCarbonTrident(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1));
        carbonTrident.pickup = AbstractArrow.Pickup.ALLOWED;
        return carbonTrident;
    }

    public static ItemAttributeModifiers createAttributes() {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, 9.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, -2.4F, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties() {
        return new Tool(List.of(), 1.0F, 2, false);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int useTime = this.getUseDuration(stack, entity) - timeLeft;
            if (useTime <10){
                return false;
            } else {
                player.awardStat(Stats.ITEM_USED.get(this));
                if (level instanceof ServerLevel serverLevel) {
                    stack.hurtWithoutBreaking(1, player);

                    ThrownCarbonTrident carbonTrident = new ThrownCarbonTrident(level, player, stack);
                    carbonTrident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);

                    if (player.hasInfiniteMaterials()) {
                        carbonTrident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }

                    level.addFreshEntity(carbonTrident);
                    level.playSound(null, carbonTrident, SoundEvents.TRIDENT_THROW.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                    return true;
                }
            }
        }
        return true;
    }
}
