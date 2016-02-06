package sync2;

/**
 * Atomic integers can help as it has a method to increment threadsafe
 * or you can synchronize the code
 * @author prithvi
 *
 */
public class App {
	private int count = 0;

	public static void main(String[] args) {
		App app = new App();
		app.doWork();
	}
	
	public synchronized void increment(){
		count++;
	}
	
	private void incrementCount(int times){
		for(int i = 0; i < times; i++){
			count++;
		}
	}
	
	private void incrementCountSynchronously(int times){
		for(int i = 0; i < times; i++){
			increment();
		}
	}
	
	private void runThreads(Thread tx, Thread ty){
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
		
		System.out.println("Count is "+ count);
		count = 0;
	}

	private void doWork() {
		
		Thread t1 = new Thread(()-> {
			for(int i = 0; i < 10000; i++){
				count++;
			}
		});
		Thread t2 = new Thread(()-> {
			for(int i = 0; i < 10000; i++){
				count++;
			}
		});
		runThreads(t1,t2);
		
		Thread t3 = new Thread(()-> incrementCount(10000));
		Thread t4 = new Thread(()-> incrementCount(10000));
		runThreads(t3,t4);
		
		Thread t5 = new Thread(()-> incrementCountSynchronously(10000));
		Thread t6 = new Thread(()-> incrementCountSynchronously(10000));
		runThreads(t5,t6);
	}

}
