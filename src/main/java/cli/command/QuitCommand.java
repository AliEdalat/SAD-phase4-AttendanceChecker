package cli.command;

import Attendance.Attendance;

public class QuitCommand implements Command {

	private Attendance attendance = Attendance.getInstance();
	
	public void execute() {
		attendance.setFinished(true);
	}

}
