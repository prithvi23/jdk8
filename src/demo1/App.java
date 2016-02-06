package demo1;

class Runner extends Thread{

	@Override
	public void run() {
		for(int i = 0; i < 10; i++){
			System.out.println("Hello "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		super.run();
	}
	
}
/*
 * This method demos the Thread overriding
 * It is important not to call the run method on the thread and the start method
 * so it does not run in the same method as the caller
 * */
public class App {

	public static void main(String[] args) {
		Runner runner1 = new Runner();
		runner1.start();
		Runner runner2 = new Runner();
		runner2.start();
	}

}

