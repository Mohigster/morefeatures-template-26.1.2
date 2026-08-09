package com.mohigster.morefeatures.data.generators.loot.sub;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.block.custom.BlueBerryBushBlock;
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
import net.minecraft.world.level.block.ColorCollection;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jspecify.annotations.NullMarked;

import java.util.Set;

public class MFBlockLootTableProvider extends BlockLootSubProvider {

    public MFBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        // SELF DROPPING

        this.dropSelf(MFBlocks.ALUMINIUM_BLOCK.get());
        this.dropSelf(MFBlocks.MAGNESIUM_BLOCK.get());
        this.dropSelf(MFBlocks.RAW_ALUMINIUM_BLOCK.get());
        this.dropSelf(MFBlocks.RAW_MAGNESIUM_BLOCK.get());
        this.dropSelf(MFBlocks.AZURITE_BLOCK.get());
        this.dropSelf(MFBlocks.RAW_AZURITE_BLOCK.get());
        this.dropSelf(MFBlocks.FLUORITE_BLOCK.get());
        this.dropSelf(MFBlocks.RAW_FLUORITE_BLOCK.get());
        this.dropSelf(MFBlocks.BISMUTH_BLOCK.get());
        this.dropSelf(MFBlocks.RAW_BISMUTH_BLOCK.get());
        this.dropSelf(MFBlocks.BLOODWOOD_SAPLING.get());
        this.dropSelf(MFBlocks.TEMPORAL_DILATOR.get());
        this.dropSelf(MFBlocks.TAINTED_SAPLING.get());
        this.dropSelf(MFBlocks.CHARRED_ROOTS.get());
        this.dropSelf(MFBlocks.PALM_SAPLING.get());
        this.dropSelf(MFBlocks.DECREPIT_SAPLING.get());
        this.dropSelf(MFBlocks.PALLID_SAPLING.get());
        this.dropSelf(MFBlocks.MAGIC_BLOCK.get());
        this.dropSelf(MFBlocks.AZURITE_STAIRS.get());
        this.dropSelf(MFBlocks.AZURITE_TRAPDOOR.get());
        this.dropSelf(MFBlocks.FLUORITE_STAIRS.get());
        this.dropSelf(MFBlocks.FLUORITE_SHELF.get());
        this.dropSelf(MFBlocks.FLUORITE_FENCE.get());
        this.dropSelf(MFBlocks.FLUORITE_FENCE_GATE.get());
        this.dropSelf(MFBlocks.FLUORITE_TRAPDOOR.get());
        this.dropSelf(MFBlocks.COMPRESSOR_BLOCK.get());
        this.dropSelf(MFBlocks.AZURITE_BUTTON.get());
        this.dropSelf(MFBlocks.AZURITE_PRESSURE_PLATE.get());
        this.dropSelf(MFBlocks.FLUORITE_BUTTON.get());
        this.dropSelf(MFBlocks.FLUORITE_WALL.get());
        this.dropSelf(MFBlocks.FLUORITE_PRESSURE_PLATE.get());
        this.dropSelf(MFBlocks.AZURITE_FENCE.get());
        this.dropSelf(MFBlocks.AZURITE_FENCE_GATE.get());
        this.dropSelf(MFBlocks.AZURITE_SHELF.get());
        this.dropSelf(MFBlocks.ROSE.get());
        this.dropSelf(MFBlocks.BLUE_ROSE.get());
        this.dropSelf(MFBlocks.EVIL_PORTAL.get());
        this.dropSelf(MFBlocks.TEST_PILLAR_BLOCK.get());
        this.dropSelf(MFBlocks.AZURITE_WALL.get());
        this.dropSelf(MFBlocks.DECREPIT_ROOTS.get());
        this.dropSelf(MFBlocks.PALLID_ROOTS.get());
        this.dropSelf(MFBlocks.VOID_ANCHOR.get());
        this.dropSelf(MFBlocks.ICICLE.get());
        this.dropSelf(MFBlocks.CHARRED_WART_BLOCK.get());
        this.dropSelf(MFBlocks.CHARRED_FUNGUS.get());
        this.dropSelf(MFBlocks.CONCRETE_PILLAR);
        this.dropSelf(MFBlocks.PLANKS);
        this.dropSelf(MFBlocks.LOG);
        this.dropSelf(MFBlocks.WOOD);
        this.dropSelf(MFBlocks.STRIPPED_LOG);
        this.dropSelf(MFBlocks.STRIPPED_WOOD);
        this.dropSelf(MFBlocks.WOODEN_STAIRS);
        this.dropSelf(MFBlocks.WOODEN_FENCE);
        this.dropSelf(MFBlocks.WOODEN_FENCE_GATE);
        this.dropSelf(MFBlocks.WOODEN_PRESSURE_PLATE);
        this.dropSelf(MFBlocks.WOODEN_BUTTON);
        this.dropSelf(MFBlocks.WOODEN_SHELF);
        this.dropSelf(MFBlocks.WOODEN_TRAPDOOR);

