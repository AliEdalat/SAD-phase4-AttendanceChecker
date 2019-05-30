package dto;

import java.text.ParseException;
import java.util.ArrayList;

import attendance.ExamAttendance;
import attendance.ExamAttendanceItem;
import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import course.Course;
import exam.Exam;
import person.Student;
import room.Room;

@JsonObject(asExtraForUnknownProperties = true)
public class ExamDTO {
	@JsonProperty(required = true)
	private int exam_id;
	@JsonProperty(required = true)
	private int room_number;
	@JsonProperty(required = true)
	private String course_name;
	@JsonProperty(required = true)
	private String start_at;
	@JsonProperty(required = true)
	private String end_at;
	@JsonProperty(required = true)
	private ProfessorDTO professor;
	@JsonProperty(required = true)
	private ArrayList<StudentDTO> students;
	
	public int getExamId() {
		return exam_id;
	}
	
	public int getRoomNumber() {
		return room_number;
	}
	
	public String getCourseName() {
		return course_name;
	}
	
	public String getStartAt() {
		return start_at;
	}
	
	public String getEndAt() {
		return end_at;
	}
	
	public ProfessorDTO getProfessor() {
		return professor;
	}
	
	public ArrayList<StudentDTO> getStudents() {
		return students;
	}

	public ExamAttendance getExamAttendance() {
		ArrayList<ExamAttendanceItem> studentAttendances = new ArrayList<>();
		for(StudentDTO studentDTO : students) {
			studentAttendances.add(studentDTO.getStudentAttendance());
		}
		return new ExamAttendance(new Exam(), studentAttendances);
	}
}
