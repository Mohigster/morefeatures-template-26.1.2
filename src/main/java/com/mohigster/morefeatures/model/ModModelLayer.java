package com.mohigster.morefeatures.model;

import com.google.common.collect.Sets;
import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

import java.util.Set;

public class ModModelLayer {
    private static final String DEFAULT_LAYER = "main";
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation CARBON_TRIDENT = register("carbon_trident");

    private static ModelLayerLocation register(final String model) {
        return register(model, "main");
    }

    private static ModelLayerLocation register(final String model, final String layer) {
        ModelLayerLocation result = createLocation(model, layer);
        if (!ALL_MODELS.add(result)) {
            throw new IllegalStateException("Duplicate registration for " + result);
        } else {
            return result;
        }
    }

    private static ModelLayerLocation createLocation(final String model, final String layer) {
        return new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoreFeatures.MODID, model), layer);
    }

}
