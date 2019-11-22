package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandGetEmpInfo extends aCommandManager {
    public CommandGetEmpInfo(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        EmployeeFileController.getEmployeeInfo(hotel.cdEmployeeFolder());
        return true;
    }

    @Override
    public String getDescription() {
        return "show employee information";
    }
}
