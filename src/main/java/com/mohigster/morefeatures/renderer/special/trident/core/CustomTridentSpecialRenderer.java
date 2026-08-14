package com.mohigster.morefeatures.renderer.special.trident.core;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import org.joml.Vector3fc;

import java.util.function.Consumer;

public class CustomTridentSpecialRenderer implements NoDataSpecialModelRenderer {
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

    protected ResourceKey<EntityType<?>> getTridentKey(){
        return this.tridentKey;
    }
}
