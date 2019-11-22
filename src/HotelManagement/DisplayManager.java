package HotelManagement;

public class DisplayManager extends aDisplay {
    Hotel hotel;
    Manager manager;


    public DisplayManager(Hotel hotel, Manager manager) {
        this.hotel = hotel;
        this.manager = manager;
        addCommand(new CommandShowHotelInfo(hotel));
        addCommand(new CommandEditRoomManager(hotel));
        addCommand(new CommandEditEmployeeManager(hotel));
        addCommand(new CommandInventoryManager(hotel));
    }
}
