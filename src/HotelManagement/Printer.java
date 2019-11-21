package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("Enter 'IC' to call inventory item            ");
		System.out.println("Enter 'AI' to add inventory item             ");
		System.out.println("Enter 'UI' to use inventory item             ");
		System.out.println("Enter 'CRT' to change room type              ");
		System.out.println("Enter 'PR' to access Payroll                 ");
		System.out.println("Enter 'Schedule' to use Schedule             ");
		System.out.println("Enter 'exit' to exit");
		System.out.println("=============================================");
		System.out.println("");
	}

	public static void printReceptionMenu() {
		System.out.println("=============================================");
		System.out.println("Enter 'DI' to display inventory             ");
		System.out.println("Enter 'UI' to use inventory item            ");
		System.out.println("Enter 'IC' to call inventory item            ");
		System.out.println("Enter 'CI' to help a customer check-in      ");
		System.out.println("Enter 'CO' to help a customer check-out      ");
		System.out.println("Enter 'LC' to look up a customer");
		System.out.println("Enter 'CV' to change VIP status of a customer");
		System.out.println("Enter 'RR' to start reservation");
		System.out.println("Enter 'DR' to delete a reservation contract");
		System.out.println("Enter 'DR' to delete a contract");
		System.out.println("Enter 'PM' to price match a reservation");
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

	public static void printScheduleMenu() {
		System.out.println("=============================================");
		System.out.println("Enter 'list' to get a list of all schedules made     ");
		System.out.println("Enter 'new' to make a new schedule    ");
		System.out.println("Enter 'Exit' to exit to previous menu      ");
	}

	public static void printAllSchedules() {
		System.out.println("=============================================");
		System.out.println("Enter Schedule name to view the Schedule     ");
		System.out.println("Enter 'Exit' to exit to previous menu     ");

	}

	public static void printDayView() {
		System.out.println("=============================================");
		System.out.println("Enter a day to view of this Schedule (Monday,Tuseday, ect)");
	}
	
	public static void printPayRollOptoins() {
		System.out.println("=============================================");
		System.out.println("Enter a employee id to start payroll calculations");

	}

	public static void printFile(File file) throws FileNotFoundException {
		Scanner sc = new Scanner(file);
		if (!sc.hasNext())
			System.out.println("There's nothing in this file"); // new code
		while (sc.hasNext())
			System.out.println(sc.nextLine());
	}

	public static void printFiles(File files) throws FileNotFoundException {// newmethod
		File[] temp = files.listFiles();
		if (temp.length == 0) {
			System.out.println("There's nothing in this file \n"); // new code
			return;
		}
		for (File f : temp) {
			Printer.printFile(f);
			System.out.print("\n");
		}
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

	public static void printRRContract(Hotel hotel) {
		for(Contract contract : hotel.getReservationContracts().values()){
//			System.out.println(contract.getCustomer());
			System.out.println(contract);
		}
	}

	public static void printOutOfBound(int n) {
		System.out.println("The command number you have entered is invalid");
		System.out.println("Please enter an index number between 1 and " + n);
	}

	public static void printBorder() {
		char[] chars = new char[50];
		Arrays.fill(chars, '=');
		System.out.println(chars);
	}
}
