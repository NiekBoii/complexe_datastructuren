package DatastructurenTests;

import datastructuren.Edge;
import datastructuren.Graph;
import datastructuren.LinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {

    Graph<Integer> graph;

    @BeforeEach
    public void init() {
        graph = new Graph<>();
    }

    @Test
    public void testEmptyGraph(){
        assertEquals(0, graph.size());
        assertTrue(graph.isEmpty());
        assertEquals(0, graph.getEdgesCount());
        assertEquals(0, graph.getVertexCount());
    }

    @Test
    public void testIfAddingVertexWorks(){
        graph.addVertex(1);
        assertTrue(graph.hasVertex(1));
        assertEquals(1, graph.getVertexCount());
    }

    @Test
    public void testIfAddingEdgeWorks(){
        graph.addVertex(5);
        graph.addVertex(1);
        graph.addEdge(1, 5, 10);
        assertTrue(graph.hasEdge(1, 5));
        assertEquals( 1, graph.getEdgesCount());
    }

    @Test
    public void testIfAddingEdgeAddsVerticesWhenTheyDontExistAndAlsoAddTheEdgeToTheTable(){
        graph.addEdge(5, 1, 20);
        assertTrue(graph.hasEdge(5, 1));
        // 1 kant op !!!
        assertFalse(graph.hasEdge(1, 5));
        assertTrue(graph.hasVertex(5));
        assertTrue(graph.hasVertex(1));
    }

    @Test
    public void testToStringReturnsRightBreadthFirstSearchString(){
        graph.addEdge(1, 2, 2);
        graph.addEdge(1, 3, 20);
        assertEquals("{ 1, 2, 3}", graph.toString());
    }

    @Test
    public void testIfPrintingAdjacencyListReturnsRightString(){
        graph.addEdge(1, 2, 20);
        graph.addEdge(1, 3, 20);
        graph.addEdge(1,4, 20);
        assertEquals("Digraph CDS {\n" +
                "\"1\" -> \"2\" [label=\"20\"]\n" +
                "\"1\" -> \"3\" [label=\"20\"]\n" +
                "\"1\" -> \"4\" [label=\"20\"]\n" +
                "}", graph.graphViz());
    }

    @Test
    public void testIfAddingDuplicateVertexThrowsIllegalArgumentException(){
        graph.addVertex(1);
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(1));
    }

    @Test
    public void testIfGetVerticesReturnExpectedVertices(){
        LinkedList<Integer> integers = new LinkedList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        Graph<Integer> graph = new Graph<>();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        assertIterableEquals(integers, graph.getVertices());
    }

    @Test
    public void testIfGetEdgesReturnsExpectedResult(){
        Graph<Integer> graph = new Graph<>();
        Edge<Integer> edge = new Edge<>(1, 2, 10);
        Edge<Integer> edge2 = new Edge<>(1, 3, 10);
        Edge<Integer> edge3 = new Edge<>(1, 4, 10);
        LinkedList<Edge<Integer>> ownEdges = new LinkedList<>();
        ownEdges.add(edge);
        ownEdges.add(edge2);
        ownEdges.add(edge3);
        graph.addEdge(edge);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        LinkedList<Edge<Integer>> graphEdges = graph.getEdges(1);
        assertIterableEquals(ownEdges, graphEdges);
    }

    @Test
    public void testIfEdgeToStringReturnsExpectedResult(){
        Graph<Integer> graph = new Graph<>();
        LinkedList<Edge<Integer>> ownEdges = new LinkedList<>();
        Edge<Integer> edge = new Edge<>(1, 2, 10);
        Edge<Integer> edge2 = new Edge<>(1, 3, 10);
        Edge<Integer> edge3 = new Edge<>(1, 4, 10);
        ownEdges.add(edge);
        ownEdges.add(edge2);
        ownEdges.add(edge3);
        graph.addEdge(edge);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        LinkedList<Edge<Integer>> graphEdges = graph.getEdges(1);
        String[] edgeToStrings= {
                "[destination -> : 2 weight -> : 10]",
                "[destination -> : 3 weight -> : 10]",
                "[destination -> : 4 weight -> : 10]"
        };
        for(int i=0; i < edgeToStrings.length; i++){
            String graphEdge = graphEdges.get(i).toString();
            String ownEdge = ownEdges.get(i).toString();
            assertEquals(edgeToStrings[i], graphEdge);
            assertEquals(edgeToStrings[i], ownEdge);
        }
    }

    @Test
    public void testIfSettingStartVerticeAsEdgeWorks(){
        graph.addEdge(2, 1, 20);
        assertEquals(2, graph.getStartVertice());
    }
}
