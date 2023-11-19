/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 *
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 *
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    @Override
    // Create an empty instance of ConcreteVerticesGraph for testing
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }

    // Test case to ensure that the graph is created and initialized properly
    @Test
    public void testGraphCreationAndInitialization() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.vertices().isEmpty()); // The graph should initially have no vertices
    }

    // Test case to check the addition of vertices to the graph
    @Test
    public void testAddingVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A")); // Adding vertex A
        assertTrue(graph.vertices().contains("A")); // Vertex A should be in the graph now

        assertTrue(graph.add("B")); // Adding vertex B
        assertTrue(graph.vertices().contains("B")); // Vertex B should be in the graph now

        assertFalse(graph.add("A")); // Adding duplicate vertex A, should return false
    }

    // Test case to check the addition and modification of edges in the graph
    @Test
    public void testAddingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");

        assertEquals(0, graph.set("A", "B", 5)); // Add edge from A to B with weight 5
        assertEquals(5, graph.set("A", "B", 3)); // Update edge weight from 5 to 3
        assertEquals(3, graph.set("A", "B", 0)); // Remove edge from A to B
        assertFalse(graph.set("C", "D", 2) > 0); // Add edge with non-existing vertices (C and D)
    }

    // Test case to check the removal of vertices from the graph
    @Test
    public void testRemovingVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 2);

        assertTrue(graph.remove("A")); // Remove vertex A
        assertFalse(graph.vertices().contains("A")); // Vertex A should not be in the graph now
        assertFalse(graph.remove("C")); // Remove non-existing vertex C, should return false
    }

    // Test case to check the string representation of the graph
    @Test
    public void testStringRepresentation() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 2);
        graph.set("B", "A", 3);

        // The expected string representation of the graph
        String expectedString = "A -> {B=2}\nB -> {A=3}";
        assertEquals(expectedString, graph.toString());
    }


}
