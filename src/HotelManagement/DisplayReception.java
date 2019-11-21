package HotelManagement;

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
