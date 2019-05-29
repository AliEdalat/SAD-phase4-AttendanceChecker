package cli.command;

import attendance.AttendanceChecker;

public class StudentCommand implements Command {

	private String sid;
	private String firstName;
	private String lastName;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public StudentCommand(String sid) {
		this.sid = sid;
	}
	
	public StudentCommand(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.sid = null;
	}
	
	public void execute() {
		
	}

}
