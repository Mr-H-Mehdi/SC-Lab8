/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    @Override
    public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph<>();
    }

    /**
     * Tests the addition of vertices to the graph.
     */
    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        
        // Adding a vertex "A"
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));

        // Adding more vertices
        assertTrue(graph.add("B"));
        assertTrue(graph.vertices().contains("B"));

        // Adding a duplicate vertex (should not be allowed)
        assertFalse(graph.add("A"));
    }

    /**
     * Tests the addition and modification of edges in the graph.
     */
    @Test
    public void testAddEdge() {
        Graph<String> graph = emptyInstance();

        // Adding an edge from "A" to "B" with weight 1
        assertEquals(0, graph.set("A", "B", 1));
        assertTrue(graph.vertices().contains("A"));
        assertTrue(graph.vertices().contains("B"));

        // Adding multiple edges
        assertEquals(0, graph.set("B", "C", 2));
        assertEquals(1, graph.set("A", "B", 3));

        // Updating the weight of an existing edge
        assertEquals(2, graph.set("B", "C", 4));

        // Adding an edge with zero weight (should remove the edge)
        assertEquals(4, graph.set("B", "C", 0));
        assertFalse(graph.targets("B").containsKey("C"));
        assertFalse(graph.sources("C").containsKey("B"));

        // Adding edges with vertices that do not exist
        assertEquals(0, graph.set("D", "E", 5));
        assertTrue(graph.vertices().contains("D"));
        assertTrue(graph.vertices().contains("E"));
    }

    /**
     * Tests the removal of vertices from the graph.
     */
    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();

        // Adding and then removing a vertex "A"
        graph.add("A");
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));

        // Removing a vertex with edges connected
        graph.set("B", "C", 1);
        assertTrue(graph.remove("B"));
        assertFalse(graph.vertices().contains("B"));
        assertFalse(graph.targets("C").containsKey("B"));

        // Removing a non-existing vertex
        assertFalse(graph.remove("D"));
    }

    /**
     * Tests the string representation of the graph.
     */
    @Test
    public void testStringRepresentation() {
        Graph<String> graph = emptyInstance();

        // Empty graph should have a string representation "Empty Graph"
        assertEquals("Empty Graph", graph.toString());

        // Adding edges and testing the string representation
        graph.set("A", "B", 1);
        graph.set("C", "A", 2);

        String expected = "A -> B: 1\nC -> A: 2";
        assertEquals(expected, graph.toString());
    }
}
