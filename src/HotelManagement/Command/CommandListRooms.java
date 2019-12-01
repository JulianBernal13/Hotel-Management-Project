package HotelManagement.Command;

import HotelManagement.Hotel;
import HotelManagement.RoomFileController;

import java.io.IOException;
import java.text.ParseException;

public class CommandListRooms extends aCommand {
    public CommandListRooms(Hotel h) {
        hotel = h;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        RoomFileController.listRooms(hotel.getFile());
        return true;
    }

    @Override
    public String getDescription() {
        return "list rooms";
    }
}
