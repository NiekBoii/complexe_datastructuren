package DatastructurenTests;

import datastructuren.LinkedList;
import datastructuren.MinHeap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MinHeapTests {

    private MinHeap<Integer> integerMinHeap;
    private MinHeap<Integer> emptyHeap;
    private LinkedList<Integer> integersForMinHeap = LinkedList.of(List.of(5,10,7,4,8,3));

    @BeforeEach
    public void init(){
        integerMinHeap = new MinHeap<>(integersForMinHeap.size());
        emptyHeap = new MinHeap<>(0);
        integersForMinHeap.forEach(integerMinHeap::push);
    }

    @Test
    public void testPopReturnsRightElement(){
        assertEquals(Integer.valueOf(3), integerMinHeap.pop());
    }

    @Test
    public void testIfCreatingExtraRoomAfterAddingWorks(){
        assertDoesNotThrow(() -> {
            emptyHeap.push(1);
        });
        assertEquals(1, emptyHeap.peek());
    }

    @Test
    public void testIfAddingWorksAtLengthOneSizeZero(){
        MinHeap<Integer> heap = new MinHeap<>(1);
        assertDoesNotThrow(() -> {
            heap.push(1);
        });
        assertEquals(1, heap.peek());
    }

    @Test
    public void testEmptyReturnsNullWhenPopped(){
        assertNull(emptyHeap.pop());
    }

    @Test
    public void testIfPeekReturnsRightObject(){
        assertEquals(Integer.valueOf(3), integerMinHeap.peek());
    }

    @Test
    public void testGraph(){
        assertEquals("Digraph CDS {\n" +
                "3 -> 5 -> 10 -> NULL1\n" +
                "10 -> NULL2\n" +
                "5 -> 8 -> NULL3\n" +
                "8 -> NULL4\n" +
                "3 -> 4 -> 7 -> NULL5\n" +
                "7 -> NULL6\n" +
                "4 -> NULL7\n" +
                "}", integerMinHeap.graphViz());
    }

    @Test
    public void testEmptyGraphViz(){
        assertEquals("""
                Digraph CDS {


                }""", new MinHeap<Integer>(1).graphViz());
    }
}
