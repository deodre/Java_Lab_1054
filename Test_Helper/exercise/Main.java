package exercise;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import eu.ase.test.MyR;

public class Main {

	public static void main(String[] args) {
		Object[][] matrix = {
				{
					new Course(1, "c - data structures", 100),
					new Course(1, "java", 200),
					new Course(1, "c - algorithms", 300)
				},
				
				{
					new Course(1, "c1", 150),
					new Course(1, "c2", 250),
					new Course(1, "c3", 350)
				},
				
				{
					new Course(1, "c4", 199),
					new Course(1, "c5", 299),
					new Course(1, "c6", 399)
				}
				
		};
		
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
		
		// ---------------------------- //
		
		CourseCollection courseCollection = new CourseCollection();
		
		List<Course> list = courseCollection.transformArrayToList(matrix[0]);
		list.stream().forEach(course -> System.out.println(course.toString()));
		
		list = courseCollection.filterByLetterSorted('c');
		list.stream().forEach(course -> System.out.println(course.toString()));

	}

}
