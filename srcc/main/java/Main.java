import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for nodes
        List<Node> nodes = new ArrayList<>();
        System.out.println("Enter the number of nodes: ");
        int nodeCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < nodeCount; i++) {
            System.out.println("Enter name for Node " + (i + 1) + ": ");
            String name = scanner.nextLine();
            try {
                Node node = new Node(name, i); // Pass the index of node
                nodes.add(node);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                i--; // Ask again
            }
        }

        // Input the fleet size
        int fleetSize = 0;
        boolean validFleet = false;
        while (!validFleet) {
            System.out.println("Enter the number of vehicles (positive integer): ");
            fleetSize = Integer.parseInt(scanner.nextLine());
            try {
                Fleet fleet = new Fleet(fleetSize);
                validFleet = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Input vehicle capacity
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < fleetSize; i++) {
            boolean validCapacity = false;
            while (!validCapacity) {
                System.out.println("Enter capacity for Vehicle " + (i + 1) + ": ");
                double capacity = Double.parseDouble(scanner.nextLine());
                try {
                    Vehicle vehicle = new Vehicle(capacity);
                    vehicles.add(vehicle);
                    validCapacity = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        // Manually input distance matrix (symmetric distances)
        double[][] distanceMatrix = new double[nodeCount][nodeCount];
        System.out.println("Enter the distance matrix (symmetric distances):");
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0; // Distance from node to itself is 0
                } else if (i < j) {
                    System.out.println("Enter the distance between " + nodes.get(i).getName() + " and " + nodes.get(j).getName() + ": ");
                    distanceMatrix[i][j] = Double.parseDouble(scanner.nextLine());
                    distanceMatrix[j][i] = distanceMatrix[i][j]; // Symmetric distance
                }
            }
        }

        // Creating fleet and VRP objects
        Fleet fleet = new Fleet(fleetSize);
        VRP vrp = new VRP(nodes, fleet, vehicles);
        vrp.setDistanceMatrix(distanceMatrix); // Set the distance matrix for the VRP solver

        // Solve the VRP
        vrp.solve();
    }
}
