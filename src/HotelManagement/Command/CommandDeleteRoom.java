package HotelManagement.Command;

import HotelManagement.Hotel;
import HotelManagement.HotelFileController;

import java.io.IOException;
import java.text.ParseException;

public class CommandDeleteRoom extends aCommand {
	public CommandDeleteRoom(Hotel h) {
		hotel = h;
	}

	@Override
	public boolean execute() throws IOException, ParseException {
		HotelFileController.move(this.hotel);
		return true;
	}

	@Override
	public String getDescription() {
		return "delete a room to the current hotel";
	}
}
