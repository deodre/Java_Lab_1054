package exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CourseCollection {

	private List<Course> listCourses;
	private Stream<Course> streamCourses;
	private Predicate<Course> predicate;
	
	public List<Course> transformArrayToList(Object[] courses) {
		this.listCourses = new ArrayList<Course>();
		for (int i = 0; i < courses.length; i++) {
			listCourses.add((Course)courses[i]);
		}
		return this.listCourses;
	}
	
	public List<Course> filterByLetterSorted(char letter) {
		
		this.predicate = course -> course.getName().startsWith(String.valueOf(letter));
		this.streamCourses = this.listCourses.stream();
		List<Course> result = this.streamCourses.sorted().filter(this.predicate).collect(Collectors.toList());
		return result;
		
	}
}
