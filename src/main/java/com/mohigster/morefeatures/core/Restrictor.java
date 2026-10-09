package com.mohigster.morefeatures.core;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
// Called by AspectJ, so the restrictions work even though they are not manually called anywhere
@SuppressWarnings("unused")
public final class Restrictor {
    // Private constructor. verifyCaller() runs through AspectJ, creating an object and calling it manually is redundant
    private Restrictor() {}

    @Before("@annotation(allowedCallers)")
    public void verifyCaller(final JoinPoint jp, final RestrictedTo allowedCallers) {
        // Collect allowed classes from the annotation
        final Set<Class<?>> allowedSet = Arrays.stream(allowedCallers.value())
                .collect(Collectors.toSet());

        final Signature sign = jp.getSignature();

        // Identify the class calling this annotated method using StackWalker
        final Class<?> actualCaller = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(frames -> frames
                        .map(StackWalker.StackFrame::getDeclaringClass)
                        // Skip StackWalker itself, the aspect method, and the target method
                        .dropWhile(type -> type.equals(StackWalker.class)
                                || type.equals(this.getClass())
                                || type.equals(sign.getDeclaringType()))
                        .findFirst()
                        .orElseThrow(() -> new SecurityException("Could not identify calling class")));

        final String message = allowedCallers.exceptionMessage().isEmpty() ? "Class [" + actualCaller.getName() +
                "] is not authorized to call method [" + sign.getName() + "]" : allowedCallers.exceptionMessage();

        // Enforce restriction
        if (!allowedSet.contains(actualCaller)) {
            throw new UnauthorizedCallException(message);
        }
    }
}
