package HotelManagement.Command;

import HotelManagement.Customer;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;

public class CommandChangeVIP extends aCommandEmployee {

    public CommandChangeVIP(Hotel h, Employee e) {
        hotel = h;
        employee = e;
    }

    @Override
    public boolean execute() throws IOException {
        Customer.changeVIPStatue(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "change a customer's VIP status";
    }
}
