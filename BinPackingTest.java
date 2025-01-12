package com.PathSquare;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class BinPackingTest {
    public static void main (String[] args) {
        BinPacking binPacking = new BinPacking();
        
        //Case 1
        List<BinPacking.Item> items = new ArrayList<>();
        items.add(new Binpacking.Item(4, "Item1"));
        items.add(new BinPacking.Item(8, "Item2"));
        items.add(new BinPacking.Item(1, "Item3"));
        items.add(new BinPacking.Item(6, "Item5"));

        int binCapacity = 10;
        System.out.println("Case 1: Basic Test");
        List<BinPacking.Bin> bins = binPacking.solveBinPacking(items, binCapacity);
        printBins(bins);

        //Case 2: All items fit in one bin
        items.clear();
        items.add(new BinPacking.Item(2, "Item1"));
        items.add(new BinPacking.Item(3, "Item2"));
        items.add(new BinPacking.item(4, "Item3"));

        binCapacity = 20;
        System.out.println("\nCase 2: All items in one bin")
        bins = binPacking.solveBinPacking(items, binCapacity);
        printBins(bins);

        //Case 3: Items need multiple bins 
        items.clear();
        items.add(new BinPacking.Item(9, "Item1"));
        items.add(new BinPacking.Item(5, "Item2"));
        items.add(new BinPacking.Item(7, "Item3"));
        items.add(new BinPacking.Item(6, "Item4"));
        items.add(new BinPacking.Item(8, "Item5"));

        binCapacity = 10;
        System.out.println("\nCase 3: Items need multiple bins");
        bins = binPacking.solveBinPacking(items, binCapacity);
        printBins(bins);

        //Case 4: One heavy item exceeding bin capacity
        items.clear();
        items.add(new BinPacking.Item(15, "Item1"));
        binCapacity = 10;
        System.out.println("\nCase 4: One heavy item exceeding bin capacity");
        printBins(bins);
    }
    
    private static void printBins(List<BinPacking.Bin> bins) {
        for (int i = 0; i < bins.size(); i++) {
            System.out.println("Bin " + (i+1) + ": " + bins.get(i));
        }
    }
}