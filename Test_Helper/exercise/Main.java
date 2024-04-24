package exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import eu.ase.test.MyR;

public class Main {

	public static void main(String[] args) {

		Object[][] matrix = null; // DEFINE A MATRIX OF COURSES HERE
		
		float result = 0;

		int m = matrix.length;

		ExecutorService execThreadPool = Executors.newFixedThreadPool(m);

		CourseRunnable[] threadsArrayWorkerTasks = new CourseRunnable[m];

		for (int i = 0; i < m; i++) {
			threadsArrayWorkerTasks[i] = new CourseRunnable(matrix, i);
			execThreadPool.execute(threadsArrayWorkerTasks[i]);
		}

		execThreadPool.shutdown();
		try {
			execThreadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}

		for (int i = 0; i < threadsArrayWorkerTasks.length; i++) {
			result += threadsArrayWorkerTasks[i].getSum();
		}

		System.out.println(result);
	}

}
