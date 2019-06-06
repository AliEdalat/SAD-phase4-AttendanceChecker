package cli;

import java.util.ArrayList;
import java.util.Scanner;

import attendance.ExamAttendance;
import cli.command.Command;
import cli.command.ExamCommand;
import cli.command.FetchCommand;
import cli.command.FinishExamCommand;
import cli.command.ProfessorCommand;
import cli.command.QuitCommand;
import cli.command.StudentCommand;

public class CLI {
	private static final CLI instance = new CLI();
    private static final Scanner reader = new Scanner(System.in);

    public static CLI getInstance() {
        return instance;
    }

    private CLI() { }

    public String getCommand() {
        System.out.println("Enter your command: ");
        return reader.nextLine();
    }

    public String[] parseCommand(String command) {
        return command.split("\\s+");
    }
    
    private boolean isNumeric(String str) { 
    	try { 
    		Integer.parseInt(str);
    		return true;
    	} catch(NumberFormatException e){
    		return false;
    	}
    }

    public Command createCommand(String[] splittedCommand) throws Exception {
        switch (splittedCommand[0]){
            case "exam":
            	if(splittedCommand.length == 2 && isNumeric(splittedCommand[1]))
            		return new ExamCommand(Integer.parseInt(splittedCommand[1]));
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "student":
            	if(splittedCommand.length == 2 && isNumeric(splittedCommand[1]))
            		return new StudentCommand(Integer.parseInt(splittedCommand[1]));
            	else if(splittedCommand.length == 3)
            		return new StudentCommand(splittedCommand[1], splittedCommand[2]);
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "finishExam":
            	if(splittedCommand.length == 1)
            		return new FinishExamCommand();
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "professor":
            	if(splittedCommand.length == 2)
            		return new ProfessorCommand(Integer.parseInt(splittedCommand[1]));
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "fetchTodayExams":
            	if(splittedCommand.length == 1)
            		return new FetchCommand();
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "quit":
            	if(splittedCommand.length == 1)
            		return new QuitCommand();
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            default:
                throw new Exception(splittedCommand[0] + " is not a command.");
        }
    }

	public void showAllComands() {
		System.out.println("********************************** commands *********************************");
		System.out.println("* 1. fetchTodayExams		// Fetch today exams.");
		System.out.println("* 2. quit		// Quit.");
		System.out.println("* 3. exam examId		// Select current exam.");
		System.out.println("* 4. student studentId		// Add student to present students list.");
		System.out.println("* 5. professor professortId		// Give sign of course's professor.");
		System.out.println("* 6. finishExam		// Send current exam attendance information.");
		System.out.println("*****************************************************************************");
	}
	
	public void showFetchCommand() {
		System.out.println("\n*exam list is empty. fetch today exam like this:");
		System.out.println("*Enter your command: ");
		System.out.println("*fetchTodayExams");
	}

	public void showExamsComand(ArrayList<ExamAttendance> todayExamList) {
		System.out.println("\n*select current exam like this:");
		System.out.println("*Enter your command: ");
		System.out.println("*exam examId");
		System.out.print("**examId: {");
		for(ExamAttendance examAttendance : todayExamList) {
			if (!examAttendance.isFinished() && !examAttendance.isExpired()) {
				System.out.print(examAttendance.getExamId());
				System.out.print(", ");
			}
		}
		System.out.println("}");
	}

	public void showStudentsCommand(ExamAttendance currentExam) {
		System.out.println("\n*mark current exam student like this:");
		System.out.println("*Enter your command: ");
		System.out.println("*student studentId");
		System.out.println("**students: {");
		currentExam.getStudentsInformation();
		System.out.println("}");
		System.out.println("\n*add professor sign like this:");
		System.out.println("*Enter your command: ");
		System.out.println("*professor professorId");
	}

	public void showFinishExamComand(ExamAttendance currentExam) {
		System.out.println("\n*send current exam data like this:");
		System.out.println("*Enter your command: ");
		System.out.println("*finishExam");
	}
}
