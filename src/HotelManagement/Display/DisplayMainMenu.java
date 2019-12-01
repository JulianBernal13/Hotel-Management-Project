package HotelManagement.Display;

import HotelManagement.Command.CommandCreateHotel;
import HotelManagement.Command.CommandManageHotel;
import HotelManagement.Printer;
import HotelManagement.iCommand;
import HotelManagement.iDisplay;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayMainMenu implements iDisplay {
    private ArrayList<iCommand> commands = new ArrayList<>();

    public DisplayMainMenu() {
        File managementSystem = new File("." + File.separator + "ManagementSystem");
        if(!managementSystem.exists()) {
            managementSystem.mkdir();
        }
        addCommand(new CommandCreateHotel(managementSystem));
        addCommand(new CommandManageHotel(managementSystem));
    }

    @Override
    public void addCommand(iCommand cmd) {
        commands.add(cmd);
    }

    @Override
    public boolean displayCommand() {
        int i = 0;
        Printer.printBorder();
        for(iCommand cmd : commands)
            System.out.println("Press " + String.valueOf(i++) + " to " + cmd.getDescription());

        System.out.println("Press " + String.valueOf(i) + " to exit");
        Printer.printBorder();

        Scanner sc = new Scanner(System.in);
        try {
            int element = sc.nextInt();
            if (element == i || !commands.get(element).execute())
                return false;
            return displayCommand();
        } catch (IOException | ArrayIndexOutOfBoundsException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
