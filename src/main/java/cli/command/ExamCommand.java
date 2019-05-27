package cli.command;

import Attendance.Attendance;

public class ExamCommand implements Command{
	
	private int eid;
	private Attendance attendance = Attendance.getInstance();
	
	public ExamCommand(int eid) {
		this.eid = eid;
	}

	public void execute() {
		
	}
}
