package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HotelMenu implements Menu{
    private File managementSystem;

    public HotelMenu(File managementSystem) {
        this.managementSystem = managementSystem;
    }

    @Override
    public void menu() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which Hotel?");
        Printer.printFolderContentInOrder(this.managementSystem);
        System.out.println("Please enter hotel name");
        String name = sc.nextLine();

        File hotelFile = new File(managementSystem.getPath() + File.separator + name);
        while (!hotelFile.exists()) {
            System.out.println("Hotel does not exist. Please enter another one");
            Printer.printFolderContent(managementSystem);
            name = sc.nextLine();
            hotelFile = new File(managementSystem.getPath() + File.separator + name);
        }

        Hotel curHotel = new Hotel(hotelFile);

        chooseHandler(curHotel);
    }

    public void chooseHandler(Hotel hotel) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Choose your occupation number");
        System.out.println("1. Manager");
        System.out.println("2. Reception");

        int occu = sc.nextInt();
        switch (occu) {
            case 1: {
                ManagerMenu managerMenu = new ManagerMenu(hotel);
                managerMenu.menu();
                chooseHandler(hotel);
                break;
            }
            case 2: {
                ReceptionMenu receptionMenu = new ReceptionMenu(hotel);
                receptionMenu.menu();
                chooseHandler(hotel);
                break;
            }
            default:
                break;
        }
    }
}
