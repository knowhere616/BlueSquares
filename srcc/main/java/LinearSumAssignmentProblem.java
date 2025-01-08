import java.util.Scanner;

public class LinearSumAssignmentProblem {

    // Hungarian algorithm to solve the Linear Sum Assignment Problem using greedy method (simplified)
    public static int hungarianAlgorithm(int[][] costMatrix) {
        int n = costMatrix.length;

        // To track which task has been assigned to which worker
        boolean[] taskAssigned = new boolean[n];  // To mark assigned tasks
        int[] assignment = new int[n]; // stores the worker assigned to each task

        int minCost = 0;

        // Initialize assignments to -1
        for (int i = 0; i < n; i++) {
            assignment[i] = -1;
        }

        // Step 1: Try to find an optimal assignment with a greedy approach
        for (int i = 0; i < n; i++) {
            int minCostForTask = Integer.MAX_VALUE;
            int workerAssigned = -1;

            for (int j = 0; j < n; j++) {
                // Check if worker is already assigned to a task or not
                if (!taskAssigned[j] && costMatrix[i][j] < minCostForTask) {
                    minCostForTask = costMatrix[i][j];
                    workerAssigned = j;
                }
            }

            // Assign this worker to the current task
            if (workerAssigned != -1) {
                assignment[i] = workerAssigned;
                taskAssigned[workerAssigned] = true;  // mark this worker as assigned
                minCost += costMatrix[i][workerAssigned];  // Add the cost to the total
            }
        }

        // We can print out the assignment here, which might be useful to debug
        System.out.println("Assignment of tasks to workers:");
        for (int i = 0; i < n; i++) {
            System.out.printf("Task %d assigned to Worker %d with cost: %d\n", i + 1, assignment[i] + 1, costMatrix[i][assignment[i]]);
        }

        return minCost;
    }

    // Solving the Linear Sum Assignment Problem from user input
    public static void solveLinearSumAssignment(int n, Scanner scanner) {
        System.out.println("Enter the cost matrix (n x n): ");
        int[][] costMatrix = new int[n][n];

        // Input the cost matrix values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("Cost from task %d to worker %d: ", i + 1, j + 1);
                costMatrix[i][j] = scanner.nextInt();
            }
        }

        // Call Hungarian algorithm to solve the assignment problem
        int minCost = hungarianAlgorithm(costMatrix);
        System.out.println("Minimum cost for assignment: " + minCost);
    }
}