package demo6;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * This method demos the Callable of and Future of an integer
 * @author prithvi
 *
 */
public class App {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<Integer> task = () -> printSomething();
		Future<Integer> result = executor.submit(task);
		while (!result.isDone()){
			System.out.println("Result "+result.get());
		}
		shutdownExecutor(executor);
	}
	
	private static int printSomething() {
		int i = 0;
		for(i = 0; i < 10; i++){
			System.out.println("Hello "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return i;
	}
	
	private static void shutdownExecutor(ExecutorService executor){
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(1, TimeUnit.DAYS);
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
