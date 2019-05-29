package cli.command;

import attendance.AttendanceChecker;

public class ExamCommand implements Command{
	
	private int eid;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public ExamCommand(int eid) {
		this.eid = eid;
	}

	public void execute() {
		
	}
}
