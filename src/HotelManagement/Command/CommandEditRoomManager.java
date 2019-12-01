package HotelManagement.Command;

import HotelManagement.Display.DisplayEditRoomManager;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandEditRoomManager extends aCommandManager {
    public CommandEditRoomManager(Hotel h) {
        this.hotel = h;
        this.manager = h.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        DisplayEditRoomManager displayEditRoomManager = new DisplayEditRoomManager(hotel);
        return displayEditRoomManager.displayCommand();
    }

    @Override
    public String getDescription() {
        return "room menu";
    }
}
