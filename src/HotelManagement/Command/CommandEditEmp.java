package HotelManagement.Command;

import HotelManagement.EmployeeFileController;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandEditEmp extends aCommandManager {
    public CommandEditEmp(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        EmployeeFileController.modifyEmployee(hotel.cdEmployeeFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "edit employee";
    }
}
