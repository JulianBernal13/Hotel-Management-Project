package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MainMenu implements Menu {
    @Override
    public void menu() throws IOException, ParseException {
        Printer.printWelcome();

        File managementSystem = new File("." + File.separator + "ManagementSystem");
        managementSystem.mkdir();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        switch (command) {
            case "new": {
                HotelFileController.createHotel(managementSystem);
                menu();
                break;
            }
            case "manage": {
                HotelMenu hotelMenu = new HotelMenu(managementSystem);
                hotelMenu.menu();
                menu();
                break;
            }
            case "exit":
                break;
            default: {
                System.out.println("Invalid command, please enter another command");
                menu();
            }
        }
    }
}
