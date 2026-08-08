package com.mohigster.morefeatures.renderer.special;

import com.mohigster.morefeatures.entity.model.MFTridentModel;
import com.mohigster.morefeatures.entity.model.MFModelLayer;
import com.mohigster.morefeatures.data.references.MFEntityTypeIds;
import com.mojang.serialization.MapCodec;
import net.minecraft.client.renderer.special.NoDataSpecialModelRenderer;
import org.jspecify.annotations.NullMarked;

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
            return new CarbonTridentSpecialRenderer(new MFTridentModel((context.entityModelSet().bakeLayer(MFModelLayer.CARBON_TRIDENT))));
        }
    }
}
