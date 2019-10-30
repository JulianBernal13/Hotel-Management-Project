package HotelManagement;

import java.io.*;
import java.util.Scanner;

public class Menu {

	public static enum HotelProperty {
		name, location, numOfLevel, levelRmNum, password;
	}

	public void menu() throws IOException {
		Printer.printWelcome();

		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();

		switch (command) {
		case "new": {
			createHotel();
			menu();
		}
		case "manage": {
			manageHotel();
			menu();
		}
		case "exit":
			break;
		default: {
			System.out.println("Invalid command, please enter another command");
			menu();
		}
		}
	}

	public void createHotel() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your hotel's name?");
		String name = sc.nextLine();
		String path = "." + File.separator + "ManagementSystem";
		File tmp = new File(path);
		tmp.mkdir();
		path += File.separator + name;
		File hotelFile = new File(path);
		while (!hotelFile.mkdir()) {
			System.out.println("This name is taken, please enter another name");
			name = sc.nextLine();
			hotelFile = new File(path);
		}
		System.out.println("What is your hotel's location? Enter an Address.");
		String address = sc.nextLine();
		System.out.println("How many floors do your hotel has?");
		int floor = Integer.parseInt(sc.nextLine());
		System.out.println("How many rooms do your hotel has for each floor?");
		int numRoom = Integer.parseInt(sc.nextLine());
		System.out.println("Please set a password for this hotel.");
		String password = sc.nextLine();

