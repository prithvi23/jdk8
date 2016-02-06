package demo7;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class App {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		
//		Future<Integer> value = executor.submit(new Callable<Integer>(){
//			public Integer call() {
//				Random random = new Random();
//				int duration = random.nextInt(4000);
//				System.out.println("Starting...");
//				try {
//					Thread.sleep(duration);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				System.out.println("Finished..");
//				return duration;
//			}
//		}); This code can be called as below
		
		Future<Integer> value = executor.submit(()->{
			Random random = new Random();
			int duration = random.nextInt(4000);
			if (duration > 2000){
				throw new IOException();
			}
			System.out.println("Starting...");
			try {
				Thread.sleep(duration);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Finished..");
			return duration;
		});
		
		shutdownExecutor(executor);
		
		try {
			System.out.println("Result is:" + value.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
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
