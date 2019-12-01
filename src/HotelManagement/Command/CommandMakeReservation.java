package HotelManagement.Command;

import HotelManagement.ContractFileController;
import HotelManagement.Employee;
import HotelManagement.Hotel;

import java.io.IOException;

public class CommandMakeReservation extends aCommandEmployee {

    public CommandMakeReservation(Hotel hotel, Employee e) {
        this.hotel = hotel;
        this.employee = e;
    }

    @Override
    public boolean execute() throws IOException {
        ContractFileController.reserveRoom(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "make a reservation";
    }
}