		Hotel hotel = new Hotel(path, name, new Location(address), floor, numRoom, password);
	}

	private void manageHotel() throws IOException {
		Scanner sc = new Scanner(System.in);
		String path = "." + File.separator + "ManagementSystem";
		System.out.println("Which Hotel?");
		File tmp = new File(path);
		for (File file : tmp.listFiles()) {
			System.out.println(file.getName());
		}
		System.out.println("Please enter hotel name");
		String name = sc.nextLine();
		File cur = new File(path + File.separator + name);
		while (!cur.exists()) {
			System.out.println("Hotel does not exist. Please enter another one");
			for (File file : tmp.listFiles()) {
				System.out.println(file.getName());
			}
			name = sc.nextLine();
			cur = new File(path + File.separator + name);
		}
		// path += File.separator + name;
		System.out.println("Choose your occupation number");
		System.out.println("1. Manager");
		System.out.println("2. Reception");

		int occu = sc.nextInt();
		switch (occu) {
		case 1: {
			managedByManager(cur);
			break;
		}
		case 2: {
			managedByReception(cur);
			break;
		}
		default:
			break;
		}
	}

	private void managedByReception(File cur) throws IOException {
		Printer.printReceptionMenu();
		Scanner sc = new Scanner(System.in);
		String command = sc.nextLine();
		switch (command) {
		case "CI": {
			String roomPath = cur.getPath() + File.separator + "Rooms";
			System.out.println("What type of room do you want?((single,double,triple,queen,king))");
			String type = sc.nextLine();
			if (Room.showTypeRoomEmpty(roomPath, type) == 1) {
				System.out.println("Which room are you going to check-in?");
				int num = Integer.parseInt(sc.nextLine());
				Room.checkIn(roomPath, num);
				System.out.println("Success! Now the room is occupied.");
			} else {
				System.out.println("Sorry, we do not have such room available.");
			}
			break;
		}
		case "CO": {
			System.out.println("which room are you going to check-out?");
			int num = Integer.parseInt(sc.nextLine());
			String roomPath = cur.getPath() + File.separator + "Rooms";
			Room.checkOut(roomPath, num);
			System.out.println("Success! Now the room is empty.");
			break;
		}
		case "LC": {
			CustomerFileController.menuLookUp(CustomerFileController.cdCustomerFile(cur));
			break;
		}
		}
	}

	private void managedByManager(File cur) throws IOException {

		String path = cur.getPath() + File.separator + "info.txt";
		File info = new File(path);
		if (info.exists()) {
			System.out.println("Please enter hotel password");
			String password = FileReader.getHotelInfo(info, Hotel.HotelProperty.password);
			System.out.println("Here is a hint " + password);
			Scanner sc = new Scanner(System.in);
			if (!sc.nextLine().equals(password)) {
				System.out.println("You are not a manager!");
				return;
			}
			System.out.println("Welcome manager");
			String path2 = cur.getPath() + File.separator + "Employee";
			File info1 = new File(path2);
			File[] info1Files = info1.listFiles();
			for (File f : info1Files) {
				if (f.getName().charAt(0) == 'M') {
					path2 = f.getPath();
				}
			}
			File info2 = new File(path2);
			Manager manager = new Manager(path2, FileReader.getManagerInfo(info2, Manager.ManagerProperty.titleName),
					FileReader.getManagerInfo(info2, Manager.ManagerProperty.id),
					FileReader.getManagerInfo(info2, Manager.ManagerProperty.paymentType),
					Integer.parseInt(FileReader.getManagerInfo(info2, Manager.ManagerProperty.salary)));
			Printer.printManagerMenu();
			String command = sc.nextLine();
			switch (command) {
			case "exit":
				break;
			case "DH": {
				FileReader.displayHotelInfo(info);
				break;
			}
			case "DE": {
				String employeePath = cur.getPath() + File.separator + "Employee";
				File info3 = new File(employeePath);
				File[] temp = info3.listFiles();
				for (File f : temp) {
					FileReader.displayEmpInfo(f);
				}
				break;
			}
			case "LR":
				File rooms = new File(cur.getPath() + File.pathSeparator + "Rooms");
				listRooms(rooms); // call Room List
			case "CE":
				break; // call employee
			case "EE": {

				String employeePath = cur.getPath() + File.separator + "Employee";

				break;
			} // edit employee
			case "AE": {
				String employeePath = cur.getPath() + File.separator + "Employee";
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Enter the title Name for new employee");
				String titleName = sc2.nextLine();
				System.out.println("Enter the ID for new employee");
				String id = sc2.nextLine();
				System.out.println("Enter the paymentType for new employee");
				String paymentType = sc2.nextLine();
				System.out.println("Enter the salary for new employee");
				String salary = sc2.nextLine();
				Employee emp = new Employee(employeePath, titleName, id, paymentType, Integer.parseInt(salary));
				manager.addEmployeeToFile(emp.getPath(), emp);
				break;
			}
			case "FE": {
				String employeePath = cur.getPath() + File.separator + "Employee";
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Enter the ID for new employee you want to fire");
				String id = sc2.nextLine();
				manager.deleteEmployeeFromFile(employeePath, id);
				break;
			}
			case "CRT": {
				String roomPath = cur.getPath() + File.separator + "Rooms";
				File f = new File(roomPath);
				FileReader.diaplayAllChosenRoom(f, Room.RoomProperty.type);
				System.out.println("Which room are you going to change type?");
				int num = Integer.parseInt(sc.nextLine());
				System.out.println("What type are you going to change?(single,double,triple,queen,king)");
				String type = sc.nextLine();
				Room.changeRoomType(roomPath, num, type);
				System.out.println("Success! Now the room is " + type);
				break;
			} // change room type
			}
		}
	}

	private void listRooms(File rooms) {
		Printer.printRoomview();
		Scanner sc = new Scanner(System.in);
		for (File file : rooms.listFiles()) {
			System.out.println("-" + file.getName());
		}
		String room = sc.nextLine();
		File check = new File(rooms + File.separator + room);
		while (!check.exists()) {
			System.out.println("room does not exist, enter another room \n");
			for (File file : rooms.listFiles()) {
				System.out.println("-" + file.getName());
			}
			room = sc.nextLine();
			check = new File(rooms + File.separator + room);
		}
		// TODO
		// james
	}

}
