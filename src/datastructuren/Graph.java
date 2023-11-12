package datastructuren;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Graph<T extends Comparable<T>> implements interfaces.Graph {

    private SeperateChainingHashTable<T, LinkedList<Edge<T>>> verticesAndEdges = new SeperateChainingHashTable<T, LinkedList<Edge<T>>>(75);
    private T startVertice = null;

    public void addVertex(T vertex) {
        verticesAndEdges.put(vertex, new LinkedList<Edge<T>>());

        if(startVertice == null){
            startVertice = vertex;
        }
    }

    public LinkedList<T> getVertices(){
        LinkedList<T> vertices = new LinkedList<>();

        for(MyEntry<T, LinkedList<Edge<T>>> entry : verticesAndEdges.entrySet()){
            vertices.add(entry.getKey());
        }
        return vertices;
    }

    public boolean isEmpty(){
        return verticesAndEdges.isEmpty();
    }

    public int size(){
        return verticesAndEdges.size();
    }

    public int getTotalWeight(){
        AtomicInteger totalWeight = new AtomicInteger();
        verticesAndEdges.values().forEach((edgeList) -> {
            edgeList.forEach((edge) -> {
                totalWeight.set(totalWeight.get() + edge.getWeight());
            });
        });
        return totalWeight.get();
    }

    public void addEdge(T source, T destination, int weight) {
        if (!verticesAndEdges.contains(source)) {
            addVertex(source);
        }

        if (!verticesAndEdges.contains(destination)) {
            addVertex(destination);
        }

        Edge<T> edge = new Edge<T>(source, destination, weight);
        verticesAndEdges.get(source).add(edge);
    }

    public void addEdge(Edge<T> edge){
        if(!verticesAndEdges.contains(edge.getSource())){
            LinkedList<Edge<T>> newEdges = new LinkedList<>();
            newEdges.add(edge);
            verticesAndEdges.put(edge.getSource(), newEdges);
        } else{
            verticesAndEdges.get(edge.getSource()).add(edge);
        }
    }

    public int getVertexCount() {
        return verticesAndEdges.entrySet().size();
    }

    public LinkedList<Edge<T>> getEdges(T vertice){
        return verticesAndEdges.get(vertice);
    }

    public int getEdgesCount() {
        int count = 0;
        for (MyEntry<T, LinkedList<Edge<T>>> v : verticesAndEdges.entrySet()) {
            count += v.getValue().size();
        }

        return count;
    }

    public boolean hasVertex(T vertex) {
        return verticesAndEdges.contains(vertex);
    }

    public boolean hasEdge(T source, T destination) {
        Edge<T> edge = new Edge<T>(source, destination);
        return verticesAndEdges.get(source).contains(edge);
    }

    public String graphViz(){
        return String.format("Digraph CDS {\n%s}", graphVizAdjacencyList());
    }

    private String graphVizAdjacencyList(){
        StringBuilder builder = new StringBuilder();

        for (MyEntry<T, LinkedList<Edge<T>>> entry: verticesAndEdges.entrySet()) {
            for (Edge<T> edge : verticesAndEdges.get(entry.getKey())) {
                builder.append( "\"" + entry.getKey().toString() + "\" -> ");
                builder.append("\"" + edge.getDestination() + "\"");
                builder.append(" [label=\"" + edge.getWeight() + "\"]");
                builder.append("\n");
            }
        }

        return builder.toString();
    }

    @Override
    public String toString(){
        return breadthFirstTraversal();
    }

    private String breadthFirstTraversal(){
        Set<T> visited = new TreeSet<>();
        LinkedList<T> queue = new LinkedList<>();

        StringBuilder breadthFirstStringBuilder = new StringBuilder();
        breadthFirstStringBuilder.append("{");

        queue.add(startVertice);
        int size = verticesAndEdges.size();

        while(!queue.isEmpty()) {
            T vertice = queue.poll();

            if (!visited.contains(vertice)){
                visited.add(vertice);

                for(Edge<T> edgesOfVertice : verticesAndEdges.get(vertice)){
                    if(!visited.contains(edgesOfVertice.getDestination())){
                        queue.add(edgesOfVertice.getDestination());
                    }
                }

                size--;
                String postFix = size != 0 ? "," : "";
                breadthFirstStringBuilder.append(" " + vertice + postFix);
            }
        }

        breadthFirstStringBuilder.append("}");
        return breadthFirstStringBuilder.toString();
    }

    public T getStartVertice(){
        return this.startVertice;
    }
}
