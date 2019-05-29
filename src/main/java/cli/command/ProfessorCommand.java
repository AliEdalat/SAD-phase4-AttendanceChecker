package cli.command;

import attendance.AttendanceChecker;

public class ProfessorCommand implements Command {

	private String pid;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public ProfessorCommand(String pid) {
		this.pid = pid;
	}
	
	public void execute() {
		
	}

}
