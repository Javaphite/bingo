package com.javaphite.bingo.regression.functions;

import java.util.function.DoubleFunction;
import java.util.function.IntFunction;

import static com.javaphite.bingo.regression.functions.TransformationUtils.toBinaryLogarithm;
import static java.lang.StrictMath.log;
import static java.lang.StrictMath.pow;

public enum RegressionTemplate {
    LINEAR("O(n)",
            "f(n) = %1$s + %2$s∙n",
            (a0, a1, n) -> a0 + a1 * n,
            TransformationUtils::toSelf,
            TransformationUtils::toSelf,
            TransformationUtils::toSelf,
            TransformationUtils::toDoubleValue),
    BINARY_LOGARITHM("O(log₂n)",
            "f(n) = %1$s + %2$s∙log₂n",
            (a0, a1, n) -> a0 + a1 * toBinaryLogarithm(n),
            TransformationUtils::toSelf,
            TransformationUtils::toSelf,
            TransformationUtils::toSelf,
            TransformationUtils::toBinaryLogarithm),
    NBINARY_LOGARITHM("O(n∙log₂n)",
            "f(n) = %1$s + %2$s∙n∙log₂n",
            (a0, a1, n) -> a0 + a1 * n * toBinaryLogarithm(n),
            TransformationUtils::toSelf,
            TransformationUtils::toSelf,
            TransformationUtils::toSelf,
            n -> n * toBinaryLogarithm(n)),
    POLYNOMIAL("O(nᵃ)",
            "f(n) = %1$s∙n%3$s",
            (a0, a1, n) -> a0 * pow(n, a1),
            a0 -> StrictMath.exp(log(a0)),
            TransformationUtils::toSelf,
            TransformationUtils::toNaturalLogarithm,
            TransformationUtils::toNaturalLogarithm);


    private final DoubleFunction<Double> firstParameterTransformer;

    private final DoubleFunction<Double> secondParameterTransformer;

    private final DoubleFunction<Double> functionTransformer;

    private final IntFunction<Double> variableTransformer;

    private final PairRegressionFunction expressionTemplate;

    private final String bigOCategory;

    private final String functionStringRepresentation;

    RegressionTemplate(String bigOCategory,
                       String functionStringRepresentation,
                       PairRegressionFunction expressionTemplate,
                       DoubleFunction<Double> firstParameterTransformer,
                       DoubleFunction<Double> secondParameterTransformer,
                       DoubleFunction<Double> functionTransformer,
                       IntFunction<Double> variableTransformer) {
        this.bigOCategory = bigOCategory;
        this.functionStringRepresentation = functionStringRepresentation;
        this.expressionTemplate = expressionTemplate;
        this.firstParameterTransformer = firstParameterTransformer;
        this.secondParameterTransformer = secondParameterTransformer;
        this.functionTransformer = functionTransformer;
        this.variableTransformer = variableTransformer;
    }

    @Override
    public String toString() {
        return bigOCategory + ": " +
                FormattingUtils.formatAsGeneralFunction(this);
    }

    public PairRegressionFunction getExpressionTemplate() {
        return expressionTemplate;
    }

    public String getBigOCategory() {
        return bigOCategory;
    }

    public DoubleFunction<Double> getFirstParameterTransformer() {
        return firstParameterTransformer;
    }

    public DoubleFunction<Double> getSecondParameterTransformer() {
        return secondParameterTransformer;
    }

    public DoubleFunction<Double> getFunctionTransformer() {
        return functionTransformer;
    }

    public IntFunction<Double> getVariableTransformer() {
        return variableTransformer;
    }

    public String getFunctionStringRepresentation() {
        return functionStringRepresentation;
    }
}
