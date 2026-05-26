package com.mohigster.morefeatures.model;

import com.mohigster.morefeatures.renderer.special.CarbonTridentSpecialRenderer;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ItemModelOutput;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelInstance;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class ModCarbonItemModelGenerators extends ItemModelGenerators {
    public ModCarbonItemModelGenerators(ItemModelOutput itemModelOutput, BiConsumer<Identifier, ModelInstance> modelOutput) {
        super(itemModelOutput, modelOutput);
    }


    @Override
    public void generateTrident(final Item item) {
        ItemModel.Unbaked flatModel = ItemModelUtils.plainModel(this.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked inHandNormalModel = ItemModelUtils.specialModel(
                ModelLocationUtils.getModelLocation(item, "_in_hand"), new CarbonTridentSpecialRenderer.Unbaked()
        );
        ItemModel.Unbaked inHandThrowingModel = ItemModelUtils.specialModel(
                ModelLocationUtils.getModelLocation(item, "_throwing"), new CarbonTridentSpecialRenderer.Unbaked()
        );
        ItemModel.Unbaked inHandModel = ItemModelUtils.conditional(
                CarbonTridentSpecialRenderer.DEFAULT_TRANSFORMATION, ItemModelUtils.isUsingItem(), inHandThrowingModel, inHandNormalModel
        );
        this.itemModelOutput.accept(item, createFlatModelDispatch(flatModel, inHandModel));
    }
}
