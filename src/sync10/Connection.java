package sync10;

import java.util.concurrent.Semaphore;

public class Connection {
	private static Connection instance = new Connection();
	private int connections = 0;
	// Fairness param - it means whichever called aquire first will be the 
	// first to permit when permit is available - may be it is performant if false
	// To limit the connections to 10 at the most
	private Semaphore sem = new Semaphore(10, true);
	private Connection(){}
	public static Connection getInstance(){
		return instance;
	}
	
	public void connect(){
//		sem.acquire();
//		System.out.println("Acquired");
//		sem.release();
//		System.out.println("Released");
//		System.out.println("Available permits:"+sem.availablePermits());
//		Connection.getInstance().connect();
		try {
			sem.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try{
			doConnect();
		}finally{
			sem.release();
		}
	}
	private void doConnect(){
		synchronized(this){
			connections++;
			System.out.println("Current connections:"+connections);
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		synchronized(this){
			connections--;
		}
	}
}