        // NETHER VINES
        this.addNetherVinesDropTable(MFBlocks.SCORCHED_VINES.get(), MFBlocks.SCORCHED_VINES_PLANT.get());
        this.addNetherVinesDropTable(MFBlocks.SMOLDERED_VINES.get(), MFBlocks.SMOLDERED_VINES_PLANT.get());

        // POTTED PLANTS

        this.flowerPotDrops(MFBlocks.POTTED_ROSE.get(),MFBlocks.ROSE.get());
        this.flowerPotDrops(MFBlocks.POTTED_BLUE_ROSE.get(), MFBlocks.BLUE_ROSE.get());
        WoodTypeCollection.SETS.forEach(set -> this.flowerPotDrops(MFBlocks.POTTED_SAPLING.pick(set).get(), set.getSaplingOrFungus().get()));
        this.flowerPotDrops(MFBlocks.POTTED_DECREPIT_ROOTS.get(), MFBlocks.DECREPIT_ROOTS.get());
        this.flowerPotDrops(MFBlocks.POTTED_PALLID_ROOTS.get(), MFBlocks.PALLID_ROOTS.get());
        this.flowerPotDrops(MFBlocks.POTTED_CHARRED_ROOTS.get(), MFBlocks.CHARRED_ROOTS.get());

        // SILK TOUCH DROPS

        this.dropOtherWhenNoSilkTouch(MFBlocks.CHARRED_NYLIUM.get(), Blocks.NETHERRACK);

        this.dropOtherWhenNoSilkTouch(MFBlocks.PALLID_NULLIUM.get(), Blocks.END_STONE);
        this.dropOtherWhenNoSilkTouch(MFBlocks.DECREPIT_NULLIUM.get(), Blocks.END_STONE);

        // NO DROP

        this.add(MFBlocks.CONJURED_ICE.get(), noDrop());

        // SLABS

        this.slabDrops(MFBlocks.AZURITE_SLAB.get());
        this.slabDrops(MFBlocks.FLUORITE_SLAB.get());
        this.slabDrops(MFBlocks.WOODEN_SLAB);

        MFBlocks.WOODEN_DOOR.forEach(block -> this.doorDrops(block.get()));

        // VERTICAL SLABS

        this.verticalSlabDrops(MFBlocks.AZURITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.FLUORITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.OAK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SPRUCE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.BIRCH_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.JUNGLE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.ACACIA_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.DARK_OAK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.CRIMSON_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.WARPED_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.MANGROVE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.CHERRY_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.BAMBOO_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.BAMBOO_MOSAIC_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.PALE_OAK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.STONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.COBBLESTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.MOSSY_COBBLESTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SMOOTH_STONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.STONE_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.MOSSY_STONE_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.COBBLED_DEEPSLATE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_DEEPSLATE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.DEEPSLATE_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.DEEPSLATE_TILE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.GRANITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_GRANITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.DIORITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_DIORITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.ANDESITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_ANDESITE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.TUFF_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_TUFF_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.TUFF_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SULFUR_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_SULFUR_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SULFUR_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.CINNABAR_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_CINNABAR_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.CINNABAR_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SANDSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SMOOTH_SANDSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.CUT_SANDSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.RED_SANDSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SMOOTH_RED_SANDSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.CUT_RED_SANDSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.NETHER_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.RED_NETHER_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.BLACKSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_BLACKSTONE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.POLISHED_BLACKSTONE_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.END_STONE_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.PURPUR_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.QUARTZ_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.SMOOTH_QUARTZ_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.PRISMARINE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.PRISMARINE_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.DARK_PRISMARINE_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.MUD_BRICK_VERTICAL_SLAB.get());
        this.verticalSlabDrops(MFBlocks.RESIN_BRICK_VERTICAL_SLAB.get());

