package com.PathSquare;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    public void testInitializeVehicle() {
        Scanner scanner = new Scanner("5\n");
        Vehicle vehicle = new Vehicle();

        assertEquals(5, vehicle.getCapacity());
        assertEquals(0, vehicle.getCurrentLoad());
    }

    @Test
    public void testResetVehicle() {
        Vehicle vehicle = new vehicle();
        vehicle.initializeVehicle(new Scanner("10\n"));
        vehicle.resetVehicle();

        assertEquals(10, vehicle.getCurrentLoad());
    }

    @Test 
    public void testDeliverPackage() {
        Vehicle vehicle = new Vehicle();
        vehicle,initializeVehicle(new Scanner("5\n"));
        vehicle.resetVehicl();
        vehicle.deliverPackage();

        assertEquals(4, vehicle.getCurrentLoad());
    }
}
