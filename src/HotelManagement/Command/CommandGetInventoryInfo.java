package HotelManagement.Command;

import HotelManagement.Hotel;
import HotelManagement.InventoryFileController;

import java.io.IOException;
import java.text.ParseException;

public class CommandGetInventoryInfo extends aCommand {
    public CommandGetInventoryInfo(Hotel h) {
        hotel = h;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        InventoryFileController.getInventoryInfo(this.hotel.cdInventoryFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "get inventory info";
    }
}
