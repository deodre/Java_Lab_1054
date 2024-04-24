package eu.ase.testthreads;

import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

public class VirtualThreadsPlayground {

	
	static int numberOfCores() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	static void concurrentDailyRoutineUsingExecutorsWithName() {
		
		@SuppressWarnings("preview")
		final ThreadFactory factory = Thread.ofVirtual().name("Routine - ", 0).factory();
		
		try (@SuppressWarnings("preview")
		ExecutorService executor = Executors.newThreadPerTaskExecutor(factory)) {
			
			Future<?> makeCoffee = executor.submit(() -> {
				System.out.printf("\n %s - Coffee is preparing", Thread.currentThread().getName());
				try {
					Thread.sleep(Duration.ofSeconds(1L));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("\n %s - Coffee is prepared", Thread.currentThread().getName());
			});
			
			Future<?> doWork = executor.submit(() -> {
				System.out.printf("\n %s - Work for your job", Thread.currentThread().getName());
				try {
					Thread.sleep(Duration.ofSeconds(1L));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.printf("\n %s - Work is done for today", Thread.currentThread().getName());
			});
			
			try {
				makeCoffee.get();
				doWork.get();
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
