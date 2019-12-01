package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Display.DisplayCustomerService;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandCustomerService extends aCommandEmployee {
    public CommandCustomerService(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        DisplayCustomerService cs = new DisplayCustomerService(this.hotel, this.employee);
        ContractFileController.makeNotification(hotel);
        return cs.displayCommand();
    }

    @Override
    public String getDescription() {
        return "customer service";
    }
}
