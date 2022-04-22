package ex02_Multythreading;

public class Counter {

    private final int block;

    public Counter(int size, int count) {
        block = size/count;
    }

    synchronized int count() {
        int counter = 0;
        int id = Integer.parseInt(Thread.currentThread().getName());
        for (int i = block * id; i < block * (id + 1) && i < arraySize; ++i)
            counter++;
    }
}
