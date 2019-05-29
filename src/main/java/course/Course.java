package course;

public class Course {
	
	private String courseName;
	private int units;
	
	public Course(String courseName ) {
		this.courseName = courseName;
		this.units = 3;
	}
	
	public String getCourseName() {
		return courseName;
	}
}
