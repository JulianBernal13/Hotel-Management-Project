package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandAddRoom extends aCommand {
	public CommandAddRoom(Hotel h) {
		hotel = h;
	}

	@Override
	public boolean execute() throws IOException, ParseException {
		HotelFileController.move(this.hotel);
		return true;
	}

	@Override
	public String getDescription() {
		return "add a room to the current hotel";
	}
}