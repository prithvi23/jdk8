package sync5;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Demonstrates producer consumer
 * 
 * @author prithvi
 *
 */
public class App {
	private static BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

	private static void producer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			queue.put(random.nextInt(100));
		}
	}

	private static void consumer() throws InterruptedException {
		Random random = new Random();
		while (true) {
			Thread.sleep(100);
			if (random.nextInt(10) == 0) {
				int value = queue.take();
				System.out.println("Taken value: " + value + " Queue size: " + queue.size());
			}
		}
	}
	
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
		Thread t1 = new Thread(() -> {
			try {
				producer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				consumer();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		runThreads(t1, t2);
	}
}
