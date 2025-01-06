import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinPacking {

    public static class Bin {
        private int capacity;
        private int currentLoad;
        private List<String> items; // Store item identifiers

        public Bin(int capacity) {
            this.capacity = capacity;
            this.currentLoad = 0;
            this.items = new ArrayList<>();
        }

        public boolean addItem(int itemWeight, String itemIdentifier) {
            if (currentLoad + itemWeight <= capacity) {
                items.add(itemIdentifier + " (Weight: " + itemWeight + ")");
                currentLoad += itemWeight;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Bin [Current Load: " + currentLoad + ", Items: " + items + "]";
        }
    }

    public static class Item {
        private int weight;
        private String identifier;

        public Item(int weight, String identifier) {
            this.weight = weight;
            this.identifier = identifier;
        }

        public int getWeight() {
            return weight;
        }

        public String getIdentifier() {
            return identifier;
        }
    }

    public List<Bin> solveBinPacking(List<Item> items, int binCapacity) {
        // Sort items by weight in descending order
        List<Item> mutableItems = new ArrayList<>(items);
        Collections.sort(mutableItems, (a, b) -> Integer.compare(b.getWeight(), a.getWeight()));

        List<Bin> bins = new ArrayList<>();
        for (Item item : mutableItems) {
            boolean placed = false;
            for (Bin bin : bins) {
                if (bin.addItem(item.getWeight(), item.getIdentifier())) {
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                Bin newBin = new Bin(binCapacity);
                newBin.addItem(item.getWeight(), item.getIdentifier());
                bins.add(newBin);
            }
        }
        return bins;
    }
}
