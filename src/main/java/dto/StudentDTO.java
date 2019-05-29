package dto;

import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import person.Student;

@JsonObject(asExtraForUnknownProperties = true)
public class StudentDTO {
	@JsonProperty(required = true)
	String first_name;
	@JsonProperty(required = true)
	String last_name;
	@JsonProperty(required = true)
	int id;
	@JsonProperty(required = true)
	int chair_number;
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public int getId() {
		return id;
	}
	
	public int getChairNumber() {
		return chair_number;
	}

//	public Student getStudent() {
//		return new Student(first_name, last_name, String.valueOf(id), chair_number);
//	}
}
