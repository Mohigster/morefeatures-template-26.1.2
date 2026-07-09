package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabBlock;
import com.mohigster.morefeatures.block.custom.verticalslab.VerticalSlabType;
import com.mohigster.morefeatures.item.ModItems;
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
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    public ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        // SELF DROPPING

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
        dropSelf(ModBlocks.BLOODWOOD_PLANKS.get());
        dropSelf(ModBlocks.BLOODWOOD_LOG.get());
        dropSelf(ModBlocks.BLOODWOOD.get());
        dropSelf(ModBlocks.BLOODWOOD_FENCE.get());
        dropSelf(ModBlocks.BLOODWOOD_FENCE_GATE.get());
        dropSelf(ModBlocks.STRIPPED_BLOODWOOD_LOG.get());
        dropSelf(ModBlocks.STRIPPED_BLOODWOOD.get());
        dropSelf(ModBlocks.BLOODWOOD_SAPLING.get());
        dropSelf(ModBlocks.BLOODWOOD_BUTTON.get());
        dropSelf(ModBlocks.BLOODWOOD_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.BLOODWOOD_SHELF.get());
        dropSelf(ModBlocks.TAINTED_PLANKS.get());
        dropSelf(ModBlocks.TAINTED_LOG.get());
        dropSelf(ModBlocks.TAINTED_WOOD.get());
        dropSelf(ModBlocks.TAINTED_FENCE.get());
        dropSelf(ModBlocks.TAINTED_FENCE_GATE.get());
        dropSelf(ModBlocks.TAINTED_BUTTON.get());
        dropSelf(ModBlocks.TAINTED_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.TAINTED_SHELF.get());
        dropSelf(ModBlocks.STRIPPED_TAINTED_LOG.get());
        dropSelf(ModBlocks.STRIPPED_TAINTED_WOOD.get());
        dropSelf(ModBlocks.TAINTED_SAPLING.get());
        dropSelf(ModBlocks.PALM_LOG.get());
        dropSelf(ModBlocks.PALM_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PALM_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PALM_WOOD.get());
        dropSelf(ModBlocks.PALM_SAPLING.get());
        dropSelf(ModBlocks.PALM_PLANKS.get());
        dropSelf(ModBlocks.PALM_FENCE_GATE.get());
        dropSelf(ModBlocks.PALM_FENCE.get());
        dropSelf(ModBlocks.DECREPIT_SAPLING.get());
        dropSelf(ModBlocks.PALLID_SAPLING.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());
        dropSelf(ModBlocks.AZURITE_STAIRS.get());
        dropSelf(ModBlocks.FLUORITE_STAIRS.get());
        dropSelf(ModBlocks.BLOODWOOD_STAIRS.get());
        dropSelf(ModBlocks.TAINTED_STAIRS.get());
        dropSelf(ModBlocks.PALM_STAIRS.get());
        dropSelf(ModBlocks.PALM_TRAPDOOR.get());
        dropSelf(ModBlocks.COMPRESSOR_BLOCK.get());
        dropSelf(ModBlocks.AZURITE_BUTTON.get());
        dropSelf(ModBlocks.AZURITE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PALM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PALM_BUTTON.get());
        dropSelf(ModBlocks.ROSE.get());
        dropSelf(ModBlocks.BLUE_ROSE.get());
        dropSelf(ModBlocks.EVIL_PORTAL.get());
        dropSelf(ModBlocks.PALM_SHELF.get());
        dropSelf(ModBlocks.DECREPIT_PLANKS.get());
        dropSelf(ModBlocks.DECREPIT_WOOD.get());
        dropSelf(ModBlocks.DECREPIT_LOG.get());
        dropSelf(ModBlocks.DECREPIT_BUTTON.get());
        dropSelf(ModBlocks.DECREPIT_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.DECREPIT_STAIRS.get());
        dropSelf(ModBlocks.DECREPIT_FENCE.get());
        dropSelf(ModBlocks.DECREPIT_FENCE_GATE.get());
        dropSelf(ModBlocks.DECREPIT_SHELF.get());
        dropSelf(ModBlocks.STRIPPED_DECREPIT_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_DECREPIT_LOG.get());
        dropSelf(ModBlocks.PALLID_PLANKS.get());
        dropSelf(ModBlocks.PALLID_WOOD.get());
        dropSelf(ModBlocks.PALLID_LOG.get());
        dropSelf(ModBlocks.PALLID_BUTTON.get());
        dropSelf(ModBlocks.PALLID_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PALLID_STAIRS.get());
        dropSelf(ModBlocks.PALLID_FENCE.get());
        dropSelf(ModBlocks.PALLID_FENCE_GATE.get());
        dropSelf(ModBlocks.PALLID_SHELF.get());
        dropSelf(ModBlocks.STRIPPED_PALLID_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PALLID_LOG.get());
        dropSelf(ModBlocks.DECREPIT_ROOTS.get());
        dropSelf(ModBlocks.PALLID_ROOTS.get());
        dropSelf(ModBlocks.VOID_ANCHOR.get());
        dropSelf(ModBlocks.ICICLE.get());

        // POTTED PLANTS

        add(ModBlocks.POTTED_ROSE.get(), createPotFlowerItemTable(ModBlocks.ROSE.get()));
        add(ModBlocks.POTTED_BLUE_ROSE.get(), createPotFlowerItemTable(ModBlocks.BLUE_ROSE.get()));
        add(ModBlocks.POTTED_TAINTED_SAPLING.get(), createPotFlowerItemTable(ModBlocks.TAINTED_SAPLING.get()));
        add(ModBlocks.POTTED_BLOODWOOD_SAPLING.get(), createPotFlowerItemTable(ModBlocks.BLOODWOOD_SAPLING.get()));
        add(ModBlocks.POTTED_PALM_SAPLING.get(), createPotFlowerItemTable(ModBlocks.PALM_SAPLING.get()));
        add(ModBlocks.POTTED_DECREPIT_SAPLING.get(), createPotFlowerItemTable(ModBlocks.DECREPIT_SAPLING.get()));
        add(ModBlocks.POTTED_PALLID_SAPLING.get(), createPotFlowerItemTable(ModBlocks.PALLID_SAPLING.get()));
        add(ModBlocks.POTTED_DECREPIT_ROOTS.get(), createPotFlowerItemTable(ModBlocks.DECREPIT_ROOTS.get()));
        add(ModBlocks.POTTED_PALLID_ROOTS.get(), createPotFlowerItemTable(ModBlocks.PALLID_ROOTS.get()));

        // SILK TOUCH DROPS

        add(ModBlocks.PALLID_NULLIUM.get(), createSingleItemTableWithSilkTouch(ModBlocks.PALLID_NULLIUM.get(), Blocks.END_STONE));
        add(ModBlocks.DECREPIT_NULLIUM.get(), createSingleItemTableWithSilkTouch(ModBlocks.DECREPIT_NULLIUM.get(), Blocks.END_STONE));

        // NO DROP

        add(ModBlocks.CONJURED_ICE.get(), noDrop());

        // SLABS

        add(ModBlocks.AZURITE_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.FLUORITE_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.BLOODWOOD_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.TAINTED_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.PALM_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.DECREPIT_SLAB.get(), this::createSlabItemTable);
        add(ModBlocks.PALLID_SLAB.get(), this::createSlabItemTable);

        // VERTICAL SLABS

        add(ModBlocks.DECREPIT_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.PALLID_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.OAK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SPRUCE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.BIRCH_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.JUNGLE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.ACACIA_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.DARK_OAK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.CRIMSON_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.WARPED_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.MANGROVE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.CHERRY_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.BAMBOO_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.PALE_OAK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.STONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.COBBLESTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SMOOTH_STONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.STONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.GRANITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_GRANITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.DIORITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_DIORITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.ANDESITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.TUFF_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_TUFF_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.TUFF_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SULFUR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_SULFUR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SULFUR_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.CINNABAR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.CINNABAR_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.CUT_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.RED_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.NETHER_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.BLACKSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.END_STONE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.PURPUR_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.QUARTZ_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.PRISMARINE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.DARK_PRISMARINE_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.MUD_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);
        add(ModBlocks.RESIN_BRICK_VERTICAL_SLAB.get(), this::createVerticalSlabItemTable);

        ModBlocks.CUT_COPPER_VERTICAL_SLAB.forEach(block -> this.add(block.get(), this::createVerticalSlabItemTable));
        ModBlocks.WOOL_VERTICAL_SLAB.forEach(block -> this.add(block.get(), this::createVerticalSlabItemTable));


        // LEAVES

        // Bloodwood
        add(ModBlocks.BLOODWOOD_LEAVES.get(),
                createLeavesDrops(ModBlocks.BLOODWOOD_LEAVES.get(), ModBlocks.BLOODWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Tainted
        add(ModBlocks.TAINTED_LEAVES.get(),
                createLeavesDrops(ModBlocks.TAINTED_LEAVES.get(), ModBlocks.TAINTED_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Palm
        add(ModBlocks.PALM_LEAVES.get(),
                createLeavesDrops(ModBlocks.PALM_LEAVES.get(), ModBlocks.PALM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Decrepit
        add(ModBlocks.DECREPIT_LEAVES.get(),
                createLeavesDrops(ModBlocks.DECREPIT_LEAVES.get(), ModBlocks.DECREPIT_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // Pallid
        add(ModBlocks.PALLID_LEAVES.get(),
                createLeavesDrops(ModBlocks.PALLID_LEAVES.get(), ModBlocks.PALLID_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        // SIGN

        add(ModBlocks.BLOODWOOD_SIGN.get(),
                createSingleItemTable(ModItems.BLOODWOOD_SIGN.asItem()));

        add(ModBlocks.BLOODWOOD_WALL_SIGN.get(),
                createSingleItemTable(ModItems.BLOODWOOD_SIGN.asItem()));

        add(ModBlocks.BLOODWOOD_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.BLOODWOOD_HANGING_SIGN.asItem()));

        add(ModBlocks.BLOODWOOD_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.BLOODWOOD_HANGING_SIGN.asItem()));

        add(ModBlocks.TAINTED_SIGN.get(),
                createSingleItemTable(ModItems.TAINTED_SIGN.asItem()));

        add(ModBlocks.TAINTED_WALL_SIGN.get(),
                createSingleItemTable(ModItems.TAINTED_SIGN.asItem()));

        add(ModBlocks.TAINTED_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.TAINTED_HANGING_SIGN.asItem()));

        add(ModBlocks.TAINTED_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.TAINTED_HANGING_SIGN.asItem()));

        add(ModBlocks.PALM_SIGN.get(),
                createSingleItemTable(ModItems.PALM_SIGN.asItem()));

        add(ModBlocks.PALM_WALL_SIGN.get(),
                createSingleItemTable(ModItems.PALM_SIGN.asItem()));

        add(ModBlocks.PALM_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.PALM_HANGING_SIGN.asItem()));

        add(ModBlocks.PALM_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.PALM_HANGING_SIGN.asItem()));

        add(ModBlocks.PALLID_SIGN.get(),
                createSingleItemTable(ModItems.PALLID_SIGN.asItem()));

        add(ModBlocks.PALLID_WALL_SIGN.get(),
                createSingleItemTable(ModItems.PALLID_SIGN.asItem()));

        add(ModBlocks.PALLID_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.PALLID_HANGING_SIGN.asItem()));

        add(ModBlocks.PALLID_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.PALLID_HANGING_SIGN.asItem()));

        add(ModBlocks.DECREPIT_SIGN.get(),
                createSingleItemTable(ModItems.DECREPIT_SIGN.asItem()));

        add(ModBlocks.DECREPIT_WALL_SIGN.get(),
                createSingleItemTable(ModItems.DECREPIT_SIGN.asItem()));

        add(ModBlocks.DECREPIT_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.DECREPIT_HANGING_SIGN.asItem()));

        add(ModBlocks.DECREPIT_WALL_HANGING_SIGN.get(),
                createSingleItemTable(ModItems.DECREPIT_HANGING_SIGN.asItem()));

        // DOOR

        add(ModBlocks.PALM_DOOR.get(), this::createDoorTable);

        // ORES

        // Aluminium ores

        add(ModBlocks.ALUMINIUM_ORE.get(),
                createOreDrop(ModBlocks.ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get()));
        add(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(),
                createOreDrop(ModBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), ModItems.RAW_ALUMINIUM.get()));

        // Everfrost ores

        add(ModBlocks.EVERFROST_PACKED_ICE_ORE.get(),
                createOreDrop(ModBlocks.EVERFROST_PACKED_ICE_ORE.get(), ModItems.RAW_EVERFROST.get()));
        add(ModBlocks.EVERFROST_BLUE_ICE_ORE.get(),
                createOreDrop(ModBlocks.EVERFROST_BLUE_ICE_ORE.get(), ModItems.RAW_EVERFROST.get()));


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
