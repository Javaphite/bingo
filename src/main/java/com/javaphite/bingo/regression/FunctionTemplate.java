package com.javaphite.bingo.regression;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;

import static java.lang.StrictMath.log;
import static java.lang.StrictMath.pow;

public enum FunctionTemplate {
    LINEAR("O(n)",
            (a0, a1, n) -> a0 + a1 * n,
            a0 -> a0,
            a1 -> a1,
            y -> y,
            x -> x+0.0),
    LOG2("O(log2(n))",
            (a0, a1, n) -> a0 + a1 * log(n)/log(2),
            a0 -> a0,
            a1 -> a1,
            y -> y,
            x -> log(x)/log(2)),
    NLOG2("O(nlog2(n))",
            (a0, a1, n) -> a0 + a1 * n * log(n)/log(2),
            a0 -> a0,
            a1 -> a1,
            y -> y,
            x -> x * log(x)/log(2)),
    POLYNOME("O(n^k)",
            (a0, a1, n) -> a0 * pow(n, a1),
            a0 -> StrictMath.exp(log(a0)),
            a1 -> a1,
            y -> log(y),
            x -> log(x));

    private final DoubleFunction<Double> firstParameterTransformer;

    private final DoubleFunction<Double> secondParameterTransformer;

    private final DoubleFunction<Double> function;

    private final IntFunction<Double> variable;

    private final BiParameterFunction expressionTemplate;

    private final String category;

    FunctionTemplate(String category,
                     BiParameterFunction expressionTemplate,
                     DoubleFunction<Double> firstParameterTransformer,
                     DoubleFunction<Double> secondParameterTransformer,
                     DoubleFunction<Double> function,
                     IntFunction<Double> variable) {
        this.category = category;
        this.expressionTemplate = expressionTemplate;
        this.firstParameterTransformer = firstParameterTransformer;
        this.secondParameterTransformer = secondParameterTransformer;
        this.function = function;
        this.variable = variable;
    }

    public BiParameterFunction getExpressionTemplate() {
        return expressionTemplate;
    }

    @Override
    public String toString() {
        return null;
    }

    public String getBigONotationExpression() {
        return category;
    }

    public DoubleFunction<Double> getFirstParameterTransformer() {
        return firstParameterTransformer;
    }

    public DoubleFunction<Double> getSecondParameterTransformer() {
        return secondParameterTransformer;
    }

    public DoubleFunction<Double> getFunction() {
        return function;
    }

    public IntFunction<Double> getVariable() {
        return variable;
    }

    public String getCategory() {
        return category;
    }
}
