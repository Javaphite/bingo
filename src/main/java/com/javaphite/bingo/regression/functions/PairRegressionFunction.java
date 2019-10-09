package com.javaphite.bingo.regression.functions;


/**
 * Ternary function of two double arguments
 * (parameters of pair regression - a0 and a1)
 * and integer size of sample - n
 */

@FunctionalInterface
public interface PairRegressionFunction {

    /**
     * @param a0 first parameter of pair regression
     * @param a1 second parameter of pair regression
     * @param n  size of sample under analysis
     * @return   value of pair regression function
     */
    Double apply(Double a0, Double a1, Integer n);
}

