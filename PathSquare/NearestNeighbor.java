import java.util.ArrayList;
import java.util.List;

public class NearestNeighbor implements RoutingAlgorithm {
    @Override
    public List<List<Integer>> solve(Graph graph, int numVehicles, int depot) {
        List<List<Integer>> routes = new ArrayList<>();
        List<Integer> singleRoute = new RouteOptimizer().nearestNeighbor(graph, depot);
        routes.add(singleRoute);
        return routes;
    }
}
