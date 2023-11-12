package AlgoritmeTests;

import static org.junit.jupiter.api.Test.*;

import algoritmes.MergeSort;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MergeSortTests {

    @Test
    public void testIfMergeSortSortsArrayBothLeftAndRight(){
        Integer[] data = {5, 6, 4, 3 ,2 , 1};
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        data = MergeSort.sort(data);
        assertArrayEquals(expected, data);
    }

    @Test
    public void testIfNullArrayThrows(){
        assertThrows(IllegalArgumentException.class, () -> MergeSort.sort(null));
    }

    @Test
    public void testIfSimpleRevertedArrayGetsSortedRight(){
        Integer[] data = {6, 5, 4, 3 ,2 , 1};
        Integer[] expected = {1, 2, 3, 4, 5, 6};
        data = MergeSort.sort(data);
        assertArrayEquals(expected, data);
    }

    @Test
    public void testIfEmptyArrayDoesNotThrowAndReturns(){
        Integer[] array = new Integer[]{};
        assertEquals(array, MergeSort.sort(array));
    }

    @Test
    public void testIfThrowsAssertionExceptionWhenArrayIsNull(){
        Integer[] data = null;
        Assertions.assertThrows(AssertionError.class, () -> {
            MergeSort.sort(data);
        });
    }
}
