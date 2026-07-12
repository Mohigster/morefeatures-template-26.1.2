package com.mohigster.morefeatures.references;

import com.mohigster.morefeatures.MoreFeatures;
import net.minecraft.resources.Identifier;

public final class MFIdentifier {
    private static final String MF_NAMESPACE = MoreFeatures.MODID;

    private MFIdentifier() {}

    /**
     * This method works identically to vanilla's withDefaultNamespace method.
     * withDefaultNamespace sets the namespace to {@value Identifier#DEFAULT_NAMESPACE}, saving time
     * by only requiring a path to be passed in, instead of constantly having to pass in the same namespace.
     * withMfNamespace instead sets the namespace to {@value MF_NAMESPACE}, achieving the
     * same optimisation by eliminating the need to use fromNamespaceAndPath which requires a namespace parameter
     */

    public static Identifier withMfNamespace(String path) {
        return Identifier.fromNamespaceAndPath(MF_NAMESPACE, path);
    }

    public static boolean isNotMfNamespace(Identifier id) {
        return !id.getNamespace().equals(MF_NAMESPACE);
    }
}
