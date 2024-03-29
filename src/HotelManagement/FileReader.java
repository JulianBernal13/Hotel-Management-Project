package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class FileReader {
	public static String getHotelInfo(File file, Hotel.Property property) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		int i = 0;
		while (i++ < property.ordinal() && in.hasNext())
			in.nextLine();
		return in.nextLine();
	}

	public static void displayHotelInfo(File info) throws FileNotFoundException {
		Scanner in = new Scanner(info);
		for (Hotel.Property tmp : Hotel.Property.values()) {
			System.out.println(tmp.name() + ": " + in.nextLine());
		}
	}

	public static void displayHotelInfo(String hotelPath) throws FileNotFoundException {
		displayHotelInfo(new File(hotelPath + File.separator + "info.txt"));
	}

	public static String displayRoomInfoIndivual(File file, Room.RoomProperty property) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		int i = 0;
		while (i++ < property.ordinal() && in.hasNext())
			in.nextLine();
		return in.nextLine();
	}
	
	public static void displayRoomInfo(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		for (Room.RoomProperty tmp : Room.RoomProperty.values()) {
			System.out.println(tmp.name() + ": " + in.nextLine());
		}
	}

	public static String getManagerInfo(File file, Manager.ManagerProperty property) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		int i = 0;
		while (i++ < property.ordinal() && in.hasNext())
			in.nextLine();
		return in.nextLine();
	}

	public static String getEmployeeInfo(File file, Employee.Property property) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		int i = 0;
		while (i++ < property.ordinal() && in.hasNext())
			in.nextLine();
		return in.nextLine();
	}

	public static void displayEmpInfo(File file) throws FileNotFoundException {
		Scanner in = new Scanner(file);
		for (Employee.Property tmp : Employee.Property.values()) {
			System.out.println(tmp.name() + ": " + in.nextLine());
		}
	}

	public static void displayAllRoom(File file) throws FileNotFoundException {

	}

	public static void diaplayAllChosenRoom(File file, Room.RoomProperty property) throws FileNotFoundException {
		ArrayList<String> allRoomsWithType = new ArrayList<>();
		for (File f : file.listFiles()) {
			Scanner sc = new Scanner(f);
			int i = 0;
			String name = "";
			while (i < property.ordinal() && sc.hasNext()) {
				if (i == Room.RoomProperty.number.ordinal())
					name = sc.nextLine();
				else {
					sc.nextLine();
				}
				++i;
			}
			String tmp = sc.nextLine();
			allRoomsWithType.add(name + "     " + tmp);
		}
		Collections.sort(allRoomsWithType);
		Printer.printArray(allRoomsWithType);
	}
}
