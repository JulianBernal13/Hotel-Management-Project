package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class CommandMove extends aCommand {
	public CommandMove(Hotel h) throws IOException {
		// Needed in order to make an updated object from the hotel file to
		// modify
		hotel = h;
		File hotelFile = new File(hotel.getPath());
		hotel = new Hotel(hotelFile);
	}

	@Override
	public boolean execute() throws IOException, ParseException {

		HotelFileController.move(this.hotel);
		return true;
	}

	@Override
	public String getDescription() {
		return "move objects into different hotels";
	}
}
