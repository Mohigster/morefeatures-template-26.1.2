package com.mohigster.morefeatures.model;

import com.google.common.collect.Sets;
import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.client.model.geom.ModelLayerLocation;

import java.util.Set;

public class MFModelLayer {
    private static final String DEFAULT_LAYER = "main";
    private static final Set<ModelLayerLocation> ALL_MODELS = Sets.newHashSet();
    public static final ModelLayerLocation CARBON_TRIDENT = register("carbon_trident");
    public static final ModelLayerLocation BISMUTH_TRIDENT = register("bismuth_trident");
    public static final ModelLayerLocation ICEOLOGER = register("iceologer");
    public static final ModelLayerLocation BLOODWOOD_BOAT = register("boat/bloodwood");
    public static final ModelLayerLocation BLOODWOOD_CHEST_BOAT = register("chest_boat/bloodwood");
    public static final ModelLayerLocation TAINTED_BOAT = register("boat/tainted");
    public static final ModelLayerLocation TAINTED_CHEST_BOAT = register("chest_boat/tainted");
    public static final ModelLayerLocation PALM_BOAT = register("boat/palm");
    public static final ModelLayerLocation PALM_CHEST_BOAT = register("chest_boat/palm");

    private static ModelLayerLocation register(final String model) {
        return register(model, DEFAULT_LAYER);
    }

    @SuppressWarnings("SameParameterValue")
    private static ModelLayerLocation register(final String model, final String layer) {
        ModelLayerLocation result = createLocation(model, layer);
        if (!ALL_MODELS.add(result)) {
            throw new IllegalStateException("Duplicate registration for " + result);
        } else {
            return result;
        }
    }

    private static ModelLayerLocation createLocation(final String model, final String layer) {
        return new ModelLayerLocation(MFIdentifier.withMfNamespace(model), layer);
    }
}
