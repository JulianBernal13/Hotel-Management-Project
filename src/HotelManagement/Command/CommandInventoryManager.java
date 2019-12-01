package HotelManagement.Command;

import HotelManagement.Display.DisplayInventoryManager;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandInventoryManager extends aCommandManager {
    public CommandInventoryManager(Hotel h) {
        this.hotel = h;
        this.manager = h.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        DisplayInventoryManager displayInventoryManager = new DisplayInventoryManager(hotel);
        return displayInventoryManager.displayCommand();
    }

    @Override
    public String getDescription() {
        return "inventory menu";
    }
}
