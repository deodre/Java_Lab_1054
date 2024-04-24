package exercise;

import eu.ase.test.Laptop;

public class CourseRunnable implements Runnable {

	private Course[] v;
	private float sum;
	
	public CourseRunnable(Object[][] m, int line) {
		
		this.v = new Course[m[0].length];
		
		for (int j = 0; j < m[0].length; j++) {
			this.v[j] = (Course)(m[line][j]);
		}
	}
	
	
	@Override
	public void run() {
		sum = 0;
		for (int i = 0; i < v.length; i++) {
			sum += this.v[i].getStudents();
		}
	}

	public float getSum() {
		return this.sum;
	}
}
