package HotelManagement.Display;

import HotelManagement.Command.*;
import HotelManagement.Hotel;

public class DisplayEditEmployeeManager extends aDisplayManager {
    public DisplayEditEmployeeManager(Hotel h) {
        this.hotel = h;
        this.manager = h.getManager();
        addCommand(new CommandGetEmpInfo(hotel));
        addCommand(new CommandAddEmp(hotel));
        addCommand(new CommandDeleteEmp(hotel));
        addCommand(new CommandEditEmp(hotel));
        addCommand(new CommandPayRoll(hotel));
        addCommand(new CommandScheduleMenu(hotel));
    }
}
