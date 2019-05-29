package attendance;

import exam.Exam;

import java.util.ArrayList;
import java.util.Date;

public class ExamAttendance {
	
	private Exam exam;
	private boolean isTeacherSigned;
	private ArrayList<ExamAttendanceItem> presenceStudentsList;
	private Report report;
//
//	public ExamAttendance(Exam exam) {
//		this.exam = exam;
//		isTeacherSigned = false;
//		presentStudentsList = new ArrayList<String>();
//	}
//
//	public void addStudent(String sid) throws Exception {
//		System.out.println("start: " + exam.getStart().getTime());
//		System.out.println("now: " + new Date().getTime());
//		if (exam.getStart().getTime() + 1800 <= new Date().getTime()
//				&& exam.getEnd().getTime() >= new Date().getTime() && exam.findStudentById(sid)) {
//			presentStudentsList.add(sid);
//		}
//		throw new Exception("you can not add student " + sid);
//	}
//
//	public void addStudent(String firstName, String lastName) throws Exception {
//		System.out.println("start: " + exam.getStart().getTime());
//		System.out.println("now: " + new Date().getTime());
//		if (exam.getStart().getTime() + 1800 <= new Date().getTime()
//				&& exam.getEnd().getTime() >= new Date().getTime() && exam.findstudentByName(firstName, lastName)) {
//			presentStudentsList.add(exam.findStudenId(firstName, lastName));
//		}
//		throw new Exception("you can not add student " + firstName + " " + lastName);
//	}
//
//	public void teacherSign(String pid) {
//		System.out.println("start: " + exam.getStart().getTime());
//		System.out.println("now: " + new Date().getTime());
//		if (exam.getStart().getTime() + 1800 <= new Date().getTime()
//				&& exam.getEnd().getTime() >= new Date().getTime() && exam.getProfessor().getId().equals(pid)) {
//			isTeacherSigned = true;
//		}
//		isTeacherSigned = false;
//	}
//
//	public ArrayList<String> getPresentStudentsList() {
//		return presentStudentsList;
//	}
//
//	public int getExamId() {
//		return exam.getExamId();
//	}
//
//	public boolean getIsTeacherSigned() {
//		return isTeacherSigned;
//	}
}
