package com.mohigster.morefeatures.renderer.special.shield.core;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;

public abstract class AbstractShieldUnbaked implements SpecialModelRenderer.Unbaked<DataComponentMap> {
    protected abstract String getShieldMaterialName();
    protected abstract String getModId();

    @Override
    public CustomShieldSpecialRenderer bake(final SpecialModelRenderer.BakingContext context) {
        if (this.getShieldMaterialName().isEmpty()){
            throw new IllegalArgumentException("Shield name cannot be empty. Please specify the name" +
                    " of your custom shield material where you override getShieldMaterialName.");
        }

        return new CustomShieldSpecialRenderer(context.sprites(),
                new ShieldModel(context.entityModelSet()
                        .bakeLayer(ModelLayers.SHIELD)),
                this.getShieldMaterialName(),
                this.getModId()
        );
    }
}

