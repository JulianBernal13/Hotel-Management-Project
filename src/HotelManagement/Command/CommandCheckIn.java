package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;

public class CommandCheckIn extends aCommandEmployee {

    public CommandCheckIn(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
    }

    @Override
    public boolean execute() throws IOException {
        ContractFileController.checkIn(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "check a customer in";
    }
}
