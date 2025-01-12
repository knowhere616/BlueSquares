import java.util.Scanner;

public class LinearSumAssignmentProblemTest {

    public static void main(String[] args) {

        //Case 1: Small cost matrix
        System.out.println("Case 1: Small Cost Matrix");
        int[][] costMatrix1 = {
            {9, 2, 7, 8},
            {6, 4, 3, 7},
            {5, 8, 1, 8},
            {7, 6, 9, 4}
        };
        int minCost1 = LinearSumAssignmentProblem.hungarianAlgorithm(costMatrix1);
        System.out.println("Minimum Cost: " + minCost1);
        System.out.println();

        //Case 2: All costs are equal
        System.out.println("case 2: All Costs Equal");
        int[][] costMatrix2 = {
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
        int minCost2 = LinearSumAssignmentProblem.hungarianAlgorithm(costMatrix2);
        System.out.println("Minimum Cost: " + minCost2);
        System.out.println();

        //Case 3: Single Task and Worker
        System.out.println("case 3: Single Task and Worker");
        int[][] costMatrix3 = {
            {5}
        };
        int minCost3 = LinearSumAssignmentProblem.hungarianAlgorithm(costMatrix3);
        System.out.println("Minimum Cost: " + minCost3);
        System.out.println();

        //Case 4: Large Cost Matrix
        System.out.println("Case 4: Large Cost Matrix");
        int[][] costMatrix4 = {
            {10, 2, 7, 8, 6},
            {6, 4, 3, 7, 9},
            {5, 8, 1, 8, 2},
            {7, 6, 9, 4, 3},
            {8, 5, 6, 2, 7}
        };
        int minCost4 = LinearSumAssignmentProblem.hungarianAlgorithm(costMatrix4);
        System.out.println("Minimum Cost: " + minCost4);
        System.out.println();
        
        //Case 5: Interactive Case
        System.out.println("Case 5: Interactive case");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the cost matrix (matrixSize): ")
        int matrixSize = scanner.nextInt();
        LinearSumAssignmentProblem.solveLinearSumAssignment(matrixSize, scanner);
        scanner.close();
    }
}