        MFBlocks.CUT_COPPER_VERTICAL_SLAB.forEach(block -> this.verticalSlabDrops(block.get()));
        this.verticalSlabDrops(MFBlocks.WOODEN_VERTICAL_SLAB);

        // Whilst the method name is the same, this method accepts a color collection rather than a block
        this.verticalSlabDrops(MFBlocks.WOOL_VERTICAL_SLAB);
        this.verticalSlabDrops(MFBlocks.CONCRETE_VERTICAL_SLAB);
        
        MFBlocks.CUT_COPPER_PILLAR.forEach(block -> this.dropSelf(block.get()));

        // LEAVES

        this.leavesDrops(MFBlocks.BLOODWOOD_LEAVES.get(), MFBlocks.BLOODWOOD_SAPLING.get());
        this.leavesDrops(MFBlocks.TAINTED_LEAVES.get(), MFBlocks.TAINTED_SAPLING.get());
        this.leavesDrops(MFBlocks.PALM_LEAVES.get(), MFBlocks.PALM_SAPLING.get());
        this.leavesDrops(MFBlocks.DECREPIT_LEAVES.get(), MFBlocks.DECREPIT_SAPLING.get());
        this.leavesDrops(MFBlocks.PALLID_LEAVES.get(), MFBlocks.PALLID_SAPLING.get());

        // SIGNS

        this.signDrops(MFBlocks.AZURITE_SIGN.get(), MFBlocks.AZURITE_WALL_SIGN.get(), MFItems.AZURITE_SIGN.get());
        this.signDrops(MFBlocks.AZURITE_HANGING_SIGN.get(), MFBlocks.AZURITE_WALL_HANGING_SIGN.get(), MFItems.AZURITE_HANGING_SIGN.get());
        this.signDrops(MFBlocks.FLUORITE_SIGN.get(), MFBlocks.FLUORITE_WALL_SIGN.get(), MFItems.FLUORITE_SIGN.get());
        this.signDrops(MFBlocks.FLUORITE_HANGING_SIGN.get(), MFBlocks.FLUORITE_WALL_HANGING_SIGN.get(), MFItems.FLUORITE_HANGING_SIGN.get());
        this.signDrops(MFBlocks.WOODEN_SIGN, MFBlocks.WOODEN_WALL_SIGN, MFItems.SIGN);
        this.signDrops(MFBlocks.WOODEN_HANGING_SIGN, MFBlocks.WOODEN_WALL_HANGING_SIGN, MFItems.HANGING_SIGN);

        // DOOR

        this.doorDrops(MFBlocks.AZURITE_DOOR.get());
        this.doorDrops(MFBlocks.FLUORITE_DOOR.get());

        // ORES

        // Aluminium ores

        this.oreDrops(MFBlocks.ALUMINIUM_ORE.get(), MFItems.RAW_ALUMINIUM.get());
        this.oreDrops(MFBlocks.DEEPSLATE_ALUMINIUM_ORE.get(), MFItems.RAW_ALUMINIUM.get());

        // Everfrost ores

        this.oreDrops(MFBlocks.EVERFROST_PACKED_ICE_ORE.get(), MFItems.RAW_EVERFROST.get());
        this.oreDrops(MFBlocks.EVERFROST_BLUE_ICE_ORE.get(), MFItems.RAW_EVERFROST.get());

        // Magnesium ores

        this.oreDrops(MFBlocks.MAGNESIUM_ORE.get(), MFItems.RAW_MAGNESIUM.get(), 1, 3);
        this.oreDrops(MFBlocks.DEEPSLATE_MAGNESIUM_ORE.get(), MFItems.RAW_MAGNESIUM.get(), 2, 4);

        // Azurite ores

        this.oreDrops(MFBlocks.AZURITE_ORE.get(), MFItems.RAW_AZURITE.get());
        this.oreDrops(MFBlocks.DEEPSLATE_AZURITE_ORE.get(), MFItems.RAW_AZURITE.get());
        this.oreDrops(MFBlocks.NETHER_AZURITE_ORE.get(), MFItems.RAW_AZURITE.get());
        this.oreDrops(MFBlocks.END_AZURITE_ORE.get(), MFItems.RAW_AZURITE.get());

