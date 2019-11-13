package HotelManagement;

import java.io.File;

public class ScheduleController {

	public static void ScheduleMenu(File hotel) {
		File schedulePath = new File(hotel.getPath()+ File.separator + "Schedules");
		if(!schedulePath.exists()) {
			schedulePath.mkdir();
		}
		Boolean toggle = false;
		while(toggle) {
			
		}
	}
}
