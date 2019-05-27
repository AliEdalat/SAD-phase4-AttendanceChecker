package dto;

import java.util.ArrayList;

import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import exam.Exam;

@JsonObject(asExtraForUnknownProperties = true)
public class DayExams {
	@JsonProperty(required = true)
	int status;
	@JsonProperty(required = true)
	String date;
	@JsonProperty(required = true)
	ArrayList<ExamDTO> classes;
	
	public int getStatus() {
		return status;
	}
	
	public String getDate() {
		return date;
	}
	
	public ArrayList<ExamDTO> getClasses() {
		return classes;
	}
	
	public ArrayList<Exam> getExams() {
		ArrayList<Exam> exams = new ArrayList<>();
		for(ExamDTO edto : classes) {
			exams.add(edto.getExam());
		}
		return exams;
	}
}
