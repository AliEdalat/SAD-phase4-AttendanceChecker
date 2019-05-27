package cli.command;

import Attendance.Attendance;

public class FinishExamCommand implements Command {

	private String eid;
	private Attendance attendance = Attendance.getInstance();
	
	public FinishExamCommand(String eid) {
		this.eid = eid;
	}
	
	public void execute() {
		
	}

}
