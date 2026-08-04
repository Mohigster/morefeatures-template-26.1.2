package com.mohigster.morefeatures.creativemodetab;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.WoodTypeCollection;
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

@SuppressWarnings("unused")
public class MFCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreFeatures.MODID);

    public static final Supplier<CreativeModeTab> MOREFEATURES_ITEMS_TAB = CREATIVE_MODE_TABS.register("morefeatures_items_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFItems.RAW_ALUMINIUM.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_items"))
            .withTabsBefore(CreativeModeTabs.INGREDIENTS)
            .withTabsAfter(MFIdentifier.withMfNamespace("morefeatures_blocks_tab"))
            .displayItems((_, output) -> {

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
            .displayItems((_, output) -> {

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
                output.accept(MFBlocks.AZURITE_VERTICAL_SLAB);
                output.accept(MFBlocks.AZURITE_SLAB);
                output.accept(MFBlocks.AZURITE_FENCE);
                output.accept(MFBlocks.AZURITE_FENCE_GATE);
                output.accept(MFBlocks.AZURITE_PRESSURE_PLATE);
                output.accept(MFBlocks.AZURITE_BUTTON);
                output.accept(MFBlocks.AZURITE_SHELF);
                output.accept(MFItems.AZURITE_SIGN); // I know it says MFItems, but it IS a block item. It's only registered there because it's shared by two blocks
                output.accept(MFItems.AZURITE_HANGING_SIGN); // Same goes for hanging signs. Shared by Ceiling hanging sign and Wall hanging sign, so the sign item is registered in MFItems
                output.accept(MFBlocks.RAW_AZURITE_BLOCK);
                output.accept(MFBlocks.AZURITE_ORE);
                output.accept(MFBlocks.DEEPSLATE_AZURITE_ORE);
                output.accept(MFBlocks.NETHER_AZURITE_ORE);
                output.accept(MFBlocks.END_AZURITE_ORE);

                // Fluorite blocks
                output.accept(MFBlocks.FLUORITE_BLOCK);
                output.accept(MFBlocks.FLUORITE_STAIRS);
                output.accept(MFBlocks.FLUORITE_VERTICAL_SLAB);
                output.accept(MFBlocks.FLUORITE_SLAB);
                output.accept(MFBlocks.FLUORITE_FENCE);
                output.accept(MFBlocks.FLUORITE_FENCE_GATE);
                output.accept(MFBlocks.FLUORITE_PRESSURE_PLATE);
                output.accept(MFBlocks.FLUORITE_BUTTON);
                output.accept(MFBlocks.FLUORITE_SHELF);
                output.accept(MFItems.FLUORITE_SIGN);
                output.accept(MFItems.FLUORITE_HANGING_SIGN);
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
            .icon(() -> new ItemStack(MFItems.BISMUTH_SWORD.get())) // 0 is Sword
            .title(Component.translatable("creativetab.morefeatures.morefeatures_equipment"))
            .withTabsBefore(MFIdentifier.withMfNamespace("morefeatures_blocks_tab"))
            .displayItems((_, output) -> {

                // Carbon tools and equipment

                output.accept(MFItems.CARBON_TRIDENT);
                output.accept(MFItems.CARBON_BOW);
                output.accept(MFItems.CARBON_CROSSBOW);
                output.accept(MFItems.CARBON_ELYTRA);
                output.accept(MFItems.CARBON_WOLF_ARMOR);

                // Bismuth Tools

                output.accept(MFItems.BISMUTH_SWORD);
                output.accept(MFItems.BISMUTH_PICKAXE);
                output.accept(MFItems.BISMUTH_AXE);
                output.accept(MFItems.BISMUTH_SHOVEL);
                output.accept(MFItems.BISMUTH_HOE);
                output.accept(MFItems.BISMUTH_SPEAR);
                output.accept(MFItems.BISMUTH_TRIDENT);
                output.accept(MFItems.BISMUTH_BOW);

                // Bismuth Armor

                output.accept(MFItems.BISMUTH_HELMET);
                output.accept(MFItems.BISMUTH_CHESTPLATE);
                output.accept(MFItems.BISMUTH_LEGGINGS);
                output.accept(MFItems.BISMUTH_BOOTS);
                output.accept(MFItems.BISMUTH_HORSE_ARMOR);
                output.accept(MFItems.BISMUTH_NAUTILUS_ARMOR);

                // Metal detector

                output.accept(MFItems.METAL_DETECTOR);
            })
            .build()
    );

    public static final Supplier<CreativeModeTab> MOREFEATURES_WOOD_TAB = CREATIVE_MODE_TABS.register("morefeatures_wood_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFBlocks.LOG.bloodwood()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_wood"))
            .withTabsBefore(MFIdentifier.withMfNamespace("morefeatures_equipment_tab"))
            .displayItems((_, output) -> {
                // Bloodwood

                WoodTypeCollection.TYPES.forEach(type -> {
                    output.accept(MFBlocks.LOG.pick(type));
                    output.accept(MFBlocks.WOOD.pick(type));
                    output.accept(MFBlocks.STRIPPED_LOG.pick(type));
                    output.accept(MFBlocks.STRIPPED_WOOD.pick(type));
                    output.accept(MFBlocks.PLANKS.pick(type));
                    output.accept(MFBlocks.WOODEN_STAIRS.pick(type));
                    output.accept(MFBlocks.WOODEN_VERTICAL_SLAB.pick(type));
                    output.accept(MFBlocks.WOODEN_SLAB.pick(type));
                    output.accept(MFBlocks.WOODEN_FENCE.pick(type));
                    output.accept(MFBlocks.WOODEN_FENCE_GATE.pick(type));
                    output.accept(MFBlocks.WOODEN_PRESSURE_PLATE.pick(type));
                    output.accept(MFBlocks.WOODEN_BUTTON.pick(type));
                    output.accept(type.getLeavesOrWart());
                    output.accept(type.getSaplingOrFungus());
                    output.accept(MFBlocks.BLOODWOOD_SAPLING);
                    output.accept(MFBlocks.WOODEN_SHELF.pick(type));
                    output.accept(MFItems.SIGN.pick(type));
                    output.accept(MFItems.HANGING_SIGN.pick(type));
                });
            })
            .build()
    );

    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Creative Tabs registered -> Performed by: " + MoreFeatures.MODID);
    }
}
