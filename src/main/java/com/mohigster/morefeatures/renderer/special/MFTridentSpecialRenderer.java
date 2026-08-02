package com.mohigster.morefeatures.renderer.special;

import com.mohigster.morefeatures.entity.model.MFModelLayer;
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
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class MFTridentSpecialRenderer implements NoDataSpecialModelRenderer {
    private final MFTridentModel model;
    private final ResourceKey<EntityType<?>> tridentKey;

    public MFTridentSpecialRenderer(MFTridentModel model, ResourceKey<EntityType<?>> tridentKey) {
        this.model = model;
        this.tridentKey = tridentKey;
    }

    @Override
    public void submit(
            final @NonNull PoseStack poseStack,
            final SubmitNodeCollector submitNodeCollector,
            final int lightCoords,
            final int overlayCoords,
            final boolean hasFoil,
            final int outlineColor
    ) {
        submitNodeCollector.submitModelPart(
                this.model.root(), poseStack,
                this.model.renderType(MFTridentModel.getTexture(this.tridentKey)),
                lightCoords, overlayCoords, null
        );
    }

    @NullMarked
    @Override
    public void getExtents(final Consumer<Vector3fc> consumer) {
        PoseStack poseStack = new PoseStack();
        this.model.root().getExtentsForGui(poseStack, consumer);
    }
}
