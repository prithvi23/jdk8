package sync3;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * Demonstrates multiple lock aquisition - to make sure that a method is really
 * restricted to one thread only at the same time
 * @author prithvi
 *
 */
public class Worker {
	private Random random = new Random();
	private Object lock1 = new Object();
	private Object lock2 = new Object();
	private List<Integer> list1 = new ArrayList<>();
	private List<Integer> list2 = new ArrayList<>();
	public void stageOne(){
		try{
			Thread.sleep(1);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
	}
	public void stageTwo(){
		try{
			Thread.sleep(1);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
	}
	public void stageLockOne(){
		synchronized(lock1){
			try{
				Thread.sleep(1);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	public void stageLockTwo(){
		synchronized(lock2){
			try{
				Thread.sleep(1);
			} catch(InterruptedException e){
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	public synchronized void syncStageOne(){
		try{
			Thread.sleep(1);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		list1.add(random.nextInt(100));
	}
	public synchronized void syncstageTwo(){
		try{
			Thread.sleep(1);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		list2.add(random.nextInt(100));
	}
	public void process(){
		for(int i=0;i<1000;i++){
			stageOne();
			stageTwo();
		}
	}
	public void processWithSync(){
		for(int i=0;i<1000;i++){
			syncStageOne();
			syncstageTwo();
		}
	}
	public void processWithLock(){
		for(int i=0;i<1000;i++){
			stageLockOne();
			stageLockTwo();
		}
	}
	private void runThreads(Thread tx, Thread ty){
		System.out.println("Starting...");
		long start = System.currentTimeMillis();
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
		long end = System.currentTimeMillis();
		System.out.println("Time taken:"+(end-start));
		System.out.println("List1 size:"+ list1.size() + " List2 size:"+list2.size());
		list1.clear();
		list2.clear();
	}
	public void main() {
//		(new Thread(()->process())).start();
		Thread t1 = new Thread(()->process());
		Thread t2 = new Thread(()->process());
		runThreads(t1,t2);
		Thread t3 = new Thread(()->processWithSync());
		Thread t4 = new Thread(()->processWithSync());
		runThreads(t3,t4);
		Thread t5 = new Thread(()->processWithLock());
		Thread t6 = new Thread(()->processWithLock());
		runThreads(t5,t6);
	}
}
