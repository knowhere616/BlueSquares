import java.util.Scanner;

public class Vehicle {
    private int capacity;
    private int currentLoad;

    // Default constructor
    public Vehicle() {
        this.capacity = 0;
        this.currentLoad = 0;
    }

    // Method to initialize a vehicle
    public void initializeVehicle(Scanner scanner) {
        int inputCapacity = 0;

        // Input validation for vehicle capacity
        while (true) {
            System.out.print("Enter vehicle capacity: ");
            if (scanner.hasNextInt()) {
                inputCapacity = scanner.nextInt();
                if (inputCapacity > 0) {
                    break;
                } else {
                    System.out.println("Capacity must be a positive number.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Consume invalid input
            }
        }

        this.capacity = inputCapacity;
        this.currentLoad = 0; // Initial load is 0
    }

    // Method to reset the vehicle's current load to capacity
    public void resetVehicle() {
        this.currentLoad = capacity;
    }

    // Get vehicle details
    public String getVehicle() {
        return "Capacity: " + capacity + ", Current Load: " + currentLoad;
    }

    // Get the capacity of the vehicle
    public int getCapacity() {
        return capacity;
    }

    // Get the current load of the vehicle
    public int getCurrentLoad() {
        return currentLoad;
    }
}