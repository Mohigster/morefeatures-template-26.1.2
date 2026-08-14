package com.mohigster.morefeatures.item;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.data.material.MFEquipmentAssets;
import com.mohigster.morefeatures.block.MFBlocks;
import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.item.custom.metaldetector.MetalDetectorItem;
import com.mohigster.morefeatures.item.custom.trident.MFTridentItem;
import com.mohigster.morefeatures.item.custom.trim.MFTrimMaterials;
import com.mohigster.morefeatures.item.food.MFFoods;
import com.mohigster.morefeatures.data.material.MFToolMaterial;
import com.mohigster.morefeatures.data.resources.references.MFItemIds;
import com.mohigster.morefeatures.data.component.MFDataComponentTypes;
import com.mohigster.morefeatures.data.sound.MFJukeboxSongs;
import com.mohigster.morefeatures.entity.MFEntityTypes;
import com.mohigster.morefeatures.item.custom.*;
import com.mohigster.morefeatures.item.custom.wand.*;
import com.mohigster.morefeatures.data.tag.MFItemTags;
import com.mohigster.morefeatures.data.material.MFArmorMaterials;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
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
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jspecify.annotations.NullMarked;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static com.mohigster.morefeatures.MoreFeatures.MODID;
import static com.mohigster.morefeatures.data.material.MFToolMaterial.BISMUTH_TOOL_MATERIAL;

