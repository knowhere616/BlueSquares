package com.PathSquare;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose input mode:");
        System.out.println("1. Enter nodes and distances manually");
        System.out.println("2. Use real addresses for nodes");
        int mode = scanner.nextInt();

        List<Node> nodes = new ArrayList<>();
        Node depot = null;

        if (mode == 1) {
            // Manual mode
            System.out.print("Enter the number of nodes: ");
            int numNodes = scanner.nextInt();

            // Create nodes
            for (int i = 0; i < numNodes; i++) {
                System.out.print("Enter a name or identifier for Node " + (i + 1) + ": ");
                String name = scanner.next();
                Node node = new Node(i + 1);
                nodes.add(node);
            }

            // Assign depot
            System.out.print("Enter the ID of the depot node: ");
            int depotId = scanner.nextInt();
            depot = nodes.stream().filter(node -> node.getId() == depotId).findFirst().orElse(null);

            // Input distances
            System.out.println("Enter distances between nodes (Node A ID, Node B ID, Distance):");
            for (int i = 0; i < numNodes * (numNodes - 1) / 2; i++) {
                System.out.print("From Node ID: ");
                int from = scanner.nextInt();
                System.out.print("To Node ID: ");
                int to = scanner.nextInt();
                System.out.print("Distance (km): ");
                double distance = scanner.nextDouble();

                nodes.get(from - 1).addDistance(to, distance);
                nodes.get(to - 1).addDistance(from, distance); // Symmetric distance
            }

        } else if (mode == 2) {
            // Real addresses mode
            System.out.print("Enter the number of nodes: ");
            int numNodes = scanner.nextInt();

            for (int i = 0; i < numNodes; i++) {
                System.out.print("Enter address for Node " + (i + 1) + ": ");
                scanner.nextLine(); // Consume newline
                String address = scanner.nextLine();

                Node node = Real_data_VRP.getCoordinatesFromAddress(address, i + 1);
                if (node != null) {
                    nodes.add(node);
                } else {
                    System.out.println("Skipping invalid address: " + address);
                }
            }

            // Assign depot
            depot = nodes.get(0); // Default depot as first node
            System.out.println("Depot set to: " + depot.getAddress());

            // Calculate distances
            double[][] distances = Real_data_VRP.calculateAllDistances(nodes);
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    nodes.get(i).addDistance(j + 1, distances[i][j]);
                }
            }
        } else {
            System.out.println("Invalid option.");
            return;
        }

        // Initialize fleet
        Fleet fleet = new Fleet();
        fleet.initializeFleet(scanner);

        // Solve VRP
        VRP vrp = new VRP();
        List<List<Node>> routes = vrp.solve(fleet, nodes, depot);

        // Print routes
        System.out.println("Optimized Routes:");
        for (int i = 0; i < routes.size(); i++) {
            System.out.println("Vehicle " + (i + 1) + ":");
            for (Node node : routes.get(i)) {
                System.out.println("  " + node);
            }
        }

        scanner.close();
    }
}
