package com.mohigster.morefeatures.item.custom.trim;

import com.mohigster.morefeatures.data.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.trim.MaterialAssetGroup;
import net.minecraft.world.item.equipment.trim.TrimMaterial;

public class MFTrimMaterials {
    public static final ResourceKey<TrimMaterial> ALUMINIUM = createId("aluminium");
    public static final ResourceKey<TrimMaterial> MAGNESIUM = createId("magnesium");
    public static final ResourceKey<TrimMaterial> AZURITE = createId("azurite");
    public static final ResourceKey<TrimMaterial> FLUORITE = createId("fluorite");
    public static final ResourceKey<TrimMaterial> CARBON = createId("carbon");
    public static final ResourceKey<TrimMaterial> BISMUTH = createId("bismuth");

    public static void bootstrap(BootstrapContext<TrimMaterial> context) {
        register(context, ALUMINIUM, Style.EMPTY.withColor(11259102), MFMaterialAssetGroups.ALUMINIUM);
        register(context, MAGNESIUM, Style.EMPTY.withColor(2636355), MFMaterialAssetGroups.MAGNESIUM);
        register(context, AZURITE, Style.EMPTY.withColor(6651320), MFMaterialAssetGroups.AZURITE);
        register(context, FLUORITE, Style.EMPTY.withColor(4380324), MFMaterialAssetGroups.FLUORITE);
        register(context, CARBON, Style.EMPTY.withColor(6513507), MFMaterialAssetGroups.CARBON);
        register(context, BISMUTH, Style.EMPTY.withColor(16738740), MFMaterialAssetGroups.BISMUTH);
    }

    private static void register(BootstrapContext<TrimMaterial> context, ResourceKey<TrimMaterial> registryKey, Style hoverTextStyle, MaterialAssetGroup assets) {
        Component description = Component.translatable(Util.makeDescriptionId("trim_material", registryKey.identifier())).withStyle(hoverTextStyle);
        context.register(registryKey, new TrimMaterial(assets, description));
    }

    private static ResourceKey<TrimMaterial> createId(String id){
        return ResourceKey.create(Registries.TRIM_MATERIAL, MFIdentifier.withMfNamespace(id));
    }
}
