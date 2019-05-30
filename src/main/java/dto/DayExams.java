package dto;

import java.util.ArrayList;

import attendance.ExamAttendance;
import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import exam.Exam;

@JsonObject(asExtraForUnknownProperties = true)
public class DayExams {
	@JsonProperty(required = true)
	private int status;
	@JsonProperty(required = true)
	private String date;
	@JsonProperty(required = true)
	private ArrayList<ExamDTO> classes;
	
	public int getStatus() {
		return status;
	}
	
	public String getDate() {
		return date;
	}
	
	public ArrayList<ExamDTO> getClasses() {
		return classes;
	}
	
	public ArrayList<ExamAttendance> getExams() {
		ArrayList<ExamAttendance> examAttendances = new ArrayList<>();
		for(ExamDTO examDTO : classes) {
			examAttendances.add(examDTO.getExamAttendance());
		}
		return examAttendances;
	}
}