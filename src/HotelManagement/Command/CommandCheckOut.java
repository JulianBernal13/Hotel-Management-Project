package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;

public class CommandCheckOut extends aCommandEmployee {
    public CommandCheckOut(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
    }

    @Override
    public boolean execute() throws IOException {
        ContractFileController.checkOut(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "check a customer out";
    }
}
