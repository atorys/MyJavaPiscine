package ex01_Synchronized;

public class Program {

    public static int steps = 0;

    public static void main(String[] args) {
        if (!isValidArguments(args)) {
            return;
        }

        Speaker printer = new Speaker();

        Runnable task = () -> {
            for (int i = 0; i < steps; ++i) {
//                synchronized (printer) {
                    printer.speak(Thread.currentThread().getName());
//                }
            }
        };

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
