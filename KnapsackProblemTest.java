public class KnapsackProblemTest {
    
    public static void main(String[] args) {
        KnapsackProblem knapsackProblem = new KnapsackProblem();

        //Case 1: Basic Test (Small Input)
        int[] weights1 = {1, 2, 3};
        int[] values1 = {6, 10, 12};
        int capacity1 = 5;
        System.out.println("Case 1: " + knapsackProblem.knapsack(weights1, values1, capacity1));
        //Expected Value: 22

        //Case 2: Zero Capacity (Edge Case)
        int[] weights2 = {1, 2, 3};
        int[] values2 = {6, 10, 12};
        int capacity2 = 0;
        System.out.println("Case 2: " + knapsackProblem.knapsack(weights2,values2, capacity2));
        //Expected Value: 0

        //Case 3: One Item (Edge Case)
        int[] weights3 = {4};
        int[] values3 = {10};
        int capacity3 = 4;
        System.out.println("Case 3: " + knapsackProblem.knapsack(weights3, values3, capacity3));
        //Expected Value: 10

        //Case 4: All items fit in the knapsack
        int[] weights4 = {1, 2, 3};
        int[] values4 = {6, 10, 12};
        int capacity4 = 6;
        System.out.println("Case 4: " + knapsackProblem.knapsack(weights4, values4, capacity4));
        //Expected Value: 28

        //Case 5: Large Input
        int[] weights5 = {5, 6, 7};
        int[] values5 = {10, 40, 50, 70};
        int capacity5 = 8;
        System.out.println("Case 5: " + knapsackProblem.knapsack(weights5, values5, capacity5));
        //Expected Value: 110

        //Case 6: Items weights exceeding the capacity
        int[] weights6 = {2, 2, 2};
        int[] values6 = {20, 30, 40};
        int capacity6 = 4;
        System.out.println("Case 6: " + knapsackProblem.knapsack(weights6, values6, capacity6));
        //Expected Value: 0

        //Case 7: Multiple items (Same weight - Different Values)
        int[] weights7 = {2, 2, 2};
        int[] values7 = {10, 15, 20};
        int capacity7 = 4;
        System.out.println("Case 7: " + knapsackProblem.knapsack(weights7, values7, capacity7));
    }
}