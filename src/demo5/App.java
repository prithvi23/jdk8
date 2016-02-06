package demo5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demo uses Executor service and a lamda to a Runnable
 * @author prithvi
 *
 */
public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(()->{printSomething();});
		shutdownExecutor(executor);
	}

	private static void printSomething() {
		for(int i = 0; i < 10; i++){
			System.out.println("Hello "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void shutdownExecutor(ExecutorService executor){
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}
		finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}
}
