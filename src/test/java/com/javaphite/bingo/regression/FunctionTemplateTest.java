package com.javaphite.bingo.regression;

import com.javaphite.bingo.regression.functions.RegressionTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FunctionTemplateTest {

    private static final String TEST_CASES_DIR = "/test-cases/";

    private static final String GENERAL_EXPRESSIONS_CASES = "function-expressions.csv";

    @ParameterizedTest
    @CsvFileSource(resources = TEST_CASES_DIR + GENERAL_EXPRESSIONS_CASES,
            numLinesToSkip = 1)
    void toStringShouldReturnGeneralFunctionExpression(String functionType, String expectedExpression) {
        RegressionTemplate template = RegressionTemplate.valueOf(functionType);

        String actualExpression = template.toString();

        assertEquals(expectedExpression, actualExpression, "Invalid regression expression!");
    }
}
