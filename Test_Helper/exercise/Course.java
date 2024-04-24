package exercise;

public class Course implements Comparable<Course> {

	private int courseId;
	private String name;
	private int students;
	
	public Course(int courseId, String name, int students) {
		super();
		this.courseId = courseId;
		this.name = name;
		this.students = students;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStudents() {
		return students;
	}

	public void setStudents(int students) {
		this.students = students;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		
		Course clone = (Course) super.clone();
		
		if (this.name != null) {
			clone.name = new String(this.getName());
		}
		
		clone.courseId = this.getCourseId();
		clone.students = this.getStudents();
		
		return clone;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", name=" + name + ", students=" + students + "]";
	}

	@Override
	public int compareTo(Course o) {
		return this.getName().compareTo(o.getName());
	}
	
	
}
