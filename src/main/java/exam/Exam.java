package exam;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import course.Course;
import person.Professor;
import person.Student;
import room.Room;

public class Exam {
	
	private int examId;
	private Date start;
	private Date end;
	private Professor professor;
	private HashMap<String, Student> students;
	private Room room;
	private Course course;
	
	public Exam(int examId, String start, String end, Course course, Room room, Professor professor,
			ArrayList<Student> students) {
		this.examId = examId;
		this.room = room;
		this.course = course;
		this.professor = professor;
		this.students = new HashMap<>();
		for(Student student : students) {
			this.students.put(student.getId(), student);
		}
		this.start = convertTimeStringToDate(start);
		this.end = convertTimeStringToDate(end);
	}
	
	private Date convertTimeStringToDate(String time) {
		int[] timeParts = new int[2];
		String temp = "";
		for(int i = 0; i< time.length(); i++) {
			if (time.charAt(i) == 'h') {
				timeParts[0] = Integer.parseInt(temp);
				temp = "";
				continue;
			} else if (time.charAt(i) == 'm') {
				timeParts[1] = Integer.parseInt(temp);
				temp = "";
				continue;
			}
			temp += time.charAt(i);
		}
		Calendar timeDate = Calendar.getInstance();
		timeDate.set(Calendar.HOUR_OF_DAY, timeParts[0]);
		timeDate.set(Calendar.MINUTE, timeParts[1]);
		return timeDate.getTime();
	}
	
	public int getExamId() {
		return examId;
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getEnd() {
		return end;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public boolean findStudentById(String sId) {
		if (students.containsKey(sId)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean findstudentByName(String firstName, String lastName) {
		Collection<Student> all = students.values();
		for(Student student : all) {
			if (student.getLastName().equals(lastName) && student.getFirstName().equals(firstName)) {
				return true;
			}
		}
		return false;
	}
	
	public String findStudenId(String firstName, String lastName) throws Exception {
		Collection<Student> all = students.values();
		for(Student student : all) {
			if (student.getLastName().equals(lastName) && student.getFirstName().equals(firstName)) {
				return student.getId();
			}
		}
		throw new Exception(firstName + " " + lastName + " can not participant in " + examId + "exam");
	}
}
