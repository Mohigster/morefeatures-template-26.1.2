package com.mohigster.morefeatures.entity.entity_types;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.entity.custom.trident.ThrownCarbonTrident;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEntityTypes {
//    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
//            DeferredRegister.create(Registries.ENTITY_TYPE, MoreFeatures.MODID);

    public static final DeferredRegister.Entities ENTITY_TYPES =
            DeferredRegister.createEntities(MoreFeatures.MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<ThrownCarbonTrident>> CARBON_TRIDENT =
            ENTITY_TYPES.register("carbon_trident", () -> createEntityType(ThrownCarbonTrident::new, 4, 20));



    private static <T extends Entity> EntityType<T> createEntityType(
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

    public static void register(IEventBus modEventBus) {
        ENTITY_TYPES.register(modEventBus);
    }
}
