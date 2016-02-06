package sync9;

import sync9.Runner;

/**
 * Resolves a deadlock using the trylock
 * @author prithvi
 *
 */
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
		Runner runner = new Runner();
		runner.finished();
		Thread t1 = new Thread(() -> {
			try {
				runner.firstThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t2 = new Thread(() -> {
			try {
				runner.secondThread();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		runThreads(t1, t2);
		runner.finished();
		Thread t3 = new Thread(() -> {
			try {
				runner.firstThreadSafeLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t4 = new Thread(() -> {
			try {
				runner.secondThreadSafeLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		runThreads(t3, t4);
		runner.finished();
		Thread t5 = new Thread(() -> {
			try {
				runner.firstThreadWithDeadLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t6 = new Thread(() -> {
			try {
				runner.secondThreadWithDeadLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		runThreads(t5, t6);
		runner.finished();
	}


}
