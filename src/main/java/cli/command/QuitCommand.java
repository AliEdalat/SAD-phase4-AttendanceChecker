package cli.command;

import attendance.AttendanceChecker;

public class QuitCommand implements Command {

	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();

	public void execute() {
		attendanceChecker.setFinished(true);
	}

}
