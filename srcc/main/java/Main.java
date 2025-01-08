import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       while (true) {
           // Display menu options
           System.out.println("\nMenu:");
           System.out.println("1. Solve Vehicle Routing Problem (VRP)");
           System.out.println("2. Solve Bin Packing Problem");
           System.out.println("3. Solve Linear Sum Assignment Problem");
           System.out.println("4. Solve Shortest Path Problem");
           System.out.println("5. Solve Knapsack Problem");
           System.out.println("6. Exit");
           System.out.print("Choose an option: ");
           int choice = getPositiveInteger(scanner);
           switch (choice) {
               case 1:
                   solveVRP(scanner);
                   break;
               case 2:
                   solveBinPacking(scanner);
                   break;
               case 3:
               System.out.print("Enter the number of workers/tasks: ");
                   int n = scanner.nextInt();
                   LinearSumAssignmentProblem.solveLinearSumAssignment(n, scanner);
                   break;
               case 4:
                   solveShortestPath(scanner);
                   break;
               case 5:
                   solveKnapsack(scanner);
                   break;
               case 6:
                   System.out.println("Exiting...");
                   scanner.close();
                   return;
               default:
                   System.out.println("Invalid choice. Please choose again.");
           }
       }
   }
   private static void solveVRP(Scanner scanner) {
       // User input for nodes
       List<Node> nodes = new ArrayList<>();
       System.out.println("Enter the number of nodes (including depot): ");
       int nodeCount = getPositiveInteger(scanner);
       // User input for depot
       System.out.println("Enter the index of the depot (1 to " + nodeCount + "): ");
       int depotIndex = getPositiveInteger(scanner) - 1;
       if (depotIndex < 0 || depotIndex >= nodeCount) {
           System.out.println("Invalid depot index. Setting default depot as node 1.");
           depotIndex = 0;
       }
       Node depot = null; // Initialize the depot
       for (int i = 0; i < nodeCount; i++) {
           System.out.println("Enter name for Node " + (i + 1) + ": ");
           String name = scanner.nextLine();
           // User input for demand of nodes
           double demand = 0;
           if (i != depotIndex) {
               System.out.println("Enter demand for Node " + (i + 1) + ": ");
               demand = getPositiveDouble(scanner);
           }
           try {
               Node node = new Node(name, i, demand); // Pass the index of node
               nodes.add(node);
               if (i == depotIndex) {
                   depot = node; // Set the depot
               }
           } catch (IllegalArgumentException e) {
               System.out.println("Error: " + e.getMessage());
               i--; // Ask again
           }
       }
       if (depot == null) {
           throw new IllegalStateException("Depot must be defined!");
       }
       // Move depot to index 0
       nodes.remove(depotIndex); // Remove depot from its current position
       nodes.add(0, depot); // Add depot to the beginning of the list
       // Input the fleet size
       int fleetSize = 0;
       boolean validFleet = false;
       while (!validFleet) {
           System.out.println("Enter the number of vehicles (positive integer): ");
           fleetSize = getPositiveInteger(scanner);
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
               double capacity = getPositiveDouble(scanner);
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
                   System.out.println("Enter the distance between " + nodes.get(i).getName() + " and "
                           + nodes.get(j).getName() + ": ");
                   distanceMatrix[i][j] = getPositiveDouble(scanner);
                   distanceMatrix[j][i] = distanceMatrix[i][j]; // Symmetric distance
               }
           }
       }
       // Creating fleet and VRP objects
       Fleet fleet = new Fleet(fleetSize);
       VRP vrp = new VRP(nodes, fleet, vehicles);
       vrp.setDistanceMatrix(distanceMatrix); // Set the distance matrix for the VRP solver
       // Set depot explicitly if required by your design
       System.out.println("\nDepot is set to: " + depot.getName());
       // Solve the VRP
       vrp.solve();
   }
   private static void solveBinPacking(Scanner scanner) {
       // Input the number of items
       System.out.print("Enter the number of items: ");
       int numItems = getPositiveInteger(scanner);
       // Input the weights of the items
       List<BinPacking.Item> items = new ArrayList<>();
       for (int i = 0; i < numItems; i++) {
           System.out.print("Enter weight of item " + (i + 1) + ": ");
           int weight = getPositiveInteger(scanner);
           items.add(new BinPacking.Item(weight, "Item " + (i + 1)));
       }
       // Input the bin capacity
       System.out.print("Enter the bin capacity: ");
       int binCapacity = getPositiveInteger(scanner);
       // Solve the bin-packing problem
       BinPacking binPacking = new BinPacking();
       List<BinPacking.Bin> bins = binPacking.solveBinPacking(items, binCapacity);
       // Output the solution
       System.out.println("\nBin Packing Solution:");
       for (int i = 0; i < bins.size(); i++) {
           System.out.println("Bin " + (i + 1) + ": " + bins.get(i));
       }
   }
   private static void solveLinearSumAssignment(Scanner scanner) {
       System.out.print("Enter the number of workers/tasks: ");
       int n = getPositiveInteger(scanner);
       int[][] costMatrix = new int[n][n];
       System.out.println("Enter the cost matrix (n x n):");
       for (int i = 0; i < n; i++) {
           for (int j = 0; j < n; j++) {
               System.out.print("Cost from task " + (i + 1) + " to worker " + (j + 1) + ": ");
               costMatrix[i][j] = getPositiveInteger(scanner);
           }
       }
       LinearSumAssignmentProblem lsaProblem = new LinearSumAssignmentProblem();
       int minCost = lsaProblem.hungarianAlgorithm(costMatrix);
       System.out.println("Minimum Cost Assignment: " + minCost);
   }

