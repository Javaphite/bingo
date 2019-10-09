package com.javaphite.bingo.regression.functions;

import java.util.HashMap;
import java.util.Map;

public final class FormattingUtils {

    static final String FIRST_PARAMETER_PLACEHOLDER = "a₀";

    static final String SECOND_PARAMETER_PLACEHOLDER = "a₁";

    public static final String INDEPENDENT_VARIABLE_PLACEHOLDER = "n";

    static final Map<Character, Character> superscriptMapping = new HashMap<>();

    static {
        superscriptMapping.put('a', 'ᵃ');
        superscriptMapping.put('₁', '¹');
        superscriptMapping.put('1', '¹');
    }

    static String formatAsGeneralFunction(RegressionTemplate template) {
        return String.format(template.getFunctionStringRepresentation(),
                FIRST_PARAMETER_PLACEHOLDER,
                SECOND_PARAMETER_PLACEHOLDER,
                toSuperscript(SECOND_PARAMETER_PLACEHOLDER));
    }

    static String toSuperscript(String text) {
        char[] superscripted = text.toCharArray();
        for (int i = 0; i < superscripted.length; i++) {
            char character = superscripted[i];
            superscripted[i] = superscriptMapping.getOrDefault(character, character);
        }
        return String.valueOf(superscripted);
    }
}
