package HotelManagement;

import java.io.FileNotFoundException;

public class DisplayCustomer extends aDisplay {
	Hotel hotel;
	Customer customer;

	public DisplayCustomer(Hotel hotel, Customer customer) throws FileNotFoundException {
		this.hotel = hotel;
		this.customer = customer;
		addCommand(new CommandGetCustomerInfo(hotel, customer));
		addCommand(new CommandCustomerDeleteIn(hotel, customer));
		addCommand(new CommandCustomerDeleteReservation(hotel, customer));

	}
}
