package com.mohigster.morefeatures.entity;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.BrineEntity;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.custom.projectile.trident.ThrownMFTrident;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.data.resources.references.MFEntityTypeIds;
import com.mohigster.morefeatures.data.resources.references.MFItemIds;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class MFEntityTypes {

    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(MoreFeatures.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownMFTrident>> CARBON_TRIDENT =
            ENTITY_TYPES.register("carbon_trident", () -> createTridentEntityType(
                    (type, level) -> new ThrownMFTrident(
                            type, level, MFItemIds.CARBON_TRIDENT.identifier(),
                            9.5F),
                    MFEntityTypeIds.CARBON_TRIDENT));

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownMFTrident>> BISMUTH_TRIDENT =
            ENTITY_TYPES.register("bismuth_trident", () -> createTridentEntityType(
                    (type, level) -> new ThrownMFTrident(
                            type, level, MFItemIds.BISMUTH_TRIDENT.identifier(),
                            11.75F),
                    MFEntityTypeIds.BISMUTH_TRIDENT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<BrineEntity>> BRINE =
            ENTITY_TYPES.register("brine", () -> createHostileEntityType(
                    BrineEntity::new,
                    5, 15, 0.8F, 1.5F, 1.3F,
                    MFEntityTypeIds.BRINE
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> BLOODWOOD_BOAT =
            ENTITY_TYPES.register("bloodwood_boat", () -> createBoatEntityType(
                    (type, level) -> new Boat(type, level, MFItems.BLOODWOOD_BOAT),
                    MFEntityTypeIds.BLOODWOOD_BOAT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> BLOODWOOD_CHEST_BOAT =
            ENTITY_TYPES.register("bloodwood_chest_boat", () -> createBoatEntityType(
                    (type, level) -> new ChestBoat(type, level, MFItems.BLOODWOOD_CHEST_BOAT),
                    MFEntityTypeIds.BLOODWOOD_CHEST_BOAT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> TAINTED_BOAT =
            ENTITY_TYPES.register("tainted_boat", () -> createBoatEntityType(
                    (type, level) -> new Boat(type, level, MFItems.TAINTED_BOAT),
                    MFEntityTypeIds.TAINTED_BOAT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> TAINTED_CHEST_BOAT =
            ENTITY_TYPES.register("tainted_chest_boat", () -> createBoatEntityType(
                    (type, level) -> new ChestBoat(type, level, MFItems.TAINTED_CHEST_BOAT),
                    MFEntityTypeIds.TAINTED_CHEST_BOAT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> PALM_BOAT =
            ENTITY_TYPES.register("palm_boat", () -> createBoatEntityType(
                    (type, level) -> new Boat(type, level, MFItems.PALM_BOAT),
                    MFEntityTypeIds.PALM_BOAT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> PALM_CHEST_BOAT =
            ENTITY_TYPES.register("palm_chest_boat", () -> createBoatEntityType(
                    (type, level) -> new ChestBoat(type, level, MFItems.PALM_CHEST_BOAT),
                    MFEntityTypeIds.PALM_CHEST_BOAT
            ));

    public static final DeferredHolder<EntityType<?>, EntityType<IceologerEntity>> ICEOLOGER =
            ENTITY_TYPES.register("iceologer", () -> createHostileEntityType(
                    IceologerEntity::new,
                    8, 10, 1f, 2f, 1.80f,
                    MFEntityTypeIds.ICEOLOGER
            ));

    private static <T extends Entity> EntityType<T> createHostileEntityType(
            EntityType.EntityFactory<T> factory, int trackingRange,
            int updateInterval, float width, float height, float eyeHeight,
            ResourceKey<EntityType<?>> entityId) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(width, height)
                .eyeHeight(eyeHeight)
                .clientTrackingRange(trackingRange)
                .notInPeaceful();

        if (updateInterval != Integer.MAX_VALUE) {
            builder.updateInterval(updateInterval);
        }

        return builder.build(entityId);
    }

    private static <T extends Entity> EntityType<T> createTridentEntityType(
            EntityType.EntityFactory<T> factory, ResourceKey<EntityType<?>> entityId) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .eyeHeight(0.13F)
                .clientTrackingRange(4)
                .noLootTable()
                .updateInterval(20);

        return createEntity(entityId, builder);
    }

    private static <T extends Entity> EntityType<T> createBoatEntityType(
            EntityType.EntityFactory<T> factory, ResourceKey<EntityType<?>> entityId) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(1.375F, 0.5625F)
                .clientTrackingRange(10)
                .noLootTable();

        return createEntity(entityId, builder);
    }

    private static <T extends Entity> EntityType<T> createEntity(
            ResourceKey<EntityType<?>> entityId,
            EntityType.Builder<T> builder
    ) {
        return builder.build(entityId);
    }

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
        MoreFeatures.LOGGER.info("Mod Entity Types registered -> Performed by: " + MoreFeatures.MODID);
    }
}
