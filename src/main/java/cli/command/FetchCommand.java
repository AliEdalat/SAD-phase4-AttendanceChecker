package cli.command;

import java.io.IOException;

import attendance.AttendanceChecker;

public class FetchCommand implements Command {

	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance().getInstance();
	
	public void execute() {
		try {
			attendanceChecker.fetchExams();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("can not fetch exams' information.");
			attendanceChecker.setFinished(true);
		}
	}

}
