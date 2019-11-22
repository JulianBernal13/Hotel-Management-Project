package HotelManagement;

import java.io.IOException;

public class DisplayInventoryEmployee extends aDisplayEmployee {
    public DisplayInventoryEmployee(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
        addCommand(new CommandGetInventoryInfo(hotel, employee));
        addCommand(new CommandGetInventoryItemInfo(hotel, employee));
        addCommand(new CommandUseInventoryItem(hotel, employee));
    }
}
