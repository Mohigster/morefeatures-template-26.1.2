package com.mohigster.morefeatures.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** This annotation indicates that a method is to only be permitted to be called within the specified array of classes */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RestrictedTo {
    Class<?>[] value();

    String exceptionMessage() default "";
}
