package com.mohigster.morefeatures.data.references;

import net.minecraft.resources.Identifier;

import static com.mohigster.morefeatures.MoreFeatures.MODID;
public final class MFIdentifier {
    private MFIdentifier() {}

    public static Identifier withMfNamespace(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }

    public static boolean isNotMfNamespace(Identifier id) {
        return !id.getNamespace().equals(MODID);
    }
}
