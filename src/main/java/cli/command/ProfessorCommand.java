package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class ProfessorCommand implements Command {

	private int pid;
	private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
	
	public ProfessorCommand(int pid) {
		this.pid = pid;
	}
	
	public void execute() {
		try {
			attendanceChecker.professorSign(this.pid);
		}catch(ProcessError e){
			System.out.println(e);
		}
		catch (Exception e) {
			System.out.print("ERROR : ");
			System.out.println(e.getMessage());
		}
	}

}
