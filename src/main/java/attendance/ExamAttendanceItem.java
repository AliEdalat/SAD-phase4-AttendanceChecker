package attendance;

import person.Student;

public class ExamAttendanceItem {
    private Student student;
    private int chairNumber;
    private boolean attended;

    public ExamAttendanceItem(String firstName, String lastName, int id, int chairNumber) {
        this.student = new Student(firstName, lastName, id);
        this.chairNumber = chairNumber;
        this.attended = false;
    }

    public Student getStudent() {
        return student;
    }

    public void attendStudent(){
        this.attended = true;
    }

    public boolean isAttended(){
        return this.attended;
    }
}

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