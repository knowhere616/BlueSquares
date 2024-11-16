import java.util.ArrayList;
import java.util.List;

public class RouteOptimizer {
   public List<Integer> nearestNeighbor(Graph graph, int startNode) {
    int numNodes = graph.getNumberOfNodes();
    List<Integer> route = new ArrayList<>();
    boolean[] visited = new boolean[numNodes];
    int currentNode = startNode;

    route.add(currentNode);
    visited[currentNode] = true;

    for (int step = 1; step < numNodes; step++) {
        int nextNode = -1;
        double shortestDistance = Double.MAX_VALUE;

        for (int i = 0; i < numNodes; i++) {
            if (!visited[i] && graph.getDistance(currentNode, i) < shortestDistance) {
                nextNode = i;
                shortestDistance = graph.getDistance(currentNode, i);
            }
        }

        route.add(nextNode);
        visited[nextNode] = true;
        currentNode = nextNode;
    }
    return route;
   }
}

