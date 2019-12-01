package HotelManagement.Display;

import HotelManagement.Command.CommandGetInventoryInfo;
import HotelManagement.Command.CommandGetInventoryItemInfo;
import HotelManagement.Command.CommandUseInventoryItem;
import HotelManagement.Employee;
import HotelManagement.Hotel;

public class DisplayInventoryEmployee extends aDisplayEmployee {
    public DisplayInventoryEmployee(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
        addCommand(new CommandGetInventoryInfo(hotel));
        addCommand(new CommandGetInventoryItemInfo(hotel));
        addCommand(new CommandUseInventoryItem(hotel));
    }
}
