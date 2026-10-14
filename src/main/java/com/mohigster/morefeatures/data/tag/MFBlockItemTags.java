package com.mohigster.morefeatures.data.tag;

import com.mohigster.morefeatures.block.collection.wood.WoodTypeCollection;
import com.mohigster.morefeatures.data.resources.MFIdentifier;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import org.jspecify.annotations.NonNull;

import java.util.Objects;

@SuppressWarnings("SameParameterValue")
public class MFBlockItemTags {
    public static final BlockItemTagId WOOL_VERTICAL_SLABS = mfTag("vertical_slabs/wool");
    public static final BlockItemTagId CONCRETE_VERTICAL_SLABS = mfTag("vertical_slabs/concrete");
    public static final BlockItemTagId WOODEN_VERTICAL_SLABS = mfTag("vertical_slabs/wooden");
    public static final BlockItemTagId CUT_COPPER_VERTICAL_SLABS = mfTag("vertical_slabs/cut_copper");
    public static final BlockItemTagId CUT_COPPER_PILLARS = mfTag("pillars/cut_copper");
    public static final BlockItemTagId CONCRETE_PILLARS = mfTag("pillars/concrete");
    public static final BlockItemTagId MODDED_PLANKS = mfTag("wood/modded_planks");
    public static final BlockItemTagId WOODEN_STAIRS = mfTag("wood/stairs");
    public static final BlockItemTagId WOODEN_SLABS = mfTag("wood/slabs");
    public static final BlockItemTagId CUSTOM_WOODEN_FENCES = mfTag("wood/fence");
    public static final BlockItemTagId WOODEN_FENCE_GATES = mfTag("wood/fence_gate");
    public static final BlockItemTagId MODDED_LOGS = mfTag("wood/logs");

    public static final WoodTypeCollection<BlockItemTagId> LOGS = simpleMfWoodTag("logs", "");
    public static final WoodTypeCollection<BlockItemTagId> WOODEN = simpleMfWoodTag("");

    private static BlockItemTagId mfTag(String name) {
        Identifier id = MFIdentifier.withMfNamespace(name);
        return create(Objects.requireNonNull(id));
    }

    public static BlockItemTagId create(@NonNull Identifier id) {
        return BlockItemTagId.create(id, id);
    }

    private static WoodTypeCollection<BlockItemTagId> simpleMfWoodTag(String prefixExtension, String name) {
        String finalExtension = prefixExtension.isEmpty() ? "" : prefixExtension + "/";

        return WoodTypeCollection.prefixWithSet("wood/" + finalExtension, WoodTypeCollection.ofAll(name), false).map(MFBlockItemTags::mfTag);
    }

    private static WoodTypeCollection<BlockItemTagId> simpleMfWoodTag(String name) {
        return simpleMfWoodTag("", name);
    }
}
