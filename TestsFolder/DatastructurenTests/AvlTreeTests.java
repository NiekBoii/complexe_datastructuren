package DatastructurenTests;

import datastructuren.AvlTree;
import datastructuren.Graph;
import datastructuren.LinkedList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AvlTreeTests {

    private AvlTree<Integer> avlTree = new AvlTree<>();
    private LinkedList<Integer> integersForAvlTree = LinkedList.of(List.of(40, 20, 10, 50, 80, 45, 120, 140, 35, 29 ,23, 22, 53, 101, 109, 83));

    @BeforeEach
    public void init(){
        integersForAvlTree.forEach(avlTree::add);
    }

    @Test
    public void emptyAvlTree(){
        AvlTree<Integer> integers = new AvlTree<>();
        assertEquals(0, integers.size());
        assertTrue(integers.isEmpty());
    }

    @Test
    public void checkIfContainsReturnsFalseWhenNotInList(){
        AvlTree<Integer> integers = new AvlTree<>();
        assertFalse(integers.contains(1));
    }

    @Test
    public void testIfAddingNulLElementThrowsIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> new AvlTree<Integer>().add(null));
    }

    @Test
    public void testValidStructure(){
        avlTree.add(5);
        avlTree.add(3);
        avlTree.add(8);
        assertTrue(avlTree.isValidTreeStructure());
    }

    @Test
    public void assertThrowsWhenTryingToFindNull(){
        assertThrows(AssertionError.class, () -> avlTree.get(null));
    }

    @Test
    public void testIfGettingWorks(){
        assertEquals(22, avlTree.get(22));
    }

    @Test
    public void testIfFindReturnsNullWhenNotInThere(){
        assertNull(avlTree.get(2000000000));
    }

    @Test
    public void testLeftRightCase(){
        AvlTree<Integer> smallTree = new AvlTree<>();
        LinkedList<Integer> integers = new LinkedList<>(List.of(3,4,5));
        smallTree.add(5);
        smallTree.add(3);
        smallTree.add(4);
        System.out.println(smallTree.graphViz());
        assertEquals("digraph CDS {\n" +
                "4 -> 3 -> NULL2\n" +
                "3 -> NULL3\n" +
                "4 -> 5 -> NULL5\n" +
                "5 -> NULL6\n" +
                "}", smallTree.graphViz());
    }

    @Test
    public void testRightLeftCase(){
        AvlTree<Integer> smallTree = new AvlTree<>();
        LinkedList<Integer> integers = new LinkedList<>(List.of(3,4,5));
        smallTree.add(3);
        smallTree.add(5);
        smallTree.add(4);
        System.out.println(smallTree.graphViz());
        assertEquals("digraph CDS {\n" +
                "4 -> 3 -> NULL2\n" +
                "3 -> NULL3\n" +
                "4 -> 5 -> NULL5\n" +
                "5 -> NULL6\n" +
                "}", smallTree.graphViz());
    }

    @Test
    public void testLeftLeftCase(){
        AvlTree<Integer> smallTree = new AvlTree<>();
        LinkedList<Integer> integers = new LinkedList<>(List.of(3,4,5));
        smallTree.add(5);
        smallTree.add(4);
        smallTree.add(3);
        assertEquals("digraph CDS {\n" +
                "4 -> 3 -> NULL2\n" +
                "3 -> NULL3\n" +
                "4 -> 5 -> NULL5\n" +
                "5 -> NULL6\n" +
                "}", smallTree.graphViz());
    }

    @Test
    public void testRightRightCase(){
        AvlTree<Integer> smallTree = new AvlTree<>();
        LinkedList<Integer> integers = new LinkedList<>(List.of(3,4,5));
        smallTree.add(3);
        smallTree.add(4);
        smallTree.add(5);
        System.out.println(smallTree.graphViz());
        assertEquals("digraph CDS {\n" +
                "4 -> 3 -> NULL2\n" +
                "3 -> NULL3\n" +
                "4 -> 5 -> NULL5\n" +
                "5 -> NULL6\n" +
                "}", smallTree.graphViz());
    }

    @Test
    public void testParentWithSubtreeInMainTree(){
        AvlTree<Integer> parentWithSubTree = new AvlTree<>();
        parentWithSubTree.add(4);
        parentWithSubTree.add(5);
        parentWithSubTree.add(3);
        parentWithSubTree.add(2);
        parentWithSubTree.add(1);
        System.out.println(parentWithSubTree.graphViz());
    }

    @Test
    public void testInOrderTraversalReturnsRightList(){
        LinkedList<Integer> integers = LinkedList.of(List.of(5, 14, 18,24, 30, 34, 40, 43, 48, 50));
        AvlTree<Integer> integerAvlTree = new AvlTree<>(5);
        integerAvlTree.add(30);
        integerAvlTree.add(18);
        integerAvlTree.add(24);
        integerAvlTree.add(40);
        integerAvlTree.add(14);
        integerAvlTree.add(34);
        integerAvlTree.add(43);
        integerAvlTree.add(48);
        integerAvlTree.add(50);
        assertIterableEquals(integers, integerAvlTree.inOrderTraversal());
    }

    @Test
    public void testInOrderTraversalString(){
        AvlTree<Integer> tree = new AvlTree();
        tree.add(2);
        tree.add(4);
        tree.add(5);
        tree.add(6);
        tree.add(10);
        tree.add(20);
        assertEquals("{ 2, 4, 5, 6, 10, 20}", tree.inOrderTraversalString());
    }

    @Test
    public void testUnevenTraversal(){
        AvlTree<Integer> tree = new AvlTree();
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(10);
        assertEquals("{ 2, 4, 6, 10}", tree.inOrderTraversalString());
    }

    @Test
    public void testEmptyTraversal(){
        AvlTree<Integer> tree = new AvlTree();
        assertEquals("{ }", tree.inOrderTraversalString());
    }

    @Test
    public void testAddingDuplicateElements(){
        AvlTree<Integer> tree = new AvlTree();
        tree.add(1);
        assertThrows(IllegalArgumentException.class, () -> tree.add(1));
    }
}
