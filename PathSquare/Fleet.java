import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Fleet {
    private List<Vehicle> vehicles;

    // Constructor
    public Fleet() {
        vehicles = new ArrayList<>();
    }

    // Method to initialize the fleet
    public void initializeFleet(Scanner scanner) {
        System.out.print("Enter the number of vehicles in the fleet: ");
        int numVehicles = scanner.nextInt();

        for (int i = 0; i < numVehicles; i++) {
            System.out.println("Initializing vehicle " + (i + 1) + ":");
            Vehicle vehicle = new Vehicle();
            vehicle.initializeVehicle(scanner);
            vehicles.add(vehicle);
        }
    }

    // Getter for the fleet (returns a copy for safety)
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles);
    }

    // Setter for the fleet (replaces the fleet entirely)
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = new ArrayList<>(vehicles); // Use a copy to protect internal structure
    }

    // Reset the fleet when returning to the depot
    public void resetFleet() {
        for (Vehicle vehicle : vehicles) {
            vehicle.resetVehicle();
        }
    }

    // Print fleet details
    public void printFleetDetails() {
        System.out.println("Fleet Details:");
        for (int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            System.out.println("Vehicle " + (i + 1) + ": Capacity = " + vehicle.getCapacity() +
                    ", Current Load = " + vehicle.getCurrentLoad());
        }
    }
}