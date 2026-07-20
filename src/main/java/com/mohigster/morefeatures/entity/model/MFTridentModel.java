package com.mohigster.morefeatures.entity.model;

import com.mohigster.morefeatures.MoreFeatures;
import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EntityType;

public class MFTridentModel extends Model<Unit> {

    private final Identifier texture;

    public MFTridentModel(final ModelPart root, Identifier textureLocation) {
        super(root, RenderTypes::entitySolid);
        this.texture = textureLocation;
    }

    public Identifier getTexture(){
        return this.texture;
    }

    // The entity key path is simply the trident e.g. the carbon trident is simply "carbon_trident"
    // So, adding textures/entity/trident/ at the start and .png at the end gives the correct texture location
    public static Identifier getTexture(ResourceKey<EntityType<?>> tridentEntityKey){
        return tridentEntityKey.identifier().withPath("textures/entity/trident/" + tridentEntityKey.identifier().getPath() + ".png");
    }

    public static void printTextureLocation(ResourceKey<EntityType<?>> tridentEntityKey){
        Identifier textureLocation = getTexture(tridentEntityKey);
        MoreFeatures.LOGGER.debug("The correct location for custom trident texture: {}/{}", textureLocation.getNamespace(), textureLocation.getPath());
    }

    public static LayerDefinition createLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        PartDefinition pole = root.addOrReplaceChild("pole", CubeListBuilder.create().texOffs(0, 6).addBox(-0.5F, 2.0F, -0.5F, 1.0F, 25.0F, 1.0F), PartPose.ZERO);
        pole.addOrReplaceChild("base", CubeListBuilder.create().texOffs(4, 0).addBox(-1.5F, 0.0F, -0.5F, 3.0F, 2.0F, 1.0F), PartPose.ZERO);
        pole.addOrReplaceChild("left_spike", CubeListBuilder.create().texOffs(4, 3).addBox(-2.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F), PartPose.ZERO);
        pole.addOrReplaceChild("middle_spike", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -4.0F, -0.5F, 1.0F, 4.0F, 1.0F), PartPose.ZERO);
        pole.addOrReplaceChild("right_spike", CubeListBuilder.create().texOffs(4, 3).mirror().addBox(1.5F, -3.0F, -0.5F, 1.0F, 4.0F, 1.0F), PartPose.ZERO);
        return LayerDefinition.create(mesh, 32, 32);
    }
}
