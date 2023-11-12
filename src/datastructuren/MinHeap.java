package datastructuren;

import interfaces.Heap;

import java.util.Arrays;

public class MinHeap<T extends Comparable<T>> implements Heap<T> {
    private T [] minHeap;
    private int size;

    public MinHeap(int size){
        minHeap = (T[]) new Comparable[size];
        this.size = 0;
    }

    private int getParentIndex(int childIndex){
        return (childIndex - 1) / 2;
    }

    private int getRightChildIndex(int parentIndex){
        return ( 2 * parentIndex ) + 2;
    }

    private int getLeftChildIndex(int parentIndex){
        return ( 2 * parentIndex ) + 1;
    }

    private boolean hasLeftChild(int index){
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index){
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index){
        return getParentIndex(index) >= 0;
    }


    public void push(T element){
        int minHeapLength = minHeap.length;
        if(size == 0){
            if(minHeapLength == 0){
                minHeap = (T[]) new Comparable[1];
            }
            minHeap[size++] = element;
        } else if(minHeapLength - 1 > size){
            minHeap[size++] = element;
            bubbleUp(size -1);
        } else{
            minHeap = Arrays.copyOf(minHeap, minHeapLength * 2);
            minHeap[size++] = element;
            bubbleUp(size - 1);
        }
    }

    private void bubbleUp(int childIndex){
        while(hasParent(childIndex) && minHeap[getParentIndex(childIndex)].compareTo(minHeap[childIndex]) > 0){
            swapElements(getParentIndex(childIndex), childIndex);
            childIndex = getParentIndex(childIndex);
        }
    }

    public T pop(){
        if(!isEmpty()) {
            T lowestElement = minHeap[0];
            swapElements(0, --size);
            bubbleDown(0);
            minHeap[size] = null;
            return lowestElement;
        }
        return null;
    }

    private void bubbleDown(int currentIndex) {
        while(hasLeftChild(currentIndex)){
            int smallerChildIndex = getLeftChildIndex(currentIndex);
            if(hasRightChild(currentIndex) && minHeap[getRightChildIndex(currentIndex)].compareTo(minHeap[getLeftChildIndex(currentIndex)] ) < 0){
                smallerChildIndex = getRightChildIndex(currentIndex);
            }

            if(minHeap[currentIndex].compareTo(minHeap[smallerChildIndex]) > 0){
                swapElements(currentIndex, smallerChildIndex);
            }

            currentIndex = smallerChildIndex;
        }
    }

    private void swapElements(int firstElement, int secondElement) {
        T copyOfChild = minHeap[firstElement];
        minHeap[firstElement] = minHeap[secondElement];
        minHeap[secondElement] = copyOfChild;
    }

    public T peek() {
        return minHeap.length >= 1 ? minHeap[0] : null;
    }

    private static int count;
    public String graphViz(){
        count = 0;
        return String.format("Digraph CDS {\n%s\n}", graphVizNodes(0));
    }

    private String graphVizNodes(int index){
        StringBuilder builder = new StringBuilder();
        if(minHeap.length == 0){
            return "";
        }

        String leftString;
        if(hasLeftChild(index)){
            leftString = minHeap[index] != null ? minHeap[index] + " -> " + graphVizNodes(getLeftChildIndex(index)) : "NULL" + (++count);
        } else{
            leftString =  minHeap[index] != null ? minHeap[index] + " -> NULL"  + (++count) : "";
        }

        builder.append(leftString);
        builder.append("\n");

        String rightString;
        if(hasRightChild(index)){
            rightString = minHeap[index] != null ? minHeap[index] + " -> " + graphVizNodes(getRightChildIndex(index)) : "NULL" + (++count);
        } else{
            rightString = minHeap[index] != null ? minHeap[index] + " -> NULL"  + (++count) : "";
        }
        builder.append(rightString);

        return builder.toString();
    }

    public boolean isEmpty(){
        return this.size == 0;
    }
}
