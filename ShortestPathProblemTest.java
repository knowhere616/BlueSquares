public class ShortestPathProblemTest {

    public static void main(String[] args) {
        ShortestPathProblem shortestPathProblem = new ShortestPathProblem();

        //Case 1: Basic Graph
        System.out.println("Case 1: Basic Graph");
        int[][] graph1 = {
            {0, 10, 0, 0, 0},
            {0, 0, 5, 0, 0},
            {0, 0, 0, 20, 1},
            {0, 0, 0, 0, 0}
        };
        shortestPathProblem.dijkstra(graph1, 0);
        System.out.println();

        //Case 2: Graph with Unreachable Nodes
        System.out.println("Case 2: Graph with Unreachable Nodes");
        int[][] graph2 = {
            {0, 8, 0, 0},
            {0, 0, 7, 0},
            {0, 0, 0, 4},
            {0, 0, 0, 0}
        };
        shortestPathProblem.dijkstra(graph2, 0);
        System.out.println();

        //Case 3: Complete Graph
        System.out.println("Case 3: Complete Graph");
        int[][] graph3 = {
            {0, 1, 2, 3},
            {1, 0, 4, 5},
            {2, 4, 0, 6},
            {3, 5, 6, 0}
        };
        shortestPathProblem.dijkstra(graph3, 0);
        System.out.println();

        //Case 4: Large Graph
        System.out.println("Test Case 4: Large Graph");
        int[][] graph4 = {
            {0, 3, 0, 0, 0, 0},
            {3, 0, 2, 1, 0, 0},
            {0, 2, 0, 4, 0, 0},
            {0, 1, 4, 0, 5, 0},
            {0, 0, 0, 5, 0, 6},
            {0, 0, 0, 0, 6, 0}
        };
        shortestPathProblem.dijkstra(graph4, 0);
        System.out.println();

        //Case 5: Disconnected graph
        System.out.println("Test Case 5: Disconnected Graph");
        int[][] graph5 = {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
        };
        shortestPathProblem.dijkstra(graph5, 0);
        System.out.println(); 
    }
}