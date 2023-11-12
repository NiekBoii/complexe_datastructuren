package algoritmes;

import datastructuren.*;
import interfaces.AStarSearchable;

import java.util.HashSet;
import java.util.Set;

public class AStar <T extends Comparable<T>>{

    public static <T extends AStarSearchable<T>> LinkedList<T> findShortestPath(Graph<T> graph, T source, T destination) {
        if(graph == null || source == null || destination == null){
            throw new IllegalArgumentException("The graph, source and destionation cannot be null for this algorithm to work");
        }
        Set<T> visited = new HashSet<>();
        MinHeap<AStarNode<T>> priorityQueue = new MinHeap<>(50);
        LinkedList<T> route = new LinkedList<>();

        priorityQueue.push(new AStarNode<>(source, 0.0));

        while(!priorityQueue.isEmpty()){
            AStarNode<T> next = priorityQueue.pop();

            route.add(next.vertice);
            visited.add(next.getVertice());
            if(next.isDestination(destination)){
                return route;
            }

            for(Edge<T> edge : graph.getEdges(next.getVertice())){
                if(!visited.contains(edge.getDestination())){
                    double currentCosts = next.gCost;
                    AStarNode<T> vertice = new AStarNode<>(edge.getDestination(), currentCosts + edge.getWeight());

                    vertice.setCostToGoal(destination);
                    priorityQueue.push(vertice);
                }
            }
        }
        return new LinkedList<>();
    }

    private static class AStarNode<T extends AStarSearchable<T>>  implements Comparable<AStarNode<T>> {
        private final  T vertice;
        private final double gCost;
        private double hCost;

        public AStarNode(T vertice, double gCost){
            this.vertice = vertice;
            this.gCost = gCost;
        }

        public void setCostToGoal(T goalVertice){
            this.hCost = vertice.provideDistance(goalVertice);
        }

        public double fCost(){
            return gCost + hCost;
        }

        public T getVertice(){
            return vertice;
        }


        public boolean isDestination(T vertice){
            return this.vertice.compareTo(vertice) == 0;
        }

        @Override
        public int compareTo(AStarNode<T> otherVertice) {
            return Double.compare(fCost(), otherVertice.fCost());
        }
    }
}
