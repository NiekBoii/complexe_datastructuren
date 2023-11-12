package DatastructurenTests;

import datastructuren.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoubleLinkedListTests {

    private LinkedList<Integer> integerLinkedList;
    private List<Integer> tempList;

    @BeforeEach
    public void init(){
        integerLinkedList = new LinkedList<>();
        tempList = new ArrayList<>();
    }

    @Test
    public void testIfGettingElementReturnsRightValue(){
        integerLinkedList = new LinkedList<>();
        integerLinkedList.add(1);
        assertEquals(1, integerLinkedList.get(0));
    }

    @Test
    public void testIfSizeOneReturnsCorrectElement(){
        Integer integer = 5;
        integerLinkedList.add(integer);
        assertEquals(1, integerLinkedList.size());
    }

    @Test
    public void testIfChangingElementValueWorks(){
        Integer integer = 5;
        integerLinkedList.add(integer);
        integerLinkedList.set(0, 10);
        assertEquals(10, integerLinkedList.get(0));
    }

    @Test
    public void addElementAtIndexZero(){
        Integer int1 = 5;
        Integer int2 = 3;
        integerLinkedList.add(int1);
        integerLinkedList.add(int2);
        integerLinkedList.add(0, 10);
        assertEquals(10, integerLinkedList.get(0));
    }

    @Test
    public void testIfAddingElementAtLastIndexWorks(){
        Integer int1 = 5;
        Integer int2 = 3;
        integerLinkedList.add(int1);
        integerLinkedList.add(int2);
        integerLinkedList.add(1, 10);
        assertEquals(10, integerLinkedList.get(1));
    }

    @Test
    public void testIfAddingElementInTheMiddleWorks(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.add(5);
        integerLinkedList.add(2, 5);
        assertEquals(5, integerLinkedList.get(2));
    }

    @Test
    public void testIfPrependInsertsRightPosition(){
        Integer int1 = 5;
        Integer int2 = 3;
        integerLinkedList.add(int1);
        integerLinkedList.add(int2);
        integerLinkedList.prepend(10);
        assertEquals(10, integerLinkedList.get(0));
    }

    @Test
    public void testIfGettingIndexOutOfBoundsThrowsException(){
        assertThrows(IndexOutOfBoundsException.class , () -> integerLinkedList.get(0));
    }

    @Test
    public void testIfIndexOfElementWithSizeOneReturnsRightNumber(){
        integerLinkedList.add(1);
        assertEquals(0, integerLinkedList.indexOf(1));
    }

    @Test
    public void testIfIndexOfElementWithMoreSizeReturnsRightNumber(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        assertEquals(2, integerLinkedList.indexOf(3));
    }

    @Test
    public void testIfIndexOfWithEmptyListReturnsMinusOne(){
        assertEquals(0, integerLinkedList.size());
        assertEquals(-1, integerLinkedList.indexOf(1));
    }

    @Test
    public void testIfIndexOfElementThatDoesNotExistReturnsMinOne(){
        assertEquals(-1, integerLinkedList.indexOf(1));
    }

    @Test
    public void testGetWithNegativeIndexThrowsOutOfBoundsException(){
        assertThrows(IndexOutOfBoundsException.class, () -> integerLinkedList.get(-1));
    }

    @Test
    public void testIfSettingNegativeIndexReturnsNull(){
        assertNull(integerLinkedList.set(-1, 1));
    }

    @Test
    public void testIfContainsReturnsTrueIfContainsElement(){
        integerLinkedList.add(1);
        assertTrue(integerLinkedList.contains(1));
    }

    @Test
    public void testIfContainsReturnsFalseIfDoesNotContainElement(){
        integerLinkedList.add(1);
        assertFalse(integerLinkedList.contains(2));
    }

    @Test
    public void testIfClearClearsList(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.clear();
        assertEquals(0, integerLinkedList.size());
    }

    @Test
    public void testIfRemoveExistingElementWorks(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(2);
        integerLinkedList.add(2);
        integerLinkedList.remove(1);
        assertEquals(3, integerLinkedList.size());
    }

    @Test
    public void testIfRemoveNonExistingElementReturnsFalse(){
        assertFalse(integerLinkedList.remove(1));
    }

    @Test
    public void testIfSizeReturns0WhenNoElements(){
        assertEquals(0, integerLinkedList.size());
    }

    @Test
    public void testIfRemoveObjectWorks(){
        integerLinkedList.add(2);
        integerLinkedList.add(1);
        Integer one = 1;
        integerLinkedList.remove(one);
        assertEquals(1, integerLinkedList.size());
    }

    @Test
    public void testIfRemoveObjectReturnsFalseWhenObjectDoesNotExist() {
        Integer one = 1;
        assertFalse(integerLinkedList.remove(one));
    }

    @Test
    public void testIfRemoveObjectReturnsFalseWhenListIsEmpty(){
        Integer one = 1;
        assertFalse(integerLinkedList.remove(one));
    }

    @Test
    public void testIfSettingElementWithIndexSmallerThan0ReturnsNull(){
        assertNull(integerLinkedList.set(-1, 1));
    }

    @Test
    public void testIfSettingElementAtLastWorks(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.set(2, 3);
        assertEquals(3, integerLinkedList.get(2));
    }

    @Test
    public void testIfSettingElementInMiddleWorks(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.set(1, 2);
        assertEquals(2, integerLinkedList.get(1));
    }

    @Test
    public void testSettingOutOfBoundsWithEmptyListReturnsNull(){
        Integer integer = 5;
        assertNull(integerLinkedList.set(4, integer));
    }


    @Test
    public void testIfToArrayWorks(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.add(3);
        integerLinkedList.add(4);
        Integer[] array = integerLinkedList.toArray(Integer.class);
        assertIterableEquals(Arrays.asList(array), integerLinkedList);
    }

    @Test
    public void testIfFromCreatingFromCollectionWithStaticMethodReturnsRight(){
        List<Integer> integers = List.of(3,5, 6, 7, 8, 9);
        LinkedList<Integer> testList = LinkedList.of(integers);
        assertIterableEquals(integers, testList);
    }

    @Test
    public void testIfCreatingFromArrayWithStaticMethodReturnsRight(){
        Integer[] integers = {3,5, 6, 7, 8, 9};
        LinkedList<Integer> testList = LinkedList.of(integers);
        assertIterableEquals(Arrays.asList(integers), testList);
    }

    @Test
    public void testIfConstructingFromCollectionReturnsRight(){
        List<Integer> integers = List.of(3,5, 6, 7, 8, 9);
        LinkedList<Integer> testList = new LinkedList<>(integers);
        assertIterableEquals(integers, testList);
    }

    @Test
    public void testForEach(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.forEach(integer -> tempList.add(integer));

        assertIterableEquals(integerLinkedList, tempList);
    }

    @Test
    public void testIfGetValueReturnsRightValue(){
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(1);
        integers.add(2);
        assertEquals(1, integers.get(Integer.valueOf(1)));
    }

    @Test
    public void testIfGetValueReturnsNullWhenNotInList(){
        LinkedList<Integer> integers = new LinkedList<>();
        assertNull(integers.get(Integer.valueOf(1)));
    }

    @Test
    public void testIfGetValueReturnsNullWhenListContainsElementButTheOneAskedForIsNot(){
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(4);
        assertNull(integers.get(Integer.valueOf(1)));
    }

    @Test
    public void testIfContainsReturnsFalseWhenElementNotInList(){
        integerLinkedList.add(1);
        assertFalse(integerLinkedList.contains(2));
    }

    @Test
    public void testIfGetValueReturnsNullWhenThereIsNoList(){
        LinkedList<Integer> integers = null;
        try {
            assertNull(integers.get(Integer.valueOf(1)));
        } catch(Exception exc){

        }
    }

    @Test
    public void testIfSpliteratorReturnsRightResults(){
        integerLinkedList.add(1);
        integerLinkedList.add(2);
        integerLinkedList.spliterator().forEachRemaining(integer -> tempList.add(integer));
        assertIterableEquals(integerLinkedList, tempList);
    }

    @Test
    public void testThrowsIllegalArgumentExceptionsWhenAddingNull(){
        LinkedList<Integer> list = new LinkedList<>();
        assertThrows( IllegalArgumentException.class, () -> list.add(null));
        assertThrows( IllegalArgumentException.class,() -> list.add(0, null));
        assertThrows( IllegalArgumentException.class,() -> list.prepend(null));
        assertThrows( IllegalArgumentException.class ,() -> list.append(null));
    }

    @Test
    public void testIfPollReturnsRightElement(){
        integerLinkedList.add(1);
        assertEquals(1, integerLinkedList.poll());
        assertNull(integerLinkedList.poll());
    }

    @Test
    public void testCompareTo(){
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        LinkedList<Integer> otherList = new LinkedList<>();
        assertTrue(list.compareTo(list) == 0);
        assertTrue(list.compareTo(otherList) > 0);
    }
}
