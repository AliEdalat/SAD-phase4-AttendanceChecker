package exam;

import java.util.ArrayList;
import java.util.Date;

public class ExamAttendance {
	
	private Exam exam;
	private boolean isTeacherSigned;
	private ArrayList<String> presentStudentsList;
	
	public ExamAttendance(Exam exam) {
		this.exam = exam;
		isTeacherSigned = false;
		presentStudentsList = new ArrayList<String>();
	}
	
	public void addStudent(String sid) {
		System.out.println("start: " + exam.getStart().getTime());
		System.out.println("now: " + new Date().getTime());
		if (exam.getStart().getTime() + 1800 <= new Date().getTime()
				&& exam.getEnd().getTime() >= new Date().getTime() && exam.findStudentById(sid)) {
			presentStudentsList.add(sid);
		}
	}
	
	public void teacherSign(String pid) {
		System.out.println("start: " + exam.getStart().getTime());
		System.out.println("now: " + new Date().getTime());
		if (exam.getStart().getTime() + 1800 <= new Date().getTime()
				&& exam.getEnd().getTime() >= new Date().getTime() && exam.getProfessor().getId().equals(pid)) {
			isTeacherSigned = true;
		}
		isTeacherSigned = false;
	}
	
	public ArrayList<String> getPresentStudentsList() {
		return presentStudentsList;
	}
	
	public int getExamId() {
		return exam.getExamId();
	}
	
	public boolean getIsTeacherSigned() {
		return isTeacherSigned;
	}
}
