package AlgoritmeTests;

import algoritmes.SelectionSort;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SelectionSortsTest {

    Integer[] smallArray = {3, 2, 5};
    Integer[] oneElementArray = {1};
    Integer[] nullArray = null;
    Integer[] emptyArray = {};

    @Test
    public void testIfSortingSmallArrayWorks(){
        Integer[] sorted = {2, 3, 5};
        assertIterableEquals(List.of(sorted), List.of(SelectionSort.sort(smallArray)));
    }

    @Test
    public void testThrowsWithNullArray(){
        assertThrows(IllegalArgumentException.class, () -> SelectionSort.sort(nullArray));
    }

    @Test
    public void testIfEmptyArrayReturnsTheArray(){
        assertNotNull(SelectionSort.sort(emptyArray));
    }

    @Test
    public void testIfOneElementArrayReturnsSorted(){
        assertIterableEquals(Arrays.asList(oneElementArray), List.of(SelectionSort.sort(oneElementArray)));
    }
}
