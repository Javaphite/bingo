package com.javaphite.bingo.regression;

import com.javaphite.bingo.regression.functions.RegressionTemplate;
import org.openjdk.jmh.results.RunResult;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.StrictMath.pow;

public class Regression {

    public Collection<Expression> evaluateAll(Collection<RunResult> statistics) {
        List<Expression> regressions = new ArrayList<>(RegressionTemplate.values().length);

        for (RegressionTemplate template : RegressionTemplate.values()) {
            regressions.add(evaluate(template, statistics));
        }

        return regressions;
    }

    private Expression evaluate(RegressionTemplate template, Collection<RunResult> statistics) {
        double sumX = 0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;

        int k = statistics.size();

        for (RunResult result : statistics) {
            Double x = template.getArgumentTransformer().apply(Integer.parseInt(result.getParams().getParam("n")));
            Double y = template.getFunctionTransformer().apply(result.getPrimaryResult().getScore());

            sumX += x;
            sumY += y;
            sumXY += y * x;
            sumX2 += x * x;
        }

        double a1 = (k * sumXY - sumX * sumY) / (k * sumX2 - pow(sumX, 2));
        double a0 = (sumY - a1 * sumX) / k;
        a0 = template.getFirstParameterTransformer().apply(a0);
        a1 = template.getSecondParameterTransformer().apply(a1);

        return new Expression(template, a0, a1);
    }
}
