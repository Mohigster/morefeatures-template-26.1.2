package com.mohigster.morefeatures.renderer.special.trident;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public final class CustomTridentSpecialRenderer implements NoDataSpecialModelRenderer {
    private final MFTridentModel model;
    private final ResourceKey<EntityType<?>> trident;

    public CustomTridentSpecialRenderer(
            final MFTridentModel model,
            final ResourceKey<EntityType<?>> trident
    ) {
        this.model = model;
        this.trident = trident;
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
                this.model.root(), poseStack,
                this.model.renderType(MFTridentModel.getTexture(this.getTrident())),
                lightCoords, overlayCoords, null
        );
    }

    @Override
    public void getExtents(final Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, consumer);
    }

    public ResourceKey<EntityType<?>> getTrident() {
        return this.trident;
    }

    public static MapCodec<Unbaked> getUnbakedCodec(
            final ModelLayerLocation location,
            final ResourceKey<EntityType<?>> trident
    ) {
        return new Unbaked(
                trident,
                location
        ).type();
    }

    public static class Unbaked implements NoDataSpecialModelRenderer.Unbaked {
        protected ModelLayerLocation location;
        private final ResourceKey<EntityType<?>> trident;

        private Unbaked(
                ResourceKey<EntityType<?>> trident,
                ModelLayerLocation location
        ) {
            this.location = location;
            this.trident = trident;
        }

        @Override
        public CustomTridentSpecialRenderer bake(
                final SpecialModelRenderer.BakingContext context
        ) {
            return new CustomTridentSpecialRenderer(
                    new MFTridentModel(context.entityModelSet()
                            .bakeLayer(this.getLocation())),
                    this.getTrident());
        }

        @Override
        public MapCodec<Unbaked> type() {
            return MapCodec.unit(this);
        }

        public ResourceKey<EntityType<?>> getTrident() {
            return this.trident;
        }

        public ModelLayerLocation getLocation() {
            return this.location;
        }
    }
}
