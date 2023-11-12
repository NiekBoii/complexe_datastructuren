package datastructuren;

public class MyEntry<K extends Comparable<K>, V>  implements Comparable<MyEntry<K, V>>{
    private K key;
    private V value;

    public MyEntry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public V getValue(){
        return this.value;
    }

    public K getKey(){
        return this.key;
    }

    @Override
    public int compareTo(MyEntry<K, V> otherEntry) {
        return this.key.compareTo(otherEntry.key);
    }
}
