package com.mohigster.morefeatures.renderer.special.shield;

import com.mojang.serialization.MapCodec;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.object.equipment.ShieldModel;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.core.component.DataComponentMap;

public class CustomShieldUnbaked implements SpecialModelRenderer.Unbaked<DataComponentMap> {
    private final String shieldMaterial;
    private final String modid;

    private final MapCodec<CustomShieldUnbaked> codec = MapCodec.unit(this).stable();

    public CustomShieldUnbaked(String shieldMaterial, String modid) {
        this.shieldMaterial = shieldMaterial;
        this.modid = modid;
    }

    protected String getShieldMaterialName() {
        return this.shieldMaterial;
    }

    protected String getModId() {
        return this.modid;
    }

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

    @Override
    public MapCodec<CustomShieldUnbaked> type() {
        return this.codec;
    }

    public static MapCodec<CustomShieldUnbaked> getMapCodec(String shieldMaterial, String modid) {
        return new CustomShieldUnbaked(shieldMaterial, modid).type();
    }
}

