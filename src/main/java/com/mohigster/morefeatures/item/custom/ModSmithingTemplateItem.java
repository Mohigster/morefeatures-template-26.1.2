package com.mohigster.morefeatures.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.List;
import java.util.function.Consumer;

public class ModSmithingTemplateItem extends Item {
    // This code is taken from vanilla and modified so that it displays the correct info
    // Necessary because Minecraft hardcoded description values.
    private static final ChatFormatting TITLE_FORMAT = ChatFormatting.GRAY;
    private static final ChatFormatting DESCRIPTION_FORMAT = ChatFormatting.BLUE;
    private static final Component INGREDIENTS_TITLE = Component.translatable(
                    Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.ingredients"))
            )
            .withStyle(TITLE_FORMAT);
    private static final Component APPLIES_TO_TITLE = Component.translatable(
                    Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.applies_to"))
            )
            .withStyle(TITLE_FORMAT);
    private static final Component SMITHING_TEMPLATE_SUFFIX = Component.translatable(
                    Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template"))
            )
            .withStyle(TITLE_FORMAT);

    private static final Component BISMUTH_UPGRADE_APPLIES_TO = Component.translatable(
            Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.bismuth_upgrade.applies_to"))
    )
            .withStyle(DESCRIPTION_FORMAT);;
    private static final Component BISMUTH_UPGRADE_INGREDIENTS = Component.translatable(
            Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.bismuth_upgrade.ingredients"))
    )
            .withStyle(DESCRIPTION_FORMAT);
    private static final Component BISMUTH_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.bismuth_upgrade.base_slot_description"))
    );
    private static final Component BISMUTH_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(
            Util.makeDescriptionId("item", Identifier.withDefaultNamespace("smithing_template.bismuth_upgrade.additions_slot_description"))
    );
    private static final Identifier EMPTY_SLOT_HELMET = Identifier.withDefaultNamespace("container/slot/helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE = Identifier.withDefaultNamespace("container/slot/chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS = Identifier.withDefaultNamespace("container/slot/leggings");
    private static final Identifier EMPTY_SLOT_BOOTS = Identifier.withDefaultNamespace("container/slot/boots");
    private static final Identifier EMPTY_SLOT_HOE = Identifier.withDefaultNamespace("container/slot/hoe");
    private static final Identifier EMPTY_SLOT_AXE = Identifier.withDefaultNamespace("container/slot/axe");
    private static final Identifier EMPTY_SLOT_SWORD = Identifier.withDefaultNamespace("container/slot/sword");
    private static final Identifier EMPTY_SLOT_SHOVEL = Identifier.withDefaultNamespace("container/slot/shovel");
    private static final Identifier EMPTY_SLOT_SPEAR = Identifier.withDefaultNamespace("container/slot/spear");
    private static final Identifier EMPTY_SLOT_PICKAXE = Identifier.withDefaultNamespace("container/slot/pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT = Identifier.withDefaultNamespace("container/slot/ingot");

    private final Component appliesTo;
    private final Component ingredients;
    private final Component baseSlotDescription;
    private final Component additionsSlotDescription;
    private final List<Identifier> baseSlotEmptyIcons;
    private final List<Identifier> additionalSlotEmptyIcons;
    public ModSmithingTemplateItem(
            final Component appliesTo,
            final Component ingredients,
            final Component baseSlotDescription,
            final Component additionsSlotDescription,
            final List<Identifier> baseSlotEmptyIcons,
            final List<Identifier> additionalSlotEmptyIcons,
            final Properties properties
    ) {
        super(properties);
        this.appliesTo = appliesTo;
        this.ingredients = ingredients;
        this.baseSlotDescription = baseSlotDescription;
        this.additionsSlotDescription = additionsSlotDescription;
        this.baseSlotEmptyIcons = baseSlotEmptyIcons;
        this.additionalSlotEmptyIcons = additionalSlotEmptyIcons;
    }
    public static ModSmithingTemplateItem createBismuthUpgradeTemplate(final Properties properties) {
        return new ModSmithingTemplateItem(
                BISMUTH_UPGRADE_APPLIES_TO,
                BISMUTH_UPGRADE_INGREDIENTS,
                BISMUTH_UPGRADE_BASE_SLOT_DESCRIPTION,
                BISMUTH_UPGRADE_ADDITIONS_SLOT_DESCRIPTION,
                createBismuthUpgradeIconList(),
                createBismuthUpgradeMaterialList(),
                properties
        );
    }
    private static List<Identifier> createBismuthUpgradeIconList() {
        return List.of(
                new Identifier[]{
                        EMPTY_SLOT_HELMET,
                        EMPTY_SLOT_SWORD,
                        EMPTY_SLOT_CHESTPLATE,
                        EMPTY_SLOT_PICKAXE,
                        EMPTY_SLOT_LEGGINGS,
                        EMPTY_SLOT_AXE,
                        EMPTY_SLOT_BOOTS,
                        EMPTY_SLOT_HOE,
                        EMPTY_SLOT_SHOVEL,
                        EMPTY_SLOT_SPEAR
                }
        );
    }

    private static List<Identifier> createBismuthUpgradeMaterialList() {
        return List.of(EMPTY_SLOT_INGOT);
    }
    @Override
    public void appendHoverText(
            final ItemStack itemStack, final TooltipContext context, final TooltipDisplay display, final Consumer<Component> builder, final TooltipFlag tooltipFlag
    ) {
        builder.accept(SMITHING_TEMPLATE_SUFFIX);
        builder.accept(CommonComponents.EMPTY);
        builder.accept(APPLIES_TO_TITLE);
        builder.accept(CommonComponents.space().append(this.appliesTo));
        builder.accept(INGREDIENTS_TITLE);
        builder.accept(CommonComponents.space().append(this.ingredients));
    }

    public Component getBaseSlotDescription() {
        return this.baseSlotDescription;
    }

    public Component getAdditionSlotDescription() {
        return this.additionsSlotDescription;
    }

    public List<Identifier> getBaseSlotEmptyIcons() {
        return this.baseSlotEmptyIcons;
    }

    public List<Identifier> getAdditionalSlotEmptyIcons() {
        return this.additionalSlotEmptyIcons;
    }
}