// To solve the Shortest Path Problem:
private static void solveShortestPath(Scanner scanner) {
   System.out.print("Enter the number of vertices: ");
   int n = getPositiveInteger(scanner);
   int[][] graph = new int[n][n];
   // Prompting user for the edges of each vertex
   for (int i = 0; i < n; i++) {
       System.out.print("Enter the number of edges from vertex " + (i + 1) + ": ");
       int numEdges = getPositiveInteger(scanner);
       for (int j = 0; j < numEdges; j++) {
           System.out.print("Enter destination vertex and weight (vertex-1 weight): ");
           int dest = getPositiveInteger(scanner) - 1;  // Index for destination vertex
           int weight = getPositiveInteger(scanner);
           // Ensuring no self-links
           if (i != dest) {
               graph[i][dest] = weight;    // Populate edge for vertex i -> dest
               graph[dest][i] = weight;    // Also populate edge for dest -> i (undirected graph)
           }
       }
   }
   // Instantiate ShortestPathProblem class and solve
   ShortestPathProblem shortestPath = new ShortestPathProblem();
   shortestPath.dijkstra(graph, 0);  // Source vertex is 0
}

   private static void solveKnapsack(Scanner scanner) {
       System.out.println("Enter the number of items: ");
       int numItems = getPositiveInteger(scanner);
       int[] weights = new int[numItems];
       int[] values = new int[numItems];
       // Input weights and values of the items
       for (int i = 0; i < numItems; i++) {
           System.out.print("Enter weight of item " + (i + 1) + ": ");
           weights[i] = getPositiveInteger(scanner);
           System.out.print("Enter value of item " + (i + 1) + ": ");
           values[i] = getPositiveInteger(scanner);
       }
       // Input the knapsack capacity
       System.out.print("Enter the capacity of the knapsack: ");
       int capacity = getPositiveInteger(scanner);
       // Solving using KnapsackProblem class
       KnapsackProblem solver = new KnapsackProblem();
       int maxProfit = solver.knapsack(weights, values, capacity);
       System.out.println("Maximum profit possible: " + maxProfit);
   }
   private static int getPositiveInteger(Scanner scanner) {
       while (true) {
           try {
               int value = Integer.parseInt(scanner.nextLine());
               if (value > 0) {
                   return value;
               } else {
                   System.out.println("Please enter a positive integer.");
               }
           } catch (NumberFormatException e) {
               System.out.println("Invalid input. Please enter a valid integer.");
           }
       }
   }
   private static double getPositiveDouble(Scanner scanner) {
       while (true) {
           try {
               double value = Double.parseDouble(scanner.nextLine());
               if (value > 0) {
                   return value;
               } else {
                   System.out.println("Please enter a positive number.");
               }
           } catch (NumberFormatException e) {
               System.out.println("Invalid input. Please enter a valid number.");
           }
       }
   }
}