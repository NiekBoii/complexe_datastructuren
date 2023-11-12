package interfaces;

public interface HashTable<K extends Comparable<K>, V extends Comparable<V>> {
    void put(K key, V value);
    boolean contains(K key);
    V get(K key);
    V remove(K key);
    String toString();
}
