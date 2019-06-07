package attendance;

import exam.Exam;
import person.Professor;
import person.Student;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class ExamAttendance {
	
	private Exam exam;
	private boolean isTeacherSigned;
	private ArrayList<ExamAttendanceItem> presenceStudentsList;
	private Report report;
	private boolean isFinished ;

	public ExamAttendance(int examId, int roomNumber, String courseName, String startAt, String endAt, Professor professor,
						  ArrayList<ExamAttendanceItem> studentAttendances) {
		this.exam = new Exam(examId, roomNumber, courseName, startAt, endAt, professor);
		this.isTeacherSigned = false;
		this.presenceStudentsList = studentAttendances;
		this.isFinished = false;
	}
	
	public void getStudentsInformation() {
		for(ExamAttendanceItem examAttendanceItem : presenceStudentsList) {
			if(!examAttendanceItem.isAttended()) {
				Student student = examAttendanceItem.getStudent();
				System.out.println("\t" + student.getId() + ", " + student.getFirstName() + ", " + student.getLastName());
		
			}
		}
	}


	public boolean isExpired(){
		return false;
		// TODO
	}

	public boolean isFinished(){
		return this.isFinished;
	}

	public void finishExam(){
		this.isFinished = true;
	}

	public boolean isSigned(){
		return this.isTeacherSigned;
	}

	public void professorSign(int id) throws ProcessError{
		if(this.exam.getPresentation().getProfessor().getId() != id){
			throw new ProcessError("professor id is not right");
		}
		else{
			this.isTeacherSigned = true;
		}
	}

	public int getExamId(){
		return this.exam.getExamId();
	}

	public void addStudentByName(String firstName, String lastName) throws ProcessError{
		ExamAttendanceItem foundedStudent = null;
		for(ExamAttendanceItem student : this.presenceStudentsList){
			if (student.getStudent().getFirstName().equals(firstName) && student.getStudent().getLastName().equals(lastName)){
				foundedStudent = student;
				break;
			}
		}

		if(foundedStudent != null){
			throw new ProcessError("student has already ");
		}else{
			foundedStudent.attendStudent();
		}
	}

	public void addStudentById(int id) throws ProcessError{
		ExamAttendanceItem foundedStudent = null;
		for(ExamAttendanceItem student : this.presenceStudentsList){
			if (student.getStudent().getId()==id){
				foundedStudent = student;
				break;
			}
		}
		if(foundedStudent == null){
			throw new ProcessError("Student has not found!");
		}else{
			foundedStudent.attendStudent();
		}
	}

	public void showStudentInfo(int id) throws ProcessError{
		ExamAttendanceItem foundedStudent = null;
		for(ExamAttendanceItem student : this.presenceStudentsList){
			if (student.getStudent().getId()==id){
				foundedStudent = student;
				break;
			}
		}
		if(foundedStudent == null){
			throw new ProcessError("Student has not found!");
		}else{
			foundedStudent.showStudentInfo();
		}
	}

	public ArrayList<String> getAttendanceStringList(){
		ArrayList<String> studentIdList = new ArrayList<>();
		for (ExamAttendanceItem student : this.presenceStudentsList){
			if (student.isAttended()){
				studentIdList.add(Integer.toString(student.getStudent().getId()));
			}
		}
		return studentIdList;
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
