package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class FinishExamCommand implements Command {

	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();

	public void execute() {
		try {
			if (!attendanceChecker.isEmptyExamsList())
				attendanceChecker.finishExam();
		}catch(ProcessError e){
			System.out.println(e);
		}
	}

}
