package AlgoritmeTests;

import algoritmes.Dijkstra;
import algoritmes.Prim;
import datastructuren.Edge;
import datastructuren.Graph;
import datastructuren.LinkedList;
import datastructuren.SeperateChainingHashTable;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraTests {
    @Test
    public void testIfDijkstrasThrowsIllegalArgumentExceptionWhenElementsAreNull(){
        Graph<String> graph = null;
        Graph<String> validGraph = new Graph<>();
        String pointOneInvalid = null;
        String pointOneValid = "PointOne";
        String pointTwoInvalid = null;
        String pointTwoValid = "PointTwo";

        assertThrows(IllegalArgumentException.class, () -> Dijkstra.findShortestPath(graph, pointOneValid, pointTwoValid));
        assertThrows(IllegalArgumentException.class, () -> Dijkstra.findShortestPath(validGraph, pointOneInvalid, pointTwoValid));
        assertThrows(IllegalArgumentException.class, () -> Dijkstra.findShortestPath(validGraph, pointOneValid, pointTwoInvalid));
    }

    @Test
    public void testFindShortestPathInGoodScenario() {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 1);
        graph.addEdge("B", "D", 4);
        graph.addEdge("C", "D", 1);

        LinkedList<String> shortestPath = Dijkstra.findShortestPath(graph, "A", "D");

        assertEquals(3, shortestPath.size());
        assertEquals("A", shortestPath.get(0));
        assertEquals("C", shortestPath.get(1));
        assertEquals("D", shortestPath.get(2));
    }

    @Test
    public void testIfFindingBetterRouteLaterStageWorks(){
        // Originele grafiek
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 1);
        graph.addEdge("A", "C", 3);
        graph.addEdge("C", "D", 1);
        graph.addEdge("B", "D", 50);

        LinkedList<String> shortestPath = Dijkstra.findShortestPath(graph, "A", "D");

        assertEquals(3, shortestPath.size());
        assertEquals("A", shortestPath.get(0));
        assertEquals("C", shortestPath.get(1));
        assertEquals("D", shortestPath.get(2));
    }

    @Test
    public void testIfFindShortestPathReturnsRightResultWhenThereIsNoPath() {
        Graph<String> graph = new Graph<>();
        graph.addEdge("A", "B", 1);
        graph.addEdge("C", "D", 2);

        LinkedList<String> shortestPath = Dijkstra.findShortestPath(graph, "A", "D");

        assertTrue(shortestPath.isEmpty());
    }

    @Test
    public void testCreateShortestPathReturnsRightResult() {
        Dijkstra.DijkstraNode<String> node1 = new Dijkstra.DijkstraNode<>("A", null, 0);
        Dijkstra.DijkstraNode<String> node2 = new Dijkstra.DijkstraNode<>("B", "A", 1);
        Dijkstra.DijkstraNode<String> node3 = new Dijkstra.DijkstraNode<>("C", "B", 3);

        SeperateChainingHashTable<String, Dijkstra.DijkstraNode<String>> table = new SeperateChainingHashTable<>(3);
        table.put("A", node1);
        table.put("B", node2);
        table.put("C", node3);
        LinkedList<String> shortestPath = Dijkstra.createShortestPath(table, "C");

        assertEquals(3, shortestPath.size());
        assertEquals("A", shortestPath.get(0));
        assertEquals("B", shortestPath.get(1));
        assertEquals("C", shortestPath.get(2));
    }
}
