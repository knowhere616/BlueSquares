package com.PathSquare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner

import static org.junit.jupiter.api.Assertions.*;

public class VRPTest {

    @Test
    public void testSolve() {
        Node depot = new Node (1, "Depot", 40.7128, -74.0060);
        Node node1 = new Node (2, "Node1", 34.0522, -118.2437);
        Node node2 = new Node (3, "Node2", 41.8781, -87.6298);

        depot.addDistance(2, 10.0);
        depot.addDistance(3, 20.0);
        node1.addDistance(3. 15.0);

        List<Node> nodes = List.of(depot, node1, node2);

        Vehicle vehicle = new Vehicle();
        vehicle.initializeVehicle(new Scanner("1\n"));
        Fleet fleet = new Fleet();
        fleet.setVehicles(List.of(vehicle));
            

        VRP vrp = new VRP();
        List<List<Node>> routes = vrp.solve(fleet, nodes, depot);

        assertEquals(1, routes.size());
        assertTrue(routes.get(0).contains(depot));
        assertTrue(routes.get(0).contains(node1) || routes.get(0).contains(node2));    
    }
}