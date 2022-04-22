package ex03_TooManyThreads;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {
    public static int THREADS_COUNT;

    public static void main(String[] args) {
        if (!isValidArguments(args))
            return;

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> System.out.println(1));
        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--threadsCount") || !args[0].contains("=")) {
            System.out.println("Invalid argument --threadsCount=?");
            return false;
        }
        try {
            THREADS_COUNT = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
            return false;
        }
        return true;
    }
}
