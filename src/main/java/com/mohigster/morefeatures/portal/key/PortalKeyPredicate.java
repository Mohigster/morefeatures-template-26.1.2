package com.mohigster.morefeatures.portal.key;

@FunctionalInterface
public interface PortalKeyPredicate<T> {
    boolean test(PortalKey portalKey, T thing);
}
