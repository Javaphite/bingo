package com.javaphite.bingo.regression;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTemplateTest {

    @ParameterizedTest
    @CsvFileSource(resources = "/test-cases/function-expressions.csv")
    void toStringShouldReturnGeneralFunctionExpression(String function, String expression) {
        FunctionTemplate template = FunctionTemplate.valueOf(function);

        String actualResult = template.toString();

        assertEquals(expression, actualResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/test-cases/bigo-notation-categories.csv")
    void getBigONotationExpressionShouldReturnRelevantValue(String function, String bigOCategory) {
        FunctionTemplate template = FunctionTemplate.valueOf(function);

        String actualResult = template.getBigONotationExpression();

        assertEquals(bigOCategory, actualResult);
    }

}
