package com.javaphite.bingo.regression.functions;

import static java.lang.StrictMath.log;

public final class TransformationUtils {

    public static <T extends Number> T toSelf(T value) {
        return value;
    }

    public static <T extends Number> Double toDoubleValue(T value) {
        return value.doubleValue();
    }

    public static <T extends Number> Double toNaturalLogarithm(T value) {
        return log(toDoubleValue(value));
    }

    public static <T extends Number> Double toBinaryLogarithm(T value) {
        return log(toDoubleValue(value)) / log(2);
    }
}
