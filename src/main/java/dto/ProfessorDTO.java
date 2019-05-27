package dto;

import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import person.Professor;

@JsonObject(asExtraForUnknownProperties = true)
public class ProfessorDTO {
	@JsonProperty(required = true)
	String first_name;
	@JsonProperty(required = true)
	String last_name;
	@JsonProperty(required = true)
	String id;
	
	public String getFirstName() {
		return first_name;
	}
	
	public String getLastName() {
		return last_name;
	}
	
	public String getId() {
		return id;
	}

	public Professor getProfessor() {
		return new Professor(first_name, last_name, id);
	}
}
