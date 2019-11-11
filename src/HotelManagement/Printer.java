package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Printer {
	public static void printWelcome() {
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
		// System.out.println("");
		System.out.println("=============================================");
		System.out.println("");
	}

	public static void printEditEmployee() {
		System.out.println("=============================================");
		System.out.println("Enter 'checkin' to check customer in         ");
		System.out.println("Enter 'checkout' to check customer out       ");
		System.out.println("Enter 'VIP' to make this customer VIP        ");
		// System.out.println("");
		System.out.println("=============================================");
		System.out.println("");
	}

	public static void printManagerMenu() {
		System.out.println("=============================================");
		System.out.println("Enter 'DH' to display hotal information      ");
		System.out.println("Enter 'DE' to display all employees          ");
		System.out.println("Enter 'DI' to display inventory              ");
		System.out.println("Enter 'LR' to list all rooms and to edit     ");
		System.out.println("Enter 'CE' to call an employee               ");
		System.out.println("Enter 'EE' to edit employee                  ");
		System.out.println("Enter 'AE' to add employee                   ");
		System.out.println("Enter 'FE' to fire employee                  ");
		System.out.println("Enter 'AI' to add inventory item           ");
		System.out.println("Enter 'UI' to use inventory item           "); // used
		System.out.println("Enter 'CRT' to change room type                  ");
		System.out.println("Enter 'exit' to exit");
		System.out.println("=============================================");
		System.out.println("");
	}

	public static void printReceptionMenu() {
		System.out.println("=============================================");
		System.out.println("Enter 'DI' to display inventory             "); // used
		System.out.println("Enter 'UI' to use inventory item            "); // used
		System.out.println("Enter 'CI' to help a customer check-in      ");
		System.out.println("Enter 'CO' to help a customer check-out      ");
		System.out.println("Enter 'LC' to look up a customer");
		System.out.println("Enter 'CV' to change VIP status of a customer");
		System.out.println("Enter 'RR' to reserve a room");
		System.out.println("Enter 'back' to back to previous menu");
		System.out.println("=============================================");

		System.out.println("");
	}

	public static void printRoomview() {
		System.out.println("=============================================");
		System.out.println("Enter room number you wish to view or Exit to exit list of rooms         ");
		System.out.println("");
	}

	public static <T> void printArray(ArrayList<T> collections) {
		for (T element : collections) {
			System.out.println(element);
		}
	}

	public static void printRoomEditMenu(Room room) {
		System.out.println("=============================================");

		System.out.println("Room number: " + room.getNumber());
		System.out.println("Room occupied: " + room.isEmpty());
		System.out.println("Room is clean: " + room.isClean());
		System.out.println("Room maintaince note: " + room.getMaintenance());
		System.out.println("Room notes: " + room.getNotes());
		System.out.println("");
		System.out.println("Enter 'Empty' to set room to empty      ");
		System.out.println("Enter 'clean' to set room to clean      ");
		System.out.println("Enter 'Maintaince' to edit maintaince notes     ");
		System.out.println("Enter 'Notes' to edit general room notes      ");
		System.out.println("Enter 'Exit' to get back to List of rooms and save changes     ");
	}

	public static void printRoomInfo(Room room) {
		System.out.println("=============================================");

		System.out.println("Room number: " + room.getNumber());
		System.out.println("Room occupied: " + room.isEmpty());
		System.out.println("Room is clean: " + room.isClean());
		System.out.println("Room maintaince note: " + room.getMaintenance());
		System.out.println("Room notes: " + room.getNotes());
	}

	public static void printNoteMaker() {
		System.out.println("=============================================");
		System.out.println("Enter 'delete' then 'line number' to delete the line      ");
		System.out.println("press enter key to enter current line and start another     ");
		System.out.println("Enter 'Done' to exit editor and save changes      ");
		System.out.println("Enter 'Exit' to exit with out saving any changes      ");
	}

	public static void printFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		while (sc.hasNext())
			System.out.println(sc.nextLine());
	}

	public static void printFolderContent(File folder) {
		for (File file : folder.listFiles())
			System.out.println(file.getName());
	}

	public static void printFolderContentInOrder(File folder) {
		ArrayList<String> names = new ArrayList<>();
		for (File file : folder.listFiles())
			names.add(file.getName());
		Collections.sort(names);
		printArray(names);
	}

	public static void printCustomer(File f) throws FileNotFoundException {
		printCustomer(new Customer(f));
	}

	public static void printCustomer(Customer c) {
		System.out.println(c);
		System.out.println("is VIP " + c.isVIP());
		System.out.println("is Staying " + c.isStaying());
	}

	public static void printManagerID(Hotel hotel) {
		System.out.println(hotel.getManager().getID());
	}

}
