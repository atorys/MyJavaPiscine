package ex01_Synchronized;

public class Printer {

	private static String	lastCalledThread;

	public Printer(String first) {
		lastCalledThread = first;
	}
	public synchronized void print() {
		while (Thread.currentThread().getName().equals(lastCalledThread)) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(lastCalledThread);
		lastCalledThread = Thread.currentThread().getName();
		notify();
	}
}
