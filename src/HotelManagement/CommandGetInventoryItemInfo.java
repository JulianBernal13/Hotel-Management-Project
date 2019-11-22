package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandGetInventoryItemInfo extends aCommandEmployee {
    public CommandGetInventoryItemInfo(Hotel h, Employee e) {
        hotel = h;
        employee = e;
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
