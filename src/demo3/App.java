package demo3;

/**
 * This method uses a Lamda for the runnable implementation
 * @author prithvi
 *
 */
public class App {

	public static void main(String[] args) {
		Runnable task = () -> {
			printSomething();
		};
		
		Thread t1 = new Thread(task); 
		Thread t2 = new Thread(task);
		t1.start();
		t2.start();
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

}
