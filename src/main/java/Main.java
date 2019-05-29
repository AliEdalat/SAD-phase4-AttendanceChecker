import attendance.AttendanceChecker;

public class Main {
	public static void main(String[] args) {
		AttendanceChecker attendanceChecker = AttendanceChecker.getInstance();
		attendanceChecker.run();
	}
}
