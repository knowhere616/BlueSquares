import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinPacking {

    public static class Bin {
        private int capacity;
        private int currentLoad;
        private List<Integer> items;

        public Bin(int capacity) {
            this.capacity = capacity;
            this.currentLoad = 0;
            this.items = new ArrayList<>();
        }

        public boolean addItem(int item) {
            if (currentLoad + item <= capacity) {
                items.add(item);
                currentLoad += item;
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Bin [Current Load: " + currentLoad + ", Items: " + items + "]";
        }
    }

    public List<Bin> solveBinPacking(List<Integer> items, int binCapacity) {
        List<Integer> mutableItems = new ArrayList<>(items);
        Collections.sort(mutableItems, Collections.reverseOrder());

        List<Bin> bins = new ArrayList<>();
        for (int item : mutableItems) {
            boolean placed = false;
            for (Bin bin : bins) {
                if (bin.addItem(item)) {
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                Bin newBin = new Bin(binCapacity);
                newBin.addItem(item);
                bins.add(newBin);
            }
        }
        return bins;
    }
}
