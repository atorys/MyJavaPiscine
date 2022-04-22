package ex02_Multythreading;

import java.util.Random;

public class Program {

    public static int arraySize = 0;
    public static int threadsCount = 0;

    public static void main(String[] args) {
        if (!isValidArguments(args))
            return ;

        System.out.println("Sum: " + arraySize);
        int[] array = new int[arraySize];
        fillArray(array, 1000);

        Runnable task = () -> {
            int counter = 0;
            int block = arraySize/threadsCount;
            int id = Integer.parseInt(Thread.currentThread().getName());
            for (int i = block * id; i < block * (id + 1) && i < arraySize; ++i)
                counter++;
            System.out.printf("Thread %d: from %d to %d sum is %d\n", id + 1, block * id, block * (id + 1), counter);
            synchronized (this) {

            }
        };

        Thread[] threadsArray = new Thread[threadsCount];
        for (int i = 0; i < threadsCount; ++i)
            threadsArray[i] = new Thread(task, String.valueOf(i));
        for (Thread thread : threadsArray)
            thread.start();
        for (Thread thread : threadsArray) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fillArray(int[] array, int modulo) {
        Random magician = new Random();
        for (int i = 0; i < array.length; ++i) {
            array[i] = magician.nextInt(modulo);
            System.out.printf("%d ", array[i]);
        }
    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 2 || !args[0].startsWith("--arraySize") || !args[0].contains("=") ||
                !args[1].startsWith("--threadsCount") || !args[1].contains("=")) {
            System.out.println("Invalid argument --arraySize=? --threadsCount=?");
            return false;
        }
        try {
            arraySize = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
            threadsCount = Integer.parseInt(args[1].substring(args[1].indexOf("=") + 1));
            if (arraySize > 2000000 || threadsCount > arraySize) {
                System.out.println("Invalid number: arraySize > 2000000 or threadsCount > arraySize");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
            return false;
        }
        return true;
    }
}
