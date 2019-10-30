package HotelManagement;

import java.util.ArrayList;
import java.util.Collections;

public class Printer {
    public static void printWelcome(){
        System.out.println("=============================================");
        System.out.println("*         Hotel Management System           *");
        System.out.println("=============================================");
        System.out.println("Enter 'new' to construct a new hotel         ");
        System.out.println("Enter 'manage' to manage hotel               ");
        System.out.println("Enter 'exit' to exit the system              ");
        System.out.println("=============================================");
        System.out.println("");
    }

    public static void printEditCustomer() {
        System.out.println("=============================================");
        System.out.println("Enter 'checkin' to check customer in         ");
        System.out.println("Enter 'checkout' to check customer out       ");
        System.out.println("Enter 'VIP' to make this customer VIP        ");
//        System.out.println("");
        System.out.println("=============================================");
        System.out.println("");
    }

    public static void printManagerMenu() {
        System.out.println("=============================================");
        System.out.println("Enter 'DH' to display hotal information      ");
        System.out.println("Enter 'DE' to display employee information   ");
        System.out.println("Enter 'LR' to list all rooms and to edit     ");
        System.out.println("Enter 'CE' to call an employee               ");
        System.out.println("Enter 'EE' to edit employee                  ");
        System.out.println("Enter 'CRT' to change room type                  ");
        System.out.println("Enter 'exit' to exit");
        System.out.println("");
    }

    public static void printReceptionMenu() {
        System.out.println("=============================================");
        System.out.println("Enter 'CI' to help a customer check-in      ");
        System.out.println("Enter 'CO' to help a customer check-out      ");
        System.out.println("Enter 'DH' to display hotal information      ");
        System.out.println("Enter 'exit' to exit");
        System.out.println("");
    }
    
    public static void printRoomview() {
    	System.out.println("=============================================");
    	System.out.println("Enter room number you wish to view          ");
    	System.out.println("");
    }

    public static <T> void printArray(ArrayList<T> collections) {
        for (T element: collections) {
            System.out.println(element);
        }
    }
}
