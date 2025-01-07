public class Node {
    private String name;
    private int index; // The index of the node in the list of nodes
    private double demand; // Demand of the node

    // Constructor with validation
    public Node(String name, int index, double demand) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Node name cannot be empty!");
        }
        if (demand < 0) {
            throw new IllegalArgumentException("Demand cannot be negative!");
        }
        this.name = name;
        this.index = index;
        this.demand = demand;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }

    public double getDemand() {
        return demand;
    }
}
