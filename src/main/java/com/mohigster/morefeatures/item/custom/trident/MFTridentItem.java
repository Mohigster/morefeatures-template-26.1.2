package com.mohigster.morefeatures.item.custom.trident;

import com.mohigster.morefeatures.entity.custom.projectile.trident.ThrownMFTrident;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.EntityType;
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
import org.jspecify.annotations.NullMarked;

import java.util.List;

public class MFTridentItem extends TridentItem {
    private final EntityType<? extends  ThrownTrident> tridentEntity;
    private final Identifier tridentId;

    public static final int THROW_THRESHOLD_TIME = 10;
    public static final float PROJECTILE_SHOOT_POWER = 2.5F;

    public MFTridentItem(Properties properties, EntityType<? extends ThrownTrident> tridentEntity, Identifier tridentId) {
        super(properties);
        this.tridentEntity = tridentEntity;
        this.tridentId = tridentId;
    }

    @NullMarked
    @Override
    public Projectile asProjectile(final Level level, final Position position, final ItemStack itemStack, final Direction direction) {
        System.out.println(tridentId);

        ThrownMFTrident trident = new ThrownMFTrident(level, position.x(), position.y(), position.z(), itemStack.copyWithCount(1), tridentEntity, tridentId);
        trident.pickup = AbstractArrow.Pickup.ALLOWED;
        return trident;
    }


    public static ItemAttributeModifiers createAttributes(double damageAmount, double attackSpeedModifier) {
        return ItemAttributeModifiers.builder()
                .add(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_ID, damageAmount, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .add(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_ID, attackSpeedModifier, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.MAINHAND)
                .build();
    }

    public static Tool createToolProperties(int damagePerBlock) {
        return new Tool(List.of(), 1.0F, damagePerBlock, false);
    }

    @NullMarked
    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int useTime = this.getUseDuration(stack, entity) - timeLeft;
            if (useTime < THROW_THRESHOLD_TIME) {
                return false;
            }

            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.TRIDENT_THROW.value(), SoundSource.PLAYERS, 1.0F, 1.0F);

            if (!level.isClientSide() && level instanceof ServerLevel serverLevel) {

                // Hurt the item stack
                stack.hurtWithoutBreaking(1, player);

                ThrownMFTrident trident = new ThrownMFTrident(level, player, stack, tridentEntity, tridentId);
                trident.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, PROJECTILE_SHOOT_POWER, 1.0F);

                if (player.hasInfiniteMaterials()) {
                    trident.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                }

                // Add to level
                serverLevel.addFreshEntity(trident);

                // Track stats
                player.awardStat(Stats.ITEM_USED.get(this));
            }

            if (!player.hasInfiniteMaterials()) {
                stack.shrink(1);
            }

            return true;
        }
        return false;
    }
}
