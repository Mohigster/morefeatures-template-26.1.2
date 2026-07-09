package com.mohigster.morefeatures.item;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.asset.ModEquipmentAssets;
import com.mohigster.morefeatures.block.ModBlocks;
import com.mohigster.morefeatures.block.references.ModBlockItemIds;
import com.mohigster.morefeatures.datacomponent.ModDataComponentTypes;
import com.mohigster.morefeatures.datagen.ModJukeboxSongs;
import com.mohigster.morefeatures.entity.entity_types.ModEntityTypes;
import com.mohigster.morefeatures.item.custom.*;
import com.mohigster.morefeatures.item.custom.wand.*;
import com.mohigster.morefeatures.toolmaterial.ModArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.Unit;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.BlocksAttacks;
import net.minecraft.world.item.component.ChargedProjectiles;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopperCollection;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
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
    public static final DeferredItem<Item> AZURITE = ITEMS.registerItem("azurite",
            properties -> new Item(properties
                    .component(ModDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 3200)));
    public static final DeferredItem<Item> RAW_AZURITE = ITEMS.registerSimpleItem("raw_azurite");

    // Fluorite items
    public static final DeferredItem<Item> FLUORITE = ITEMS.registerItem("fluorite",
            properties -> new Item(properties
                    .component(ModDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 3200)));
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

    // Everfrost items
    public static final DeferredItem<Item> EVERFROST = ITEMS.registerItem("everfrost",
            properties -> new Item(properties
                    .component(ModDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 6400)));
    public static final DeferredItem<Item> RAW_EVERFROST = ITEMS.registerSimpleItem("raw_everfrost");

    // Elemental rods
    public static final DeferredItem<Item> BRINE_ROD = ITEMS.registerSimpleItem("brine_rod");

    // Metal detector
    public static final DeferredItem<Item> METAL_DETECTOR = ITEMS.registerItem("metal_detector",
            properties -> new MetalDetectorItem(properties
                    .durability(128)
            ){
                @SuppressWarnings("deprecation")
                @NullMarked
                @Override
                public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                    builder.accept(Component.translatable("tooltip.morefeatures.metal_detector"));
                    super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
                }
            });

    // Frosted core
    public static final DeferredItem<Item> FROSTED_CORE = ITEMS.registerSimpleItem("frosted_core");


    // Wands
    public static final DeferredItem<Item> ICE_WAND = ITEMS.registerItem("ice_wand",
            properties -> new IceWandItem(properties
                    .durability(600)
                    .repairable(ModItems.FROSTED_CORE.get())
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> FIRE_WAND = ITEMS.registerItem("fire_wand",
            properties -> new FireWandItem(properties
                    .durability(600)
                    .repairable(Items.BLAZE_POWDER)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> HEALING_WAND = ITEMS.registerItem("healing_wand",
            properties -> new HealingWandItem(properties
                    .durability(600)
                    .repairable(Items.GLISTERING_MELON_SLICE)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> EARTH_WAND = ITEMS.registerItem("earth_wand",
            properties -> new EarthWandItem(properties
                    .durability(600)
                    .repairable(Items.DEEPSLATE)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> LIGHTNING_WAND = ITEMS.registerItem("lightning_wand",
            properties -> new LightningWandItem(properties
                    .durability(600)
                    .repairable(Items.REDSTONE)
                    .rarity(Rarity.UNCOMMON)
            ));

    // Spawn eggs
    public static final DeferredItem<Item> ICEOLOGER_SPAWN_EGG = ITEMS.registerItem("iceologer_spawn_egg",
            properties -> new SpawnEggItem(properties
                    .spawnEgg(ModEntityTypes.ICEOLOGER.get())
            ));


    // Music Discs

    public static final DeferredItem<Item> MUSIC_DISC_AQUAMARINE = ITEMS.registerItem("music_disc_aquamarine",
            properties -> new Item(properties
                    .jukeboxPlayable(ModJukeboxSongs.AQUAMARINE_KEY)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)));

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
            properties -> new CarbonTridentItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(594)
                    .repairable(CARBON_FIBER.get())
                    .attributes(CarbonTridentItem.createAttributes())
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, CarbonTridentItem.createToolProperties())
                    .component(DataComponents.WEAPON, new Weapon(1))
            ));

    public static final DeferredItem<Item> CARBON_SHIELD = ITEMS.registerItem("carbon_shield",
            properties -> new ShieldItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(685)
                    .repairable(CARBON_FIBER.get())
                    .equippableUnswappable(EquipmentSlot.OFFHAND)
                    .delayedComponent(
                            DataComponents.BLOCKS_ATTACKS,
                            context -> new BlocksAttacks(
                                    0.25F,
                                    1.0F,
                                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                    Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                                    Optional.of(SoundEvents.SHIELD_BLOCK),
                                    Optional.of(SoundEvents.SHIELD_BREAK)
                            )
                    )
                    .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
            ));

    // Bismuth tools and equipment

    public static final DeferredItem<Item> BISMUTH_UPGRADE_SMITHING_TEMPLATE = registerItem(
            "bismuth_upgrade_smithing_template", ModSmithingTemplateItem::createBismuthUpgradeTemplate, new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final DeferredItem<Item> BISMUTH_AXE = ITEMS.registerItem("bismuth_axe",
            properties -> new AxeItem(BISMUTH_TOOL_MATERIAL, 6f, -3f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(BISMUTH.get())
            ));

    public static final DeferredItem<Item> BISMUTH_SHOVEL = ITEMS.registerItem("bismuth_shovel",
            properties -> new ShovelItem(BISMUTH_TOOL_MATERIAL, 1f, -3.0f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(BISMUTH.get())
            ));

    public static final DeferredItem<Item> BISMUTH_HOE = ITEMS.registerItem("bismuth_hoe",
            properties -> new HoeItem(BISMUTH_TOOL_MATERIAL, -5.4f, 1f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(BISMUTH.get())
            ));

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

    public static final DeferredItem<Item> BISMUTH_BOW = ITEMS.registerItem("bismuth_bow",
            properties -> new BowItem(properties
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .durability(1516)
                    .enchantable(19)
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

    public static final DeferredItem<Item> BISMUTH_TRIDENT = ITEMS.registerItem("bismuth_trident",
            properties -> new BismuthTridentItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(997)
                    .attributes(BismuthTridentItem.createAttributes())
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, BismuthTridentItem.createToolProperties())
                    .component(DataComponents.WEAPON, new Weapon(2))
            ));

    // Sign items

    public static final DeferredItem<SignItem> BLOODWOOD_SIGN =
            ITEMS.register("bloodwood_sign", () ->
                    new SignItem(
                            ModBlocks.BLOODWOOD_SIGN.get(),
                            ModBlocks.BLOODWOOD_WALL_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "bloodwood_sign")))
                    )
            );

    public static final DeferredItem<SignItem> BLOODWOOD_HANGING_SIGN =
            ITEMS.register("bloodwood_hanging_sign", () ->
                    new HangingSignItem(
                            ModBlocks.BLOODWOOD_HANGING_SIGN.get(),
                            ModBlocks.BLOODWOOD_WALL_HANGING_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "bloodwood_hanging_sign")))
                    )
            );

    public static final DeferredItem<SignItem> TAINTED_SIGN =
            ITEMS.register("tainted_sign", () ->
                    new SignItem(
                            ModBlocks.TAINTED_SIGN.get(),
                            ModBlocks.TAINTED_WALL_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "tainted_sign")))
                    )
            );

    public static final DeferredItem<SignItem> TAINTED_HANGING_SIGN =
            ITEMS.register("tainted_hanging_sign", () ->
                    new HangingSignItem(
                            ModBlocks.TAINTED_HANGING_SIGN.get(),
                            ModBlocks.TAINTED_WALL_HANGING_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "tainted_hanging_sign")))
                    )
            );

    public static final DeferredItem<SignItem> PALM_SIGN =
            ITEMS.register("palm_sign", () ->
                    new SignItem(
                            ModBlocks.PALM_SIGN.get(),
                            ModBlocks.PALM_WALL_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_sign")))
                    )
            );

    public static final DeferredItem<SignItem> PALM_HANGING_SIGN =
            ITEMS.register("palm_hanging_sign", () ->
                    new HangingSignItem(
                            ModBlocks.PALM_HANGING_SIGN.get(),
                            ModBlocks.PALM_WALL_HANGING_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_hanging_sign")))
                    )
            );

    public static final DeferredItem<SignItem> PALLID_SIGN =
            ITEMS.register("pallid_sign", () ->
                    new SignItem(
                            ModBlocks.PALLID_SIGN.get(),
                            ModBlocks.PALLID_WALL_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "pallid_sign")))
                    )
            );

    public static final DeferredItem<SignItem> PALLID_HANGING_SIGN =
            ITEMS.register("pallid_hanging_sign", () ->
                    new HangingSignItem(
                            ModBlocks.PALLID_HANGING_SIGN.get(),
                            ModBlocks.PALLID_WALL_HANGING_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "pallid_hanging_sign")))
                    )
            );

    public static final DeferredItem<SignItem> DECREPIT_SIGN =
            ITEMS.register("decrepit_sign", () ->
                    new SignItem(
                            ModBlocks.DECREPIT_SIGN.get(),
                            ModBlocks.DECREPIT_WALL_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "decrepit_sign")))
                    )
            );

    public static final DeferredItem<SignItem> DECREPIT_HANGING_SIGN =
            ITEMS.register("decrepit_hanging_sign", () ->
                    new HangingSignItem(
                            ModBlocks.DECREPIT_HANGING_SIGN.get(),
                            ModBlocks.DECREPIT_WALL_HANGING_SIGN.get(),
                            new Item.Properties().stacksTo(16).setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "decrepit_hanging_sign")))
                    )
            );

    // Boat items

    public static final DeferredItem<BoatItem> PALM_BOAT = ITEMS.registerItem(
            "palm_boat",
            props -> new BoatItem(ModEntityTypes.PALM_BOAT.get(), props)
    );

    public static final DeferredItem<BoatItem> PALM_CHEST_BOAT = ITEMS.registerItem(
            "palm_chest_boat",
            props -> new BoatItem(ModEntityTypes.PALM_CHEST_BOAT.get(), props)
    );

    public static final List<DeferredItem<Item>> BISMUTH_EQUIPMENT = registerEquipmentItems("bismuth", BISMUTH_TOOL_MATERIAL, ModArmorMaterials.BISMUTH,
            new float[]{5.5f, -2.2f}, new float[]{0f, -2.8f},
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
