package datastructuren;

import algoritmes.LinearSearch;
import interfaces.HashTable;

import java.util.Arrays;

public class SeperateChainingHashTable<K extends Comparable<K>, V extends Comparable<V>> implements HashTable<K, V>{
    private int capacity;
    private int size;

    private LinkedList<MyEntry<K, V>>[] entries;

    public SeperateChainingHashTable(int capacity){
        if(capacity == 0){
            throw new IllegalArgumentException("Capacity must be of value higher then 0 for the HashTable to function properly.");
        }
        this.capacity = capacity;
        entries = (LinkedList<MyEntry<K, V>>[]) new LinkedList[capacity];
        size = 0;
    }

    public void put(K key, V value){
        assert key!=null && value!=null : "Key or value is null";
        checkIfKeyIsNull(key);
        if(contains(key)){
            throw new IllegalArgumentException("HashTable cannot contain duplicate keys!");
        }

        int index = getHashIndex(key);
        MyEntry<K, V> newEntry = new MyEntry<>(key, value);
        if (entries[index] == null) {
            entries[index] = new LinkedList<>();
        }

        entries[index].add(new MyEntry<>(key, value));
        size++;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public V get(K key){
        checkIfKeyIsNull(key);

        int chainIndex = getHashIndex(key);
        MyEntry<K, V> possibleEntry = new MyEntry<>(key, null);

        LinkedList<MyEntry<K, V>> seperateChainList = entries[chainIndex];
        if(seperateChainList == null){
            return null;
        } else{
            MyEntry<K, V> entry = LinearSearch.search(seperateChainList.iterator(), possibleEntry);
            return entry != null ? entry.getValue() : null;
        }
    }

    public V remove(K key) {
        checkIfKeyIsNull(key);
        int chainIndex = getHashIndex(key);
        MyEntry<K, V> possibleEntry = new MyEntry<>(key, null);

        // 2 elementen in ketting.
        LinkedList<MyEntry<K, V>> chainList = entries[chainIndex];
        if (chainList == null) {
            return null;
        } else {
            V value = getValue(chainList, possibleEntry);
            if (value != null) {
                chainList.remove(possibleEntry);
                if (chainList.size() == 0) {
                    entries[chainIndex] = null;
                    size--;
                }
                return value;
            }
        }
        return null;
    }

    public void clear(){
        this.entries = (LinkedList<MyEntry<K, V>>[]) new LinkedList[capacity];
        this.size = 0;
    }

    public void checkIfKeyIsNull(K key) throws IllegalArgumentException{
        if(key == null){
            throw new IllegalArgumentException("Key cannot be null");
        }
    }

    @Override
    public boolean contains(K key){
        return size != 0 && get(key) != null;
    }

    public LinkedList<MyEntry<K, V>> entrySet(){
        LinkedList<MyEntry<K, V>> entrySet = (LinkedList<MyEntry<K, V>>) new LinkedList<MyEntry<K, V>>();

        for (LinkedList<MyEntry<K, V>> myEntries : entries) {
            if (myEntries != null) {
                for (MyEntry<K, V> entry : myEntries) {
                    entrySet.add(entry);
                }
            }
        }
        return entrySet;
    }

    public LinkedList<V> values(){
        LinkedList<V> values = new LinkedList<>();

        for(LinkedList<MyEntry<K, V>> myEntries : entries){
            if(myEntries != null){
                for(MyEntry<K, V> entry : myEntries){
                    values.add(entry.getValue());
                }
            }
        }
        return values;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(MyEntry<K, V> myEntry : entrySet()){
            builder.append(String.format("{%s %s}", myEntry.getKey(), myEntry.getValue()));
        }
        return builder.toString();
    }

    public int size(){
        return this.size;
    }

    private int getHashIndex(K key){
        return Math.abs(key.hashCode()) % capacity;
    }

    private V getValue(LinkedList<MyEntry<K, V>> seperateChainList, MyEntry<K, V> entry){
        MyEntry<K, V> resultEntry = LinearSearch.search(seperateChainList.iterator(), entry);
        return resultEntry != null ? resultEntry.getValue() : null;
    }
}
