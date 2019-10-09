package com.javaphite.bingo.regression;

import com.javaphite.bingo.regression.functions.RegressionTemplate;

import java.util.function.IntFunction;

public class Expression {

    private RegressionTemplate template;

    private Double a0;

    private Double a1;

    private IntFunction<Double> expression;

    public Expression(RegressionTemplate template, Double a0, Double a1) {
        this.template = template;
        this.a0 = a0;
        this.a1 = a1;
        expression = n -> template.getExpressionTemplate().apply(a0, a1, n);
    }

    public IntFunction<Double> getExpression() {
        return expression;
    }

    @Override
    public String toString() {
        return new StringBuilder(template.getBigOCategory())
                .append(": a0=").append(a0)
                .append("; a1=").append(a1)
                .toString();
    }
}
