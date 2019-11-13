package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ManagerMenu implements Menu {
	Hotel hotel;
	Manager manager;

	public ManagerMenu(Hotel hotel) {
		this.hotel = hotel;
	}

	@Override
	public void menu() throws IOException {
		File cur = new File(hotel.getPath());
		File employeeFiles = new File(cur.getPath() + File.separator + "Employee");
		System.out.println("Welcome manager");
		Printer.printManagerMenu();
		EmployeeFileController.deleteEmployee2(employeeFiles);
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		switch (command) {
		case "exit":
			break;
		case "DH": {
			FileReader.displayHotelInfo(hotel.getPath());
			break;
		}
		case "DE": {
			EmployeeFileController.getEmployeesInfo(employeeFiles);
			break;
		}
		case "LR": {
			RoomFileController.listRooms(cur); // call Room List
			break;
		}
		case "CE": {
			EmployeeFileController.getEmployeeInfo(employeeFiles);
			break;
		}
		case "EE": {
			EmployeeFileController.modifyEmployee(employeeFiles);
			break;
		}
		case "AE": {
			EmployeeFileController.createEmployee(employeeFiles, hotel);
			break;
		}
		case "FE": {
			EmployeeFileController.deleteEmployee(employeeFiles);
			break;
		}
		case "CRT": {
			RoomFileController.applyRoomType(cur);
			menu();
			break;
		} // change room type
		case "Schedule":{
			ScheduleController.ScheduleMenu(cur);
			break;
		}
		}
	}
}
