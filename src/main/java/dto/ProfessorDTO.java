package dto;

import attendance.ProcessError;
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

	public int getId() {
		return Integer.parseInt(this.id);
	}


	public Professor getProfessor(){
		return new Professor(first_name,last_name,Integer.parseInt(id));
	}
}
