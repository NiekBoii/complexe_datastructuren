package datastructuren;

public class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {
    private T source;
    private T destination;
    private int weight;

    public Edge(T source, T destination){
        this.source = source;
        this.destination = destination;
    }

    public Edge(T source, T destination, int weight){
        this(source, destination);
        this.weight = weight;
    }

    public T getSource() {
        return source;
    }

    public T getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge<T> o) {
        return this.source.compareTo(o.source) + this.destination.compareTo(o.destination);
    }

    @Override
    public String toString(){
        return  "[destination -> : " + this.destination.toString() + " weight -> : " + this.weight + "]";
    }
}
