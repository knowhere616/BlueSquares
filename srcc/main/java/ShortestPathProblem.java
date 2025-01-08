import java.util.Arrays;

public class ShortestPathProblem {

    // Utility function to find the vertex with the minimum
    // distance value, from the set of vertices not yet
    // included in the shortest path tree
    public int minDistance(int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Utility function to print the constructed distance array
    public void printSolution(int dist[]) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                System.out.println((i + 1) + " \t\t ∞"); // ∞ for unreachable nodes
            } else {
                System.out.println((i + 1) + " \t\t " + dist[i]);
            }
        }
    }

    // Function that implements Dijkstra's single-source shortest path algorithm for a graph
    public void dijkstra(int graph[][], int src) {
        int V = graph.length;
        int[] dist = new int[V]; // The output array, dist[i] holds the shortest distance from src to i
        Boolean[] sptSet = new Boolean[V]; // sptSet[i] will be true if vertex i is included in shortest path tree or distance is finalized

        // Initialize distances to infinity and sptSet to false
        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(sptSet, false);
        dist[src] = 0; // Distance from source to itself is always 0

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed
            int u = minDistance(dist, sptSet);
            sptSet[u] = true; // Mark the picked vertex as processed

            // Update dist[] values of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v]; // Update the distance if a shorter path is found
                }
            }
        }

        // Print the solution (distances from source to all vertices)
        printSolution(dist);
    }

}