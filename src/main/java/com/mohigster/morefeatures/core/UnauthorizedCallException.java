package com.mohigster.morefeatures.core;

/**
 * This exception is thrown when a method annotated with {@link RestrictedTo} is called in a class that does not have permission to do so
 */
final class UnauthorizedCallException extends SecurityException {
    UnauthorizedCallException(String message) {
        super(message);
    }
}
