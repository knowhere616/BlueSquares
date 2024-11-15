import java.util.List;

public class Graph {
    private final List<Node> nodes;
    private final double[][] distanceMatrix;

    Graph(List<Node> nodes) {
        this.nodes = nodes;
        this.distanceMatrix = calculateDistances();
    }

    private double[][] calculateDistances() {
        int n = nodes.size();
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = nodes.get(i).distanceTo(nodes.get(j));
            }
        }
        return matrix;
    }

    public double getDistance(int from, int to) {
        return distanceMatrix[from][to];
    }

    public int getNumberOfNodes() {
        return nodes.size();
    }    
}
