package HotelManagement.Display;

import HotelManagement.Command.*;
import HotelManagement.Employee;
import HotelManagement.Hotel;

public class DisplayCustomerService extends aDisplayEmployee {
    public DisplayCustomerService(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
        addCommand(new CommandCheckIn(hotel, employee));
        addCommand(new CommandCheckOut(hotel, employee));
        addCommand(new CommandMakeReservation(hotel, employee));
        addCommand(new CommandChangeVIP(hotel, employee));
        addCommand(new CommandPriceMatch(hotel, employee));
        addCommand(new CommandDeleteReservation(hotel, employee));
        addCommand(new CommandShowRoomPic(hotel, employee));

    }
}
