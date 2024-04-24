package eu.ase.lab9;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		
		Runnable th8 = () -> {
			String name = Thread.currentThread().getName();
			System.out.println("Hello from " + name);
		};
		
		Thread worker_th8 = new Thread(th8, "thread java 8");
		
		worker_th8.start();
		
		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(2);
		
		threadPoolExecutor.submit(th8);
		threadPoolExecutor.submit(th8);

		try {
			System.out.println("Attempt shutdown");
			threadPoolExecutor.shutdown();
			threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (!threadPoolExecutor.isShutdown()) {
				System.out.println("Cancell running tasks");
			}
			threadPoolExecutor.shutdownNow();
		}
		
		
		Runnable th9 = () -> {
			String name = Thread.currentThread().getName();
			System.out.println("Hello from " + name);
		};
		
		Thread tw_th9 = Thread.ofVirtual().name("Virtual Thread").unstarted(th9);
		
		tw_th9.start();
		try {
			tw_th9.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Main finished");

		System.out.println("Number of cores: " + VirtualThreadPlayground.getNoCores());
	
		VirtualThreadPlayground.concurrentRoutineExecutorsWithName();
	}

}
