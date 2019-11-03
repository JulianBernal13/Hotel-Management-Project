package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HotelFileController implements FileController{
    public static void createHotel() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your hotel's name?");
        String name = sc.nextLine();
        String path = "." + File.separator + "ManagementSystem";
        File tmp = new File(path);
        tmp.mkdir();
        path += File.separator + name;
        File hotelFile = new File(path);
        while (!hotelFile.mkdir()) {
            System.out.println("This name is taken, please enter another name");
            name = sc.nextLine();
            hotelFile = new File(path);
        }
        System.out.println("What is your hotel's location? Enter an Address.");
        String address = sc.nextLine();
        System.out.println("How many floors do your hotel has?");
        int floor = Integer.parseInt(sc.nextLine());
        System.out.println("How many rooms do your hotel has for each floor?");
        int numRoom = Integer.parseInt(sc.nextLine());
        System.out.println("Please set a password for this hotel.");
        String password = sc.nextLine();

        Hotel hotel = new Hotel(path, name, new Location(address), floor, numRoom, password);
    }
}
