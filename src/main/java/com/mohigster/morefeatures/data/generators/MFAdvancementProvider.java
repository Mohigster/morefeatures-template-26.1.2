package com.mohigster.morefeatures.data.generators;

import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static net.minecraft.advancements.triggers.InventoryChangeTrigger.TriggerInstance.hasItems;

public class MFAdvancementProvider extends AdvancementProvider {

    public MFAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new ModAdvancements()));
    }

    @SuppressWarnings("unused")
    public static class ModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, @NonNull Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(MFItems.BISMUTH,
                            Component.translatable("advancements.morefeatures.root.title"),
                            Component.translatable("advancements.morefeatures.root.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false)
                    .addCriterion("has_bismuth", hasItems(ItemPredicate.Builder.item().of(items, MFItems.BISMUTH.asItem())))
                    .save(output, MFIdentifier.withMfNamespace("morefeatures/root"));

            AdvancementHolder getHoe = Advancement.Builder.advancement()
                    .parent(root)
                    .display(MFItems.BISMUTH_HOE,
                            Component.translatable("advancement.morefeatures.get_hoe.title"),
                            Component.translatable("advancement.morefeatures.get_hoe.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("bismuth_hoe", hasItems(ItemPredicate.Builder.item().of(items, MFItems.BISMUTH_HOE.asItem())))
                    .save(output, MFIdentifier.withMfNamespace("morefeatures/bismuth_hoe"));

            AdvancementHolder craftPortal = Advancement.Builder.advancement()
                    .parent(root)
                    .display(MFBlocks.EVIL_PORTAL,
                            Component.translatable("advancement.morefeatures.get_portal.title"),
                            Component.translatable("advancement.morefeatures.get_portal.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("evil_portal", hasItems(ItemPredicate.Builder.item().of(items, MFBlocks.EVIL_PORTAL.asItem())))
                    .save(output, MFIdentifier.withMfNamespace("morefeatures/evil_portal"));
        }
    }
}
