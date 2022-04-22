package ex02_Multythreading;

import java.util.Random;

public class Program {

    public static int ARRAY_SIZE;
    public static int THREADS_COUNT;
    public static int reference_result = 0;

    public static void main(String[] args) {
        if (!isValidArguments(args))
            return ;

        int[] array = new int[ARRAY_SIZE];
        fillArray(array, 1000);
        for (int i = 0; i < ARRAY_SIZE; ++i)
            reference_result += array[i];
        System.out.println("Sum: " + reference_result);

        Counter counter = new Counter(ARRAY_SIZE, THREADS_COUNT);
        Runnable task = () -> {
                counter.count(array);
        };

        Thread[] threadsArray = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; ++i)
            threadsArray[i] = new Thread(task, Integer.toString(i));
        for (Thread thread : threadsArray)
            thread.start();
        for (Thread thread : threadsArray) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("Sum by threads: %d", counter.getSum());
    }

    public static void fillArray(int[] array, int modulo) {
        Random magician = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = magician.nextInt(modulo*2) - modulo;
        }
    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize") || !args[0].contains("=") ||
                !args[1].startsWith("--threadsCount") || !args[1].contains("=")) {
            System.err.println("Invalid argument --arraySize=? --threadsCount=?");
            return false;
        }
        try {
            ARRAY_SIZE = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
            THREADS_COUNT = Integer.parseInt(args[1].substring(args[1].indexOf("=") + 1));
            if (ARRAY_SIZE > 2000000 || THREADS_COUNT > ARRAY_SIZE || ARRAY_SIZE < 0 || THREADS_COUNT < 0) {
                System.err.println("Invalid number: arraySize > 2000000 or threadsCount > arraySize");
                return false;
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid number");
            return false;
        }
        return true;
    }
}
