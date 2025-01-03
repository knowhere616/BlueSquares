package com.PathSquare;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    public void testAddDistance() {
        Node node = new Node(1);
        node.addDistance(2, 5.0);

        assertEquals(5.0, node.getDistanceTo(2));
    }

    @Test
    public void testDefaultDistance() {
        Node node = new node(1);

        assertEquals(Double.MAX_VALUE, node.getDistanceTO(2));
    }
    
    @Test
    public void testGetters() {
        Node node = new Node(1, "Address1", 40.7128, -74.0060);
        
        assertEquals(1, node.getId());
        assertEquals("Address1", node.getAddress());
        assertEquals(40.7128, node.getLatitude());
        assertEquals(-74.0060, node.getLongitude());
    }
}










}   