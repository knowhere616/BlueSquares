import java.util.ArrayList;
import java.util.List;

public class VRP implements RoutingAlgorithm {
    @Override
    public List<List<Integer>> solve(Graph graph, int numVehicles, int depot) {
        int numNodes = graph.getNumberOfNodes();
        boolean[] visited = new boolean[numNodes];
        visited[depot] = true;

        List<List<Integer>> routes = new ArrayList<>();
        for (int i = 0; i < numVehicles; i++) {
            routes.add(new ArrayList<>());
            routes.get(i).add(depot);
        }

        for (int i = 0; i < numVehicles; i++) {
            int currentNode = depot;

            while (true) {
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
            }

            routes.get(i).add(depot);
        }

        return routes; 
    }
}
