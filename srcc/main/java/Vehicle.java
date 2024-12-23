public class Vehicle {
    private double capacity;

    public Vehicle(double capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Vehicle capacity must be a positive floating point number!");
        }
        this.capacity = capacity;
    }

    public double getCapacity() {
        return capacity;
    }
}
