package sync6;

import java.util.Scanner;

public class Processor {
	public void produce() throws InterruptedException{
		synchronized(this){
			System.out.println("Producer thread running...");
			wait(); // Might hang indefinitely - instead use a timeout. Can only call within 
					// synchronized block and it will lose the lock after wait
			System.out.println("Resumed producer.");
		}
	}
	public void consume() throws InterruptedException{
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized(this){
			System.out.println("Consumer thread running...");
			System.out.println("Waiting for return key");
			scanner.nextLine();
			System.out.println("Return key pressed");
			notify(); // Same as wait - can only be within a synch block and may wait indef
			Thread.sleep(5000);
			// It notifies all the threads that are waiting on the same lock
			System.out.println("Resumed consumer.");
		}
	}
}
