package dto;

import attendance.ExamAttendanceItem;
import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import person.Student;

@JsonObject(asExtraForUnknownProperties = true)
public class StudentDTO {
	@JsonProperty(required = true)
	private String first_name;
	@JsonProperty(required = true)
	private String last_name;
	@JsonProperty(required = true)
	private int id;
	@JsonProperty(required = true)
	private int chair_number;

	public ExamAttendanceItem getStudentAttendance() {
		return new ExamAttendanceItem(first_name, last_name, id, chair_number);
	}
}
