package HotelManagement.Command;

import HotelManagement.*;

import java.io.IOException;

public class CommandShowRoomPic extends aCommandEmployee {

    public CommandShowRoomPic(Hotel hotel, Employee employee) {
        this.hotel = hotel;
        this.employee = employee;
    }

    @Override
    public boolean execute() throws IOException {
        RoomFileController.showRoomPic(hotel);
        return true;
    }

    @Override
    public String getDescription() {
        return "show room picture";
    }
}
