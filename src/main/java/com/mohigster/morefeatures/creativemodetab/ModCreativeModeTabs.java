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
                output.accept(ModBlocks.RAW_AZURITE_BLOCK);
                output.accept(ModBlocks.AZURITE_ORE);
                output.accept(ModBlocks.DEEPSLATE_AZURITE_ORE);
                output.accept(ModBlocks.NETHER_AZURITE_ORE);
                output.accept(ModBlocks.END_AZURITE_ORE);

                // Fluorite blocks
                output.accept(ModBlocks.FLUORITE_BLOCK);
                output.accept(ModBlocks.RAW_FLUORITE_BLOCK);
                output.accept(ModBlocks.FLUORITE_ORE);
                output.accept(ModBlocks.DEEPSLATE_FLUORITE_ORE);
                output.accept(ModBlocks.NETHER_FLUORITE_ORE);
                output.accept(ModBlocks.END_FLUORITE_ORE);
            })
            .build());

    public static final Supplier<CreativeModeTab> MOREFEATURES_EQUIPMENT_TAB = CREATIVE_MODE_TABS.register("morefeatures_equipment_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.BISMUTH_EQUIPMENT.get(0).asItem())) // 0 is Sword
            .title(Component.translatable("creativetab.morefeatures.morefeatures_equipment"))
            .withTabsBefore(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures_blocks_tab"))
            .displayItems((itemDisplayParameters, output) -> {

                // Tools

                output.accept(ModItems.BISMUTH_EQUIPMENT.get(0).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(1).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(2).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(3).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(4).asItem());

                // Armor

                output.accept(ModItems.BISMUTH_EQUIPMENT.get(5).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(6).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(7).asItem());
                output.accept(ModItems.BISMUTH_EQUIPMENT.get(8).asItem());
            })
            .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
