package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class QuitCommand implements Command {

	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();

	public void execute(){
		try {
			attendanceChecker.finishProcess();
		}
		catch(Exception e) {
			System.out.print("ERROR : ");
			System.out.println(e.getMessage());
		}
	}
}
