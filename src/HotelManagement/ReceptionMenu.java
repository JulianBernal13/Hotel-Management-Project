package HotelManagement;

import java.io.File;
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
			RoomFileController.checkIn(hotelFile);
			menu();
			break;
		}
		case "CO": {
			RoomFileController.checkOut(hotelFile);
			menu();
			break;
		}
		case "LC": {
			CustomerFileController.menuLookUp(CustomerFileController.cdCustomerFile(hotelFile));
			break;
		}
		case "back": {
				break;
			}
		}
    }
}
