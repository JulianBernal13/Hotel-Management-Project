package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandGetInventoryItemInfo extends aCommand {
    public CommandGetInventoryItemInfo(Hotel h) {
        hotel = h;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        InventoryFileController.getInventoryItemInfo(this.hotel.cdInventoryFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "get inventory item information";
    }
}
