package dto;

import java.text.ParseException;
import java.util.ArrayList;

import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import course.Course;
import exam.Exam;
import person.Student;
import room.Room;

@JsonObject(asExtraForUnknownProperties = true)
public class ExamDTO {
	@JsonProperty(required = true)
	int exam_id;
	@JsonProperty(required = true)
	int room_number;
	@JsonProperty(required = true)
	String course_name;
	@JsonProperty(required = true)
	String start_at;
	@JsonProperty(required = true)
	String end_at;
	@JsonProperty(required = true)
	ProfessorDTO professor;
	@JsonProperty(required = true)
	ArrayList<StudentDTO> students;
	
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

	public Exam getExam() {
		ArrayList<Student> studentObjects = new ArrayList<>();
		for(StudentDTO sdto : students) {
			studentObjects.add(sdto.getStudent());
		}
		return new Exam(exam_id, start_at, end_at, new Course(course_name), new Room(room_number),
				professor.getProfessor(), studentObjects);
	}
}
