package com.mohigster.morefeatures.entity.entity_types;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.BrineEntity;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.custom.projectile.trident.ThrownMFTrident;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.references.MFIdentifier;
import com.mohigster.morefeatures.references.MFItemIds;
import net.minecraft.core.registries.Registries;
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
            ENTITY_TYPES.register("carbon_trident", () -> createTridentEntityType((type, level) -> new ThrownMFTrident(type, level, MFItemIds.CARBON_TRIDENT.identifier()), "carbon_trident"));

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownMFTrident>> BISMUTH_TRIDENT =
            ENTITY_TYPES.register("bismuth_trident", () -> createTridentEntityType((type, level) -> new ThrownMFTrident(type, level, MFItemIds.BISMUTH_TRIDENT.identifier()), "bismuth_trident"));

    public static final DeferredHolder<EntityType<?>, EntityType<BrineEntity>> BRINE =
            ENTITY_TYPES.register("brine", () -> createHostileEntityType(BrineEntity::new, 5, 15, 0.8F, 1.5F, 1.3F, "brine"));

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> BLOODWOOD_BOAT =
            ENTITY_TYPES.register("bloodwood_boat",
                    () -> EntityType.Builder.<Boat>of(
                                    (type, level) -> new Boat(type, level, MFItems.BLOODWOOD_BOAT),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(createKey("bloodwood_boat"))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> BLOODWOOD_CHEST_BOAT =
            ENTITY_TYPES.register("bloodwood_chest_boat",
                    () -> EntityType.Builder.<ChestBoat>of(
                                    (type, level) -> new ChestBoat(type, level, MFItems.BLOODWOOD_CHEST_BOAT),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(createKey("bloodwood_chest_boat"))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> TAINTED_BOAT =
            ENTITY_TYPES.register("tainted_boat",
                    () -> EntityType.Builder.<Boat>of(
                                    (type, level) -> new Boat(type, level, MFItems.TAINTED_BOAT),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(createKey("tainted_boat"))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> TAINTED_CHEST_BOAT =
            ENTITY_TYPES.register("tainted_chest_boat",
                    () -> EntityType.Builder.<ChestBoat>of(
                                    (type, level) -> new ChestBoat(type, level, MFItems.TAINTED_CHEST_BOAT),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(createKey("tainted_chest_boat"))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> PALM_BOAT =
            ENTITY_TYPES.register("palm_boat",
                    () -> EntityType.Builder.<Boat>of(
                                    (type, level) -> new Boat(type, level, MFItems.PALM_BOAT),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(createKey("palm_boat"))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> PALM_CHEST_BOAT =
            ENTITY_TYPES.register("palm_chest_boat",
                    () -> EntityType.Builder.<ChestBoat>of(
                                    (type, level) -> new ChestBoat(type, level, MFItems.PALM_CHEST_BOAT),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(createKey("palm_chest_boat"))
            );

    public static final DeferredHolder<EntityType<?>, EntityType<IceologerEntity>> ICEOLOGER =
            ENTITY_TYPES.register("iceologer", () -> createHostileEntityType(IceologerEntity::new, 8, 10, 1f, 2f, 1.80f, "iceologer"));

    private static <T extends Entity> EntityType<T> createHostileEntityType(
            EntityType.EntityFactory<T> factory, int trackingRange, int updateInterval, float width, float height, float eyeHeight, String path) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(width, height)
                .eyeHeight(eyeHeight)
                .clientTrackingRange(trackingRange)
                .notInPeaceful();


        if (updateInterval != Integer.MAX_VALUE) {
            builder.updateInterval(updateInterval);
        }

        return builder.build(createKey(path));
    }

    private static <T extends Entity> EntityType<T> createTridentEntityType(
            EntityType.EntityFactory<T> factory, String path) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .eyeHeight(0.13F)
                .clientTrackingRange(4)
                .noLootTable()
                .updateInterval(20);


        return builder.build(createKey(path));
    }

    private static ResourceKey<EntityType<?>> createKey(String id){
        return ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace(id));
    }

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
        MoreFeatures.LOGGER.info("Mod Entity Types registered -> Performed by: " + MoreFeatures.MODID);
    }
}
