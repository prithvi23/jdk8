package sync8;


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
				runner.firstThreadWithLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread t4 = new Thread(() -> {
			try {
				runner.secondThreadWithLock();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		runThreads(t3, t4);
		runner.finished();
	}

}
