package SortSearchTimeTesting;

import algoritmes.BinarySearch;
import algoritmes.LinearSearch;
import algoritmes.MergeSort;
import algoritmes.SelectionSort;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class TimeResultTestApplication {

    private final int AMOUNT_OF_RUNTIMES = 4;
    private static final String N_TO_AVERAGE_IN_SECONDS = "N, Gemiddelde tijd in seconden";

    private static ResultSet selectionSortResults = new ResultSet("SelectionSortTimes", N_TO_AVERAGE_IN_SECONDS);
    private static ResultSet mergeSortTimes = new ResultSet("mergeSortTimes", N_TO_AVERAGE_IN_SECONDS);
    private static ResultSet testLinearSearchTimes = new ResultSet("linearSearchTestTimes", N_TO_AVERAGE_IN_SECONDS);
    private static ResultSet testBinarySearchTimes = new ResultSet("testBinarySearchTimes", N_TO_AVERAGE_IN_SECONDS);

    private static ArrayList<ResultSet> testResultSets = new ArrayList<>();

    @BeforeAll
    public static void initialize(){
        testResultSets.add(selectionSortResults);
        testResultSets.add(mergeSortTimes);
        testResultSets.add(testLinearSearchTimes);
        testResultSets.add(testBinarySearchTimes);
    }

    @ParameterizedTest
    @DisplayName("SelectionSortTimes.csv.")
    @ValueSource(ints = {10000, 20000, 40000, 80000, 160000})
    public void testSelectionSortTimes(int N) {
        Integer[] randomArray = generateRandomArray(N, false);
        BigDecimal timeInSeconds = Executor.execute((array) -> SelectionSort.sort(array), randomArray, AMOUNT_OF_RUNTIMES);
        selectionSortResults.addResult(new Result(timeInSeconds.doubleValue(), N));
    }

    @ParameterizedTest
    @DisplayName("MergeSortTimes")
    @ValueSource(ints = {200000, 400000, 800000, 1200000, 1600000, 2800000,  3200000, 6400000})
    public void testMergeSortTimes(int N) {
        Integer[] randomArray = generateRandomArray(N, false);
        BigDecimal timeInSeconds = Executor.execute((array) -> MergeSort.sort(array), randomArray, AMOUNT_OF_RUNTIMES);
        mergeSortTimes.addResult(new Result(timeInSeconds.doubleValue(), N));
    }

    @ParameterizedTest
    @DisplayName("testLinearSearchTimes")
    @ValueSource(ints = {10000, 20000, 80000, 160000, 320000})
    public void testLinearSearchTimes(int N) {
        Integer[] randomArray = generateInOrder(N);
        Integer lastElement = randomArray[randomArray.length - 1];
        BigDecimal timeInSeconds = Executor.execute((array) -> LinearSearch.search(Arrays.stream(array).iterator(), lastElement), randomArray, AMOUNT_OF_RUNTIMES);
        System.out.println(timeInSeconds);
        testLinearSearchTimes.addResult(new Result(timeInSeconds.doubleValue(), N));
    }

    @ParameterizedTest
    @DisplayName("testBinarySearchTimes.csv")
    @ValueSource(ints = {50000,1000000, 2000000, 8000000, 16000000, 32000000})
    public void testBinarySearchTimes(int N) {
        Integer[] sequenceArray = generateInOrder(N);
        BigDecimal timeInSeconds = Executor.execute((testArray) -> BinarySearch.search(testArray ,testArray.length + 20), sequenceArray, AMOUNT_OF_RUNTIMES);
        System.out.println(timeInSeconds);
        testBinarySearchTimes.addResult(new Result(timeInSeconds.doubleValue(), N));
    }

    @AfterAll
    public static void writeResultsToCsv() throws IOException {
        for(ResultSet resultSet : testResultSets){
            CsvWriter.toCsv(resultSet.toCsvString(), resultSet.getFileName());
        }
    }

    public static Integer[] generateRandomArray(int size, boolean sort) {
        Integer[] randomArray = new Integer[size];

        Random r = new Random();

        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = r.nextInt(0, 10000);
        }

        if(sort){
            return MergeSort.sort(randomArray);
        }
        return randomArray;
    }

    public static Integer[] generateInOrder(int size){
        Integer[] inOrderIntegers = new Integer[size];
        for(int i = 0; i < size; i++){
            inOrderIntegers[i] = i;
        }

        return inOrderIntegers;
    }
}

