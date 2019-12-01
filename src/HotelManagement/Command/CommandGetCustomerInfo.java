package HotelManagement.Command;

import HotelManagement.Customer;
import HotelManagement.Hotel;
import HotelManagement.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class CommandGetCustomerInfo extends aCommandCustomer {
	public CommandGetCustomerInfo(Hotel h, Customer c) throws FileNotFoundException {
		hotel = h;
		customer = c;
	}

	@Override
	public boolean execute() throws IOException, ParseException {

		File cFile = new File(hotel.getPath() + File.separator + "Customer" + File.separator + customer.getFirstname()
				+ " " + customer.getLastname() + ".txt");
		Printer.printFile(cFile);
		return true;
	}

	@Override
	public String getDescription() {
		return "get your customer info";
	}
}
