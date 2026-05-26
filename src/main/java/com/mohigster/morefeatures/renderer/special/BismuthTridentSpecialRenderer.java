package com.mohigster.morefeatures.renderer.special;

import com.mohigster.morefeatures.entity.projectile.BismuthTridentModel;
import com.mohigster.morefeatures.entity.projectile.CarbonTridentModel;
import com.mohigster.morefeatures.model.ModModelLayer;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Transformation;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import org.joml.Vector3f;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class BismuthTridentSpecialRenderer implements NoDataSpecialModelRenderer {
    public static final Transformation DEFAULT_TRANSFORMATION = new Transformation(null, null, new Vector3f(1.0F, -1.0F, -1.0F), null);
    private final BismuthTridentModel model;


    public BismuthTridentSpecialRenderer(BismuthTridentModel model) {
        this.model = model;
    }

    @Override
    public void submit(
            final PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final int overlayCoords,
            final boolean hasFoil,
            final int outlineColor
    ) {
        submitNodeCollector.submitModelPart(
                this.model.root(), poseStack, this.model.renderType(BismuthTridentModel.TEXTURE), lightCoords, overlayCoords, null, false, hasFoil, -1, null, outlineColor
        );
    }

    @Override
    public void getExtents(final Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, consumer);
    }

    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<BismuthTridentSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new BismuthTridentSpecialRenderer.Unbaked());

        @Override
        public MapCodec<BismuthTridentSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public BismuthTridentSpecialRenderer bake(final BakingContext context) {
            return new BismuthTridentSpecialRenderer(new BismuthTridentModel((context.entityModelSet().bakeLayer(ModModelLayer.BISMUTH_TRIDENT))));
        }
    }
}
