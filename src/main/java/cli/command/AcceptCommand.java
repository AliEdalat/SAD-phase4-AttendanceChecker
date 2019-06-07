package cli.command;

import attendance.AttendanceChecker;
import attendance.ProcessError;

public class AcceptCommand implements Command {

    private int sid = 0;
    private AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();

    public AcceptCommand(int sid) {
        this.sid = sid;
    }

    public void execute() {
        try {
            if (!attendanceChecker.isEmptyExamsList())
                attendanceChecker.acceptStudentAttendance(this.sid);
        }catch(ProcessError e){
            System.out.println(e);
        }
    }

}
