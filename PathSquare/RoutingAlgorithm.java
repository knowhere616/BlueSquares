import java.util.List;

// Το όρισμα numVehicles θέλει αλλαγη; - fleet ή vehicle;
public interface RoutingAlgorithm {
    Lists<List<Integer>> solve(Graph graph, int numVehicles, int depot);
}
