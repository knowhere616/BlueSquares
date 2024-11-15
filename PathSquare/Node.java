public class Node {
    private final int id;
    private final double x, y;

    public Node(int id, double x, double y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public double distanceTo(Node other) {
        //υπολογισμός ευκλείδιας απόστασης μεταξύ κόμβων
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }

    public int getId() {
        return id;
    }
}