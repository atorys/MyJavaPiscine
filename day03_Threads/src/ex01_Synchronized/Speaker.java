package ex01_Synchronized;

public class Speaker {
	public synchronized void speak(String words) {
		System.out.println(words);
		notify();
	}
}
