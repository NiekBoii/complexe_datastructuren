package interfaces;

@FunctionalInterface
public interface MyOwnComparator<T> {
    int compare(T thisObject, T otherObject);
}