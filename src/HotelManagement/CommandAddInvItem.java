package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandAddInvItem extends aCommandManager {
    public CommandAddInvItem(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        InventoryFileController.createItem(hotel.cdInventoryFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "add inventory item";
    }
}
