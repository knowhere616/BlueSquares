import java.util.ArrayList;
import java.util.List;

public class VRP implements RoutingAlgorithm {

    @Override
    // Μήπως πρέπει να περάσουμε τον στόλο ως όρισμα;
    public List<List<Integer>> solve(Graph graph, int numVehicles, int depot) {
        int numNodes = graph.getNumberOfNodes();
        boolean[] visited = new boolean[numNodes];
        visited[depot] = true;

        // Get the vehicles of the fleet
        Fleet fleet = new Fleet();
        List<Vehicle> vehicles = fleet.getVehicles();

        List<List<Integer>> routes = new ArrayList<>();

        for (int i = 0; i < vehicles.size(); i++) {
            routes.add(new ArrayList<>());
            routes.get(i).add(depot);
        }

        for (int i = 0; i < vehicles.size(); i++) {
            int currentNode = depot;
            Vehicle vehicle = vehicles.get(i);

            while (true) {

                // Return to depot and reload if currentload is 0
                if (vehicle.getCurrentLoad() == 0) {
                    routes.get(i).add(depot);
                    vehicle.resetVehicle(); // restock
                    currentNode = depot;
                }

                int nextNode = -1;
                double shortestDistance = Double.MAX_VALUE;

                for (int j = 0; j < numNodes; j++) {
                    if (!visited[j] && graph.getDistance(currentNode, j) < shortestDistance) {
                        nextNode = j;
                        shortestDistance = graph.getDistance(currentNode, j);
                    }
                }
                
                if (nextNode == -1) break;

                routes.get(i).add(nextNode);
                visited[nextNode] = true;
                currentNode = nextNode;

                vehicle.deliverPackage();
            }

            routes.get(i).add(depot);
        }

        return routes; 
    }
}
