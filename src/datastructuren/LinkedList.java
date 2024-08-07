package datastructuren;

import interfaces.List;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Consumer;

public class LinkedList<T extends Comparable<T>> implements List<T>, Iterable<T>, Comparable<LinkedList<T>>{

    private final LinkedListNode<T> headLinkedListNode;
    private final LinkedListNode<T> tailLinkedListNode;
    private int size;
    private LinkedListIterator<T> iterator;

    public LinkedList() {
        this.headLinkedListNode = new LinkedListNode<T>(null, null, null);
        this.tailLinkedListNode = new LinkedListNode<T>(null, null, null);

        this.headLinkedListNode.setPreviousNode(tailLinkedListNode);
        this.tailLinkedListNode.setNextNode(headLinkedListNode);
        size = 0;
    }

    public LinkedList(Collection<T> initialElements){
        this();
        for(T element: initialElements){
            add(element);
        }
    }

    public T poll(){
        if(size > 0){
            T element = get(0);
            remove(element);
            return element;
        }
        return null;
    }

    public static <T extends Comparable<T>> LinkedList<T> of(Collection<T> typeObjects) {
        LinkedList<T> newList = new LinkedList<>();
        for(T object : typeObjects){
            newList.add(object);
        }
        return newList;
    }

    public static <T extends Comparable<T>> LinkedList<T> of(T[] typeObjects){
        LinkedList<T> newList = new LinkedList<>();
        Arrays.stream(typeObjects).forEach(newList::add);
        return newList;
    }

    @Override
    public boolean add(T element) {
        return append(element);
    }

    public boolean prepend(T element){
        if(element == null){
            throw new IllegalArgumentException("You cannot pass null as an argument.");
        }
        LinkedListNode<T> newToBeAdded = new LinkedListNode<>(tailLinkedListNode, tailLinkedListNode.getNextNode(), element);

        tailLinkedListNode.getNextNode().setPreviousNode(newToBeAdded);
        tailLinkedListNode.setNextNode(newToBeAdded);

        size++;
        return true;
    }

    public boolean append(T element) {
        if(element == null){
            throw new IllegalArgumentException("You cannot pass null as an argument.");
        }
        LinkedListNode<T> newToBeAdded = new LinkedListNode<>(null, headLinkedListNode, element);

        if (!isEmpty()) {
            newToBeAdded.setPreviousNode(headLinkedListNode.getPreviousNode());
            headLinkedListNode.getPreviousNode().setNextNode(newToBeAdded);
        } else{
            newToBeAdded.setPreviousNode(tailLinkedListNode);
            tailLinkedListNode.setNextNode(newToBeAdded);
        }

        headLinkedListNode.setPreviousNode(newToBeAdded);
        size++;
        return true;
    }

    public void add(int index, T element) {
        if(element == null){
            throw new IllegalArgumentException("You cannot pass null as an argument.");
        }
        int customIndex = 0;
        int size = size();

        if(index >= size-1){
            LinkedListNode<T> currentElement = headLinkedListNode.getPreviousNode();
            LinkedListNode<T> newLinkedListNode = new LinkedListNode<>(currentElement.getPreviousNode(), currentElement, element);
            currentElement.getPreviousNode().setNextNode(newLinkedListNode);
            this.size++;
        } else if(index == 0){
            prepend(element);
        } else{
            LinkedListNode<T> currentLinkedListNode = tailLinkedListNode.getNextNode();
            while(currentLinkedListNode != tailLinkedListNode && currentLinkedListNode != headLinkedListNode){
                if(customIndex == index){
                    LinkedListNode<T> newToBeAdded = new LinkedListNode<>(currentLinkedListNode.getPreviousNode(), currentLinkedListNode, element);

                    currentLinkedListNode.getPreviousNode().setNextNode(newToBeAdded);
                    currentLinkedListNode.setPreviousNode(newToBeAdded);
                    this.size++;
                    return;
                }
                currentLinkedListNode = currentLinkedListNode.getNextNode();
                customIndex++;
            }
        }
    }

