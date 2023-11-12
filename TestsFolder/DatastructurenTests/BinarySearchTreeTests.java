package DatastructurenTests;

import algoritmes.SelectionSort;
import datastructuren.BinarySearchTree;
import datastructuren.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeTests {

    private BinarySearchTree<Integer> leftRootLeafTree;
    private BinarySearchTree<Integer> rootNodeLeafTree;
    private BinarySearchTree<Integer> rightRootLeafTree;
    private BinarySearchTree<Integer> rootLeafTree;

    private static final Integer five = 5;
    private static final Integer eleven = 11;
    private static final Integer one = 1;

    private static LinkedList<Integer> rootNodeLeafTreeIntegers = LinkedList.of(List.of(11, 10, 12, 5, 20, 44, 68, 50, 30, 15, 18, 14));
    private static LinkedList<Integer> leftRootLeafTreeIntegers = LinkedList.of(List.of(5, 3, 1));
    private static LinkedList<Integer> rightRootLeafTreeIntegers = LinkedList.of(List.of(1, 3, 5));

    @BeforeEach
    public void init(){
        leftRootLeafTree = new BinarySearchTree<>();
        rootNodeLeafTree = new BinarySearchTree<>();
        rightRootLeafTree = new BinarySearchTree<>();
        rootLeafTree = new BinarySearchTree<>();

        rootNodeLeafTreeIntegers = LinkedList.of(List.of(11, 10, 12, 5, 20, 44, 68, 50, 30, 15, 18, 14));
        leftRootLeafTreeIntegers = LinkedList.of(List.of(5, 3, 1));
        rightRootLeafTreeIntegers = LinkedList.of(List.of(1, 3, 5));

        leftRootLeafTreeIntegers.forEach(leftRootLeafTree::add);
        rootNodeLeafTreeIntegers.forEach(rootNodeLeafTree::add);
        rightRootLeafTreeIntegers.forEach(rightRootLeafTree::add);
        rootLeafTree.add(3);
        rootLeafTree.add(1);
    }

    @Test
    public void testRemovingRootWithLeftTreeOneDirectChild(){
        leftRootLeafTreeIntegers.remove(five);
        leftRootLeafTree.remove(5);
        assertEquals(leftRootLeafTree.size(), leftRootLeafTreeIntegers.size());
    }

    @Test
    public void testIfTreeIsvalidUsingGraphViz(){
        leftRootLeafTree = new BinarySearchTree<>();
        leftRootLeafTree.add(5);
        leftRootLeafTree.add(3);
        leftRootLeafTree.add(1);
        assertEquals("digraph CDS {\n" +
                "5 -> 3 -> 1 -> NULL3\n" +
                "1 -> NULL4\n" +
                "3 -> NULL5\n" +
                "5 -> NULL6\n" +
                "}", leftRootLeafTree.graphViz());
    }

    @Test
    public void testRemovingRootWithRightTreeOneDirectChild(){
        rightRootLeafTreeIntegers.remove(one);
        rightRootLeafTree.remove(one);
        assertEquals(rightRootLeafTree.size(), rightRootLeafTreeIntegers.size());
    }

    @Test
    public void testRemoveNodeWithChildAndParent(){
        rightRootLeafTree.remove(Integer.valueOf(3));
        assertEquals(2, rightRootLeafTree.size());
    }

    @Test
    public void testIfRemovingLeafWorks(){
        rootLeafTree.remove(1);
        assertEquals(1, rootLeafTree.size());
    }

    @Test
    public void testIfSearchingLeftSideOfTreeWorks(){
        assertTrue(rootNodeLeafTree.search(10));
    }

    @Test
    public void testIfRemovingOneELementWorksWithNoChildren(){
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>(1);
        integerBinarySearchTree.remove(1);
        assertEquals(0, integerBinarySearchTree.size());
    }

    @Test
    public void testIfRemovingWorksAfterRemovingNodeWithTwoChildrenReplacementHasNoChildren(){
        rootNodeLeafTree.remove(20);
        rootNodeLeafTreeIntegers.remove(Integer.valueOf(20));
        assertEquals(rootNodeLeafTreeIntegers.size(), rootNodeLeafTree.size());
    }

    @Test
    public void testIfRemovingWorksAfterRemovingNodeWithTwoChildrenReplacementHasChild(){
        rootNodeLeafTree.remove(18);
        rootNodeLeafTree.remove(20);
        Integer twenty = 20;
        rootNodeLeafTreeIntegers.remove(twenty);
        rootNodeLeafTreeIntegers.remove(Integer.valueOf(18));
        assertEquals(rootNodeLeafTreeIntegers.size(), rootNodeLeafTree.size());
    }

    @Test
    public void testIfHeightReturnsRightNumber(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.add(5);
        tree.add(3);
        tree.add(4);
        assertEquals(2, tree.height());
    }

    @Test
    public void testIfReturnsFalseWhenEmpty(){
        assertEquals(0, new BinarySearchTree<>().size());
    }

    @Test
    public void testReturnsFalseWhenTreeIsEmpty(){
        assertFalse(new BinarySearchTree<Integer>().remove(5));
    }

    @Test
    public void testIfIteratingInTraversalWorks(){
        LinkedList<Integer> list = rootNodeLeafTree.inOrderTraversal();
        assertIterableEquals(list, Arrays.stream(SelectionSort.sort(rootNodeLeafTreeIntegers.toArray(Integer.class))).toList());
    }

    @Test
    public void testContainsReturnsNullWhenNodeNotInTree(){
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
        integerBinarySearchTree.add(1);
        assertFalse(integerBinarySearchTree.contains(2));
    }

    @Test
    public void testContainsReturnsTrueWhenNodeInTree(){
        BinarySearchTree<Integer> integerBinarySearchTree = new BinarySearchTree<>();
        integerBinarySearchTree.add(2);
        assertTrue(integerBinarySearchTree.contains(2));
    }

    @Test
    public void testIfSearchReturnsFalseWhenNotInSearchTree(){
        assertFalse(rootNodeLeafTree.search(120000));
    }
}
