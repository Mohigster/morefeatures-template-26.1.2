package com.mohigster.morefeatures.references;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.resources.Identifier;

public final class MFIdentifier {

    private MFIdentifier() {}

    public static Identifier withMfNamespace(String path) { // This method is named after vanilla's withDefaultNamespace method
        return Identifier.fromNamespaceAndPath(MoreFeatures.MODID, path);
    }

    public static boolean isNotMfNamespace(Identifier id) {
        return !id.getNamespace().equals(MoreFeatures.MODID);
    }
}
