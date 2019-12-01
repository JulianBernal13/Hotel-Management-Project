package HotelManagement.Command;

import HotelManagement.Hotel;
import HotelManagement.RoomFileController;

import java.io.IOException;
import java.text.ParseException;

public class CommandChangeRoomType extends aCommandManager {
    public CommandChangeRoomType(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        RoomFileController.applyRoomType(hotel.getFile());
        return true;
    }

    @Override
    public String getDescription() {
        return "change room type";
    }
}
