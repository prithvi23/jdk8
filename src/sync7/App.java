package sync7;

import sync7.Processor;

public class App {

	private static void runThreads(Thread tx, Thread ty){
		System.out.println("Starting...");
		tx.start();
		ty.start();
		/*
		 * Make sure that the threads complete by calling the join
		 */
		try {
			tx.join();
			ty.join();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Processor processor = new Processor();
		Thread t1 = new Thread(() -> {
			try {
				processor.produce();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				processor.consume();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		runThreads(t1, t2);
	}
}
