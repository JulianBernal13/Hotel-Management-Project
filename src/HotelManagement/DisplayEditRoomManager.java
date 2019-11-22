package HotelManagement;

public class DisplayEditRoomManager extends aDisplayManager {
    public DisplayEditRoomManager(Hotel h) {
        this.hotel = h;
        this.manager = h.getManager();
        addCommand(new CommandListRooms(hotel));
        addCommand(new CommandChangeRoomType(hotel));
    }
}
