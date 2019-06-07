package cli;

import java.util.ArrayList;
import java.util.Scanner;

import attendance.ExamAttendance;
import cli.command.*;

public class CLI {
	private static final CLI instance = new CLI();
    private static final Scanner reader = new Scanner(System.in, "UTF-8");

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
            case "StartExam":
            	if(splittedCommand.length == 2 && isNumeric(splittedCommand[1]))
            		return new ExamCommand(Integer.parseInt(splittedCommand[1]));
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "SignStudent":
            	if(splittedCommand.length == 2 && isNumeric(splittedCommand[1]))
            		return new StudentCommand(Integer.parseInt(splittedCommand[1]));
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
			case "AcceptStudent":
				if(splittedCommand.length == 2 && isNumeric(splittedCommand[1]))
					return new AcceptCommand(Integer.parseInt(splittedCommand[1]));
				else
					throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "FinishExam":
            	if(splittedCommand.length == 1)
            		return new FinishExamCommand();
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "SignProfessor":
            	if(splittedCommand.length == 2)
            		return new ProfessorCommand(Integer.parseInt(splittedCommand[1]));
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "FetchTodayExams":
            	if(splittedCommand.length == 1)
            		return new FetchCommand();
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "Quit":
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
		System.out.println("* 1. FetchTodayExams		// Fetch today exams.");
		System.out.println("* 2. StartExam examId		// Select current exam.");
		System.out.println("* 3. SignStudent studentId		// Check student info.");
		System.out.println("* 4. AcceptStudent studentId		// Add student to students presence list.");
		System.out.println("* 5. SignProfessor professorId		// Give sign of course's professor.");
		System.out.println("* 6. FinishExam		// Send current exam attendance information.");
		System.out.println("* 7. Quit		// Quit attendance checker system.");
		System.out.println("*****************************************************************************");
	}
	
	public void showFetchCommand() {
		System.out.println("*Exams list is empty. Fetch today exam like this:");
		System.out.println("*FetchTodayExams");
	}

	public void showExamsComand(ArrayList<ExamAttendance> todayExamList) {
		System.out.println("*Select current exam like this:");
		System.out.println("*StartExam examId");
		System.out.print("**ExamIds: {");
		for(ExamAttendance examAttendance : todayExamList) {
			if (!examAttendance.isFinished() && !examAttendance.isExpired()) {
				System.out.print(examAttendance.getExamId());
				if (todayExamList.indexOf(examAttendance) < todayExamList.size()-1)
					System.out.print(", ");
			}
		}
		System.out.println("}");
	}

	public void showStudentsCommand(ExamAttendance currentExam) {
		System.out.println("*Mark current exam student like this:");
		System.out.println("*SignStudent studentId");
		System.out.println("**Students: {");
		currentExam.getStudentsInformation();
		System.out.println("}");
		System.out.println("*Add professor sign like this:");
		System.out.println("*SignProfessor professorId");
	}

	public void showFinishExamComand() {
		System.out.println("*Send current exam data like this:");
		System.out.println("*FinishExam");
	}
}
