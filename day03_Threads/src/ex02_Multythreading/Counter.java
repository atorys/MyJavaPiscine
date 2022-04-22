package ex02_Multythreading;

public class Counter {

    private final int block;
    private final int size;
    private int lastId;
    private static int sum;

    public Counter(int size, int count) {
        this.block = (int)Math.ceil(size/(float)count);
        this.size = size;
        this.lastId = 0;
    }

    public int getSum() {
        return sum;
    }

    public synchronized void count(int[] array) {
        int counter = 0;
        int id = Integer.parseInt(Thread.currentThread().getName());
        int start;
        int end;
        try {
            while (id != lastId)
                wait();
            start = block * id;
            end = size - start > block ? start + block : size;
            for (int i = start; i < end; ++i) {
                counter += array[i];
            }
            System.out.printf("Thread %d: from %d to %d sum is %d\n", id + 1, start, end - 1, counter);
            sum += counter;
            lastId++;
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
