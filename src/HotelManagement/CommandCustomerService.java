package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandCustomerService extends aCommandEmployee {
    CommandCustomerService(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        DisplayCustomerService cs = new DisplayCustomerService(this.hotel, this.employee);
        return cs.displayCommand();
    }

    @Override
    public String getDescription() {
        return "customer service";
    }
}
