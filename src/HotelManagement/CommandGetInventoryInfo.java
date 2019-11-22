package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandGetInventoryInfo extends aCommandEmployee {
    public CommandGetInventoryInfo(Hotel h, Employee e) {
        hotel = h;
        employee = e;
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
