package HotelManagement.Display;

import HotelManagement.Command.*;
import HotelManagement.Hotel;
import HotelManagement.Manager;

import java.io.IOException;

public class DisplayManager extends aDisplay {
	Hotel hotel;
	Manager manager;

	public DisplayManager(Hotel hotel, Manager manager) throws IOException {
		this.hotel = hotel;
		this.manager = manager;
		addCommand(new CommandShowHotelInfo(hotel));
		addCommand(new CommandEditRoomManager(hotel));
		addCommand(new CommandEditEmployeeManager(hotel));
		addCommand(new CommandInventoryManager(hotel));
		addCommand(new CommandMove(hotel)); // new code
		addCommand(new CommandAddRoom(hotel)); // new code
		addCommand(new CommandDeleteRoom(hotel)); // new code
		addCommand(new CommandContractReport(hotel));
		
	}
}
