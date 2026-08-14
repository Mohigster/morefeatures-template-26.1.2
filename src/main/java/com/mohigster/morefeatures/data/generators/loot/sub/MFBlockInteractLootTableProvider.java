package com.mohigster.morefeatures.data.generators.loot.sub;


import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.BlueBerryBushBlock;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.resources.references.MFLootTableIds;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.jspecify.annotations.NonNull;

import java.util.function.BiConsumer;

public record MFBlockInteractLootTableProvider(HolderLookup.Provider registries) implements LootTableSubProvider {
    @Override
    public void generate(@NonNull BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        this.generateSimple(
                output,
                MFLootTableIds.HARVEST_BLUE_BERRY_BUSH,
                this.createBerryInteractTable(
                        MFBlocks.BLUE_BERRY_BUSH.get(),
                        MFItems.BLUE_BERRY.get()
                )
        );
    }

    @SuppressWarnings("SameParameterValue")
    private void generateSimple(
            BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output,
            ResourceKey<LootTable> lootTable,
            LootTable.Builder builder) {
        output.accept(
                lootTable,
                builder
        );
    }

    private LootTable.Builder createBerryInteractTable(Block bush, Item berry){
        return LootTable.lootTable().withPool(LootPool.lootPool().add(LootItem.lootTableItem(berry)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(bush)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueBerryBushBlock.AGE, 3)))))
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(berry)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F))))
                );
    }
}
