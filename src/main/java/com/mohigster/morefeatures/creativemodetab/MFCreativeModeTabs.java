package com.mohigster.morefeatures.creativemodetab;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class MFCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreFeatures.MODID);

    public static final Supplier<CreativeModeTab> MOREFEATURES_ITEMS_TAB = CREATIVE_MODE_TABS.register("morefeatures_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFItems.RAW_ALUMINIUM.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_items"))
            .withTabsBefore(CreativeModeTabs.INGREDIENTS)
            .withTabsAfter(MFIdentifier.withMfNamespace("morefeatures_blocks_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Aluminium items
                output.accept(MFItems.RAW_ALUMINIUM);
                output.accept(MFItems.ALUMINIUM_INGOT);

                // Magnesium items
                output.accept(MFItems.RAW_MAGNESIUM);
                output.accept(MFItems.MAGNESIUM_INGOT);

                // Azurite items
                output.accept(MFItems.RAW_AZURITE);
                output.accept(MFItems.AZURITE);

                // Fluorite items
                output.accept(MFItems.RAW_FLUORITE);
                output.accept(MFItems.FLUORITE);

                // Bismuth items
                output.accept(MFItems.RAW_BISMUTH);
                output.accept(MFItems.BISMUTH);
                output.accept(MFItems.BISMUTH_SCRAP);
                output.accept(MFItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE);

                // Carbon
                output.accept(MFItems.CARBON_FIBER);

                // Music disc
                output.accept(MFItems.MUSIC_DISC_AQUAMARINE);
            })
            .build()
    );


    // May subdivide this blocks tab into natural blocks, building blocks, etc. Depends on what I add and how big these tabs get.
    public static final Supplier<CreativeModeTab> MOREFEATURES_BLOCKS_TAB = CREATIVE_MODE_TABS.register("morefeatures_blocks_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFBlocks.ALUMINIUM_ORE.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_blocks"))
            .displayItems((itemDisplayParameters, output) -> {

                // Aluminium blocks
                output.accept(MFBlocks.ALUMINIUM_BLOCK);
                output.accept(MFBlocks.RAW_ALUMINIUM_BLOCK);
                output.accept(MFBlocks.ALUMINIUM_ORE);
                output.accept(MFBlocks.DEEPSLATE_ALUMINIUM_ORE);

                // Magnesium blocks
                output.accept(MFBlocks.MAGNESIUM_BLOCK);
                output.accept(MFBlocks.RAW_MAGNESIUM_BLOCK);
                output.accept(MFBlocks.MAGNESIUM_ORE);
                output.accept(MFBlocks.DEEPSLATE_MAGNESIUM_ORE);

                // Bismuth blocks
                output.accept(MFBlocks.BISMUTH_BLOCK);
                output.accept(MFBlocks.RAW_BISMUTH_BLOCK);
                output.accept(MFBlocks.BISMUTH_ORE);

                // Azurite blocks
                output.accept(MFBlocks.AZURITE_BLOCK);
                output.accept(MFBlocks.AZURITE_STAIRS);
                output.accept(MFBlocks.AZURITE_SLAB);
                output.accept(MFBlocks.AZURITE_BUTTON);
                output.accept(MFBlocks.AZURITE_PRESSURE_PLATE);
                output.accept(MFBlocks.RAW_AZURITE_BLOCK);
                output.accept(MFBlocks.AZURITE_ORE);
                output.accept(MFBlocks.DEEPSLATE_AZURITE_ORE);
                output.accept(MFBlocks.NETHER_AZURITE_ORE);
                output.accept(MFBlocks.END_AZURITE_ORE);

                // Fluorite blocks
                output.accept(MFBlocks.FLUORITE_BLOCK);
                output.accept(MFBlocks.FLUORITE_STAIRS);
                output.accept(MFBlocks.FLUORITE_SLAB);
                output.accept(MFBlocks.RAW_FLUORITE_BLOCK);
                output.accept(MFBlocks.FLUORITE_ORE);
                output.accept(MFBlocks.DEEPSLATE_FLUORITE_ORE);
                output.accept(MFBlocks.NETHER_FLUORITE_ORE);
                output.accept(MFBlocks.END_FLUORITE_ORE);

                // Magic block
                output.accept(MFBlocks.MAGIC_BLOCK);

                // Compressor block
                output.accept(MFBlocks.COMPRESSOR_BLOCK);

                // Void anchor block
                output.accept(MFBlocks.VOID_ANCHOR);

                // Decrepit blocks
                output.accept(MFBlocks.DECREPIT_NULLIUM);
                output.accept(MFBlocks.DECREPIT_ROOTS);

                // Pallid blocks
                output.accept(MFBlocks.PALLID_NULLIUM);
                output.accept(MFBlocks.PALLID_ROOTS);
            })
            .build()
    );

    public static final Supplier<CreativeModeTab> MOREFEATURES_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("morefeatures_equipment_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFItems.BISMUTH_EQUIPMENT.getFirst().asItem())) // 0 is Sword
            .title(Component.translatable("creativetab.morefeatures.morefeatures_equipment"))
            .withTabsBefore(MFIdentifier.withMfNamespace("morefeatures_blocks_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Carbon tools and equipment

                output.accept(MFItems.CARBON_TRIDENT);
                output.accept(MFItems.CARBON_BOW);
                output.accept(MFItems.CARBON_CROSSBOW);
                output.accept(MFItems.CARBON_ELYTRA);
                output.accept(MFItems.CARBON_WOLF_ARMOR);

                // Bismuth Tools

                output.accept(MFItems.BISMUTH_EQUIPMENT.get(0).asItem());
                output.accept(MFItems.BISMUTH_EQUIPMENT.get(1).asItem());
                output.accept(MFItems.BISMUTH_AXE);
                output.accept(MFItems.BISMUTH_SHOVEL);
                output.accept(MFItems.BISMUTH_HOE);
                output.accept(MFItems.BISMUTH_SPEAR);
                output.accept(MFItems.BISMUTH_TRIDENT);
                output.accept(MFItems.BISMUTH_BOW);

                // Bismuth Armor

                output.accept(MFItems.BISMUTH_EQUIPMENT.get(2).asItem());
                output.accept(MFItems.BISMUTH_EQUIPMENT.get(3).asItem());
                output.accept(MFItems.BISMUTH_EQUIPMENT.get(4).asItem());
                output.accept(MFItems.BISMUTH_EQUIPMENT.get(5).asItem());
                output.accept(MFItems.BISMUTH_HORSE_ARMOR);
                output.accept(MFItems.BISMUTH_NAUTILUS_ARMOR);

                // Metal detector

                output.accept(MFItems.METAL_DETECTOR);
            })
            .build()
    );

    public static final Supplier<CreativeModeTab> MOREFEATURES_WOOD_TAB = CREATIVE_MODE_TABS.register("morefeatures_wood_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFBlocks.BLOODWOOD_LOG))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_wood"))
            .withTabsBefore(MFIdentifier.withMfNamespace("morefeatures_equipment_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Bloodwood

                output.accept(MFBlocks.BLOODWOOD_LOG);
                output.accept(MFBlocks.BLOODWOOD);
                output.accept(MFBlocks.STRIPPED_BLOODWOOD_LOG);
                output.accept(MFBlocks.STRIPPED_BLOODWOOD);
                output.accept(MFBlocks.BLOODWOOD_PLANKS);
                output.accept(MFBlocks.BLOODWOOD_STAIRS);
                output.accept(MFBlocks.BLOODWOOD_VERTICAL_SLAB);
                output.accept(MFBlocks.BLOODWOOD_SLAB);
                output.accept(MFBlocks.BLOODWOOD_FENCE);
                output.accept(MFBlocks.BLOODWOOD_FENCE_GATE);
                output.accept(MFBlocks.BLOODWOOD_PRESSURE_PLATE);
                output.accept(MFBlocks.BLOODWOOD_BUTTON);
                output.accept(MFBlocks.BLOODWOOD_LEAVES);
                output.accept(MFBlocks.BLOODWOOD_SAPLING);
                output.accept(MFBlocks.BLOODWOOD_SHELF);
                output.accept(MFItems.BLOODWOOD_SIGN);
                output.accept(MFItems.BLOODWOOD_HANGING_SIGN);

                // Tainted wood

                output.accept(MFBlocks.TAINTED_LOG);
                output.accept(MFBlocks.TAINTED_WOOD);
                output.accept(MFBlocks.STRIPPED_TAINTED_LOG);
                output.accept(MFBlocks.STRIPPED_TAINTED_WOOD);
                output.accept(MFBlocks.TAINTED_PLANKS);
                output.accept(MFBlocks.TAINTED_STAIRS);
                output.accept(MFBlocks.TAINTED_VERTICAL_SLAB);
                output.accept(MFBlocks.TAINTED_SLAB);
                output.accept(MFBlocks.TAINTED_FENCE);
                output.accept(MFBlocks.TAINTED_FENCE_GATE);
                output.accept(MFBlocks.TAINTED_PRESSURE_PLATE);
                output.accept(MFBlocks.TAINTED_BUTTON);
                output.accept(MFBlocks.TAINTED_LEAVES);
                output.accept(MFBlocks.TAINTED_SAPLING);
                output.accept(MFBlocks.TAINTED_SHELF);
                output.accept(MFItems.TAINTED_SIGN);
                output.accept(MFItems.TAINTED_HANGING_SIGN);

                // Palm wood

                output.accept(MFBlocks.PALM_LOG);
                output.accept(MFBlocks.PALM_WOOD);
                output.accept(MFBlocks.STRIPPED_PALM_LOG);
                output.accept(MFBlocks.STRIPPED_PALM_WOOD);
                output.accept(MFBlocks.PALM_PLANKS);
                output.accept(MFBlocks.PALM_STAIRS);
                output.accept(MFBlocks.PALM_VERTICAL_SLAB);
                output.accept(MFBlocks.PALM_SLAB);
                output.accept(MFBlocks.PALM_FENCE);
                output.accept(MFBlocks.PALM_FENCE_GATE);
                output.accept(MFBlocks.PALM_DOOR);
                output.accept(MFBlocks.PALM_TRAPDOOR);
                output.accept(MFBlocks.PALM_PRESSURE_PLATE);
                output.accept(MFBlocks.PALM_BUTTON);
                output.accept(MFBlocks.PALM_LEAVES);
                output.accept(MFBlocks.PALM_SAPLING);
                output.accept(MFBlocks.PALM_SHELF);
                output.accept(MFItems.PALM_SIGN);
                output.accept(MFItems.PALM_HANGING_SIGN);
                output.accept(MFItems.PALM_BOAT);
                output.accept(MFItems.PALM_CHEST_BOAT);

                // Decrepit wood

                output.accept(MFBlocks.DECREPIT_LOG);
                output.accept(MFBlocks.DECREPIT_WOOD);
                output.accept(MFBlocks.STRIPPED_DECREPIT_LOG);
                output.accept(MFBlocks.STRIPPED_DECREPIT_WOOD);
                output.accept(MFBlocks.DECREPIT_PLANKS);
                output.accept(MFBlocks.DECREPIT_STAIRS);
                output.accept(MFBlocks.DECREPIT_VERTICAL_SLAB);
                output.accept(MFBlocks.DECREPIT_SLAB);
                output.accept(MFBlocks.DECREPIT_FENCE);
                output.accept(MFBlocks.DECREPIT_FENCE_GATE);
                output.accept(MFBlocks.DECREPIT_PRESSURE_PLATE);
                output.accept(MFBlocks.DECREPIT_BUTTON);
                output.accept(MFBlocks.DECREPIT_LEAVES);
                output.accept(MFBlocks.DECREPIT_SAPLING);
                output.accept(MFBlocks.DECREPIT_SHELF);
                output.accept(MFItems.DECREPIT_SIGN);
                output.accept(MFItems.DECREPIT_HANGING_SIGN);

                // Pallid wood

                output.accept(MFBlocks.PALLID_LOG);
                output.accept(MFBlocks.PALLID_WOOD);
                output.accept(MFBlocks.STRIPPED_PALLID_LOG);
                output.accept(MFBlocks.STRIPPED_PALLID_WOOD);
                output.accept(MFBlocks.PALLID_PLANKS);
                output.accept(MFBlocks.PALLID_STAIRS);
                output.accept(MFBlocks.PALLID_VERTICAL_SLAB);
                output.accept(MFBlocks.PALLID_SLAB);
                output.accept(MFBlocks.PALLID_FENCE);
                output.accept(MFBlocks.PALLID_FENCE_GATE);
                output.accept(MFBlocks.PALLID_PRESSURE_PLATE);
                output.accept(MFBlocks.PALLID_BUTTON);
                output.accept(MFBlocks.PALLID_LEAVES);
                output.accept(MFBlocks.PALLID_SAPLING);
                output.accept(MFBlocks.PALLID_SHELF);
                output.accept(MFItems.PALLID_SIGN);
                output.accept(MFItems.PALLID_HANGING_SIGN);
            })
            .build()
    );


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
