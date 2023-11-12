package algoritmes;

import datastructuren.*;

import java.util.HashMap;

public class  Dijkstra {

    public static <T extends Comparable<T>> LinkedList<T> findShortestPath(Graph<T> graph, T source, T destination){
        if(graph == null || source == null || destination == null){
            throw new IllegalArgumentException("The graph, source and destionation cannot be null for this algorithm to work");
        }

        MinHeap<DijkstraNode<T>> priorityQueue = new MinHeap<>(graph.size());
        SeperateChainingHashTable<T, DijkstraNode<T>> shortestRoutesForNodes = new SeperateChainingHashTable<>(graph.size());

        DijkstraNode<T> startNode = new DijkstraNode<>(source, null, 0);
        priorityQueue.push(startNode);
        shortestRoutesForNodes.put(source, startNode);
        while(!priorityQueue.isEmpty()){
            DijkstraNode<T> next = priorityQueue.pop();

            if(shortestRoutesForNodes.contains(next.currentVertice)){
                shortestRoutesForNodes.remove(next.currentVertice);
            }
            shortestRoutesForNodes.put(next.currentVertice, next);


            if(next.isDestination(destination)){
                return createShortestPath(shortestRoutesForNodes, next.currentVertice);
            }

            for(Edge<T> edge : graph.getEdges(next.currentVertice)){
                int currentWeight = next.weightToGetToCurrent;
                DijkstraNode<T> newVertice = new DijkstraNode<>(edge.getDestination(), next.currentVertice, currentWeight + edge.getWeight());

                if (!shortestRoutesForNodes.contains(newVertice.currentVertice) || currentWeight + edge.getWeight() < shortestRoutesForNodes.get(newVertice.currentVertice).weightToGetToCurrent) {
                    priorityQueue.push(newVertice);
                }
            }
        }

        return new LinkedList<>();
    }


    public static <T extends Comparable<T>> LinkedList<T> createShortestPath(SeperateChainingHashTable<T, DijkstraNode<T>> informationWrappers, T destination){
        LinkedList<T> route = new LinkedList<T>();

        T current = destination;
        while(current != null){
            route.prepend(current);

            DijkstraNode<T> currentWrapper = informationWrappers.get(current);
            if(currentWrapper != null){
                current = currentWrapper.from;
            } else{
                break;
            }
        }
        return route;
    }

    public static class DijkstraNode<T extends Comparable<T>> implements Comparable<DijkstraNode<T>>{
        private final T from;
        private final T currentVertice;
        private final Integer weightToGetToCurrent;

        public DijkstraNode(T currentVertice, T from, int weightToGetToCurrent){
            this.currentVertice = currentVertice;
            this.from = from;
            this.weightToGetToCurrent = weightToGetToCurrent;
        }

        public boolean isDestination(T other){
            return this.currentVertice.compareTo(other) == 0;
        }

        @Override
        public int compareTo(DijkstraNode<T> otherDijkstraNode) {
            return this.weightToGetToCurrent.compareTo(otherDijkstraNode.weightToGetToCurrent);
        }
    }
}
