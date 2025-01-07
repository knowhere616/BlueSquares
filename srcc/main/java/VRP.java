import java.util.List;
import java.util.ArrayList;

public class VRP {
    private List<Node> nodes;
    private Fleet fleet;
    private List<Vehicle> vehicles;
    private double[][] distanceMatrix; // To store the distances between nodes
    private double[] remainingDemand;

    // Constructor to initialize the VRP with nodes, fleet, and vehicles
    public VRP(List<Node> nodes, Fleet fleet, List<Vehicle> vehicles) {
        if (nodes == null || nodes.size() == 0) {
            throw new IllegalArgumentException("At least one node is required!");
        }
        if (fleet == null || fleet.getSize() <= 0) {
            throw new IllegalArgumentException("Fleet must be valid with a positive number of vehicles!");
        }
        if (vehicles == null || vehicles.size() != fleet.getSize()) {
            throw new IllegalArgumentException("The number of vehicles must match the fleet size!");
        }

        // Validate that the first node is the depot
        Node depot = nodes.get(0);
        if (depot.getDemand() != 0) {
            throw new IllegalArgumentException("The depot (node 0) must have zero demand!");
        }

        this.nodes = nodes;
        this.fleet = fleet;
        this.vehicles = vehicles;

        // Initialize distance matrix with the same size as the number of nodes
        this.distanceMatrix = new double[nodes.size()][nodes.size()];

        // Initializing the remaining demand for each node
        this.remainingDemand = new double[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            this.remainingDemand[i] = nodes.get(i).getDemand();
        }

    }

    // Nearest Neighbor algorithm to find the optimal route
    public void solve() {
        System.out.println("Solving VRP with " + nodes.size() + " nodes and " + vehicles.size() + " vehicles.");

        // For each vehicle in the fleet, apply the nearest neighbor heuristic
        for (int v = 0; v < fleet.getSize(); v++) {
            Vehicle vehicle = vehicles.get(v);
            System.out.println("Solving for Vehicle " + (v + 1) + " with capacity: " + vehicle.getCapacity());

            // Run nearest neighbor algorithm
            List<Node> route = nearestNeighbor(v);

            System.out.println("Route for Vehicle " + (v + 1) + ": ");
            double totalDistance = 0;
            // Print the route and calculate the total distance
            for (int i = 0; i < route.size(); i++) {
                System.out.print(route.get(i).getName());
                if (i < route.size() - 1) {
                    System.out.print(" -> ");
                }
                if (i < route.size() - 1) {
                    totalDistance += distanceMatrix[route.get(i).getIndex()][route.get(i + 1).getIndex()];
                }
            }
            System.out.println("\nTotal Distance: " + totalDistance + " units");
        }
    }

    // Nearest Neighbor Algorithm to construct a simple route for each vehicle
    private List<Node> nearestNeighbor(int vehicleIndex) {

        // Create the Vehicle object for the given index
        Vehicle vehicle = vehicles.get(vehicleIndex);

        // Identify the depot (always node 0)
        Node depot = nodes.get(0);

        List<Node> route = new ArrayList<>();
        boolean[] visited = new boolean[nodes.size()];

        // Starting from node 0 (the depot)
        Node currentNode = depot;
        route.add(currentNode);
        visited[currentNode.getIndex()] = true;
        double remainingCapacity = vehicle.getCapacity();

        // Apply nearest neighbor heuristic for vehicle route
        while (route.size() < nodes.size()) {
            double minDistance = Double.MAX_VALUE;
            Node nearestNode = null;

            // Search for the nearest unvisited node
            for (Node node : nodes) {
                if (!visited[node.getIndex()] && node != depot && remainingDemand[node.getIndex()] > 0) {
                    double distance = distanceMatrix[currentNode.getIndex()][node.getIndex()];
                    if (distance < minDistance && remainingDemand[node.getIndex()] <= remainingCapacity) {
                        minDistance = distance;
                        nearestNode = node;
                    }
                }
            }

            // If no more nodes can be served, return to depot
            if (nearestNode == null) {
                if (currentNode != depot) {
                    route.add(depot); // Return to depot to refill
                    remainingCapacity = vehicle.getCapacity(); // Refill the vehicle
                    currentNode = depot;
                } else {
                    break; // No more nodes can be served, exit loop
                }
            } else {
                // Serve the nearest node
                route.add(nearestNode);
                visited[nearestNode.getIndex()] = true;
                remainingCapacity -= nearestNode.getDemand();
                remainingDemand[nearestNode.getIndex()] = 0; // Mark demand as fully served
                currentNode = nearestNode;

                // If capacity is exhausted, return to the depot
                if (remainingCapacity <= 0) {
                    route.add(depot);
                    remainingCapacity = vehicle.getCapacity(); // Refill capacity
                    currentNode = depot;
                }
            }

        }
        return route;
    }

    // Method to set the distance matrix (populate from user input or calculated
    // values)
    public void setDistanceMatrix(double[][] distanceMatrix) {
        this.distanceMatrix = distanceMatrix;
    }
}