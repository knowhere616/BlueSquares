import java.util.*;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the nodes");
        int numNodes = scanner.nextInt();

        List<Node> nodes = new ArrayList<>();
        System.out.println("Enter the coordinates for each node (id x y):");
        for (int i = 0; i < numNodes; i++) {
            int id = scanner.nextInt();
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            nodes.add(new Node(id, x, y));
        }

        Graph graph = new Graph(nodes);
        NearestNeighbor optimizer = new NearestNeighbor();

        System.out.println("Enter the starting node:");
        int startNode = scanner.nextInt();

        scanner.close(); // we close the scanner after the last input!

        List<Integer> route = optimizer.nearestNeighbor(graph, startNode);
        System.out.println("Optimized route: " + route);
    }
}
