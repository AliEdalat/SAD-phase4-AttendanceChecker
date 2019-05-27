package dto;

import java.util.ArrayList;

import com.jsoniter.annotation.JsonObject;
import com.jsoniter.annotation.JsonProperty;

@JsonObject(asExtraForUnknownProperties = true)
public class PostBody {
	@JsonProperty(required = true)
	private int exam_id;
	@JsonProperty(required = true)
	private boolean is_teacher_signed;
	@JsonProperty(required = true)
	private ArrayList<Integer> present_students_list;
	
	public PostBody(int eid, boolean isSigned, ArrayList<String> studentIdList) {
		this.exam_id = eid;
		this.is_teacher_signed = isSigned;
		ArrayList<Integer> list = new ArrayList<>();
		for(String id : studentIdList) {
			list.add(Integer.parseInt(id));
		}
		this.present_students_list = list;
	}
	
	public void setExamId(int examId) {
		this.exam_id = examId;
	}
	
	public void setIsTeacherSigned(boolean isTeacherSigned) {
		this.is_teacher_signed = isTeacherSigned;
	}
	
	public void setPresentStudentsList(ArrayList<String> presentStudents) {
		ArrayList<Integer> list = new ArrayList<>();
		for(String id : presentStudents) {
			list.add(Integer.parseInt(id));
		}
		this.present_students_list = list;
	}
	
}
