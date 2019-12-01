package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandPriceMatch extends aCommandEmployee {

    public CommandPriceMatch(Hotel h, Employee e) {
        hotel = h;
        employee = e;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        ContractFileController.priceMatchMenu(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "price match a contract";
    }
}
