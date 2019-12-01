package HotelManagement.Command;

import HotelManagement.Display.DisplayInventoryEmployee;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;
import java.text.ParseException;

public class CommandInventoryEmployee extends aCommandEmployee {
    public CommandInventoryEmployee(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        DisplayInventoryEmployee di = new DisplayInventoryEmployee(this.hotel, this.employee);
        return di.displayCommand();
    }

    @Override
    public String getDescription() {
        return "manage inventory";
    }
}
