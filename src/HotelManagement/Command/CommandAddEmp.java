package HotelManagement.Command;

import HotelManagement.EmployeeFileController;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandAddEmp extends aCommandManager {
    public CommandAddEmp(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        EmployeeFileController.createEmployee(hotel.cdEmployeeFolder(), hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "hire employee";
    }
}
