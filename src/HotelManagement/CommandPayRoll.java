package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandPayRoll extends aCommandManager {
    public CommandPayRoll(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        EmployeeFileController.payroll(hotel.getFile());
        return true;
    }

    @Override
    public String getDescription() {
        return "payroll";
    }
}
