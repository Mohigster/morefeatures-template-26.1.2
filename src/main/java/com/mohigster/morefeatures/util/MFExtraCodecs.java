package com.mohigster.morefeatures.util;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;

import java.util.function.Function;

public class MFExtraCodecs {
    public static final Codec<Double> POSITIVE_DOUBLE = doubleRangeExclusiveWithMessage(n -> "Value must be positive: " + n);
    public static final Codec<Double> NON_NEGATIVE_DOUBLE = doubleRangeInclusiveWithMessage(n -> "Value must be non-negative: " + n);

    private static Codec<Double> doubleRangeExclusiveWithMessage(Function<Double, String> error) {
        return Codec.DOUBLE.validate(value ->
                value.compareTo(0.0) > 0 && value.compareTo(Double.MAX_VALUE) <= 0
                        ? DataResult.success(value) : DataResult.error(() -> error.apply(value))
        );
    }

    private static Codec<Double> doubleRangeInclusiveWithMessage(Function<Double, String> error) {
        return Codec.DOUBLE.validate(value ->
                value.compareTo(0.0) >= 0 && value.compareTo(Double.MAX_VALUE) <= 0
                        ? DataResult.success(value) : DataResult.error(() -> error.apply(value))
        );
    }
}
