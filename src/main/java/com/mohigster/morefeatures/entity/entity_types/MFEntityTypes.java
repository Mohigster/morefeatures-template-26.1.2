package com.mohigster.morefeatures.entity.entity_types;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.BrineEntity;
import com.mohigster.morefeatures.entity.custom.IceologerEntity;
import com.mohigster.morefeatures.entity.custom.projectile.trident.ThrownBismuthTrident;
import com.mohigster.morefeatures.entity.custom.projectile.trident.ThrownCarbonTrident;
import com.mohigster.morefeatures.item.MFItems;
import com.mohigster.morefeatures.references.MFIdentifier;
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


    public static final DeferredHolder<EntityType<?>, EntityType<ThrownCarbonTrident>> CARBON_TRIDENT =
            ENTITY_TYPES.register("carbon_trident", () -> createTridentEntityType(ThrownCarbonTrident::new, "carbon_trident"));

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownBismuthTrident>> BISMUTH_TRIDENT =
            ENTITY_TYPES.register("bismuth_trident", () -> createTridentEntityType(ThrownBismuthTrident::new, "bismuth_trident"));

    public static final DeferredHolder<EntityType<?>, EntityType<BrineEntity>> BRINE =
            ENTITY_TYPES.register("brine", () -> createHostileEntityType(BrineEntity::new, 5, 15, 0.8F, 1.5F, 1.3F, "brine"));

    public static final DeferredHolder<EntityType<?>, EntityType<Boat>> PALM_BOAT =
            ENTITY_TYPES.register("palm_boat",
                    () -> EntityType.Builder.<Boat>of(
                                    (type, level) -> new Boat(type, level, MFItems.PALM_BOAT::get),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace("palm_boat"))));

    public static final DeferredHolder<EntityType<?>, EntityType<ChestBoat>> PALM_CHEST_BOAT =
            ENTITY_TYPES.register("palm_chest_boat",
                    () -> EntityType.Builder.<ChestBoat>of(
                                    (type, level) -> new ChestBoat(type, level, MFItems.PALM_CHEST_BOAT::get),
                                    MobCategory.MISC)
                            .sized(1.375F, 0.5625F)
                            .clientTrackingRange(10)
                            .noLootTable()
                            .build(ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace("palm_chest_boat"))));

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

        return builder.build(ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace(path)));
    }

    private static <T extends Entity> EntityType<T> createTridentEntityType(
            EntityType.EntityFactory<T> factory, String path) {

        EntityType.Builder<T> builder = EntityType.Builder.of(factory, MobCategory.MISC)
                .sized(0.5F, 0.5F)
                .eyeHeight(0.13F)
                .clientTrackingRange(4)
                .noLootTable()
                .updateInterval(20);


        return builder.build(ResourceKey.create(Registries.ENTITY_TYPE, MFIdentifier.withMfNamespace(path)));
    }

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}
