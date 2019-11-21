package HotelManagement;

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
        return "to make a reservation";
    }
}
