package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandDeleteReservation extends aCommandEmployee {
    public CommandDeleteReservation(Hotel h, Employee e) {
        hotel = h;
        employee = e;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        ContractFileController.deleteContract(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "to cancel a reservation";
    }
}
