package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

import java.io.IOException;

public class ExamCommand implements Command{
	
	private int eid;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public ExamCommand(int eid) {
		this.eid = eid;
	}

	public void execute() {
		try {
			attendanceChecker.selectExam(this.eid);
		}catch(ProcessError e){
			System.out.println(e);
		}
		catch (Exception e) {
			System.out.print("ERROR : ");
			System.out.println(e.getMessage());
		}
	}
}
