import java.util.HashMap;
import java.util.Map;

public class Node {
    private int id;
    private String address;
    private double latitude;
    private double longitude;
    private Map<Integer, Double> distances; // Stores distances to other nodes by ID

    // Constructor for manual input (distances known)
    public Node(int id) {
        this.id = id;
        this.distances = new HashMap<>();
    }

    // Constructor for real addresses (coordinates known)
    public Node(int id, String address, double latitude, double longitude) {
        this.id = id;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.distances = new HashMap<>();
    }

    // Add a distance to another node
    public void addDistance(int nodeId, double distance) {
        distances.put(nodeId, distance);
    }

    // Get distance to another node
    public double getDistanceTo(int nodeId) {
        return distances.getOrDefault(nodeId, Double.MAX_VALUE); // Return large value if no distance found
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        if (address != null) {
            return "Node " + id + ": " + address + " (" + latitude + ", " + longitude + ")";
        } else {
            return "Node " + id + ": (Manually Entered)";
        }
    }
}
