package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;

@SuppressWarnings("SameParameterValue")
public class MFBlockItemTags {
    public static final BlockItemTagId WOOL_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/wool");
    public static final BlockItemTagId CONCRETE_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/concrete");
    public static final BlockItemTagId WOODEN_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/wooden");
    public static final BlockItemTagId CUT_COPPER_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/cut_copper");
    public static final BlockItemTagId CUT_COPPER_PILLARS = morefeaturesTag("pillars/cut_copper");
    public static final BlockItemTagId CONCRETE_PILLARS = morefeaturesTag("pillars/concrete");
    public static final BlockItemTagId MODDED_PLANKS = morefeaturesTag("wood/modded_planks");
    public static final BlockItemTagId WOODEN_STAIRS = morefeaturesTag("wood/stairs");
    public static final BlockItemTagId WOODEN_SLABS = morefeaturesTag("wood/slabs");
    public static final BlockItemTagId CUSTOM_WOODEN_FENCES = morefeaturesTag("wood/fence");
    public static final BlockItemTagId WOODEN_FENCE_GATES = morefeaturesTag("wood/fence_gate");
    public static final BlockItemTagId MODDED_LOGS = morefeaturesTag("wood/logs");

    public static final WoodTypeCollection<BlockItemTagId> LOGS = simpleMorefeaturesWoodTag("logs", "");
    public static final WoodTypeCollection<BlockItemTagId> WOODEN = simpleMorefeaturesWoodTag("");

    private static BlockItemTagId morefeaturesTag(String name) {
        Identifier id = MFIdentifier.withMfNamespace(name);
        return create(id);
    }

    public static BlockItemTagId create(Identifier id) {
        return BlockItemTagId.create(id, id);
    }

    private static WoodTypeCollection<BlockItemTagId> simpleMorefeaturesWoodTag(String prefixExtension, String name) {
        String finalExtension = prefixExtension.isEmpty() ? "" : prefixExtension + "/";

        return WoodTypeCollection.prefixWithSet("wood/" + finalExtension, WoodTypeCollection.createForAll(name), false).map(MFBlockItemTags::morefeaturesTag);
    }

    private static WoodTypeCollection<BlockItemTagId> simpleMorefeaturesWoodTag(String name) {
        return simpleMorefeaturesWoodTag("", name);
    }
}
