package HotelManagement;

import java.io.IOException;
import java.text.ParseException;

public class CommandScheduleMenu extends aCommandManager {
    public CommandScheduleMenu(Hotel h) {
        hotel = h;
        manager = hotel.getManager();
    }

    @Override
    public boolean execute() throws IOException, ParseException {
        ScheduleController.ScheduleMenu(hotel.getFile());
        return true;
    }

    @Override
    public String getDescription() {
        return "schedule menu";
    }
}
