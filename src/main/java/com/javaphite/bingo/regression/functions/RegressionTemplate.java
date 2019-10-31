package com.javaphite.bingo.regression.functions;

import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.IntFunction;

import static com.javaphite.bingo.regression.functions.TransformationUtils.toBinaryLogarithm;
import static java.lang.StrictMath.log;
import static java.lang.StrictMath.pow;

public enum RegressionTemplate {
    LINEAR(template -> template
            .setBigOCategory("O(n)")
            .setGeneralFunction("f(n) = %1$s + %2$s∙n")
            .setExpressionTemplate((a0, a1, n) -> a0 + a1 * n)),
    BINARY_LOGARITHM(template -> template
            .setBigOCategory("O(log₂n)")
            .setGeneralFunction("f(n) = %1$s + %2$s∙log₂n")
            .setExpressionTemplate((a0, a1, n) -> a0 + a1 * toBinaryLogarithm(n))
            .setArgumentTransformer(TransformationUtils::toBinaryLogarithm)),
    NBINARY_LOGARITHM(template -> template
            .setBigOCategory("O(n∙log₂n)")
            .setGeneralFunction("f(n) = %1$s + %2$s∙n∙log₂n")
            .setExpressionTemplate((a0, a1, n) -> a0 + a1 * n * toBinaryLogarithm(n))
            .setArgumentTransformer(n -> n * toBinaryLogarithm(n))),
    POLYNOMIAL(template -> template
            .setBigOCategory("O(nᵃ)")
            .setGeneralFunction("f(n) = %1$s∙n%3$s")
            .setExpressionTemplate((a0, a1, n) -> a0 * pow(n, a1))
            .setFirstParameterTransformer(a0 -> StrictMath.exp(log(a0)))
            .setFunctionTransformer(TransformationUtils::toNaturalLogarithm)
            .setArgumentTransformer(TransformationUtils::toNaturalLogarithm));

    private String bigOCategory;

    private String generalFunction;

    private PairRegressionFunction expressionTemplate;

    private DoubleFunction<Double> firstParameterTransformer = TransformationUtils::toSelf;

    private DoubleFunction<Double> secondParameterTransformer = TransformationUtils::toSelf;

    private DoubleFunction<Double> functionTransformer = TransformationUtils::toSelf;

    private IntFunction<Double> argumentTransformer = TransformationUtils::toDoubleValue;

    RegressionTemplate(Consumer<RegressionTemplate> configurator) {
        configurator.accept(this);
    }

    @Override
    public String toString() {
        return bigOCategory + ": " +
                FormattingUtils.formatAsGeneralFunction(this);
    }

    public String getBigOCategory() {
        return bigOCategory;
    }

    private RegressionTemplate setBigOCategory(String category) {
        bigOCategory = category;
        return this;
    }

    public String getGeneralFunction() {
        return generalFunction;
    }

    private RegressionTemplate setGeneralFunction(String generalFunction) {
        this.generalFunction = generalFunction;
        return this;
    }

    public PairRegressionFunction getExpressionTemplate() {
        return expressionTemplate;
    }

    private RegressionTemplate setExpressionTemplate(PairRegressionFunction expressionTemplate) {
        this.expressionTemplate = expressionTemplate;
        return this;
    }

    public DoubleFunction<Double> getFirstParameterTransformer() {
        return firstParameterTransformer;
    }

    private RegressionTemplate setFirstParameterTransformer(DoubleFunction<Double> firstParameterTransformer) {
        this.firstParameterTransformer = firstParameterTransformer;
        return this;
    }

    public DoubleFunction<Double> getSecondParameterTransformer() {
        return secondParameterTransformer;
    }

    private RegressionTemplate setSecondParameterTransformer(DoubleFunction<Double> secondParameterTransformer) {
        this.secondParameterTransformer = secondParameterTransformer;
        return this;
    }

    public DoubleFunction<Double> getFunctionTransformer() {
        return functionTransformer;
    }

    private RegressionTemplate setFunctionTransformer(DoubleFunction<Double> functionTransformer) {
        this.functionTransformer = functionTransformer;
        return this;
    }

    public IntFunction<Double> getArgumentTransformer() {
        return argumentTransformer;
    }

    private RegressionTemplate setArgumentTransformer(IntFunction<Double> argumentTransformer) {
        this.argumentTransformer = argumentTransformer;
        return this;
    }
}
