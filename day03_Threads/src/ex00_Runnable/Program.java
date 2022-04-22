package ex00_Runnable;

public class Program {

    public static int STEPS = 0;

    public static void main(String[] args) {
        if (!isValidArguments(args)) {
            return;
        }

        Runnable task = () -> {
            for (int i = 0; i < STEPS; ++i) {
                System.out.println(Thread.currentThread().getName());
            }
        };

        Thread.currentThread().setName("Human");
        Thread thread1 = new Thread(task, "Egg");
        Thread thread2 = new Thread(task, "Hen");
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        for (int i = 0; i < STEPS; ++i) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count") || !args[0].contains("=")) {
            System.err.println("Invalid argument --count=?");
            return false;
        }
        try {
            STEPS = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
        } catch (NumberFormatException e) {
            System.err.println("Invalid number");
            return false;
        }
        return true;
    }
}
