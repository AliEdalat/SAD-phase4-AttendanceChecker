package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class StudentCommand implements Command {

	private int sid = 0;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public StudentCommand(int sid) {
		this.sid = sid;
	}
	
	public void execute() {
		try {
			attendanceChecker.showStudentInfo(this.sid);
		}catch(ProcessError e){
			System.out.println(e);
		}
	}

}
