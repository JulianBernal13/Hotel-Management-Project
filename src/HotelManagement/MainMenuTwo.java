package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainMenuTwo implements Menu {
    @Override
    public void menu() throws IOException {
        Printer.printWelcome();

        File managementSystem = new File("." + File.separator + "ManagementSystem");
        managementSystem.mkdir();

        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        switch (command) {
            case "new": {
                HotelFileController.createHotel();
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
