package com.mohigster.morefeatures.item;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.asset.ModEquipmentAssets;
import com.mohigster.morefeatures.item.custom.MetalDetectorItem;
import com.mohigster.morefeatures.item.custom.ModSmithingTemplateItem;
import com.mohigster.morefeatures.toolmaterial.ModArmorMaterials;
import net.minecraft.util.Unit;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
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
    public static final DeferredItem<Item> BISMUTH = ITEMS.registerItem("bismuth",
            properties -> new Item(properties
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));
    public static final DeferredItem<Item> BISMUTH_SCRAP = ITEMS.registerItem("bismuth_scrap",
            properties -> new Item(properties
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant())
    );
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerItem("raw_bismuth",
            properties -> new Item(properties
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant())
    );

    // Elemental rods
    public static final DeferredItem<Item> BRINE_ROD = ITEMS.registerSimpleItem("brine_rod");

    // Metal detector
    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector",
            properties -> new MetalDetectorItem(properties
                    .durability(128)
            ));

    // Carbon Items and tools
    public static final DeferredItem<Item> CARBON_FIBER = ITEMS.registerItem("carbon_fiber",
            properties -> new Item(properties
                    .fireResistant()
            ));

    public static final DeferredItem<Item> CARBON_BOW = ITEMS.registerItem("carbon_bow",
            properties -> new BowItem(properties
                    .durability(856)
                    .repairable(CARBON_FIBER.get())
                    .enchantable(15)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> CARBON_CROSSBOW = ITEMS.registerItem("carbon_crossbow",
            properties -> new CrossbowItem(properties
                    .durability(885)
                    .repairable(CARBON_FIBER.get())
                    .enchantable(15)
                    .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> CARBON_WOLF_ARMOR = ITEMS.registerItem("carbon_wolf_armor",
            properties -> new Item(properties
                    .durability(256)
                    .repairable(CARBON_FIBER.get())
                    .enchantable(15)
                    .fireResistant()
                    .wolfArmor(ModArmorMaterials.CARBON)
            ));

    public static final DeferredItem<Item> CARBON_ELYTRA = ITEMS.registerItem("carbon_elytra",
            properties -> new Item(properties
                    .durability(893)
                    .repairable(CARBON_FIBER.get())
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .component(DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                    .setAsset(ModEquipmentAssets.CARBON_ELYTRA)
                                    .setDamageOnHurt(false)
                                    .build()

                    )
            ));

    public static final DeferredItem<Item> CARBON_TRIDENT = ITEMS.registerItem("carbon_trident",
            properties -> new TridentItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(594)
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, TridentItem.createToolProperties())
                    .component(DataComponents.WEAPON, new Weapon(1))
            ));



    // Bismuth tools and equipment
    public static final DeferredItem<Item> BISMUTH_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "bismuth_upgrade_smithing_template", ModSmithingTemplateItem::createBismuthUpgradeTemplate, new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final DeferredItem<Item> BISMUTH_AXE = ITEMS.registerItem("bismuth_axe",
            properties -> new AxeItem(BISMUTH_TOOL_MATERIAL, 8f, -3f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(BISMUTH.get()
                    )));

    public static final DeferredItem<Item> BISMUTH_SHOVEL = ITEMS.registerItem("bismuth_shovel",
            properties -> new ShovelItem(BISMUTH_TOOL_MATERIAL, 0f, 0f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(BISMUTH.get()
                    )));

    public static final DeferredItem<Item> BISMUTH_HOE = ITEMS.registerItem("bismuth_hoe",
            properties -> new HoeItem(BISMUTH_TOOL_MATERIAL, 4.5f, -3f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(BISMUTH.get()
                    )));

    public static final DeferredItem<Item> BISMUTH_SPEAR = ITEMS.registerItem("bismuth_spear",
            properties -> new Item(properties
                    .spear(
                            BISMUTH_TOOL_MATERIAL,
                            1.35F,
                            1.75F,
                            0.30F,
                            2.5F,
                            9.5F,
                            5.8F,
                            5.3F,
                            13.0F,
                            4.8F
                    )
                    .enchantable(15)
                    .repairable(BISMUTH.get())
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    ));

    public static final DeferredItem<Item> BISMUTH_HORSE_ARMOR = ITEMS.registerItem("bismuth_horse_armor",
            properties -> new Item(properties
                    .horseArmor(ModArmorMaterials.BISMUTH)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> BISMUTH_NAUTILUS_ARMOR = ITEMS.registerItem("bismuth_nautilus_armor",
            properties -> new Item(properties
                    .nautilusArmor(ModArmorMaterials.BISMUTH)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));



    public static final List<DeferredItem<Item>> BISMUTH_EQUIPMENT = registerEquipmentItems("bismuth", BISMUTH_TOOL_MATERIAL, ModArmorMaterials.BISMUTH,
            new float[]{7.5f, -2.2f}, new float[]{4f, -2.8f},
            new Item.Properties().rarity(Rarity.RARE).fireResistant().enchantable(15));


    public static List<DeferredItem<Item>> registerEquipmentItems(String name, ToolMaterial toolmaterial, ArmorMaterial armormaterial,
                                                            float[] swordattr, float[] pickaxeattr,
                                                            Item.Properties itemProp) {

        return List.of(
                registerItem(name + "_sword", (p) -> new Item(p.sword(toolmaterial, swordattr[0], swordattr[1])), itemProp),
                registerItem(name + "_pickaxe", (p) -> new Item(p.pickaxe(toolmaterial, pickaxeattr[0], pickaxeattr[1])), itemProp),

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
