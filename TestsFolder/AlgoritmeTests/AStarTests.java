package AlgoritmeTests;

import algoritmes.AStar;
import algoritmes.Prim;
import datastructuren.Graph;
import datastructuren.LinkedList;
import interfaces.AStarSearchable;
import interfaces.CoordinateProvider;
import interfaces.Heuristic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AStarTests {

    @Test
    public void testIfAStarAlgorithmThrowsIllegalArgumentExceptions(){
        Graph<MyTestNode> graph = null;
        Graph<MyTestNode> validGraph = new Graph<>();
        MyTestNode pointOneInvalid = null;
        MyTestNode pointOneValid = new MyTestNode("B", 2, 2);
        MyTestNode pointTwoInvalid = null;
        MyTestNode pointTwoValid = new MyTestNode("A", 1, 1);

        assertThrows(IllegalArgumentException.class, () -> AStar.findShortestPath(graph, pointOneValid, pointTwoValid));
        assertThrows(IllegalArgumentException.class, () -> AStar.findShortestPath(validGraph, pointOneInvalid, pointTwoValid));
        assertThrows(IllegalArgumentException.class, () -> AStar.findShortestPath(validGraph, pointOneValid, pointTwoInvalid));
    }

    @Test
    public void testFindShortestPathWithOneVeryBadHeuristicEstimate() {
        Graph<MyTestNode> graph = new Graph<>();

        MyTestNode source = new MyTestNode("A", 1, 0);
        MyTestNode otherMiddle = new MyTestNode("C", 20, 0);
        MyTestNode middle = new MyTestNode("B", 1, 0);
        MyTestNode destination = new MyTestNode("D", 3, 2);

        graph.addEdge(source, middle, 1);
        graph.addEdge(source, otherMiddle, 19);
        graph.addEdge(middle, destination, 1);
        graph.addEdge(otherMiddle, destination, 17);

        LinkedList<MyTestNode> shortestPath = AStar.findShortestPath(graph, source, destination);

        assertEquals(source, shortestPath.get(0));
        assertEquals(middle, shortestPath.get(1));
        assertEquals(destination, shortestPath.get(2));
    }

    @Test
    public void testFindShortestPathWithUnreachableDestination() {
        Graph<MyTestNode> graph = new Graph<>();
        MyTestNode source = new MyTestNode("A", 1, 0);
        MyTestNode destination = new MyTestNode("E", 5, 5);

        graph.addVertex(source);
        graph.addVertex(destination);

        LinkedList<MyTestNode> shortestPath = AStar.findShortestPath(graph, source, destination);

        assertTrue(shortestPath.isEmpty());
    }

    @Test
    public void testFindShortestPathWithSameSourceAndDestination() {
        Graph<MyTestNode> graph = new Graph<>();
        MyTestNode sourceAndDestination = new MyTestNode("A", 1, 0);

        graph.addEdge(sourceAndDestination, sourceAndDestination, 0);

        LinkedList<MyTestNode> shortestPath = AStar.findShortestPath(graph, sourceAndDestination, sourceAndDestination);

        assertEquals(1, shortestPath.size());
        assertEquals(sourceAndDestination, shortestPath.get(0));
    }

    private static class MyTestNode implements AStarSearchable<MyTestNode>, CoordinateProvider<MyTestNode> {
        private final String name;
        private final double xPos;
        private final double yPos;

        public MyTestNode(String name, int xPos, int yPos) {
            this.name = name;
            this.xPos = xPos;
            this.yPos = yPos;
        }

        @Override
        public double provideDistance(MyTestNode other) {
            return euclideanDistance(this, other);
        }

        @Override
        public int compareTo(MyTestNode other) {
            return this.name.compareTo(other.name);
        }

        @Override
        public double provideX() {
            return this.xPos;
        }

        @Override
        public String toString(){
            return this.name;
        }

        @Override
        public double provideY() {
            return this.yPos;
        }
    }
}
