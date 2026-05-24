package com.mohigster.morefeatures.datagen;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.advancements.criterion.InventoryChangeTrigger.TriggerInstance.hasItems;

public class ModAdvancementProvider extends AdvancementProvider {

    public ModAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new ModAdvancements()));
    }

    public static class ModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(ModItems.BISMUTH,
                            Component.translatable("advancements.morefeatures.root.title"),
                            Component.translatable("advancements.morefeatures.root.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false)
                    .addCriterion("has_bismuth", hasItems(ItemPredicate.Builder.item().of(items, ModItems.BISMUTH.asItem())))
                    .save(output, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures/root"));

            AdvancementHolder getHoe = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModItems.BISMUTH_HOE,
                            Component.translatable("advancement.morefeatures.get_hoe.title"),
                            Component.translatable("advancement.morefeatures.get_hoe.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("bismuth_hoe", hasItems(ItemPredicate.Builder.item().of(items, ModItems.BISMUTH_HOE.asItem())))
                    .save(output, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures/bismuth_hoe"));

            AdvancementHolder craftPortal = Advancement.Builder.advancement()
                    .parent(root)
                    .display(ModBlocks.EVIL_PORTAL,
                            Component.translatable("advancement.morefeatures.get_portal.title"),
                            Component.translatable("advancement.morefeatures.get_portal.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("evil_portal", hasItems(ItemPredicate.Builder.item().of(items, ModBlocks.EVIL_PORTAL.asItem())))
                    .save(output, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "morefeatures/evil_portal"));



        }
    }
}
