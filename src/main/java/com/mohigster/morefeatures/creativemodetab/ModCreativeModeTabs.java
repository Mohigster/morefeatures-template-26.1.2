package com.mohigster.morefeatures.creativemodetab;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
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
                output.accept(ModItems.RAW_ALUMINIUM);
                output.accept(ModItems.ALUMINIUM_INGOT);
            })
            .build());

    // No blocks in the mod yet, but they are coming! Raw Aluminium is used as a placeholder until then.

    // May subdivide this into natural blocks, building blocks, etc. Depends on what I add and how big these tabs get.
    public static final Supplier<CreativeModeTab> MOREFEATURES_BLOCKS_TAB = CREATIVE_MODE_TABS.register("morefeatures_blocks_tab", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.ALUMINIUM_INGOT.get()))
            .title(Component.translatable("creativetab.morefeatures.morefeatures_blocks"))
            .displayItems((itemDisplayParameters, output) -> {
                // Aluminium blocks (actually items for now, but pretend that isn't the case)
                output.accept(ModItems.RAW_ALUMINIUM);
            })
            .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
