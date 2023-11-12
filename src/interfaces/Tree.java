package interfaces;

public interface Tree<T> {
    void add(T value);
    boolean contains(T value);
    boolean isLeaf();
    int height();
    String graphViz();
    int size();
    String toString();
}
