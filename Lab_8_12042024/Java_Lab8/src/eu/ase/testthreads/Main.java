package eu.ase.testthreads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HelloThread helloThread = new HelloThread("1. Threads Java 1.1 -> 17");
		
		helloThread.start();
		
//		try {
//			tJ5.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		//HelloRunnable tJ5Plus = new HelloRunnable("Threads Java 1.1 -> 17");
		
		HelloRunnable helloRunnable = new HelloRunnable();
		Thread tw_helloRunnable = new Thread(helloRunnable, "2. Threads Java 1.1 -> 17");
		
		tw_helloRunnable.start();
		
		// Starting with Java 7
		
		Runnable runnableTask = new Runnable() {

			@Override
			public void run() {
				String name = Thread.currentThread().getName();
				System.out.println("Hello " + name);
			}
			
		};
		
		Thread tw_runnableTask = new Thread(runnableTask, "3. Threads Java 7 -> 17");
		
		tw_runnableTask.start();
		
		// Starting with Java 8
		
		Runnable newRunnableTask = () -> {
			String name = Thread.currentThread().getName();
			System.out.println("Hello " + name);
		};
		
		Thread tw_newRunnableTask = new Thread(newRunnableTask, "4. Threads Java 8 -> 17");

		tw_newRunnableTask.start();
		
		
		ExecutorService executorThreadsPool = Executors.newFixedThreadPool(2);
		
		executorThreadsPool.submit(newRunnableTask);
		executorThreadsPool.submit(newRunnableTask);
		
		
		try {
			
			System.out.println("Attempt to shutdown executor");
			executorThreadsPool.shutdown();
			executorThreadsPool.awaitTermination(5, TimeUnit.SECONDS);
			
			
			
		} catch (InterruptedException e) {
			System.err.println("Task interrupted");
		} finally {
			if (!executorThreadsPool.isTerminated()) {
				System.err.println("Cancel non-finished tasks");
			}
			executorThreadsPool.shutdownNow();
			System.out.println("Shutdown finished");
		}
		
		ExecutorService executorThreadsPoolFutureCallable = Executors.newFixedThreadPool(1);
		
		Callable<Integer> taskCallable = () -> {
			try {
				TimeUnit.SECONDS.sleep(2);
				return 999;
			} catch (InterruptedException ie) {
				throw new IllegalStateException("Task callable interrupted", ie);
			}
			
		};
		
		
		Future<Integer> future = executorThreadsPoolFutureCallable.submit(taskCallable);
		Integer result = null;
		
		try {
			result = future.get();
			System.out.println("Result wout/" + result);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (future.isDone()) {
			try {
				result = future.get();
				System.out.println("Result w/" + result);

			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			
			System.out.println("Attempt to shutdown executor");
			executorThreadsPoolFutureCallable.shutdown();
			executorThreadsPoolFutureCallable.awaitTermination(5, TimeUnit.SECONDS);
			
			
			
		} catch (InterruptedException e) {
			System.err.println("Task interrupted");
		} finally {
			if (!executorThreadsPoolFutureCallable.isTerminated()) {
				System.err.println("Cancel non-finished tasks");
			}
			executorThreadsPoolFutureCallable.shutdownNow();
			System.out.println("Shutdown finished");
		}
		
	
		Runnable taskJava19 = () -> {
			String name = Thread.currentThread().getName();
			System.out.println("Java19: Hello " + name);
		};
		
		
		Thread tw_threadJ19 = Thread.ofVirtual().name("Virtual thread").unstarted(taskJava19);
		
		tw_threadJ19.start();
		
		
		System.out.printf("# of CPU cores: %s", VirtualThreadsPlayground.numberOfCores());
		
		VirtualThreadsPlayground.concurrentDailyRoutineUsingExecutorsWithName();


		System.out.println("Main Program finished!");

	}

}
