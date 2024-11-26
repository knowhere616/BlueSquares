import java.util.ArrayList;
import java.util.List;

public class VRP {
    // Solves the VRP problem using pre-defined distances
    public List<List<Node>> solve(Fleet fleet, List<Node> nodes, Node depot) {
        List<List<Node>> routes = new ArrayList<>();
        List<Vehicle> vehicles = fleet.getVehicles();
        List<Node> unvisitedNodes = new ArrayList<>(nodes);
        unvisitedNodes.remove(depot); // Exclude the depot from unvisited nodes

        // Initialize routes for each vehicle
        for (Vehicle vehicle : vehicles) {
            List<Node> route = new ArrayList<>();
            route.add(depot); // Start each route at the depot
            routes.add(route);
        }

        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            List<Node> route = routes.get(i);
            Node currentNode = depot;

            while (!unvisitedNodes.isEmpty() && vehicle.getCurrentLoad() > 0) {
                Node nearestNode = null;
                double shortestDistance = Double.MAX_VALUE;

                // Find the nearest unvisited node
                for (Node node : unvisitedNodes) {
                    double distance = currentNode.getDistanceTo(node.getId());
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        nearestNode = node;
                    }
                }

                if (nearestNode == null) {
                    break; // No more reachable nodes
                }

                // Visit the nearest node
                route.add(nearestNode);
                unvisitedNodes.remove(nearestNode);
                currentNode = nearestNode;
                vehicle.deliverPackage();
            }

            // Return the vehicle to the depot after finishing
            route.add(depot);
        }

        return routes;
    }
}
