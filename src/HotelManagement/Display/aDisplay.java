package HotelManagement.Display;

import HotelManagement.Printer;
import HotelManagement.Command.iCommand;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class aDisplay implements iDisplay {
    private ArrayList<iCommand> commands = new ArrayList<>();

    @Override
    public void addCommand(iCommand cmd) {
        commands.add(cmd);
    }

    @Override
    public boolean displayCommand() throws IOException, ParseException {
        int i = 0;
        final int n = commands.size();
        Printer.printBorder();
        for (iCommand command : commands){
            System.out.println("Press " + (i++) + " to " + command.getDescription());
        }
        System.out.println("Press " + (i++) + " to back to the previous page");
        System.out.println("Press " + (i++) + " to back to the main menu");
//        System.out.println("Press " + (i++) + " to exit the system");
        Printer.printBorder();

        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        if (command >= 0 && command < n) {
            return commands.get(command).execute() && displayCommand();
        }
        else if (command == n || command == n + 1) {
            return command == n;
        }
        else {
            System.out.println("Invalid command");
            return displayCommand();
        }
    }
}
