package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandUseInventoryItem extends aCommandEmployee {
    public CommandUseInventoryItem(Hotel h, Employee e) {
        hotel = h;
        employee = e;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        InventoryFileController.useItem(hotel.cdInventoryFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "use inventory item";
    }
}
