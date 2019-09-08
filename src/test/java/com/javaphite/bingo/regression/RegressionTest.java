package com.javaphite.bingo.regression;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.StrictMath.pow;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 1)
@Measurement(iterations = 3)
@Fork(value = 1, warmups = 0)
public class RegressionTest {

    @Param({"10", "30", "50", "80", "100", "200", "300", "400", "500", "1000", "10000"})
    public int n;

    List<Long> list;

    Regression sut = new Regression();

    public static void main(String[] args) throws RunnerException {
        Collection<RunResult> statistics = new Runner(new OptionsBuilder()
                .include(RegressionTest.class.getSimpleName())
                .build())
                .run();

        Collection<Expression> regressions = new Regression().evaluateAll(statistics);
        Expression mostSuitable =
                regressions.stream()
                        .min((r1, r2) -> (criteria(r1, statistics) - criteria(r2, statistics) > 0) ? 1 : -1)
                        .get();
        System.out.println(mostSuitable);
        regressions.forEach(r -> System.out.println(r + "\n" + criteria(r, statistics)));
    }

    @Setup
    public void setup() {
        list = new LinkedList<>();

        for (int i = 0; i < (n - 1); i++) {
            list.add(Math.round(Math.random()*n));
        }
    }

    @Benchmark
    public void benchmark() {
        list.sort(Long::compareTo);
    }

    private static double criteria(Expression regression, Collection<RunResult> statistics) {
        double criteria = 0;

        for (RunResult result : statistics) {
            int n = Integer.parseInt(result.getParams().getParam("n"));
            double diff = result.getPrimaryResult().getScore() - regression.getExpression().apply(n);
            criteria += pow(diff, 2);
        }

        return criteria;
    }
}