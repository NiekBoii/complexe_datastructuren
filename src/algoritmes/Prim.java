package algoritmes;

import datastructuren.*;
import interfaces.MCSTBoundChecker;

public class Prim<T extends Comparable<T>> {

    public static <T extends Comparable<T>> LinkedList<Edge<T>> determineMinimumCostSpanningTree(Graph<T> graph, T pointOne) {
        MCSTBoundChecker<T> noBounds = other -> true;
        return determineMinimumCostSpanningTree(graph, pointOne, noBounds, noBounds);
    }

    public static <T extends Comparable<T>> LinkedList<Edge<T>> determineMinimumCostSpanningTree(Graph<T> graph, T pointOne, MCSTBoundChecker<T> boundOne, MCSTBoundChecker<T> boundTwo) {
        if (graph == null || pointOne == null) {
            throw new IllegalArgumentException("Graph, pointOne or point two all have to be not null.");
        }
        MinHeap<PrimNode<T>> priorityQueue = new MinHeap<>(graph.size());
        SeperateChainingHashTable<T, Edge<T>> leastWeighingEdges = new SeperateChainingHashTable<>(graph.size());
        SeperateChainingHashTable<T, Edge<T>> resultSet = new SeperateChainingHashTable<>(graph.size());

        var nextPrimNode = new PrimNode<T>(new Edge<T>(pointOne, pointOne, 0));
        priorityQueue.push(nextPrimNode);

        while (!priorityQueue.isEmpty()) {
            nextPrimNode = priorityQueue.pop();
            if (leastWeighingEdges.contains(nextPrimNode.edge.getDestination())) {
                continue;
            }
            leastWeighingEdges.put(nextPrimNode.edge.getDestination(), nextPrimNode.edge);

            if (boundOne.isInBounds(nextPrimNode.edge.getDestination()) && boundTwo.isInBounds(nextPrimNode.edge().getDestination()) && !resultSet.contains(nextPrimNode.edge.getDestination())) {
                resultSet.put(nextPrimNode.edge.getDestination(), nextPrimNode.edge);
            }

            for (Edge<T> edge : graph.getEdges(nextPrimNode.edge.getDestination())) {
                priorityQueue.push(new PrimNode<T>(edge));
            }
        }
        return resultSet.values();
    }

    private record PrimNode<T extends Comparable<T>>(Edge<T> edge) implements Comparable<PrimNode<T>> {

        @Override
        public int compareTo(PrimNode<T> otherPrimNode) {
            return Integer.compare(edge.getWeight(), otherPrimNode.edge.getWeight());
        }
    }
}
