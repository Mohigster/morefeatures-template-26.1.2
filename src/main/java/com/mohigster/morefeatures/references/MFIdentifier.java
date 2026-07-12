package com.mohigster.morefeatures.references;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.resources.Identifier;

public final class MFIdentifier {
    private static final String MF_NAMESPACE = MoreFeatures.MODID;

    private MFIdentifier() {}

    public static Identifier withMfNamespace(String path) {
        return Identifier.fromNamespaceAndPath(MF_NAMESPACE, path);
    }

    public static boolean isNotMfNamespace(Identifier id) {
        return !id.getNamespace().equals(MF_NAMESPACE);
    }
}
