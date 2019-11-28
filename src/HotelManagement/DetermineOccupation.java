package HotelManagement;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class DetermineOccupation {
	public static boolean execute(Hotel hotel) throws IOException, ParseException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter your ID");
		Printer.printManagerID(hotel);
		String id = sc.nextLine();
		if (hotel.getManager(id) != null) {
			DisplayManager displayManager = new DisplayManager(hotel, hotel.getManager());
			if (displayManager.displayCommand()) {
				return execute(hotel);
			}
			return true;
		}
		Employee e = hotel.getEmployee(id);
		if (e != null) {
			DisplayReception displayReception = new DisplayReception(hotel, e);
			if (displayReception.displayCommand()) {
				return execute(hotel);
			}
			return true;
		}
		Customer c = hotel.getCustomer(id);
		if (c != null) {
			DisplayCustomer displayCustomer = new DisplayCustomer(hotel, c);
			if (displayCustomer.displayCommand()) {
				return execute(hotel);
			}
			return true;
		}
		System.out.println("ID does not exist, do you want to continue? y/n");
		if (sc.nextLine().equals("y")) {
			return execute(hotel);
		}
		return true;
	}
}
