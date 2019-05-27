package cli;

import java.util.Scanner;

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

    public Command createCommand(String[] splittedCommand) throws Exception {
        switch (splittedCommand[0]){
            case "exam":
            	if(splittedCommand.length == 2)
            		return new ExamCommand(Integer.parseInt(splittedCommand[1]));
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "student":
            	if(splittedCommand.length == 2)
            		return new StudentCommand(splittedCommand[1]);
            	else if(splittedCommand.length == 3)
            		return new StudentCommand(splittedCommand[1], splittedCommand[2]);
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "finishExam":
            	if(splittedCommand.length == 2)
            		return new FinishExamCommand(splittedCommand[1]);
            	else
            		throw new Exception("command " + splittedCommand[0] + "is not correct.");
            case "professor":
            	if(splittedCommand.length == 2)
            		return new ProfessorCommand(splittedCommand[1]);
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
}
