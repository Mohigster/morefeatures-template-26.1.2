package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabType;
import com.mohigster.morefeatures.item.MFItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class MFBlockLootTableProvider extends BlockLootSubProvider {

    public MFBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        // SELF DROPPING

        dropSelf(MFBlocks.ALUMINIUM_BLOCK.get());
        dropSelf(MFBlocks.MAGNESIUM_BLOCK.get());
        dropSelf(MFBlocks.RAW_ALUMINIUM_BLOCK.get());
        dropSelf(MFBlocks.RAW_MAGNESIUM_BLOCK.get());
        dropSelf(MFBlocks.AZURITE_BLOCK.get());
        dropSelf(MFBlocks.RAW_AZURITE_BLOCK.get());
        dropSelf(MFBlocks.FLUORITE_BLOCK.get());
        dropSelf(MFBlocks.RAW_FLUORITE_BLOCK.get());
        dropSelf(MFBlocks.BISMUTH_BLOCK.get());
        dropSelf(MFBlocks.RAW_BISMUTH_BLOCK.get());
        dropSelf(MFBlocks.BLOODWOOD_PLANKS.get());
        dropSelf(MFBlocks.BLOODWOOD_LOG.get());
        dropSelf(MFBlocks.BLOODWOOD.get());
        dropSelf(MFBlocks.BLOODWOOD_FENCE.get());
        dropSelf(MFBlocks.BLOODWOOD_FENCE_GATE.get());
        dropSelf(MFBlocks.STRIPPED_BLOODWOOD_LOG.get());
        dropSelf(MFBlocks.STRIPPED_BLOODWOOD.get());
        dropSelf(MFBlocks.BLOODWOOD_SAPLING.get());
        dropSelf(MFBlocks.BLOODWOOD_BUTTON.get());
        dropSelf(MFBlocks.BLOODWOOD_PRESSURE_PLATE.get());
        dropSelf(MFBlocks.BLOODWOOD_SHELF.get());
        dropSelf(MFBlocks.TAINTED_PLANKS.get());
        dropSelf(MFBlocks.TAINTED_LOG.get());
        dropSelf(MFBlocks.TAINTED_WOOD.get());
        dropSelf(MFBlocks.TAINTED_FENCE.get());
        dropSelf(MFBlocks.TAINTED_FENCE_GATE.get());
        dropSelf(MFBlocks.TAINTED_BUTTON.get());
        dropSelf(MFBlocks.TAINTED_PRESSURE_PLATE.get());
        dropSelf(MFBlocks.TAINTED_SHELF.get());
        dropSelf(MFBlocks.STRIPPED_TAINTED_LOG.get());
        dropSelf(MFBlocks.STRIPPED_TAINTED_WOOD.get());
        dropSelf(MFBlocks.TAINTED_SAPLING.get());
        dropSelf(MFBlocks.PALM_LOG.get());
        dropSelf(MFBlocks.PALM_WOOD.get());
        dropSelf(MFBlocks.STRIPPED_PALM_LOG.get());
        dropSelf(MFBlocks.STRIPPED_PALM_WOOD.get());
        dropSelf(MFBlocks.PALM_SAPLING.get());
        dropSelf(MFBlocks.PALM_PLANKS.get());
        dropSelf(MFBlocks.PALM_FENCE_GATE.get());
        dropSelf(MFBlocks.PALM_FENCE.get());
        dropSelf(MFBlocks.DECREPIT_SAPLING.get());
        dropSelf(MFBlocks.PALLID_SAPLING.get());
        dropSelf(MFBlocks.MAGIC_BLOCK.get());
        dropSelf(MFBlocks.AZURITE_STAIRS.get());
        dropSelf(MFBlocks.AZURITE_TRAPDOOR.get());
        dropSelf(MFBlocks.FLUORITE_STAIRS.get());
        dropSelf(MFBlocks.BLOODWOOD_STAIRS.get());
        dropSelf(MFBlocks.TAINTED_STAIRS.get());
        dropSelf(MFBlocks.PALM_STAIRS.get());
        dropSelf(MFBlocks.PALM_TRAPDOOR.get());
        dropSelf(MFBlocks.COMPRESSOR_BLOCK.get());
        dropSelf(MFBlocks.AZURITE_BUTTON.get());
        dropSelf(MFBlocks.AZURITE_PRESSURE_PLATE.get());
        dropSelf(MFBlocks.PALM_PRESSURE_PLATE.get());
        dropSelf(MFBlocks.PALM_BUTTON.get());
        dropSelf(MFBlocks.ROSE.get());
        dropSelf(MFBlocks.BLUE_ROSE.get());
        dropSelf(MFBlocks.EVIL_PORTAL.get());
        dropSelf(MFBlocks.PALM_SHELF.get());
        dropSelf(MFBlocks.DECREPIT_PLANKS.get());
        dropSelf(MFBlocks.DECREPIT_WOOD.get());
        dropSelf(MFBlocks.DECREPIT_LOG.get());
        dropSelf(MFBlocks.DECREPIT_BUTTON.get());
        dropSelf(MFBlocks.DECREPIT_PRESSURE_PLATE.get());
        dropSelf(MFBlocks.DECREPIT_STAIRS.get());
        dropSelf(MFBlocks.DECREPIT_FENCE.get());
        dropSelf(MFBlocks.DECREPIT_FENCE_GATE.get());
        dropSelf(MFBlocks.DECREPIT_SHELF.get());
        dropSelf(MFBlocks.STRIPPED_DECREPIT_WOOD.get());
        dropSelf(MFBlocks.STRIPPED_DECREPIT_LOG.get());
        dropSelf(MFBlocks.PALLID_PLANKS.get());
        dropSelf(MFBlocks.PALLID_WOOD.get());
        dropSelf(MFBlocks.PALLID_LOG.get());
        dropSelf(MFBlocks.PALLID_BUTTON.get());
        dropSelf(MFBlocks.PALLID_PRESSURE_PLATE.get());
        dropSelf(MFBlocks.PALLID_STAIRS.get());
        dropSelf(MFBlocks.PALLID_FENCE.get());
        dropSelf(MFBlocks.PALLID_FENCE_GATE.get());
        dropSelf(MFBlocks.PALLID_SHELF.get());
        dropSelf(MFBlocks.STRIPPED_PALLID_WOOD.get());
        dropSelf(MFBlocks.STRIPPED_PALLID_LOG.get());
        dropSelf(MFBlocks.DECREPIT_ROOTS.get());
        dropSelf(MFBlocks.PALLID_ROOTS.get());
        dropSelf(MFBlocks.VOID_ANCHOR.get());
        dropSelf(MFBlocks.ICICLE.get());

        // POTTED PLANTS

        add(MFBlocks.POTTED_ROSE.get(), createPotFlowerItemTable(MFBlocks.ROSE.get()));
        add(MFBlocks.POTTED_BLUE_ROSE.get(), createPotFlowerItemTable(MFBlocks.BLUE_ROSE.get()));
        add(MFBlocks.POTTED_TAINTED_SAPLING.get(), createPotFlowerItemTable(MFBlocks.TAINTED_SAPLING.get()));
        add(MFBlocks.POTTED_BLOODWOOD_SAPLING.get(), createPotFlowerItemTable(MFBlocks.BLOODWOOD_SAPLING.get()));
        add(MFBlocks.POTTED_PALM_SAPLING.get(), createPotFlowerItemTable(MFBlocks.PALM_SAPLING.get()));
        add(MFBlocks.POTTED_DECREPIT_SAPLING.get(), createPotFlowerItemTable(MFBlocks.DECREPIT_SAPLING.get()));
        add(MFBlocks.POTTED_PALLID_SAPLING.get(), createPotFlowerItemTable(MFBlocks.PALLID_SAPLING.get()));
        add(MFBlocks.POTTED_DECREPIT_ROOTS.get(), createPotFlowerItemTable(MFBlocks.DECREPIT_ROOTS.get()));
        add(MFBlocks.POTTED_PALLID_ROOTS.get(), createPotFlowerItemTable(MFBlocks.PALLID_ROOTS.get()));

        // SILK TOUCH DROPS

        add(MFBlocks.PALLID_NULLIUM.get(), createSingleItemTableWithSilkTouch(MFBlocks.PALLID_NULLIUM.get(), Blocks.END_STONE));
        add(MFBlocks.DECREPIT_NULLIUM.get(), createSingleItemTableWithSilkTouch(MFBlocks.DECREPIT_NULLIUM.get(), Blocks.END_STONE));

        // NO DROP

        add(MFBlocks.CONJURED_ICE.get(), noDrop());

        // SLABS

        add(MFBlocks.AZURITE_SLAB.get(), this::createSlabItemTable);
        add(MFBlocks.FLUORITE_SLAB.get(), this::createSlabItemTable);
        add(MFBlocks.BLOODWOOD_SLAB.get(), this::createSlabItemTable);
        add(MFBlocks.TAINTED_SLAB.get(), this::createSlabItemTable);
        add(MFBlocks.PALM_SLAB.get(), this::createSlabItemTable);
        add(MFBlocks.DECREPIT_SLAB.get(), this::createSlabItemTable);
        add(MFBlocks.PALLID_SLAB.get(), this::createSlabItemTable);

        // VERTICAL SLABS

        add(MFBlocks.AZURITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.BLOODWOOD_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.TAINTED_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.PALM_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.DECREPIT_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.PALLID_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.OAK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SPRUCE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.BIRCH_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.JUNGLE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.ACACIA_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.DARK_OAK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.CRIMSON_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.WARPED_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.MANGROVE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.CHERRY_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.BAMBOO_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.PALE_OAK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.STONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.COBBLESTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SMOOTH_STONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.STONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.GRANITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.DIORITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.ANDESITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.TUFF_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.TUFF_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SULFUR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.CINNABAR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.NETHER_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.BLACKSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.PURPUR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.QUARTZ_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.PRISMARINE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.MUD_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(MFBlocks.RESIN_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);

        MFBlocks.CUT_COPPER_VERTICAL_SLAB.forEach(block -> this.add(block.get(), this::createVerticalSlabItemTable));
        MFBlocks.WOOL_VERTICAL_SLAB.forEach(block -> this.add(block.get(), this::createVerticalSlabItemTable));


        // LEAVES

        // Bloodwood
        add(MFBlocks.BLOODWOOD_LEAVES.get(),
                createLeavesDrops(MFBlocks.BLOODWOOD_LEAVES.get(), MFBlocks.BLOODWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Tainted
        add(MFBlocks.TAINTED_LEAVES.get(),
                createLeavesDrops(MFBlocks.TAINTED_LEAVES.get(), MFBlocks.TAINTED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Palm
        add(MFBlocks.PALM_LEAVES.get(),
                createLeavesDrops(MFBlocks.PALM_LEAVES.get(), MFBlocks.PALM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Decrepit
        add(MFBlocks.DECREPIT_LEAVES.get(),
                createLeavesDrops(MFBlocks.DECREPIT_LEAVES.get(), MFBlocks.DECREPIT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Pallid
        add(MFBlocks.PALLID_LEAVES.get(),
                createLeavesDrops(MFBlocks.PALLID_LEAVES.get(), MFBlocks.PALLID_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // SIGN

        add(MFBlocks.AZURITE_SIGN.get(),
                createSingleItemTable(MFItems.AZURITE_SIGN.asItem()));

        add(MFBlocks.AZURITE_WALL_SIGN.get(),
                createSingleItemTable(MFItems.AZURITE_SIGN.asItem()));

        add(MFBlocks.AZURITE_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.AZURITE_SIGN.asItem()));

        add(MFBlocks.AZURITE_WALL_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.AZURITE_SIGN.asItem()));

        add(MFBlocks.BLOODWOOD_SIGN.get(),
                createSingleItemTable(MFItems.BLOODWOOD_SIGN.asItem()));

        add(MFBlocks.BLOODWOOD_WALL_SIGN.get(),
                createSingleItemTable(MFItems.BLOODWOOD_SIGN.asItem()));

        add(MFBlocks.BLOODWOOD_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.BLOODWOOD_HANGING_SIGN.asItem()));

        add(MFBlocks.BLOODWOOD_WALL_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.BLOODWOOD_HANGING_SIGN.asItem()));

        add(MFBlocks.TAINTED_SIGN.get(),
                createSingleItemTable(MFItems.TAINTED_SIGN.asItem()));

        add(MFBlocks.TAINTED_WALL_SIGN.get(),
                createSingleItemTable(MFItems.TAINTED_SIGN.asItem()));

        add(MFBlocks.TAINTED_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.TAINTED_HANGING_SIGN.asItem()));

        add(MFBlocks.TAINTED_WALL_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.TAINTED_HANGING_SIGN.asItem()));

        add(MFBlocks.PALM_SIGN.get(),
                createSingleItemTable(MFItems.PALM_SIGN.asItem()));

        add(MFBlocks.PALM_WALL_SIGN.get(),
                createSingleItemTable(MFItems.PALM_SIGN.asItem()));

        add(MFBlocks.PALM_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.PALM_HANGING_SIGN.asItem()));

        add(MFBlocks.PALM_WALL_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.PALM_HANGING_SIGN.asItem()));

        add(MFBlocks.PALLID_SIGN.get(),
                createSingleItemTable(MFItems.PALLID_SIGN.asItem()));

        add(MFBlocks.PALLID_WALL_SIGN.get(),
                createSingleItemTable(MFItems.PALLID_SIGN.asItem()));

        add(MFBlocks.PALLID_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.PALLID_HANGING_SIGN.asItem()));

        add(MFBlocks.PALLID_WALL_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.PALLID_HANGING_SIGN.asItem()));

        add(MFBlocks.DECREPIT_SIGN.get(),
                createSingleItemTable(MFItems.DECREPIT_SIGN.asItem()));

        add(MFBlocks.DECREPIT_WALL_SIGN.get(),
                createSingleItemTable(MFItems.DECREPIT_SIGN.asItem()));

        add(MFBlocks.DECREPIT_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.DECREPIT_HANGING_SIGN.asItem()));

        add(MFBlocks.DECREPIT_WALL_HANGING_SIGN.get(),
                createSingleItemTable(MFItems.DECREPIT_HANGING_SIGN.asItem()));

        // DOOR

        add(MFBlocks.AZURITE_DOOR.get(), this::createDoorTable);
        add(MFBlocks.PALM_DOOR.get(), this::createDoorTable);

        // ORES

        // Aluminium ores

        add(MFBlocks.ALUMINIUM_ORE.get(),
                createOreDrop(MFBlocks.ALUMINIUM_ORE.get(), MFItems.RAW_ALUMINIUM.get()));
        add(MFBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                createOreDrop(MFBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), MFItems.RAW_ALUMINIUM.get()));

        // Everfrost ores

        add(MFBlocks.EVERFROST_PACKED_ICE_ORE.get(),
                createOreDrop(MFBlocks.EVERFROST_PACKED_ICE_ORE.get(), MFItems.RAW_EVERFROST.get()));
        add(MFBlocks.EVERFROST_BLUE_ICE_ORE.get(),
                createOreDrop(MFBlocks.EVERFROST_BLUE_ICE_ORE.get(), MFItems.RAW_EVERFROST.get()));


        // Magnesium ores

        add(MFBlocks.MAGNESIUM_ORE.get(),
                createMultipleOreDrops(MFBlocks.MAGNESIUM_ORE.get(), MFItems.RAW_MAGNESIUM.get(), 1, 3));
        add(MFBlocks.DEEPSLATE_MAGNESIUM_ORE.get(),
                createMultipleOreDrops(MFBlocks.MAGNESIUM_ORE.get(), MFItems.RAW_MAGNESIUM.get(), 2, 4));

        // Azurite ores

        add(MFBlocks.AZURITE_ORE.get(),
                createOreDrop(MFBlocks.AZURITE_ORE.get(), MFItems.RAW_AZURITE.get()));
        add(MFBlocks.DEEPSLATE_AZURITE_ORE.get(),
                createOreDrop(MFBlocks.DEEPSLATE_AZURITE_ORE.get(), MFItems.RAW_AZURITE.get()));
        add(MFBlocks.NETHER_AZURITE_ORE.get(),
                createOreDrop(MFBlocks.NETHER_AZURITE_ORE.get(), MFItems.RAW_AZURITE.get()));
        add(MFBlocks.END_AZURITE_ORE.get(),
                createOreDrop(MFBlocks.END_AZURITE_ORE.get(), MFItems.RAW_AZURITE.get()));

        // Fluorite ores

        add(MFBlocks.FLUORITE_ORE.get(),
                createOreDrop(MFBlocks.FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get()));
        add(MFBlocks.DEEPSLATE_FLUORITE_ORE.get(),
                createOreDrop(MFBlocks.DEEPSLATE_FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get()));
        add(MFBlocks.NETHER_FLUORITE_ORE.get(),
                createOreDrop(MFBlocks.NETHER_FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get()));
        add(MFBlocks.END_FLUORITE_ORE.get(),
                createOreDrop(MFBlocks.END_FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get()));

        // Bismuth ore

        add(MFBlocks.BISMUTH_ORE.get(),
                createOreDrop(MFBlocks.BISMUTH_ORE.get(), MFItems.RAW_BISMUTH.get()));
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
        return MFBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createVerticalSlabItemTable(Block slab) { // Built pretty much entirely out of the createSlabItemTable method
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(this.applyExplosionDecay(slab, LootItem.lootTableItem(slab).apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(slab)
                                                .setProperties(StatePropertiesPredicate.Builder.properties()
                                                        .hasProperty(VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE)) // These two are the reason a custom method was necessary
                                ))
                        )
                )
        );
    }
}
