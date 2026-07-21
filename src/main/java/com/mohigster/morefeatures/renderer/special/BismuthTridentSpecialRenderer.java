package com.mohigster.morefeatures.renderer.special;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mohigster.morefeatures.model.MFModelLayer;
import com.mohigster.morefeatures.references.MFEntityTypeIds;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import org.jspecify.annotations.NullMarked;

public class BismuthTridentSpecialRenderer extends MFTridentSpecialRenderer {
    public BismuthTridentSpecialRenderer(MFTridentModel model) {
        super(model, MFEntityTypeIds.BISMUTH_TRIDENT);
    }

    public record Unbaked() implements NoDataSpecialModelRenderer.Unbaked {
        public static final MapCodec<BismuthTridentSpecialRenderer.Unbaked> MAP_CODEC = MapCodec.unit(new BismuthTridentSpecialRenderer.Unbaked());

        @NullMarked
        @Override
        public MapCodec<BismuthTridentSpecialRenderer.Unbaked> type() {
            return MAP_CODEC;
        }

        public BismuthTridentSpecialRenderer bake(final BakingContext context) {
            return new BismuthTridentSpecialRenderer(new MFTridentModel((context.entityModelSet().bakeLayer(MFModelLayer.BISMUTH_TRIDENT))));
        }
    }
}
