import java.util.List;

public interface RoutingAlgorithm {
    Lists<List<Integer>> solve(Graph graph, int numVehicles, int depot);
}
