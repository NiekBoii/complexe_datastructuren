package SortSearchTimeTesting;

import algoritmes.MergeSort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;


public class Executor<T> {

    public static <T> BigDecimal execute(Consumer<T> algorithm, T payLoad , int amountOfRunTimes){
        TestResult[] results = new TestResult[amountOfRunTimes];
        for(int i = 0; i < amountOfRunTimes; i++){
            long start = System.nanoTime();
            algorithm.accept(payLoad);
            long end = System.nanoTime();
            TestResult tr = new TestResult(end - start);
            results[i] = tr;
        }

        BigDecimal resultsCombined = new BigDecimal("0.0");
        for(TestResult result : results){
            System.out.println(result.getDurationInNanos() + " nanos.");
            resultsCombined = resultsCombined.add(new BigDecimal(result.getDurationInNanos()));
        }

        resultsCombined = resultsCombined.divide(new BigDecimal(amountOfRunTimes), RoundingMode.HALF_UP).divide(new BigDecimal(1000000000.0));

        return resultsCombined;
    }
}
