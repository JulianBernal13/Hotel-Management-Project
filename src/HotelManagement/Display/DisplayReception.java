package HotelManagement.Display;

import HotelManagement.Command.CommandCustomerService;
import HotelManagement.Command.CommandInventoryEmployee;
import HotelManagement.ContractFileController;
import HotelManagement.Employee;
import HotelManagement.Hotel;

public class DisplayReception extends aDisplay {
    Hotel hotel;
    Employee employee;

    public DisplayReception(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
        addCommand(new CommandCustomerService(hotel, employee));
        addCommand(new CommandInventoryEmployee(hotel, employee));
    }

}
