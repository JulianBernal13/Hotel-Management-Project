package HotelManagement.Command;

import HotelManagement.Display.DisplayEditEmployeeManager;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandEditEmployeeManager extends aCommandManager {
    public CommandEditEmployeeManager(Hotel h) {
        this.hotel = h;
        this.manager = h.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        DisplayEditEmployeeManager displayEditEmployeeManager = new DisplayEditEmployeeManager(hotel);
        return displayEditEmployeeManager.displayCommand();
    }

    @Override
    public String getDescription() {
        return "employee menu";
    }
}
