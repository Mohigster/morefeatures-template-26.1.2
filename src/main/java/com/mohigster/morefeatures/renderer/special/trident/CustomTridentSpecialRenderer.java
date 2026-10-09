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
    private final ResourceKey<EntityType<?>> tridentKey;

    public CustomTridentSpecialRenderer(
            MFTridentModel model,
            ResourceKey<EntityType<?>> tridentKey
    ) {
        this.model = model;
        this.tridentKey = tridentKey;
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
                this.model.renderType(MFTridentModel.getTexture(this.getTridentKey())),
                lightCoords, overlayCoords, null
        );
    }

    @Override
    public void getExtents(final Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, consumer);
    }

    public ResourceKey<EntityType<?>> getTridentKey(){
        return this.tridentKey;
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

    public static final class Unbaked implements NoDataSpecialModelRenderer.Unbaked {
        private final ModelLayerLocation location;
        private final ResourceKey<EntityType<?>> trident;
        private final MapCodec<Unbaked> codec = unitStable(this);

        private Unbaked(ResourceKey<EntityType<?>> trident, ModelLayerLocation location) {
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
            return this.codec;
        }

        public ResourceKey<EntityType<?>> getTrident() {
            return this.trident;
        }

        public ModelLayerLocation getLocation() {
            return this.location;
        }

        private static <T extends Unbaked> MapCodec<T> unitStable(T unbaked){
            return MapCodec.unit(unbaked).stable();
        }
    }
}
