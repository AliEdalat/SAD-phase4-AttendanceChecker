package CLI;

import java.util.Scanner;

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
                return new RegisterCommand(splittedCommand[1]);
            case "student":
                return new AddProjectCommand(project);
            case "finishExam":
                return new BidCommand(bid);
            case "professor":
                return new AuctionCommand(auctionDTO.getProjectTitle());
            case "fetchTodayExams":
                return new AuctionCommand(auctionDTO.getProjectTitle());
            default:
                throw new Exception(splittedCommand[0] + " is not a command.");
        }
    }
}
