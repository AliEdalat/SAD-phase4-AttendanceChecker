package exam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import course.Course;
import person.Professor;
import person.Student;
import room.Room;

public class Exam {
	
	private int examId;
	private Date date;
	// TODO: convert pattern 9h5m to Date + date 
	private Date start;
	// TODO: convert pattern 9h5m to Date + date
	private Date end;
	private Professor professor;
	private ArrayList<Student> students;
	private Room room;
	private Course course;
	
	public Exam(int examId, String date, String start, String end, Course course, Room room, Professor professor,
			ArrayList<Student> students) throws ParseException {
		this.examId = examId;
		this.room = room;
		this.course = course;
		this.professor = professor;
		this.students = students;
		SimpleDateFormat formatter=new SimpleDateFormat("E MMM dd yyyy");
		this.date = formatter.parse(date);
		// TODO: fill end and start.
	}
}
