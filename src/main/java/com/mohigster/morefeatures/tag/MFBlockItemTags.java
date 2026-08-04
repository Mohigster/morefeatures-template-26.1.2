package com.mohigster.morefeatures.tag;

import com.mohigster.morefeatures.references.MFIdentifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;
import net.minecraft.tags.TagKey;

public class MFBlockItemTags {
    public static final BlockItemTagId WOOL_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/wool");
    public static final BlockItemTagId CONCRETE_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/concrete");
    public static final BlockItemTagId CUSTOM_WOODEN_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/custom_wooden");
    public static final BlockItemTagId CONCRETE_SLABS = morefeaturesTag("concrete_slabs");
    public static final BlockItemTagId CONCRETE_STAIRS = morefeaturesTag("concrete_stairs");
    public static final BlockItemTagId CUT_COPPER_VERTICAL_SLABS = morefeaturesTag("vertical_slabs/cut_copper");
    public static final BlockItemTagId CUT_COPPER_PILLARS = morefeaturesTag("pillars/cut_copper");
    public static final BlockItemTagId CONCRETE_PILLARS = morefeaturesTag("pillars/concrete");
    public static final BlockItemTagId MODDED_PLANKS = morefeaturesTag("wood/modded_planks");
    public static final BlockItemTagId BLOODWOOD_LOGS = morefeaturesTag("wood/logs/bloodwood");
    public static final BlockItemTagId BLOODWOOD = morefeaturesTag("wood/bloodwood");
    public static final BlockItemTagId TAINTED_LOGS = morefeaturesTag("wood/logs/tainted");
    public static final BlockItemTagId PALM_LOGS = morefeaturesTag("wood/logs/palm");
    public static final BlockItemTagId CHARRED_STEMS = morefeaturesTag("wood/logs/charred");
    public static final BlockItemTagId DECREPIT_LOGS = morefeaturesTag("wood/logs/decrepit");
    public static final BlockItemTagId PALLID_LOGS = morefeaturesTag("wood/logs/pallid");
    public static final BlockItemTagId WOODEN_STAIRS = morefeaturesTag("wood/stairs");
    public static final BlockItemTagId WOODEN_SLABS = morefeaturesTag("wood/slabs");
    public static final BlockItemTagId CUSTOM_WOODEN_FENCES = morefeaturesTag("wood/fence");
    public static final BlockItemTagId WOODEN_FENCE_GATES = morefeaturesTag("wood/fence_gate");

    private static BlockItemTagId morefeaturesTag(String name) {
        Identifier id = MFIdentifier.withMfNamespace(name);
        return create(id);
    }

    public static BlockItemTagId create(Identifier id) {
        return new BlockItemTagId(TagKey.create(Registries.BLOCK, id), TagKey.create(Registries.ITEM, id));
    }
}
