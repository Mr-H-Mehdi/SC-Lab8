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
    public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph<>();
    }

    @Test
    public void testGraphCreationAndInitialization() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.vertices().isEmpty());
    }

    @Test
    public void testAddingVertices() {
        Graph<String> graph = emptyInstance();
        assertTrue(graph.add("A"));
        assertTrue(graph.vertices().contains("A"));

        assertTrue(graph.add("B"));
        assertTrue(graph.vertices().contains("B"));

        assertFalse(graph.add("A")); // Adding duplicate vertex
    }

    @Test
    public void testAddingEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");

        assertEquals(0, graph.set("A", "B", 5)); // Add edge from A to B with weight 5
        assertEquals(5, graph.set("A", "B", 3)); // Update edge weight from 5 to 3
        assertEquals(3, graph.set("A", "B", 0)); // Remove edge from A to B
        assertFalse(graph.set("C", "D", 2) > 0); // Add edge with non-existing vertices
    }

    @Test
    public void testRemovingVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 2);

        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
        assertFalse(graph.remove("C")); // Remove non-existing vertex
    }

    @Test
    public void testStringRepresentation() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 2);
        graph.set("B", "A", 3);

        String expectedString = "A -> {B=2}\nB -> {A=3}";
        assertEquals(expectedString, graph.toString());
    }

    // Add more test methods for other scenarios...

}
