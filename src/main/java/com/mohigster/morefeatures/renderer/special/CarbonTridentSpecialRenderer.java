package com.mohigster.morefeatures.renderer.special;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mohigster.morefeatures.model.MFModelLayer;
import com.mohigster.morefeatures.references.MFEntityTypeIds;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;

import java.util.function.Consumer;

public class CarbonTridentSpecialRenderer extends MFTridentSpecialRenderer {
    public CarbonTridentSpecialRenderer(MFTridentModel model) {
        super(model, MFEntityTypeIds.CARBON_TRIDENT);
    }

    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<CarbonTridentSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new CarbonTridentSpecialRenderer.Unbaked());

        @NullMarked
        @Override
        public MapCodec<CarbonTridentSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public CarbonTridentSpecialRenderer bake(final BakingContext context) {
            return new CarbonTridentSpecialRenderer(new MFTridentModel((context.entityModelSet().bakeLayer(MFModelLayer.CARBON_TRIDENT)), MFTridentModel.getTexture(MFEntityTypeIds.CARBON_TRIDENT)));
        }
    }
}
