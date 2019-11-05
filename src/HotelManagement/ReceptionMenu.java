package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ReceptionMenu implements Menu{
    private Hotel hotel;

    public ReceptionMenu(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public void menu() throws IOException {
		Printer.printReceptionMenu();
		File hotelFile = new File(this.hotel.getPath());
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		switch (command) {
		case "CI": {
//			RoomFileController.checkIn(hotelFile);
			CheckIn ci = new CheckIn(this.hotel);
			ci.menu();
			menu();
			break;
		}
		case "CO": {
			RoomFileController.checkOut(hotelFile);
			menu();
			break;
		}
		case "LC": {
			CustomerFileController.menuLookUp(CustomerFileController.cdCustomerFolder(hotelFile));
			break;
		}
			case "CV": {
				changeVIPStatue();
				break;
			}
		case "back": {
				break;
			}
		}
    }

    public void changeVIPStatue() throws IOException {
		System.out.println("Please enter customer's name");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		Customer customer = hotel.getCustomer(name);
		if(customer == null) {
			System.out.println("Would you like to register customer " + name + "? y/n");
			if(!sc.nextLine().equals("y"))
				return;
			hotel.addCustomer(name);
			customer = hotel.getCustomer(name);
		}
		System.out.println("This customer is " + (customer.isVIP() ? "" : "not ") + "VIP");
		System.out.println("Would you like to change it? y/n");
		if(sc.nextLine().equals("y")){
			customer.setVIP(!customer.isVIP());
			customer.writeToFile();
			System.out.println("Change successful");
		}
	}
}
