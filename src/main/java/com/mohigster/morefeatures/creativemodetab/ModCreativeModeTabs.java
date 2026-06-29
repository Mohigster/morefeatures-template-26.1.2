package com.mohigster.morefeatures.creativemodetab;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreFeatures.MODID);

    public static final Supplier<CreativeModeTab> MOREFEATURES_ITEMS_TAB = CREATIVE_MODE_TABS.register("morefeatures_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.RAW_ALUMINIUM.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_items"))
            .withTabsBefore(CreativeModeTabs.INGREDIENTS)
            .withTabsAfter(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures_blocks_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Aluminium items
                output.accept(ModItems.RAW_ALUMINIUM);
                output.accept(ModItems.ALUMINIUM_INGOT);

                // Magnesium items
                output.accept(ModItems.RAW_MAGNESIUM);
                output.accept(ModItems.MAGNESIUM_INGOT);

                // Azurite items
                output.accept(ModItems.RAW_AZURITE);
                output.accept(ModItems.AZURITE);

                // Fluorite items
                output.accept(ModItems.RAW_FLUORITE);
                output.accept(ModItems.FLUORITE);

                // Bismuth items
                output.accept(ModItems.RAW_BISMUTH);
                output.accept(ModItems.BISMUTH);
                output.accept(ModItems.BISMUTH_SCRAP);
                output.accept(ModItems.BISMUTH_UPGRADE_SMITHING_TEMPLATE);

                // Carbon
                output.accept(ModItems.CARBON_FIBER);

                // Music disc
                output.accept(ModItems.MUSIC_DISC_AQUAMARINE);
            })
            .build());


    // May subdivide this blocks tab into natural blocks, building blocks, etc. Depends on what I add and how big these tabs get.
    public static final Supplier<CreativeModeTab> MOREFEATURES_BLOCKS_TAB = CREATIVE_MODE_TABS.register("morefeatures_blocks_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.ALUMINIUM_ORE.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_blocks"))
            .displayItems((itemDisplayParameters, output) -> {

                // Aluminium blocks
                output.accept(ModBlocks.ALUMINIUM_BLOCK);
                output.accept(ModBlocks.RAW_ALUMINIUM_BLOCK);
                output.accept(ModBlocks.ALUMINIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_ALUMINIUM_ORE);

                // Magnesium blocks
                output.accept(ModBlocks.MAGNESIUM_BLOCK);
                output.accept(ModBlocks.RAW_MAGNESIUM_BLOCK);
                output.accept(ModBlocks.MAGNESIUM_ORE);
                output.accept(ModBlocks.DEEPSLATE_MAGNESIUM_ORE);

                // Bismuth blocks
                output.accept(ModBlocks.BISMUTH_BLOCK);
                output.accept(ModBlocks.RAW_BISMUTH_BLOCK);
                output.accept(ModBlocks.BISMUTH_ORE);

                // Azurite blocks
                output.accept(ModBlocks.AZURITE_BLOCK);
                output.accept(ModBlocks.AZURITE_STAIRS);
                output.accept(ModBlocks.AZURITE_SLAB);
                output.accept(ModBlocks.AZURITE_BUTTON);
                output.accept(ModBlocks.AZURITE_PRESSURE_PLATE);
                output.accept(ModBlocks.RAW_AZURITE_BLOCK);
                output.accept(ModBlocks.AZURITE_ORE);
                output.accept(ModBlocks.DEEPSLATE_AZURITE_ORE);
                output.accept(ModBlocks.NETHER_AZURITE_ORE);
                output.accept(ModBlocks.END_AZURITE_ORE);

                // Fluorite blocks
                output.accept(ModBlocks.FLUORITE_BLOCK);
                output.accept(ModBlocks.FLUORITE_STAIRS);
                output.accept(ModBlocks.FLUORITE_SLAB);
                output.accept(ModBlocks.RAW_FLUORITE_BLOCK);
                output.accept(ModBlocks.FLUORITE_ORE);
                output.accept(ModBlocks.DEEPSLATE_FLUORITE_ORE);
                output.accept(ModBlocks.NETHER_FLUORITE_ORE);
                output.accept(ModBlocks.END_FLUORITE_ORE);

                // Magic block
                output.accept(ModBlocks.MAGIC_BLOCK);

                // Compressor block
                output.accept(ModBlocks.COMPRESSOR_BLOCK);

                // Void anchor block
                output.accept(ModBlocks.VOID_ANCHOR);

                // Decrepit blocks
                output.accept(ModBlocks.DECREPIT_NULLIUM);
                output.accept(ModBlocks.DECREPIT_ROOTS);

                // Pallid blocks
                output.accept(ModBlocks.PALLID_NULLIUM);
                output.accept(ModBlocks.PALLID_ROOTS);
            })
            .build());

    public static final Supplier<CreativeModeTab> MOREFEATURES_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("morefeatures_equipment_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())) // 0 is Sword
            .title(Component.translatable("creativetab.morefeatures.morefeatures_equipment"))
            .withTabsBefore(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures_blocks_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Carbon tools and equipment

                output.accept(ModItems.CARBON_TRIDENT);
                output.accept(ModItems.CARBON_BOW);
                output.accept(ModItems.CARBON_CROSSBOW);
                output.accept(ModItems.CARBON_ELYTRA);
                output.accept(ModItems.CARBON_WOLF_ARMOR);

                // Bismuth Tools

                output.accept(ModItems.BISMUTH_EQUIPMENT.get(0).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(1).asItem());
                output.accept(ModItems.BISMUTH_AXE);
                output.accept(ModItems.BISMUTH_SHOVEL);
                output.accept(ModItems.BISMUTH_HOE);
                output.accept(ModItems.BISMUTH_SPEAR);
                output.accept(ModItems.BISMUTH_TRIDENT);
                output.accept(ModItems.BISMUTH_BOW);

                // Bismuth Armor

                output.accept(ModItems.BISMUTH_EQUIPMENT.get(2).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(3).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(4).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());
                output.accept(ModItems.BISMUTH_HORSE_ARMOR);
                output.accept(ModItems.BISMUTH_NAUTILUS_ARMOR);

                // Metal detector

                output.accept(ModItems.METAL_DETECTOR);
            })
            .build());

    public static final Supplier<CreativeModeTab> MOREFEATURES_WOOD_TAB = CREATIVE_MODE_TABS.register("morefeatures_wood_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModBlocks.BLOODWOOD_LOG))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_wood"))
            .withTabsBefore(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures_equipment_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Bloodwood

                output.accept(ModBlocks.BLOODWOOD_LOG);
                output.accept(ModBlocks.BLOODWOOD);
                output.accept(ModBlocks.STRIPPED_BLOODWOOD_LOG);
                output.accept(ModBlocks.STRIPPED_BLOODWOOD);
                output.accept(ModBlocks.BLOODWOOD_PLANKS);
                output.accept(ModBlocks.BLOODWOOD_STAIRS);
                output.accept(ModBlocks.BLOODWOOD_SLAB);

                // Tainted wood

                output.accept(ModBlocks.TAINTED_LOG);
                output.accept(ModBlocks.TAINTED_WOOD);
                output.accept(ModBlocks.STRIPPED_TAINTED_LOG);
                output.accept(ModBlocks.STRIPPED_TAINTED_WOOD);
                output.accept(ModBlocks.TAINTED_PLANKS);
                output.accept(ModBlocks.TAINTED_STAIRS);
                output.accept(ModBlocks.TAINTED_SLAB);

                // Palm wood

                output.accept(ModBlocks.PALM_LOG);
                output.accept(ModBlocks.PALM_WOOD);
                output.accept(ModBlocks.STRIPPED_PALM_LOG);
                output.accept(ModBlocks.STRIPPED_PALM_WOOD);
                output.accept(ModBlocks.PALM_PLANKS);
                output.accept(ModBlocks.PALM_STAIRS);
                output.accept(ModBlocks.PALM_SLAB);
                output.accept(ModBlocks.PALM_FENCE);
                output.accept(ModBlocks.PALM_FENCE_GATE);
                output.accept(ModBlocks.PALM_DOOR);
                output.accept(ModBlocks.PALM_TRAPDOOR);
                output.accept(ModBlocks.PALM_PRESSURE_PLATE);
                output.accept(ModBlocks.PALM_BUTTON);
                output.accept(ModBlocks.PALM_LEAVES);
                output.accept(ModBlocks.PALM_SAPLING);
                output.accept(ModBlocks.PALM_SHELF);
                output.accept(ModItems.PALM_SIGN);
                output.accept(ModItems.PALM_HANGING_SIGN);
                output.accept(ModItems.PALM_BOAT);
                output.accept(ModItems.PALM_CHEST_BOAT);

                // Decrepit wood

                output.accept(ModBlocks.DECREPIT_LOG);
                output.accept(ModBlocks.DECREPIT_WOOD);
                output.accept(ModBlocks.STRIPPED_DECREPIT_LOG);
                output.accept(ModBlocks.STRIPPED_DECREPIT_WOOD);
                output.accept(ModBlocks.DECREPIT_PLANKS);
                output.accept(ModBlocks.DECREPIT_STAIRS);
                output.accept(ModBlocks.DECREPIT_SLAB);
                output.accept(ModBlocks.DECREPIT_FENCE);
                output.accept(ModBlocks.DECREPIT_FENCE_GATE);
                output.accept(ModBlocks.DECREPIT_PRESSURE_PLATE);
                output.accept(ModBlocks.DECREPIT_BUTTON);
                output.accept(ModBlocks.DECREPIT_LEAVES);
                output.accept(ModBlocks.DECREPIT_SAPLING);

                // Pallid wood

                output.accept(ModBlocks.STRIPPED_PALLID_LOG);
                output.accept(ModBlocks.STRIPPED_PALLID_WOOD);
                output.accept(ModBlocks.PALLID_PLANKS);
                output.accept(ModBlocks.PALLID_STAIRS);
                output.accept(ModBlocks.PALLID_SLAB);
                output.accept(ModBlocks.PALLID_FENCE);
                output.accept(ModBlocks.PALLID_FENCE_GATE);
                output.accept(ModBlocks.PALLID_PRESSURE_PLATE);
                output.accept(ModBlocks.PALLID_BUTTON);
                output.accept(ModBlocks.PALLID_LEAVES);
                output.accept(ModBlocks.PALLID_SAPLING);
            })
            .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
