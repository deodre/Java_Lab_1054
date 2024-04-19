package eu.ase.lab9;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadPlayground {

	public static int getNoCores() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	public static void concurrentRoutineExecutorsWithName() {
		
		final ThreadFactory factory = Thread.ofVirtual().name("Thread - ", 0).factory();
		
		try(ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
			
			Future<?> makeCoffee = executor.submit(() -> {
				System.out.println("Start making coffee - " + Thread.currentThread().getName());
				try {
					Thread.sleep(Duration.ofSeconds(1L));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Coffee making done - " + Thread.currentThread().getName());
			});
			
			Future<?> doJob = executor.submit(() -> {
				System.out.println("Start do job - " + Thread.currentThread().getName());
				try {
					Thread.sleep(Duration.ofSeconds(1L));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Job done - " + Thread.currentThread().getName());
			});
			
			try {
				makeCoffee.get();
				doJob.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
	}
}
