package com.mohigster.morefeatures.item;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.item.custom.ModSmithingTemplateItem;
import com.mohigster.morefeatures.toolmaterial.ModArmorMaterial;
import com.mohigster.morefeatures.toolmaterial.ModToolMaterial;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.Function;

import static com.mohigster.morefeatures.MoreFeatures.MODID;
import static com.mohigster.morefeatures.toolmaterial.ModToolMaterial.BISMUTH_TOOL_MATERIAL;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static DeferredItem<Item> registerItem(String name, Function<Item.Properties, Item> function, Item.Properties itemProp) {
        return ITEMS.register(name, () -> function.apply(itemProp.setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name)))));
    }



    // Item registration. JSON files are generated with DataGen. See MoreFeaturesDataGen and classes in the datagen package.

    // Texture files and language data still must be manually added.

    // This mod has no theme and therefore may add a large number of items. For convenience, Items should be
    // split into categories headed by comments, e.g. // Aluminium items.

    // Aluminium items
    public static final DeferredItem<Item> RAW_ALUMINIUM = ITEMS.registerSimpleItem("raw_aluminium");
    public static final DeferredItem<Item> ALUMINIUM_INGOT = ITEMS.registerSimpleItem("aluminium_ingot");

    // Magnesium items
    public static final DeferredItem<Item> RAW_MAGNESIUM = ITEMS.registerSimpleItem("raw_magnesium");
    public static final DeferredItem<Item> MAGNESIUM_INGOT = ITEMS.registerSimpleItem("magnesium_ingot");

    // Azurite items
    public static final DeferredItem<Item> AZURITE = ITEMS.registerSimpleItem("azurite");
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");

    // Fluorite items
    public static final DeferredItem<Item> FLUORITE = ITEMS.registerSimpleItem("fluorite");
    public static final DeferredItem<Item> RAW_FLUORITE = ITEMS.registerSimpleItem("raw_fluorite");

    // Bismuth items
    public static final DeferredItem<Item> BISMUTH = ITEMS.registerSimpleItem("bismuth");
    public static final DeferredItem<Item> BISMUTH_SCRAP = ITEMS.registerSimpleItem("bismuth_scrap");
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerSimpleItem("raw_bismuth");

    // Elemental rods
    public static final DeferredItem<Item> BRINE_ROD = ITEMS.registerSimpleItem("brine_rod");

    // Tools and equipment
    public static final DeferredItem<Item> BISMUTH_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "bismuth_upgrade_smithing_template", ModSmithingTemplateItem::createBismuthUpgradeTemplate, new Item.Properties().rarity(Rarity.UNCOMMON)
    );



    public static final List<DeferredItem<Item>> BISMUTH_EQUIPMENT = registerEquipmentItems("bismuth", BISMUTH_TOOL_MATERIAL, ModArmorMaterial.Armor.BISMUTH,
            new float[]{7.5f, -2.2f}, new float[]{4f, -2.8f}, new float[]{8f, -3f}, new float[]{0f, 0f}, new float[]{4.5f, -3f},
            new Item.Properties().rarity(Rarity.RARE));


    public static List<DeferredItem<Item>> registerEquipmentItems(String name, ToolMaterial toolmaterial, ArmorMaterial armormaterial,
                                                            float[] swordattr, float[] pickaxeattr, float[] axeattr, float[] shovelattr, float[] hoeattr,
                                                            Item.Properties itemProp) {

        return List.of(
                registerItem(name + "_sword", (p) -> new Item(p.sword(toolmaterial, swordattr[0], swordattr[1])), itemProp),
                registerItem(name + "_pickaxe", (p) -> new Item(p.pickaxe(toolmaterial, pickaxeattr[0], pickaxeattr[1])), itemProp),
                registerItem(name + "_axe", (p) -> new Item(p.axe(toolmaterial, axeattr[0], axeattr[1])), itemProp),
                registerItem(name + "_hoe", (p) -> new Item(p.hoe(toolmaterial, hoeattr[0], hoeattr[1])), itemProp),
                registerItem(name + "_shovel", (p) -> new Item(p.shovel(toolmaterial, shovelattr[0], shovelattr[1])), itemProp),

                registerItem(name + "_helmet", (p) -> new Item(p.humanoidArmor(armormaterial, ArmorType.HELMET)), itemProp),
                registerItem(name + "_chestplate", (p) -> new Item(p.humanoidArmor(armormaterial, ArmorType.CHESTPLATE)), itemProp),
                registerItem(name + "_leggings", (p) -> new Item(p.humanoidArmor(armormaterial, ArmorType.LEGGINGS)), itemProp),
                registerItem(name + "_boots", (p) -> new Item(p.humanoidArmor(armormaterial, ArmorType.BOOTS)), itemProp)
        );
    }








    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Items registered -> Performed by: " + MODID);
    }
}
