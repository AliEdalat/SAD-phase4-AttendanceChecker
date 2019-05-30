package attendance;

import exam.Exam;
import person.Professor;

import java.util.ArrayList;
import java.util.Date;

public class ExamAttendance {
	
	private Exam exam;
	private boolean isTeacherSigned;
	private ArrayList<ExamAttendanceItem> presenceStudentsList;
	private Report report;

	public ExamAttendance(int examId, int roomNumber, String courseName, String startAt, String endAt, Professor professor,
						  ArrayList<ExamAttendanceItem> studentAttendances) {
		this.exam = new Exam(examId, roomNumber, courseName, startAt, endAt, professor);
		this.isTeacherSigned = false;
		this.presenceStudentsList = studentAttendances;

	}

//	public void teacherSign(String pid) {
//		System.out.println("start: " + exam.getStart().getTime());
//		System.out.println("now: " + new Date().getTime());
//		if (exam.getStart().getTime() + 1800 <= new Date().getTime()
//				&& exam.getEnd().getTime() >= new Date().getTime() && exam.getProfessor().getId().equals(pid)) {
//			isTeacherSigned = true;
//		}
//		isTeacherSigned = false;
//	}

}
