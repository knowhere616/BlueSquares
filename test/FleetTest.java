package com.PathSquare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FleetTest {

    @Test
    public void testInitializeFleet() {
        Scanner scanner = new Scanner("2\n5\n10\n");
        Fleet fleet = new Fleet();
        fleet.initializeFleet(scanner);

        assertEquals(2, fleet.getVehicles().size());
        assertEquals(5, fleet.getVehicles().get(0).getCapacity());
        assertEquals(10, fleet.getVehicles().get(1).getCapacity());
    }

    @Test
    public void testSetAndGetVehicles() {
        Fleet fleet = new Fleet();
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle());
        fleet.setVehicles(vehicles);

        assertEquals(1, fleet.getVehicles().size());
    }

    @Test
    public void testResetFleet() {
        Fleet fleet = new Fleet();
        Vehicle vehicle = new Vehicle();
        vehicle.initializeVehicle(new Scanner("10\n"));
        fleet.setVehicles(List.of(vehicle));

        fleet.resetFleet();
        assertEquals(vehicle.getCapacity(), vehicle.getCurrentLoad());
    }
}