    @Override
    public void clear() {
        headLinkedListNode.setNextNode(null);
        headLinkedListNode.setPreviousNode(tailLinkedListNode);

        tailLinkedListNode.setNextNode(headLinkedListNode);
        tailLinkedListNode.setPreviousNode(null);
        size = 0;
    }

    @Override
    public boolean contains(T element) {
        if(!isEmpty()) {
            LinkedListNode<T> currentLinkedListNode = tailLinkedListNode.getNextNode();
            for(T object : this){
                if(object.compareTo(element) == 0){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public T get(int index) {
        if(index + 1 > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        int customIndex = 0;
        for (T object : this) {
            if (customIndex == index) {
                return object;
            }
            customIndex++;
        }
        return null;
    }

    @Override
    public int indexOf(T element) {
        int customIndex = 0;
        if(!isEmpty()) {
            for(T object : this){
                if(object.compareTo(element) == 0){
                    return customIndex;
                }
                customIndex++;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty(){
        return headLinkedListNode.getPreviousNode() == tailLinkedListNode && tailLinkedListNode.getNextNode() == headLinkedListNode;
    }

    public T[] toArray(Class<T> clazz) {
        T[] newArray = (T[]) Array.newInstance(clazz, size());

        int index = 0;
        for(T object : this){
            newArray[index] = object;
            ++index;
        }
        return newArray;
    }

    @Override
    public int size() {
        return this.size;
    }

    public boolean remove(T element){
        if(!isEmpty()) {
            iterator = iterator();
            while(iterator.hasNext()){
                if(iterator.next().compareTo(element) == 0){
                    iterator.remove();
                    --size;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @param index the index of the element to be removed
     * @return Does not return the object but true when succesful false when unsuccesful.
     */
    @Override
    public boolean remove(int index) {
        int customIndex = 0;
        if(!isEmpty()) {
            iterator = iterator();
            while(iterator.hasNext()){
                iterator.next();
                if(customIndex == index){
                    iterator.remove();
                    --size;
                    return true;
                }
                customIndex++;
            }
        }
        return false;
    }

    public T set(int index, T element) {
        int customIndex = 0;
        if(index < 0){
            return null;
        }

        if(!isEmpty()){
            if(index == size()-1){
                T data = headLinkedListNode.getPreviousNode().getData();
                headLinkedListNode.getPreviousNode().setData(element);
                return data;
            } else{
                iterator = iterator();
                LinkedListNode<T> currentLinkedListNode = tailLinkedListNode;
                while(iterator.hasNext()){
                    if(customIndex == index){
                        T data = iterator.next();
                        currentLinkedListNode.setData(element);
                        return data;
                    }
                    currentLinkedListNode = currentLinkedListNode.getNextNode();
                    customIndex++;
                }
            }
        }
        return null;
    }

    public String graphViz(){
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("digraph CDS {\n%s\n}", graphVizNodes()));
        return builder.toString();
    }

    public String graphVizNodes(){
        String toString = "";
        LinkedListIterator<T> iterator = iterator();
        T element = iterator.next();
        while(element != null){
            toString += element.toString();
            if(iterator.hasNext()) {
                toString += " -> ";
            }
            element = iterator.next();
        }
        return toString;
    }

    public T get(T value){
        for(T element : this){
            if(element.compareTo(value) == 0){
                return element;
            }
        }
        return null;
    }

    @Override
    public LinkedListIterator<T> iterator() {
        return new LinkedListIterator<>(tailLinkedListNode, headLinkedListNode);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<T> spliterator() {
        return Iterable.super.spliterator();
    }

    @Override
    public int compareTo(LinkedList<T> otherList) {
        return this.equals(otherList) ? 0 : Integer.compare(this.size, otherList.size);
    }

    @Override
    public String toString(){
    StringBuilder builder = new StringBuilder();
      for(T object : this){
          builder.append(object +"\n");
      }
    return builder.toString();
    }
}


