package eu.ase.usejavalib;

import eu.ase.javalib.Student;

public class Main {

	public static void main(String[] args) {
		System.out.println("Hello");
		
		Student student = new Student(1, "John", new float[] {6f, 7f});
		
		System.out.println(student.toString());
	}

}
