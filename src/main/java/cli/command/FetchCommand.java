package cli.command;

import java.io.IOException;

import Attendance.Attendance;

public class FetchCommand implements Command {

	private Attendance attendance = Attendance.getInstance();
	
	public void execute() {
		try {
			attendance.fetchExams();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("can not fetch exams' information.");
			attendance.setFinished(true);
		}
	}

}
