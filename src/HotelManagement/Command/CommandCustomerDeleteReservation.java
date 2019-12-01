package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Customer;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandCustomerDeleteReservation extends aCommandCustomer {
	public CommandCustomerDeleteReservation(Hotel h, Customer c) {
		hotel = h;
		customer = c;
	}

	@Override
	public boolean execute() throws IOException, ParseException {
		ContractFileController.deleteContractRes(hotel, customer);
		return true;
	}

	@Override
	public String getDescription() {
		return "delete reservation";
	}
}
