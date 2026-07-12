package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.entity.entity_types.MFEntityTypes;
import com.mohigster.morefeatures.item.MFItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.stream.Stream;

public class MFEntityLootTableProvider extends EntityLootSubProvider {
    public MFEntityLootTableProvider(HolderLookup.Provider registries) {
        super(FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {
        this.generateSimpleMobLoot(MFEntityTypes.ICEOLOGER.get(), MFItems.FROSTED_CORE, 0.3F, 2.0F);
        this.generateSimpleMobLoot(MFEntityTypes.BRINE.get(), MFItems.BRINE_ROD, 0.5F, 1.0F);
    }

    protected void generateSimpleMobLoot(EntityType<?> entity, ItemLike item, float dropChance, float maxAmount) {
        LootPool.Builder pool = LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, maxAmount)))
                        .apply(EnchantedCountIncreaseFunction.lootingMultiplier(this.registries, UniformGenerator.between(0.0F, 1.0F))))
                .when(LootItemKilledByPlayerCondition.killedByPlayer());

        if (dropChance > 1.0F) { // 1.0F = 100% drop chance. A drop chance can not be greater than 100%, so the argument is illegal
            throw new IllegalArgumentException("Failed to generate a loot table for " + entity + "; drop chance must not be greater than 1!");
        }

        if (dropChance < 1.0F) {
            pool.when(LootItemRandomChanceCondition.randomChance(dropChance));
        }

        this.add(entity, LootTable.lootTable().withPool(pool));
    }

    @Override
    protected @NonNull Stream<EntityType<?>> getKnownEntityTypes() {
        return MFEntityTypes.ENTITY_TYPES.getEntries().stream().map(Holder::value);
    }
}
