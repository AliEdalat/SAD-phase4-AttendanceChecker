package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class StudentCommand implements Command {

	private int sid = 0;
	private String firstName = null;
	private String lastName = null;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public StudentCommand(int sid) {
		this.sid = sid;
	}
	
	public StudentCommand(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public void execute() {
		try {
			if(this.firstName!=null && this.lastName!=null){
				attendanceChecker.addStudentByName(firstName,lastName);
			}else{
				attendanceChecker.addStudentById(this.sid);
			}
		}catch(ProcessError e){
			System.out.println(e);
		}
		catch (Exception e) {
			System.out.print("ERROR : ");
			System.out.println(e.getMessage());
		}
	}

}
