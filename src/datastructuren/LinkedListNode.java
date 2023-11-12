package datastructuren;

public class LinkedListNode<T> {
    private LinkedListNode<T> previousLinkedListNode;
    private LinkedListNode<T> nextLinkedListNode;
    private T data;

    public LinkedListNode(LinkedListNode<T> previousLinkedListNode, LinkedListNode<T> nextLinkedListNode, T data){
        this.previousLinkedListNode = previousLinkedListNode;
        this.nextLinkedListNode = nextLinkedListNode;
        this.data = data;
    }

    public LinkedListNode<T> getPreviousNode() {
        return previousLinkedListNode;
    }

    public LinkedListNode<T> getNextNode() {
        return nextLinkedListNode;
    }

    public T getData() {
        return data;
    }

    public void setPreviousNode(LinkedListNode<T> previousLinkedListNode) {
        this.previousLinkedListNode = previousLinkedListNode;
    }

    public void setNextNode(LinkedListNode<T> nextLinkedListNode) {
        this.nextLinkedListNode = nextLinkedListNode;
    }

    public void setData(T data) {
        this.data = data;
    }

}

