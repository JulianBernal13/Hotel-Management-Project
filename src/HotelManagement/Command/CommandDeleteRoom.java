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
		HotelFileController.deleteRoom(this.hotel);
		return true;
	}

	@Override
	public String getDescription() {
		return "delete a room from the current hotel";
	}
}
