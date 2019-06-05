package cli.command;

import java.io.IOException;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class FetchCommand implements Command {

	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public void execute() {
		try {
			attendanceChecker.fetchExams();
		}catch(ProcessError e){
			System.out.println(e);
		}
		catch (Exception e) {
			System.out.print("ERROR : ");
			System.out.println(e.getMessage());

		}
	}

}
