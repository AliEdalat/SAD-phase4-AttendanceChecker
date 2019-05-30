package exam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import person.Student;
import presentation.Presentation;
import room.Room;

public class Exam {
	
	private int id;
	private Date start;
	private Date end;
	private Presentation presentation;
	private Room room;
	
	public Exam(int examId, String start, String end, Presentation presentation, Room room,
			ArrayList<Student> students) {
		this.id = examId;
		this.room = room;
		this.presentation = new Presentation();
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
		return id;
	}
	
	public Date getStart() {
		return start;
	}
	
	public Date getEnd() {
		return end;
	}

	public Room getRoom() {
		return room;
	}
	
//	public boolean findStudentById(String sId) {
//		if (students.containsKey(sId)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public boolean findstudentByName(String firstName, String lastName) {
//		Collection<Student> all = students.values();
//		for(Student student : all) {
//			if (student.getLastName().equals(lastName) && student.getFirstName().equals(firstName)) {
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public String findStudenId(String firstName, String lastName) throws Exception {
//		Collection<Student> all = students.values();
//		for(Student student : all) {
//			if (student.getLastName().equals(lastName) && student.getFirstName().equals(firstName)) {
//				return student.getId();
//			}
//		}
//		throw new Exception(firstName + " " + lastName + " can not participant in " + id + "exam");
//	}
}
//TODO: what about exam time ? Date is not sufficent!
