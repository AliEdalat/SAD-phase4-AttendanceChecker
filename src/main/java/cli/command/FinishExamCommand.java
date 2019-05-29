package cli.command;

import attendance.AttendanceChecker;

public class FinishExamCommand implements Command {

	private String eid;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public FinishExamCommand(String eid) {
		this.eid = eid;
	}
	
	public void execute() {
		
	}

}
