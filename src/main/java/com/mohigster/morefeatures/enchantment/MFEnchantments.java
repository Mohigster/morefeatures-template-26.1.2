package com.mohigster.morefeatures.enchantment;

import com.mohigster.morefeatures.enchantment.custom.ThunderEnchantmentEffect;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.tag.MFItemTags;
import net.minecraft.advancements.predicates.DamageSourcePredicate;
import net.minecraft.advancements.predicates.TagPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class MFEnchantments {
    public static final ResourceKey<Enchantment> THUNDERBOLT = registerKey("thunderbolt");
    public static final ResourceKey<Enchantment> THUNDERING = registerKey("thundering");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        var enchantment = context.lookup(Registries.ENCHANTMENT);
        var items = context.lookup(Registries.ITEM);

        register(context, THUNDERBOLT, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(MFItemTags.THUNDERBOLT_ENCHANTABLE),
                        items.getOrThrow(MFItemTags.THUNDERBOLT_ENCHANTABLE), 5, 2,
                        Enchantment.dynamicCost(5, 8), Enchantment.dynamicCost(25, 8), 3, EquipmentSlotGroup.MAINHAND))
                .exclusiveWith(enchantment.getOrThrow(EnchantmentTags.DAMAGE_EXCLUSIVE))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ThunderEnchantmentEffect(1),
                        DamageSourceCondition.hasDamageSource(
                                DamageSourcePredicate.Builder.damageType()
                                        .tag(TagPredicate.is(DamageTypeTags.IS_PROJECTILE))
                        )
                ));

        register(context, THUNDERING, Enchantment.enchantment(Enchantment.definition(items.getOrThrow(MFItemTags.THUNDERING_ENCHANTABLE),
                        items.getOrThrow(MFItemTags.THUNDERING_ENCHANTABLE), 5, 2,
                        Enchantment.dynamicCost(5, 8), Enchantment.dynamicCost(25, 8), 3, EquipmentSlotGroup.MAINHAND))
                .withEffect(
                        EnchantmentEffectComponents.POST_ATTACK,
                        EnchantmentTarget.ATTACKER,
                        EnchantmentTarget.VICTIM,
                        new ThunderEnchantmentEffect(1)
                ));
    }


    private static ResourceKey<Enchantment> registerKey(String id) {
        return ResourceKey.create(Registries.ENCHANTMENT, MFIdentifier.withMfNamespace(id));
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.identifier()));
    }
}
