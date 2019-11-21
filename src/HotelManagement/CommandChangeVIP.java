package HotelManagement;

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
        return "to change a customer's VIP status";
    }
}