        // Fluorite ores

        this.oreDrops(MFBlocks.FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get());
        this.oreDrops(MFBlocks.DEEPSLATE_FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get());
        this.oreDrops(MFBlocks.NETHER_FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get());
        this.oreDrops(MFBlocks.END_FLUORITE_ORE.get(), MFItems.RAW_FLUORITE.get());

        // Bismuth ore

        this.oreDrops(MFBlocks.BISMUTH_ORE.get(), MFItems.RAW_BISMUTH.get());

        // Berry bush

        this.berryBushDrops(MFBlocks.BLUE_BERRY_BUSH.get(), MFItems.BLUE_BERRY.get());
    }


    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }

    @NullMarked
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MFBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }

    protected LootTable.Builder createVerticalSlabItemTable(Block verticalSlab) { // Built pretty much entirely out of the createSlabItemTable method
        return LootTable.lootTable().withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                .add(this.applyExplosionDecay(verticalSlab, LootItem.lootTableItem(verticalSlab)
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F))
                                .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(verticalSlab)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(
                                                VerticalSlabBlock.TYPE, VerticalSlabType.DOUBLE))))))
        );
    }

    protected <T extends Block> void verticalSlabDrops(ColorCollection<DeferredBlock<T>> blockSet){
        blockSet.forEach(block -> this.verticalSlabDrops(block.get()));
    }

    @SuppressWarnings("SameParameterValue")
    protected <T extends Block> void verticalSlabDrops(WoodTypeCollection<DeferredBlock<T>> blockSet){
        blockSet.forEach(block -> this.verticalSlabDrops(block.get()));
    }
    
    protected void verticalSlabDrops(Block block){
        this.add(block, this::createVerticalSlabItemTable);
    }

    protected void slabDrops(Block block){
        this.add(block, this::createSlabItemTable);
    }

    @SuppressWarnings("SameParameterValue")
    protected <T extends Block> void slabDrops(WoodTypeCollection<DeferredBlock<T>> blockSet){
        blockSet.forEach(block -> this.slabDrops(block.get()));
    }

    protected void leavesDrops(Block leaves, Block sapling){
        this.add(leaves, this.createLeavesDrops(leaves, sapling, NORMAL_LEAVES_SAPLING_CHANCES));
    }

    protected void signDrops(Block standing, Block wall, Item sign){
        this.add(standing, this.createSingleItemTable(sign));
        this.add(wall, this.createSingleItemTable(sign));
    }

    protected <B extends Block, I extends Item> void signDrops(WoodTypeCollection<DeferredBlock<B>> standing, WoodTypeCollection<DeferredBlock<B>> wall, WoodTypeCollection<DeferredItem<I>> item){
        WoodTypeCollection.SETS.forEach(type ->
                this.signDrops(standing.pick(type).get(), wall.pick(type).get(), item.pick(type).get())
        );
    }

    protected void oreDrops(Block ore, Item drop){
        this.add(ore, this.createOreDrop(ore, drop));
    }

    protected void oreDrops(Block ore, Item drop, float minDrops, float maxDrops){
        this.add(ore, this.createMultipleOreDrops(ore, drop, minDrops, maxDrops));
    }

    @SuppressWarnings("SameParameterValue")
    protected <T extends Block> void dropSelf(ColorCollection<DeferredBlock<T>> blockSet){
        blockSet.forEach(block -> this.dropSelf(block.get()));
    }

    protected <T extends Block> void dropSelf(WoodTypeCollection<DeferredBlock<T>> blockSet){
        blockSet.forEach(block -> this.dropSelf(block.get()));
    }

    protected void berryBushDrops(Block bush, Item berry) {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        this.add(bush, block -> this.applyExplosionDecay(
                block, LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(bush)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueBerryBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(berry))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(bush)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueBerryBushBlock.AGE, 2))
                        )
                        .add(LootItem.lootTableItem(berry))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                )));
    }

    protected void dropOtherWhenNoSilkTouch(Block block, Block other){
        this.add(block, this.createSingleItemTableWithSilkTouch(block, other));
    }

    protected void doorDrops(Block block){
        this.add(block, this::createDoorTable);
    }

    protected void flowerPotDrops(Block potted, Block flower){
        this.add(potted, this.createPotFlowerItemTable(flower));
    }
}
