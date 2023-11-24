package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

/**
 * Tests for static methods of the Graph class.
 */
public class GraphStaticTest {

    /**
     * Test to ensure that assertions are enabled.
     * Make sure assertions are enabled with VM argument: -ea
     */
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // This assertion should fail if assertions are not enabled.
    }

    /**
     * Test for an empty graph's vertices.
     */
    @Test
    public void testEmptyVerticesEmpty() {
        assertEquals("Expected empty() graph to have no vertices",
                Collections.emptySet(), Graph.empty().vertices());
    }

    /**
     * Test using Integer as labels for vertices.
     */
    @Test
    public void testIntegerAsLabel() {
        Graph<Integer> graph = Graph.empty();
        final int vertex1 = 1;
        final int vertex2 = 2;
        final int vertex3 = 3;

        // Adding vertices
        final boolean addedVertex1 = graph.add(vertex1);
        final boolean addedVertex2 = graph.add(vertex2);

        // Adding edges with weights
        final int weight1 = 2;
        final int weight2 = 1;
        final int prevWeight1 = graph.set(vertex1, vertex2, weight1);
        final int prevWeight2 = graph.set(vertex3, vertex1, weight2);

        // Get initial number of vertices
        final int initialNumVertices = graph.vertices().size();

        // Remove a vertex
        final boolean removedVertex2 = graph.remove(vertex2);

        // Assertions
        assertTrue("Expected vertex1 added", addedVertex1);
        assertTrue("Expected vertex2 added", addedVertex2);
        assertEquals("Expected no previous weight", 0, prevWeight1);
        assertEquals("Expected no previous weight", 0, prevWeight2);
        assertEquals("Expected correct num vertices", 3, initialNumVertices);
        assertTrue("Expected vertex2 removed", removedVertex2);
        assertEquals("Expected one vertex removed", initialNumVertices - 1, graph.vertices().size());

        // Printing the graph (for debugging purposes)
        System.out.println(graph);
    }
}
