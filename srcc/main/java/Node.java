public class Node {
    private String name;
    private int index; // The index of the node in the list of nodes

    // Constructor with validation
    public Node(String name, int index) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Node name cannot be empty!");
        }
        this.name = name;
        this.index = index;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
