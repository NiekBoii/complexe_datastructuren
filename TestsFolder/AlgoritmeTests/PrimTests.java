package AlgoritmeTests;

import algoritmes.Prim;
import datastructuren.Graph;
import datastructuren.LinkedList;
import datastructuren.Edge;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PrimTests {

    @Test
    public void testIfPrimDoesNotGoInCircles() {
        Graph<String> graph = new Graph<>();

        Edge<String> aToB = new Edge<>("A", "B", 1);
        Edge<String> aToC = new Edge<>("A", "B", 1);
        Edge<String> bToC = new Edge<>("B", "C", 1);
        Edge<String> bToA = new Edge<>("B", "A", 1);
        Edge<String> cToA = new Edge<>("C", "A", 1);
        Edge<String> cToB = new Edge<>("C", "B", 1);
        graph.addEdge(aToB);
        graph.addEdge(aToC);
        graph.addEdge(bToC);
        graph.addEdge(bToA);
        graph.addEdge(cToA);
        graph.addEdge(cToB);

        LinkedList<Edge<String>> minimumSpanningTree = Prim.determineMinimumCostSpanningTree(graph, "A");

        int totalCost = 0;
        for(Edge<String> edge : minimumSpanningTree){
            totalCost += edge.getWeight();
        }
        assertEquals(2, totalCost);
    }

    @Test
    public void testDetermineMinimumCostSpanningTreeWithDisconnectedGraph() {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 2);
        graph.addEdge("C", "D", 3);

        LinkedList<Edge<String>> minimumSpanningTree = Prim.determineMinimumCostSpanningTree(graph, "A");

        for(Edge edge: minimumSpanningTree){
            System.out.println(edge.getSource() + " " + edge.getDestination());
        }
        assertEquals(0, minimumSpanningTree.get(0).getWeight());
        assertEquals(2, minimumSpanningTree.get(1).getWeight());
    }

    @Test
    public void testIfPrimThrowsIllegalArgumentExceptionForNullArguments(){
        Graph<String> graph = null;
        Graph<String> validGraph = new Graph<>();
        String pointOneInvalid = null;
        String pointOneValid = "PointOne";
        String pointTwoInvalid = null;
        String pointTwoValid = "PointTwo";

        assertThrows(IllegalArgumentException.class, () -> Prim.determineMinimumCostSpanningTree(graph, pointOneValid));
        assertThrows(IllegalArgumentException.class, () -> Prim.determineMinimumCostSpanningTree(validGraph, pointOneInvalid));
        assertThrows(IllegalArgumentException.class, () -> Prim.determineMinimumCostSpanningTree(validGraph, pointOneValid));
    }

    @Test
    public void testDetermineMinimumCostSpanningTreeWithSingleNodeGraph() {
        Graph<String> graph = new Graph<>();
        graph.addVertex("A");
        LinkedList<Edge<String>> minimumSpanningTree = Prim.determineMinimumCostSpanningTree(graph, "A");
        assertEquals(0, minimumSpanningTree.get(0).getWeight());
    }
}