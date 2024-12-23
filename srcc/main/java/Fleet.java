public class Fleet {
    private int size;

    public Fleet(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Fleet size must be a positive integer!");
        }
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
