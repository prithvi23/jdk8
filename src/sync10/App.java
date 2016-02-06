package sync10;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Demonstrates Semaphores 
 * Lock / unlock has to happen in the same thread
 * However with aquire and release you can do it in different threads
 * @author prithvi
 *
 */
public class App {

	public static void main(String[] args) throws Exception{

		ExecutorService executor = Executors.newCachedThreadPool();
		for(int i = 0; i < 200;i++){
//			executor.submit(new Runnable(){
//				@Override
//				public void run() {
//					Connection.getInstance().connect();
//				}
//			}); Can be written as below :)
			executor.submit(() -> Connection.getInstance().connect());
		}
		shutdownExecutor(executor);	
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
