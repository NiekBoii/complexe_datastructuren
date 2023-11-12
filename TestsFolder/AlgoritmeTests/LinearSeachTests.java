package AlgoritmeTests;

import algoritmes.LinearSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class LinearSeachTests {

    private String[] smallStringArray;
    @BeforeEach
    public void init(){
        smallStringArray = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
    }

    @Test
    public void testIfTargetCanBeFoundInList(){
        assertEquals("A", LinearSearch.search(Arrays.stream(smallStringArray).iterator(), "A"));
    }

    @Test
    public void testIfNullTargetReturnsNull(){
        assertThrows(IllegalArgumentException.class, () -> LinearSearch.search(Arrays.stream(smallStringArray).iterator(), null));
    }

    @Test
    public void testIfElementNotInListReturnsNull(){
        assertNull(LinearSearch.search(Arrays.stream(smallStringArray).iterator(), "a"));
    }
}
