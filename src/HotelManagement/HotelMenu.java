package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HotelMenu implements Menu{
    File managementSyetem;

    public HotelMenu(File managementSyetem) {
        this.managementSyetem = managementSyetem;
    }

    @Override
    public void menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which Hotel?");
        Printer.printFolderContent(this.managementSyetem);
        System.out.println("Please enter hotel name");
        String name = sc.nextLine();

        File hotelFile = new File(managementSyetem.getPath() + File.separator + name);
        while (!hotelFile.exists()) {
            System.out.println("Hotel does not exist. Please enter another one");
            Printer.printFolderContent(managementSyetem);
            name = sc.nextLine();
            hotelFile = new File(managementSyetem.getPath() + File.separator + name);
        }

        Hotel curHotel = new Hotel(hotelFile);

        chooseHandler(curHotel);
    }

    public static void chooseHandler(Hotel hotel) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose your occupation number");
        System.out.println("1. Manager");
        System.out.println("2. Reception");

        int occu = sc.nextInt();
        switch (occu) {
            case 1: {
                ManagerMenu managerMenu = new ManagerMenu(hotel);
                managerMenu.menu();
                break;
            }
            case 2: {
                ReceptionMenu receptionManu = new ReceptionMenu(hotel);
                receptionManu.menu();
                break;
            }
            default:
                break;
        }
    }
}
