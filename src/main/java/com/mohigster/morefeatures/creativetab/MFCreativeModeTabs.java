package com.mohigster.morefeatures.creativetab;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.gemstone.GemstoneCollection;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.core.RestrictedTo;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class MFCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreFeatures.MODID);

    public static final Supplier<CreativeModeTab> MOREFEATURES_ITEMS_TAB = register("morefeatures_items_tab", CreativeModeTab.builder()
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

                GemstoneCollection.GEMS.forEach(gem -> {
                    output.accept(MFItems.GEM.pick(gem));
                    output.accept(MFItems.RAW_GEM.pick(gem));
                });

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
    );

    // May subdivide this blocks tab into natural blocks, building blocks, etc. Depends on what I add and how big these tabs get.
    public static final Supplier<CreativeModeTab> MOREFEATURES_BLOCKS_TAB = register("morefeatures_blocks_tab", CreativeModeTab.builder()
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
                GemstoneCollection.GEMS.forEach(gem -> accept(
                        output,
                        MFBlocks.GEMSTONE_BLOCK.pick(gem),
                        MFBlocks.GEMSTONE_STAIRS.pick(gem),
                        MFBlocks.GEMSTONE_VERTICAL_SLAB.pick(gem),
                        MFBlocks.GEMSTONE_SLAB.pick(gem),
                        MFBlocks.GEMSTONE_FENCE.pick(gem),
                        MFBlocks.GEMSTONE_FENCE_GATE.pick(gem),
                        MFBlocks.GEMSTONE_PRESSURE_PLATE.pick(gem),
                        MFBlocks.GEMSTONE_BUTTON.pick(gem),
                        MFBlocks.GEMSTONE_SHELF.pick(gem),
                        MFItems.GEMSTONE_SIGN.pick(gem),
                        MFItems.GEMSTONE_HANGING_SIGN.pick(gem),
                        MFBlocks.RAW_GEM_BLOCK.pick(gem),
                        MFBlocks.ORE.pick(gem),
                        MFBlocks.NETHER_ORE.pick(gem),
                        MFBlocks.END_ORE.pick(gem)
                ));

                // Magic block
                output.accept(MFBlocks.MAGIC_BLOCK);

                // Compressor block
                output.accept(MFBlocks.COMPRESSOR_BLOCK);

                // Void anchor block
                output.accept(MFBlocks.VOID_ANCHOR);

                // Charred blocks
                output.accept(MFBlocks.CHARRED_NYLIUM);
                output.accept(MFBlocks.CHARRED_ROOTS);

                // Decrepit blocks
                output.accept(MFBlocks.DECREPIT_NULLIUM);
                output.accept(MFBlocks.DECREPIT_ROOTS);

                // Pallid blocks
                output.accept(MFBlocks.PALLID_NULLIUM);
                output.accept(MFBlocks.PALLID_ROOTS);
            })
    );

    public static final Supplier<CreativeModeTab> MOREFEATURES_EQUIPMENT_TAB = register("morefeatures_equipment_tab", CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFItems.BISMUTH_SWORD.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_equipment"))
            .withTabsBefore(MFIdentifier.withMfNamespace("morefeatures_blocks_tab"))
            .displayItems((_, output) -> {

                // Carbon tools and equipment
                accept(
                        output,
                        MFItems.CARBON_TRIDENT,
                        MFItems.CARBON_BOW,
                        MFItems.CARBON_CROSSBOW,
                        MFItems.CARBON_ELYTRA,
                        MFItems.CARBON_WOLF_ARMOR,
                        MFItems.CARBON_SHIELD
                );

                accept(
                        output,
                        // Weapons
                        MFItems.BISMUTH_SWORD,
                        MFItems.BISMUTH_PICKAXE,
                        MFItems.BISMUTH_AXE,
                        MFItems.BISMUTH_SHOVEL,
                        MFItems.BISMUTH_HOE,
                        MFItems.BISMUTH_SPEAR,
                        MFItems.BISMUTH_TRIDENT,
                        MFItems.BISMUTH_BOW,
                        // Armor
                        MFItems.BISMUTH_HELMET,
                        MFItems.BISMUTH_CHESTPLATE,
                        MFItems.BISMUTH_LEGGINGS,
                        MFItems.BISMUTH_BOOTS,
                        MFItems.BISMUTH_HORSE_ARMOR,
                        MFItems.BISMUTH_NAUTILUS_ARMOR
                );

                // Metal detector

                output.accept(MFItems.METAL_DETECTOR);
            })
    );

    public static final Supplier<CreativeModeTab> MOREFEATURES_WOOD_TAB = register("morefeatures_wood_tab", CreativeModeTab.builder()
            .icon(() -> new ItemStack(MFBlocks.LOG.bloodwood()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_wood"))
            .withTabsBefore(MFIdentifier.withMfNamespace("morefeatures_equipment_tab"))
            .displayItems((_, output) ->
                    WoodTypeCollection.SETS.forEach(set -> {
                        accept(
                            output,
                            MFBlocks.LOG.pick(set),
                            MFBlocks.WOOD.pick(set),
                            MFBlocks.STRIPPED_LOG.pick(set),
                            MFBlocks.STRIPPED_WOOD.pick(set),
                            MFBlocks.PLANKS.pick(set),
                            MFBlocks.WOODEN_STAIRS.pick(set),
                            MFBlocks.WOODEN_VERTICAL_SLAB.pick(set),
                            MFBlocks.WOODEN_SLAB.pick(set),
                            MFBlocks.WOODEN_FENCE.pick(set),
                            MFBlocks.WOODEN_FENCE_GATE.pick(set),
                            MFBlocks.WOODEN_PRESSURE_PLATE.pick(set),
                            MFBlocks.WOODEN_BUTTON.pick(set),
                            // Leaves and warts, as well as saplings, are registered separately
                            // because they are registered using different classes to each other
                            // These get methods substitute calling MFBlocks.LEAVES.pick(set);
                            set.leavesOrWart(),
                            set.saplingOrFungus(),
                            MFBlocks.WOODEN_SHELF.pick(set),
                            MFItems.WOODEN_SIGN.pick(set),
                            MFItems.WOODEN_HANGING_SIGN.pick(set)
                        );
                        if (set.hasBoat()) {
                            accept(
                                    output,
                                    set.getBoat(),
                                    set.getChestBoat()
                            );
                        }
                    })
            )
    );

    private static Supplier<CreativeModeTab> register(String name, CreativeModeTab.Builder builder) {
        return CREATIVE_MODE_TABS.register(name, builder::build);
    }

    @RestrictedTo(MoreFeatures.class)
    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
        validateTabs();
        MoreFeatures.LOGGER.info("Mod Creative Tabs registered -> Performed by: " + MoreFeatures.MODID);
    }

    private static void validateTabs() {
        if (CREATIVE_MODE_TABS.getEntries().isEmpty()) {
            throw new IllegalStateException("No creative mode tabs registered");
        }
    }

    private static void accept(CreativeModeTab.Output output, List<ItemLike> items){
        if (items.isEmpty())
            throw new IllegalStateException("Creative mode tab cannot accept an empty list of items!");
        items.forEach(output::accept);
    }

    private static void accept(CreativeModeTab.Output output, ItemLike... items){
        accept(output, List.of(items));
    }
}
