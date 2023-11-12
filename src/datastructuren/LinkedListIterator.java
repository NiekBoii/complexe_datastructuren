package datastructuren;

import java.util.Iterator;
import java.util.function.Consumer;

public class LinkedListIterator<T> implements Iterator<T> {
    private LinkedListNode<T> currentLinkedListNode;
    private LinkedListNode<T> headLinkedListNode;

    public LinkedListIterator(LinkedListNode<T> tailLinkedListNode, LinkedListNode<T> headLinkedListNode){
        this.currentLinkedListNode = tailLinkedListNode;
        this.headLinkedListNode = headLinkedListNode;
    }

    @Override
    public boolean hasNext () {
        return currentLinkedListNode.getNextNode() != headLinkedListNode;
    }

    @Override
    public T next () {
        T data = currentLinkedListNode.getNextNode().getData();
        currentLinkedListNode = currentLinkedListNode.getNextNode();
        return data;
    }

    @Override
    public void remove () {
        LinkedListNode<T> tempLinkedListNode = currentLinkedListNode;
        currentLinkedListNode.getPreviousNode().setNextNode(currentLinkedListNode.getNextNode());
        currentLinkedListNode.getNextNode().setPreviousNode(currentLinkedListNode.getPreviousNode());
    }

    @Override
    public void forEachRemaining (Consumer< ? super T > action){
        Iterator.super.forEachRemaining(action);
    }
}
