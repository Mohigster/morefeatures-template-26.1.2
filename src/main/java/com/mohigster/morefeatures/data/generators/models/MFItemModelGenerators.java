package com.mohigster.morefeatures.data.generators.models;

import com.mohigster.morefeatures.item.custom.trim.MFMaterialAssetGroups;
import com.mohigster.morefeatures.item.custom.trim.MFTrimMaterials;
import net.minecraft.client.color.item.Dye;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.SelectItemModel;
import net.minecraft.client.renderer.item.properties.select.TrimMaterialProperty;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;
import net.minecraft.world.item.equipment.trim.TrimMaterials;
import org.jspecify.annotations.NonNull;

import java.util.ArrayList;
import java.util.List;

public final class MFItemModelGenerators {
    private final ItemModelGenerators itemModels;

    public static final List<ItemModelGenerators.TrimMaterialData> TRIM_MATERIAL_MODELS = new ArrayList<>(
            List.of(
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.QUARTZ, TrimMaterials.QUARTZ),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.IRON, TrimMaterials.IRON),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.NETHERITE, TrimMaterials.NETHERITE),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.REDSTONE, TrimMaterials.REDSTONE),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.COPPER, TrimMaterials.COPPER),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.GOLD, TrimMaterials.GOLD),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.EMERALD, TrimMaterials.EMERALD),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.DIAMOND, TrimMaterials.DIAMOND),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.LAPIS, TrimMaterials.LAPIS),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.AMETHYST, TrimMaterials.AMETHYST),
                    new ItemModelGenerators.TrimMaterialData(MaterialAssetGroup.RESIN, TrimMaterials.RESIN),
                    new ItemModelGenerators.TrimMaterialData(MFMaterialAssetGroups.MAGNESIUM, MFTrimMaterials.MAGNESIUM),
                    new ItemModelGenerators.TrimMaterialData(MFMaterialAssetGroups.ALUMINIUM, MFTrimMaterials.ALUMINIUM),
                    new ItemModelGenerators.TrimMaterialData(MFMaterialAssetGroups.AZURITE, MFTrimMaterials.AZURITE),
                    new ItemModelGenerators.TrimMaterialData(MFMaterialAssetGroups.FLUORITE, MFTrimMaterials.FLUORITE),
                    new ItemModelGenerators.TrimMaterialData(MFMaterialAssetGroups.CARBON, MFTrimMaterials.CARBON),
                    new ItemModelGenerators.TrimMaterialData(MFMaterialAssetGroups.BISMUTH, MFTrimMaterials.BISMUTH)
            )
    );

    private MFItemModelGenerators(ItemModelGenerators itemModels) {
        this.itemModels = itemModels;
    }

    public static MFItemModelGenerators create(@NonNull ItemModelGenerators itemModels) {
        return new MFItemModelGenerators(itemModels);
    }

    public void customModelWithFlatInvTexture(Item item) {
        ItemModel.Unbaked flatModel = ItemModelUtils.plainModel(this.itemModels.createFlatItemModel(item, ModelTemplates.FLAT_ITEM));
        ItemModel.Unbaked inHandModel = ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(item, "_in_hand"));
        this.itemModels().itemModelOutput.accept(item, ItemModelGenerators.createFlatModelDispatch(flatModel, inHandModel));
    }

    public void generateTrimmableItem(Item armor, ResourceKey<EquipmentAsset> equipmentAssetId, Identifier slotTrimPrefix, boolean hasDyedLayer) {
        Identifier modelLocation = ModelLocationUtils.getModelLocation(armor);
        Material itemTexture = TextureMapping.getItemTexture(armor);
        Material overlayTexture = TextureMapping.getItemTexture(armor, "_overlay");
        List<SelectItemModel.SwitchCase<ResourceKey<TrimMaterial>>> cases = new ArrayList<>(TRIM_MATERIAL_MODELS.size());

        for (ItemModelGenerators.TrimMaterialData material : TRIM_MATERIAL_MODELS) {
            Identifier trimModelLocation = modelLocation.withSuffix("_" + material.assets().base().suffix() + "_trim");
            Material trimOverlayTexture = new Material(slotTrimPrefix.withSuffix("_" + material.assets().assetId(equipmentAssetId).suffix()));
            ItemModel.Unbaked trimModel;
            if (hasDyedLayer) {
                this.itemModels.generateLayeredItem(trimModelLocation, itemTexture, overlayTexture, trimOverlayTexture);
                trimModel = ItemModelUtils.tintedModel(trimModelLocation, new Dye(-6265536));
            } else {
                this.itemModels.generateLayeredItem(trimModelLocation, itemTexture, trimOverlayTexture);
                trimModel = ItemModelUtils.plainModel(trimModelLocation);
            }

            cases.add(ItemModelUtils.when(material.materialKey(), trimModel));
        }

        ItemModel.Unbaked untrimmedModel;
        if (hasDyedLayer) {
            ModelTemplates.TWO_LAYERED_ITEM.create(modelLocation, TextureMapping.layered(itemTexture, overlayTexture), this.itemModels().modelOutput);
            untrimmedModel = ItemModelUtils.tintedModel(modelLocation, new Dye(-6265536));
        } else {
            ModelTemplates.FLAT_ITEM.create(modelLocation, TextureMapping.layer0(itemTexture), this.itemModels().modelOutput);
            untrimmedModel = ItemModelUtils.plainModel(modelLocation);
        }

        this.itemModels().itemModelOutput.accept(armor, ItemModelUtils.select(new TrimMaterialProperty(), untrimmedModel, cases));
    }

    public ItemModelGenerators itemModels() {
        return this.itemModels;
    }
}
