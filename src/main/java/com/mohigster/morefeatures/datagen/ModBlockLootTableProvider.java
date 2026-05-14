package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ALUMINIUM_BLOCK.get());
        dropSelf(ModBlocks.MAGNESIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_ALUMINIUM_BLOCK.get());
        dropSelf(ModBlocks.RAW_MAGNESIUM_BLOCK.get());
        dropSelf(ModBlocks.AZURITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_AZURITE_BLOCK.get());
        dropSelf(ModBlocks.FLUORITE_BLOCK.get());
        dropSelf(ModBlocks.RAW_FLUORITE_BLOCK.get());
        dropSelf(ModBlocks.BISMUTH_BLOCK.get());
        dropSelf(ModBlocks.RAW_BISMUTH_BLOCK.get());

        // Aluminium ores

        add(ModBlocks.ALUMINIUM_ORE.get(),
                createOreDrop(ModBlocks.ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get()));
        add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                createOreDrop(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get()));

        // Magnesium ores

        add(ModBlocks.MAGNESIUM_ORE.get(),
                createMultipleOreDrops(ModBlocks.MAGNESIUM_ORE.get(), ModItems.RAW_MAGNESIUM.get(), 1, 3));
        add(ModBlocks.DEEPSLATE_MAGNESIUM_ORE.get(),
                createMultipleOreDrops(ModBlocks.MAGNESIUM_ORE.get(), ModItems.RAW_MAGNESIUM.get(), 2, 4));

        // Azurite ores

        add(ModBlocks.AZURITE_ORE.get(),
                createOreDrop(ModBlocks.AZURITE_ORE.get(), ModItems.RAW_AZURITE.get()));
        add(ModBlocks.DEEPSLATE_AZURITE_ORE.get(),
                createOreDrop(ModBlocks.DEEPSLATE_AZURITE_ORE.get(), ModItems.RAW_AZURITE.get()));
        add(ModBlocks.NETHER_AZURITE_ORE.get(),
                createOreDrop(ModBlocks.NETHER_AZURITE_ORE.get(), ModItems.RAW_AZURITE.get()));
        add(ModBlocks.END_AZURITE_ORE.get(),
                createOreDrop(ModBlocks.END_AZURITE_ORE.get(), ModItems.RAW_AZURITE.get()));

        // Fluorite ores

        add(ModBlocks.FLUORITE_ORE.get(),
                createOreDrop(ModBlocks.FLUORITE_ORE.get(), ModItems.RAW_FLUORITE.get()));
        add(ModBlocks.DEEPSLATE_FLUORITE_ORE.get(),
                createOreDrop(ModBlocks.DEEPSLATE_FLUORITE_ORE.get(), ModItems.RAW_FLUORITE.get()));
        add(ModBlocks.NETHER_FLUORITE_ORE.get(),
                createOreDrop(ModBlocks.NETHER_FLUORITE_ORE.get(), ModItems.RAW_FLUORITE.get()));
        add(ModBlocks.END_FLUORITE_ORE.get(),
                createOreDrop(ModBlocks.END_FLUORITE_ORE.get(), ModItems.RAW_FLUORITE.get()));

        // Bismuth ore

        add(ModBlocks.BISMUTH_ORE.get(),
                createOreDrop(ModBlocks.BISMUTH_ORE.get(), ModItems.RAW_BISMUTH.get()));


    }


    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
