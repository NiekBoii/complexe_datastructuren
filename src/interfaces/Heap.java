package interfaces;

public interface Heap<T> {
    T peek();
    T pop();
    void push(T element);
    String graphViz();
}
