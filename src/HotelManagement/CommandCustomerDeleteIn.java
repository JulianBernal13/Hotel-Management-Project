package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandCustomerDeleteIn extends aCommandCustomer {
	public CommandCustomerDeleteIn(Hotel h, Customer c) {
		hotel = h;
		customer = c;
	}

	@Override
	public boolean execute() throws IOException, ParseException {
		ContractFileController.deleteContractIn(hotel, customer);
		return true;
	}

	@Override
	public String getDescription() {
		return "delete check in";
	}
}
