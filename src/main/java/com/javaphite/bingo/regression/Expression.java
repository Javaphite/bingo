package com.javaphite.bingo.regression;

import java.util.function.IntFunction;

public class Expression {

    private FunctionTemplate template;

    private Double a0;

    private Double a1;

    private IntFunction<Double> expression;

    public Expression(FunctionTemplate template, Double a0, Double a1) {
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
        return new StringBuilder(template.getBigONotationExpression())
                .append(": a0=").append(a0)
                .append("; a1=").append(a1)
                .toString();
    }
}
