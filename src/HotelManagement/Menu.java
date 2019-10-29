package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {


    public void menu() throws IOException {
        System.out.println("=============================================");
        System.out.println("*         Hotel Management System           *");
        System.out.println("=============================================");
        System.out.println("Enter 'new' to construct a new hotel         ");
        System.out.println("Enter 'manage' to manage hotel               ");
        System.out.println("Enter 'exit' to exit the system              ");
//        System.out.println("");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        switch(command) {
            case "new": {
                createHotel();
                menu();
            }
            case "manage": {
                manageHotel();
                menu();
            }
            default: break;
        }
    }

    public void createHotel() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What is your hotel's name?");
        String name = sc.nextLine();
        File hotelFile = new File(name);
        while(!hotelFile.mkdir()) {
            System.out.println("This name is taken, please enter another name");
            name = sc.nextLine();
            hotelFile = new File(name);
        }
        System.out.println("What is your hotel's location? Enter an Address.");
        String address = sc.nextLine();
        System.out.println("How many floors do your hotel has?");
        int floor = Integer.parseInt(sc.nextLine());
        System.out.println("How many rooms do your hotel has for each floor?");
        int numRoom = Integer.parseInt(sc.nextLine());
        System.out.println("Please set a password for this hotel.");
        String password = sc.nextLine();
        String path = "." + File.separator + name;

        Hotel hotel = new Hotel(path, name, new Location(address), floor, numRoom, password);
        // save the hotel
//        File f = new File(home + sep + "ManagementSystem" + sep + name + ".txt");
//        String path = home + File.separator + "ManagementSystem" + File.separator + name + ".txt";
//        PrintWriter out = new PrintWriter(path);
//        out.println(name);
//        out.println(address);
//        out.println(password);
//        out.println("Rooms:");
//        for (int i = 0; i <floor ; i++) {
//            for (int j = 0; j < numRoom; j++) {
//                out.println(hotel.getRooms()[i][j].toString());
//            }
//            out.println();
//        }
//        out.println("Employees:"+" \n");
//        try {
//            for (int i = 0; i < hotel.getEmployees().size(); i++) {
//                out.write(hotel.getEmployees().get(i).toString() + " \n");
//            }
//        }
//        catch (NullPointerException e){
//            out.write("There is no employee.");
//        }
//        out.flush();
//        out.close();
//        System.out.println("Success! "+hotel.getName()+" has been constructed!");
    }

    private void manageHotel() {
    }

}