public class MFItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    @SuppressWarnings("SameParameterValue")
    private static DeferredItem<Item> registerItem(String name, Function<Item.Properties, Item> function, Item.Properties itemProp) {
        return ITEMS.register(name, () -> function.apply(itemProp.setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MODID, name)))));
    }

    // Item registration. JSON files are generated with DataGen. See MoreFeaturesDataGen and classes in the datagen package.

    // Texture files and language data still must be manually added.

    // This mod has no theme and therefore may add a large number of items. For convenience, Items should be
    // split into categories headed by comments, e.g. // Aluminium items.

    // Aluminium items
    public static final DeferredItem<Item> RAW_ALUMINIUM = registerSimpleItem(MFItemIds.RAW_ALUMINIUM);
    public static final DeferredItem<Item> ALUMINIUM_INGOT = registerItem(MFItemIds.ALUMINIUM_INGOT,
            properties -> new Item(properties
                    .trimMaterial(MFTrimMaterials.ALUMINIUM)
            ));

    // Magnesium items
    public static final DeferredItem<Item> RAW_MAGNESIUM = registerSimpleItem(MFItemIds.RAW_MAGNESIUM);
    public static final DeferredItem<Item> MAGNESIUM_INGOT = registerItem(MFItemIds.MAGNESIUM_INGOT,
            properties -> new Item(properties
                    .trimMaterial(MFTrimMaterials.MAGNESIUM)
            ));

    // Azurite items
    public static final DeferredItem<Item> AZURITE = registerItem(MFItemIds.AZURITE,
            properties -> new Item(properties
                    .component(MFDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 3200)
                    .trimMaterial(MFTrimMaterials.AZURITE)
            ));
    public static final DeferredItem<Item> RAW_AZURITE = registerSimpleItem(MFItemIds.RAW_AZURITE);

    // Fluorite items
    public static final DeferredItem<Item> FLUORITE = registerItem(MFItemIds.FLUORITE,
            properties -> new Item(properties
                    .component(MFDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 3200)
                    .trimMaterial(MFTrimMaterials.FLUORITE)
            ));
    public static final DeferredItem<Item> RAW_FLUORITE = registerSimpleItem(MFItemIds.RAW_FLUORITE);

    // Bismuth items
    public static final DeferredItem<Item> BISMUTH = registerItem(MFItemIds.BISMUTH,
            properties -> new Item(properties
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .trimMaterial(MFTrimMaterials.BISMUTH)
            ));
    public static final DeferredItem<Item> BISMUTH_SCRAP = registerItem(MFItemIds.BISMUTH_SCRAP,
            properties -> new Item(properties
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant()
            ));
    public static final DeferredItem<Item> RAW_BISMUTH = registerItem(MFItemIds.RAW_BISMUTH,
            properties -> new Item(properties
                    .rarity(Rarity.UNCOMMON)
                    .fireResistant()
            ));

    // Everfrost items
    public static final DeferredItem<Item> EVERFROST = registerItem(MFItemIds.EVERFROST,
            properties -> new Item(properties
                    .component(MFDataComponentTypes.COMPRESSOR_FUEL_VALUE.get(), 6400)));
    public static final DeferredItem<Item> RAW_EVERFROST = registerSimpleItem(MFItemIds.RAW_EVERFROST);

    // Elemental rods
    public static final DeferredItem<Item> BRINE_ROD = registerSimpleItem(MFItemIds.BRINE_ROD);

    // Metal detector
    public static final DeferredItem<MetalDetectorItem> METAL_DETECTOR = registerMetalDetector(MFItemIds.METAL_DETECTOR, Component.translatable("tooltip.morefeatures.metal_detector"));

    // Frosted core
    public static final DeferredItem<Item> FROSTED_CORE = registerSimpleItem(MFItemIds.FROSTED_CORE);

    // Wands
    public static final DeferredItem<Item> ICE_WAND = registerItem(MFItemIds.ICE_WAND,
            properties -> new IceWandItem(properties
                    .durability(636)
                    .repairable(MFItems.FROSTED_CORE.get())
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> FIRE_WAND = registerItem(MFItemIds.FIRE_WAND,
            properties -> new FireWandItem(properties
                    .durability(636)
                    .repairable(Items.BLAZE_POWDER)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> HEALING_WAND = registerItem(MFItemIds.HEALING_WAND,
            properties -> new HealingWandItem(properties
                    .durability(636)
                    .repairable(Items.GLISTERING_MELON_SLICE)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> EARTH_WAND = registerItem(MFItemIds.EARTH_WAND,
            properties -> new EarthWandItem(properties
                    .durability(636)
                    .repairable(Items.DEEPSLATE)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> LIGHTNING_WAND = registerItem(MFItemIds.LIGHTNING_WAND,
            properties -> new LightningWandItem(properties
                    .durability(636)
                    .repairable(Items.REDSTONE)
                    .rarity(Rarity.UNCOMMON)
            ));

    public static final DeferredItem<Item> TIME_WAND = registerItem(MFItemIds.TIME_WAND,
            properties -> new TimeWandItem(properties
                    .durability(636)
                    .repairable(Items.CLOCK)
                    .rarity(Rarity.UNCOMMON)
            ));

    // Spawn eggs
    public static final DeferredItem<Item> ICEOLOGER_SPAWN_EGG = registerItem(MFItemIds.ICEOLOGER_SPAWN_EGG,
            properties -> new SpawnEggItem(properties
                    .spawnEgg(MFEntityTypes.ICEOLOGER.get())
            ));

    // Music Discs

    public static final DeferredItem<Item> MUSIC_DISC_AQUAMARINE = registerItem(MFItemIds.MUSIC_DISC_AQUAMARINE,
            properties -> new Item(properties
                    .jukeboxPlayable(MFJukeboxSongs.AQUAMARINE_KEY)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
            ));

    public static final DeferredItem<Item> MUSIC_DISC_SNOW_QUEEN = registerItem(MFItemIds.MUSIC_DISC_SNOW_QUEEN,
            properties -> new Item(properties
                    .jukeboxPlayable(MFJukeboxSongs.SNOW_QUEEN_KEY)
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
            ));

    // Carbon Items and tools
    public static final DeferredItem<Item> CARBON_FIBER = registerItem(MFItemIds.CARBON_FIBER,
            properties -> new Item(properties
                    .fireResistant()
                    .trimMaterial(MFTrimMaterials.CARBON)
            ));

    public static final DeferredItem<Item> CARBON_BOW = registerItem(MFItemIds.CARBON_BOW,
            properties -> new BowItem(properties
                    .durability(856)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .enchantable(15)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> CARBON_CROSSBOW = registerItem(MFItemIds.CARBON_CROSSBOW,
            properties -> new CrossbowItem(properties
                    .durability(885)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .enchantable(15)
                    .component(DataComponents.CHARGED_PROJECTILES, ChargedProjectiles.EMPTY)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> CARBON_WOLF_ARMOR = registerItem(MFItemIds.CARBON_WOLF_ARMOR,
            properties -> new Item(properties
                    .durability(256)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .enchantable(15)
                    .fireResistant()
                    .wolfArmor(MFArmorMaterials.CARBON)
            ));

    public static final DeferredItem<Item> CARBON_ELYTRA = registerItem(MFItemIds.CARBON_ELYTRA,
            properties -> new Item(properties
                    .durability(893)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .component(DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                    .setAsset(MFEquipmentAssets.CARBON)
                                    .setDamageOnHurt(false)
                                    .build()
                    )
            ));

    public static final DeferredItem<Item> CARBON_TRIDENT = registerItem(MFItemIds.CARBON_TRIDENT,
            properties -> new MFTridentItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(594)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .tool(MFToolMaterial.CARBON_TOOL_MATERIAL, BlockTags.MINEABLE_WITH_HOE, 2.0F, 6F, 0.5F)
                    .attributes(MFTridentItem.createAttributes(9.0D, -2.4D))
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, MFTridentItem.createToolProperties(2))
                    .component(DataComponents.WEAPON, new Weapon(1)),
                    9.5F,
                    MFEntityTypes.CARBON_TRIDENT.get(),
                    MFItemIds.CARBON_TRIDENT.identifier()
            ));

    public static final DeferredItem<Item> CARBON_SHIELD = registerItem(MFItemIds.CARBON_SHIELD,
            properties -> new ShieldItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(685)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .equippableUnswappable(EquipmentSlot.OFFHAND)
                    .delayedComponent(
                            DataComponents.BLOCKS_ATTACKS,
                            context -> new BlocksAttacks(
                                    0.25F,
                                    0.9F,
                                    List.of(new BlocksAttacks.DamageReduction(90.0F, Optional.empty(), 0.0F, 1.0F)),
                                    new BlocksAttacks.ItemDamageFunction(3.0F, 1.0F, 1.0F),
                                    Optional.of(context.getOrThrow(DamageTypeTags.BYPASSES_SHIELD)),
                                    Optional.of(SoundEvents.SHIELD_BLOCK),
                                    Optional.of(SoundEvents.SHIELD_BREAK)
                            )
                    )
                    .component(DataComponents.BREAK_SOUND, SoundEvents.SHIELD_BREAK)
            ));

    public static final DeferredItem<Item> BISMUTH_SHIELD = registerItem(MFItemIds.BISMUTH_SHIELD,
            properties -> new ShieldItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(685)
                    .repairable(MFItemTags.CARBON_TOOL_MATERIAL_REPAIRABLE)
                    .equippableUnswappable(EquipmentSlot.OFFHAND)
                    .delayedComponent(
                            DataComponents.BLOCKS_ATTACKS,
                            context -> new BlocksAttacks(
                                    0.25F,
                                    0.7F,
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
            "bismuth_upgrade_smithing_template", MFSmithingTemplateItem::createBismuthUpgradeTemplate, new Item.Properties().rarity(Rarity.UNCOMMON)
    );

    public static final DeferredItem<Item> BISMUTH_HELMET = registerItem(MFItemIds.BISMUTH_HELMET,
            properties -> new Item(properties
                    .humanoidArmor(MFArmorMaterials.BISMUTH, ArmorType.HELMET)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .enchantable(15)
                    .repairable(MFItemTags.REPAIRS_BISMUTH_ARMOR)
            ));

    public static final DeferredItem<Item> BISMUTH_CHESTPLATE = registerItem(MFItemIds.BISMUTH_CHESTPLATE,
            properties -> new Item(properties
                    .humanoidArmor(MFArmorMaterials.BISMUTH, ArmorType.CHESTPLATE)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .enchantable(15)
                    .repairable(MFItemTags.REPAIRS_BISMUTH_ARMOR)
            ));

    public static final DeferredItem<Item> BISMUTH_LEGGINGS = registerItem(MFItemIds.BISMUTH_LEGGINGS,
            properties -> new Item(properties
                    .humanoidArmor(MFArmorMaterials.BISMUTH, ArmorType.LEGGINGS)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .enchantable(15)
                    .repairable(MFItemTags.REPAIRS_BISMUTH_ARMOR)
            ));

    public static final DeferredItem<Item> BISMUTH_BOOTS = registerItem(MFItemIds.BISMUTH_BOOTS,
            properties -> new Item(properties
                    .humanoidArmor(MFArmorMaterials.BISMUTH, ArmorType.BOOTS)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .enchantable(15)
                    .repairable(MFItemTags.REPAIRS_BISMUTH_ARMOR)
            ));

    public static final DeferredItem<Item> BISMUTH_PICKAXE = registerItem(MFItemIds.BISMUTH_PICKAXE,
            properties -> new Item(properties
                    .pickaxe(
                            BISMUTH_TOOL_MATERIAL,
                            0F,
                            -2.8F
                    )
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_SWORD = registerItem(MFItemIds.BISMUTH_SWORD,
            properties -> new Item(properties
                    .sword(
                            BISMUTH_TOOL_MATERIAL,
                            0F,
                            -2.8F
                    )
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_AXE = registerItem(MFItemIds.BISMUTH_AXE,
            properties -> new AxeItem(BISMUTH_TOOL_MATERIAL, 6f, -3f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_SHOVEL = registerItem(MFItemIds.BISMUTH_SHOVEL,
            properties -> new ShovelItem(BISMUTH_TOOL_MATERIAL, 1f, -3.0f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_HOE = registerItem(MFItemIds.BISMUTH_HOE,
            properties -> new HoeItem(BISMUTH_TOOL_MATERIAL, -5.4f, 1f, properties
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.RARE)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_SPEAR = registerItem(MFItemIds.BISMUTH_SPEAR,
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
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
                    .fireResistant()
                    .rarity(Rarity.RARE)
            ));

    public static final DeferredItem<Item> BISMUTH_BOW = registerItem(MFItemIds.BISMUTH_BOW,
            properties -> new BowItem(properties
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .durability(1516)
                    .enchantable(19)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_HORSE_ARMOR = registerItem(MFItemIds.BISMUTH_HORSE_ARMOR,
            properties -> new Item(properties
                    .horseArmor(MFArmorMaterials.BISMUTH)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
            ));

    public static final DeferredItem<Item> BISMUTH_NAUTILUS_ARMOR = registerItem(MFItemIds.BISMUTH_NAUTILUS_ARMOR,
            properties -> new Item(properties
                    .nautilusArmor(MFArmorMaterials.BISMUTH)
                    .rarity(Rarity.RARE)
                    .fireResistant()
            ));

    public static final DeferredItem<Item> BISMUTH_ELYTRA = registerItem(MFItemIds.BISMUTH_ELYTRA,
            properties -> new Item(properties
                    .durability(893)
                    .repairable(BISMUTH.get())
                    .enchantable(15)
                    .fireResistant()
                    .rarity(Rarity.EPIC)
                    .component(DataComponents.GLIDER, Unit.INSTANCE)
                    .component(DataComponents.EQUIPPABLE,
                            Equippable.builder(EquipmentSlot.CHEST)
                                    .setEquipSound(SoundEvents.ARMOR_EQUIP_ELYTRA)
                                    .setAsset(MFEquipmentAssets.BISMUTH)
                                    .setDamageOnHurt(false)
                                    .build()
                    )
            ));

    public static final DeferredItem<Item> BISMUTH_TRIDENT = registerItem(MFItemIds.BISMUTH_TRIDENT,
            properties -> new MFTridentItem(properties
                    .fireResistant()
                    .enchantable(15)
                    .durability(997)
                    .repairable(MFItemTags.BISMUTH_TOOL_MATERIALS)
                    .attributes(MFTridentItem.createAttributes(11.0D, -1.9D))
                    .rarity(Rarity.RARE)
                    .component(DataComponents.TOOL, MFTridentItem.createToolProperties(3))
                    .component(DataComponents.WEAPON, new Weapon(2)),
                    11.75F,
                    MFEntityTypes.BISMUTH_TRIDENT.get(),
                    MFItemIds.BISMUTH_TRIDENT.identifier()
            ));

    // Sign items

    // Most block items are registered automatically in the registerBlock method.
    // However, sign items are shared by two blocks, (wall and standing / ceiling)
    // so they have to be registered separately to their respective blocks.

    public static final DeferredItem<Item> AZURITE_SIGN =
            registerItem(MFItemIds.AZURITE_SIGN, properties ->
                    new SignItem(
                            MFBlocks.AZURITE_SIGN.get(),
                            MFBlocks.AZURITE_WALL_SIGN.get(),
                            properties.stacksTo(16)
                    )
            );

    public static final DeferredItem<Item> AZURITE_HANGING_SIGN =
            registerItem(MFItemIds.AZURITE_HANGING_SIGN, properties ->
                    new HangingSignItem(
                            MFBlocks.AZURITE_HANGING_SIGN.get(),
                            MFBlocks.AZURITE_WALL_HANGING_SIGN.get(),
                            properties.stacksTo(16)
                    )
            );

    public static final DeferredItem<Item> FLUORITE_SIGN =
            registerItem(MFItemIds.FLUORITE_SIGN, properties ->
                    new SignItem(
                            MFBlocks.FLUORITE_SIGN.get(),
                            MFBlocks.FLUORITE_WALL_SIGN.get(),
                            properties.stacksTo(16)
                    )
            );

    public static final DeferredItem<Item> FLUORITE_HANGING_SIGN =
            registerItem(MFItemIds.FLUORITE_HANGING_SIGN, properties ->
                    new HangingSignItem(
                            MFBlocks.FLUORITE_HANGING_SIGN.get(),
                            MFBlocks.FLUORITE_WALL_HANGING_SIGN.get(),
                            properties.stacksTo(16)
                    )
            );

    public static final WoodTypeCollection<DeferredItem<Item>> WOODEN_SIGN = WoodTypeCollection.registerItems(
            MFItemIds.WOODEN_SIGN,
            MFItems::registerItem,
            (set, props) -> new SignItem(
                    MFBlocks.WOODEN_SIGN.pick(set).get(),
                    MFBlocks.WOODEN_WALL_SIGN.pick(set).get(),
                    props
            ),
            (_, props) -> props.stacksTo(16)
    );

    public static final WoodTypeCollection<DeferredItem<Item>> WOODEN_HANGING_SIGN = WoodTypeCollection.registerItems(
            MFItemIds.WOODEN_HANGING_SIGN,
            MFItems::registerItem,
            (set, props) -> new HangingSignItem(
                    MFBlocks.WOODEN_HANGING_SIGN.pick(set).get(),
                    MFBlocks.WOODEN_WALL_HANGING_SIGN.pick(set).get(),
                    props
            ),
            (_, props) -> props.stacksTo(16)
    );

    // Boat items

    public static final DeferredItem<Item> BLOODWOOD_BOAT = registerItem(
            MFItemIds.BLOODWOOD_BOAT,
            props -> new BoatItem(MFEntityTypes.BLOODWOOD_BOAT.get(), props)
    );

    public static final DeferredItem<Item> BLOODWOOD_CHEST_BOAT = registerItem(
            MFItemIds.BLOODWOOD_CHEST_BOAT,
            props -> new BoatItem(MFEntityTypes.BLOODWOOD_CHEST_BOAT.get(), props)
    );

    public static final DeferredItem<Item> TAINTED_BOAT = registerItem(
            MFItemIds.TAINTED_BOAT,
            props -> new BoatItem(MFEntityTypes.TAINTED_BOAT.get(), props)
    );

    public static final DeferredItem<Item> TAINTED_CHEST_BOAT = registerItem(
            MFItemIds.TAINTED_CHEST_BOAT,
            props -> new BoatItem(MFEntityTypes.TAINTED_CHEST_BOAT.get(), props)
    );

    public static final DeferredItem<Item> PALM_BOAT = registerItem(
            MFItemIds.PALM_BOAT,
            props -> new BoatItem(MFEntityTypes.PALM_BOAT.get(), props)
    );

    public static final DeferredItem<Item> PALM_CHEST_BOAT = registerItem(
            MFItemIds.PALM_CHEST_BOAT,
            props -> new BoatItem(MFEntityTypes.PALM_CHEST_BOAT.get(), props)
    );

    public static final DeferredItem<Item> BLUE_BERRY = registerItem(MFItemIds.BLUE_BERRY,
            properties -> new BlockItem(MFBlocks.BLUE_BERRY_BUSH.get(), properties.food(MFFoods.BLUE_BERRY))
    );

    // Keep in mind, Block Items (except for signs because their items are shared by two blocks, wall and standing/ceiling) are registered automatically by the registerBlock method in MFBlocks.
    // As such, there is no Block Items being registered here in this class. See MFBlocks if you want to look at how they are registered

    private static <T extends Item> DeferredItem<T> registerItem(ResourceKey<Item> id, Function<Item.Properties, T> function){
        return ITEMS.registerItem(id.identifier().getPath(), props -> function.apply(props.setId(id)));
    }

    @SuppressWarnings("SameParameterValue")
    private static DeferredItem<MetalDetectorItem> registerMetalDetector(ResourceKey<Item> id, Component... components){
        return ITEMS.registerItem(id.identifier().getPath(), props -> new MetalDetectorItem(props.setId(id).durability(256)){
            @SuppressWarnings("deprecation")
            @NullMarked
            @Override
            public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display, Consumer<Component> builder, TooltipFlag tooltipFlag) {
                for(var component : components) {
                    builder.accept(component);
                }
                super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
            }
        });
    }

    private static DeferredItem<Item> registerSimpleItem(ResourceKey<Item> id){
        return ITEMS.registerItem(id.identifier().getPath(), Item::new, UnaryOperator.identity());
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
        MoreFeatures.LOGGER.info("Mod Items registered -> Performed by: " + MODID);
    }
}