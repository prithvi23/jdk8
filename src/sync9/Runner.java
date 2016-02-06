package sync9;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * Both methods got one lock and is waiting on the other lock
 * and both wont release it so it will be a deadlock
 * @author prithvi
 *
 */
public class Runner {
	private Account acc1 = new Account();
	private Account acc2 = new Account();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
	private Lock lock3 = new ReentrantLock();
	private Lock lock4 = new ReentrantLock();
	
	private void aquireLocks(Lock firstLock, Lock secondLock) throws InterruptedException{
		while(true){
			// Aquire
			boolean gotFirstLock = false;
			boolean gotSecondLock = false;
			try {
				gotFirstLock = firstLock.tryLock(); // Use a timeout
				gotSecondLock = secondLock.tryLock();
			} finally{
				if (gotFirstLock && gotSecondLock)
					return;
				if(gotFirstLock){
					firstLock.unlock();
				}
				if (gotSecondLock){
					secondLock.unlock();
				}
			}
			// Locks not got
			Thread.sleep(1);
		}
	}
	
	public void firstThread() throws InterruptedException{
		Random random = new Random();
		for(int i= 0; i < 10000; i++){
			Account.transfer(acc1,acc2,random.nextInt(100));
		}
	}
	public void secondThread() throws InterruptedException{
		Random random = new Random();
		for(int i= 0; i < 10000; i++){
			Account.transfer(acc2,acc1,random.nextInt(100));
		}
	}
	public void firstThreadWithDeadLock() throws InterruptedException{
		Random random = new Random();
		for(int i= 0; i < 10000; i++){
			lock1.lock();
			lock2.lock();
			try{
				Account.transfer(acc1,acc2,random.nextInt(100));
			} finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	public void secondThreadWithDeadLock() throws InterruptedException{
		Random random = new Random();
		for(int i= 0; i < 10000; i++){
			lock2.lock();
			lock1.lock();
			try{
				Account.transfer(acc2,acc1,random.nextInt(100));
			}finally{
				lock1.unlock();
				lock2.unlock();
			}
		}
	}
	public void firstThreadSafeLock() throws InterruptedException{
		Random random = new Random();
		for(int i= 0; i < 10000; i++){
			aquireLocks(lock3, lock4);
			try{
				Account.transfer(acc1,acc2,random.nextInt(100));
			} finally{
				lock3.unlock();
				lock4.unlock();
			}
		}
	}
	public void secondThreadSafeLock() throws InterruptedException{
		Random random = new Random();
		for(int i= 0; i < 10000; i++){
			aquireLocks(lock3, lock4);
			try{
				Account.transfer(acc2,acc1,random.nextInt(100));
			}finally{
				lock3.unlock();
				lock4.unlock();
			}
		}
	}
	public void finished(){
		System.out.println("Account 1 balance:" + acc1.getBalance());
		System.out.println("Account 2 balance:" + acc2.getBalance());
		System.out.println("Total balance:" + (acc1.getBalance()+acc2.getBalance()));
		acc1.reset();
		acc2.reset();
	}
	
}
