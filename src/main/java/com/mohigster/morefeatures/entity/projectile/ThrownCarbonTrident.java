package com.mohigster.morefeatures.entity.projectile;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.object.projectile.TridentModel;
import net.minecraft.resources.Identifier;

public class ThrownCarbonTrident extends TridentModel {

    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(MoreFeatures.MODID, "textures/entity/trident/carbon_trident.png");
    public ThrownCarbonTrident(ModelPart root) {
        super(root);
    }

}
