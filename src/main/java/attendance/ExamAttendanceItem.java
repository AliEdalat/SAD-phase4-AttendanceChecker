package attendance;

import person.Student;

public class ExamAttendanceItem {
    private Student student;
    private boolean attended;

    public ExamAttendanceItem(Student student) {
        this.student = student;
        this.attended = false;
    }
}
