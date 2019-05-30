package dto;

import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

import person.Professor;

@JsonObject(asExtraForUnknownProperties = true)
public class ProfessorDTO {
	@JsonProperty(required = true)
	private String first_name;
	@JsonProperty(required = true)
	private String last_name;
	@JsonProperty(required = true)
	private String id;

	public Professor getProfessor() {
		return new Professor(first_name, last_name, id);
	}
}
