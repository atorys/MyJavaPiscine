package ex00_Runnable;

public class Program {

    public static int steps = 0;

    public static void main(String[] args) {
        if (!isValidArguments(args)) {
            return;
        }

        Runnable task = () -> {
            for (int i = 0; i < steps; ++i) {
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
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < steps; ++i) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    public static boolean isValidArguments(String[] args) {
        if (args.length != 1 || !args[0].startsWith("--count") || !args[0].contains("=")) {
            System.out.println("Invalid argument");
            return false;
        }
        try {
            steps = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
        } catch (NumberFormatException e) {
            System.out.println("Invalid number");
            return false;
        }
        return true;
    }
}
