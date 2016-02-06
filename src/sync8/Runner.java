package sync8;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private int count;
	private Lock lock = new ReentrantLock();
	private Condition cond = lock.newCondition();
	
	private void increment() {
		for(int i = 0; i < 10000; i++){
			count++;
		}
	}
	public void firstThread() throws InterruptedException{
		increment();
	}
	public void secondThread() throws InterruptedException{
		increment();
	}
	public void firstThreadWithLock() throws InterruptedException{
		lock.lock();
		// after locking make sure that all the other code below is within
		// a try catch - so that there is no infinite loop
		System.out.println("Waiting..");
		cond.await();
		System.out.println("Woken up!");
		try{
			increment(); 
		}finally{
			lock.unlock();
		}
	}
	public void secondThreadWithLock() throws InterruptedException{
		Thread.sleep(1000);
		lock.lock();
		System.out.println("Press the return key!");
		new Scanner(System.in).nextLine();
		System.out.println("Got return key");
		cond.signal();
		try{
			increment(); 
		}finally{
			lock.unlock();
		}
	}
	public void finished(){
		System.out.println("Count is :"+count);
		count = 0;
	}
	
}
