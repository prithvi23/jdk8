package demo8;

import java.util.Random;

public class App {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Starting...");
		Thread t1 = new Thread(()->{
			Random random = new Random();
			for(int i = 0; i< 1E8; i++){
				if (Thread.currentThread().isInterrupted()){
					System.out.println("Interrupted");
					break;
				}
				Math.sin(random.nextDouble());
			}
		});
		t1.start();
		Thread.sleep(500);
		t1.interrupt(); // This actually does not stop the thread - but sets a flag to say that it is interrupted 
		t1.join();
		System.out.println("Finished");
	}

}
