package HotelManagement.Command;

import HotelManagement.EmployeeFileController;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandDeleteEmp extends aCommandManager {
    public CommandDeleteEmp(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        EmployeeFileController.deleteEmployee(hotel.cdEmployeeFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "fire employee";
    }
}
