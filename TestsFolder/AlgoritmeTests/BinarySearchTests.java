package AlgoritmeTests;

import algoritmes.BinarySearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTests {

    String[] emptyArray;
    String[] oneStringElementArray;
    String oneStringElement;

    Integer[] smallIntegersArray;
    int outOfBoundsLeft;
    int outOfBoundsRight;
    int smallIntegersArrayMaxInteger;
    int smallintegersArrayMinInteger;


    @BeforeEach
    public void init(){
        emptyArray = new String[0];
        oneStringElement = "Element";
        oneStringElementArray = new String[]{oneStringElement};

        smallIntegersArray = new Integer[]{0, 1, 2, 3, 4, 5};

        if(smallIntegersArray.length >= 2){
            outOfBoundsLeft = smallIntegersArray[0] -1;
            outOfBoundsRight = smallIntegersArray[smallIntegersArray.length -1] + 1;
            smallIntegersArrayMaxInteger = smallIntegersArray[smallIntegersArray.length -1];
            smallintegersArrayMinInteger = smallIntegersArray[0];
        }
    }

    @Test
    public void testIfEmptyArrayReturnsNull(){
        assertThrows(IllegalArgumentException.class , () -> BinarySearch.search(emptyArray, null));
    }

    @Test
    public void testIfArrayWithLength0ReturnsNull(){
        assertNull(BinarySearch.search(emptyArray, "Dont make this target null."));
    }

    @Test
    public void testIfNulLArrayReturnsNull(){
        assertThrows(IllegalArgumentException.class, () -> BinarySearch.search(null, "Needle"));
    }

    @Test
    public void testIfArrayWithSizeOneReturnsRightElement(){
        assertEquals(oneStringElement, BinarySearch.search(oneStringElementArray, oneStringElement));
    }

    @Test
    public void testIfLeftArraySideReturnsRightElement(){
        assertEquals(smallintegersArrayMinInteger, BinarySearch.search(smallIntegersArray, smallintegersArrayMinInteger));
    }

    @Test
    public void testIfRightArraySideReturnsRightElement(){
        assertEquals(smallIntegersArrayMaxInteger, BinarySearch.search(smallIntegersArray, smallIntegersArrayMaxInteger));
    }

    @Test
    public void testIfTargetLeftOutOfBoundsCausesNullReturn(){
        Integer shouldBeNull = BinarySearch.search(smallIntegersArray, outOfBoundsLeft);
        assertNull(shouldBeNull);
    }

    @Test
    public void testIfTargetRightOutOfBoundsCausesNullReturn(){
        Integer shouldBeNull = BinarySearch.search(smallIntegersArray, outOfBoundsRight);
        assertNull(shouldBeNull);
    }
}
