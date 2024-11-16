import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the nodes");
        int numNodes = scanner.nextInt();

        List<Node> nodes = new ArrayList<>();
        System.out.println("Enter the coordinates for each node (id x y):");
        for (int i = 0; i < numNodes; i++) {
            System.out.println("Enter details for node " + i + ":");
            System.out.println(" x-coordinate ");
            double x = scanner.nextDouble();
            System.out.println(" y-coordinate ");
            double y = scanner.nextDouble();
            nodes.add(new Node(id, x, y));
        }

        Graph graph = new Graph(nodes);

        System.out.print("Enter the number of vehicles: ")
        int numVehicles = scanner.nextInt();

<<<<<<< HEAD
        System.out.print("Enter the depot node (starting point): ");
        int depot = scanner.nextInt();

        RoutingAlgorithm algorithm;
        System.out.println("Choose algorithm: 1 for NN, 2 for VRP");
        int choice = scanner.nextInt();

        if (choice == 1) {
            algorithm = new NearestNeighbor();
        } else if (choice == 2) {
            algorithm = new VRP();
        } else {
            System.out.println("Invalid choice");
            return;
        }
        
        List<List<Integer>> routes = algorithm.solve(graph, numVehicles, depot);
        System.out.println("Optimized Routes: " + routes);
=======
        scanner.close(); // we close the scanner after the last input!

        List<Integer> route = optimizer.nearestNeighbor(graph, startNode);
        System.out.println("Optimized route: " + route);
>>>>>>> fa31012779f93b9eecbb8e28fa6f063e86665acf
    }
}
