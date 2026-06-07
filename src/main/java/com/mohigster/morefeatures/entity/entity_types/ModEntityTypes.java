package com.mohigster.morefeatures.entity.entity_types;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.BrineEntity;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.custom.boat.PalmBoat;
import com.mohigster.morefeatures.entity.custom.trident.ThrownBismuthTrident;
import com.mohigster.morefeatures.entity.custom.trident.ThrownCarbonTrident;
import com.mohigster.morefeatures.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;


public class ModEntityTypes {

    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(MoreFeatures.MODID);


    public static final DeferredHolder<EntityType<?>, EntityType<ThrownCarbonTrident>> CARBON_TRIDENT =
            ENTITY_TYPES.register("carbon_trident", () -> createCarbonEntityType(ThrownCarbonTrident::new, 4, 20));

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBismuthTrident>> BISMUTH_TRIDENT =
            ENTITY_TYPES.register("bismuth_trident", () -> createBismuthEntityType(ThrownBismuthTrident::new, 4, 20));

    public static final DeferredHolder<EntityType<?>, EntityType<BrineEntity>> BRINE_MOB =
            ENTITY_TYPES.register("brine", () -> createBrineEntityType(BrineEntity::new, 4, 20));

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> PALM_BOAT =
            ENTITY_TYPES.register("palm_boat",
                    () -> EntityType.Builder.<Boat>of(
                                    (type, level) -> new Boat(type, level, () -> ModItems.PALM_BOAT.get()),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_boat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> PALM_CHEST_BOAT =
            ENTITY_TYPES.register("palm_chest_boat",
                    () -> EntityType.Builder.<ChestBoat>of(
                                    (type, level) -> new ChestBoat(type, level, () -> ModItems.PALM_CHEST_BOAT.get()),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "palm_chest_boat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<IceologerEntity>> ICEOLOGER =
            ENTITY_TYPES.register("iceologer", () -> createIceologerEntityType(IceologerEntity::new, 4, 20));

    private static <T extends Entity> EntityType<T> createCarbonEntityType(
            EntityType.EntityFactory<T> factory, int trackingRange, int updateInterval) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5f, 0.5f) // Replaces dimensions(EntityDimensions.changing())
                .eyeHeight(0.13F)
                .clientTrackingRange(trackingRange);


        if (updateInterval != Integer.MAX_VALUE) {
            builder.updateInterval(updateInterval);
        }

        // NeoForge requires standard projectile/misc entities to allow tracking modifications
        return builder.build(CARBON_TRIDENT.getKey());
    }

    private static <T extends Entity> EntityType<T> createBismuthEntityType(
            EntityType.EntityFactory<T> factory, int trackingRange, int updateInterval) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5f, 0.5f) // Replaces dimensions(EntityDimensions.changing())
                .eyeHeight(0.13F)
                .clientTrackingRange(trackingRange);


        if (updateInterval != Integer.MAX_VALUE) {
            builder.updateInterval(updateInterval);
        }

        // NeoForge requires standard projectile/misc entities to allow tracking modifications
        return builder.build(BISMUTH_TRIDENT.getKey());
    }

    private static <T extends Entity> EntityType<T> createBrineEntityType(
            EntityType.EntityFactory<T> factory, int trackingRange, int updateInterval) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5f, 0.5f) // Replaces dimensions(EntityDimensions.changing())
                .eyeHeight(0.13F)
                .clientTrackingRange(trackingRange);


        if (updateInterval != Integer.MAX_VALUE) {
            builder.updateInterval(updateInterval);
        }

        // NeoForge requires standard projectile/misc entities to allow tracking modifications
        return builder.build(BRINE_MOB.getKey());
    }

    private static <T extends Entity> EntityType<T> createIceologerEntityType(
            EntityType.EntityFactory<T> factory, int trackingRange, int updateInterval) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5f, 0.5f) // Replaces dimensions(EntityDimensions.changing())
                .eyeHeight(0.13F)
                .clientTrackingRange(trackingRange);


        if (updateInterval != Integer.MAX_VALUE) {
            builder.updateInterval(updateInterval);
        }

        return builder.build(ICEOLOGER.getKey());
    }

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}
