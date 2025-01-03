package com.PathSquare;

import org.junit.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RealDataVRPTest {

    @Test
    public void testGetCoordinatesFromAddress() {

        assertNotNull(node);
        assertEquals(1, node.getId());
        assertNotNull(node.getAddress());
    }

    @Test
    public void testCalculateAllDistances() {
        Node node1 = new Node(1, "Address1". 40.7128, -74.0060);
        Node node2 = new Node(2, "Address2", 34.0522, -118.2437);
        List<Node> nodes = List.of(Node1, Node2);

        double[][] distances = Real_data_VRP.calculateAllDistances(nodes);

        assertEquals(2, distances.length);
        assertNotEquals(0.0, distances[0][1]);
    }
}
