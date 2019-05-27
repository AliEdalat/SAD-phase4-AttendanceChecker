package cli.command;

import Attendance.Attendance;

public class ProfessorCommand implements Command {

	private String pid;
	private Attendance attendance = Attendance.getInstance();
	
	public ProfessorCommand(String pid) {
		this.pid = pid;
	}
	
	public void execute() {
		
	}

}
