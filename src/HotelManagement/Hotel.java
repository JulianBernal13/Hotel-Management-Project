package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.io.IOException;

/**
 * @author Yingxie Gao, Anji Yu
 * @date 10/18/19 22:30
 */

public class Hotel {

	//needs to be matched with the initialization order in constructor
	public static enum Property {
		name, location, numOfLevel, levelRmNum, password;
	}

	private String name;
	private Location location;
	private int numOfLevel;
	private int levelRmNum;
	private String password;
	private String path;
	private File rooms[][];
	private ArrayList<File> customers;
	private Manager manager; //
	private ArrayList<File> employees;
	private HashMap<String, Integer> price;


	public Hotel (File hotelFile) throws FileNotFoundException {
		this.path = hotelFile.getPath();
		ArrayList<String> info = FileController.extractInfo(new File( path + File.separator + "info.txt"));
		this.name = info.get(Property.name.ordinal());
		this.password = info.get(Property.password.ordinal());
		this.numOfLevel = Integer.parseInt(info.get(Property.numOfLevel.ordinal()));
		this.levelRmNum = Integer.parseInt(info.get(Property.levelRmNum.ordinal()));
		this.location = new Location(info.get(Property.location.ordinal()));
		rooms = new File[numOfLevel][levelRmNum];
		for(int i = 0; i < numOfLevel; ++i) {
			for(int j = 0; j < levelRmNum; ++j) {
				rooms[i][j] = new File(hotelFile.getPath() + File.separator
						+ FileController.convertToTxt((i + 1) * 100 + j));
			}
		}
		customers = FileController.getAllFile(CustomerFileController.cdCustomerFile(hotelFile));
		employees = FileController.getAllFile(EmployeeFileController.cdEmployeeFile(hotelFile));
	}

	public String getPath() {
		return path;
	}

	public void writeHotelInfo() throws FileNotFoundException {
		File info = new File(path + File.separator + "info.txt");
		PrintWriter writer = new PrintWriter(info);
		writer.println(name);
		writer.println(location);
		writer.println(numOfLevel);
		writer.println(levelRmNum);
		writer.println(password);
		writer.flush();
		writer.close();
	}

	public Customer getCustomer(String name) throws FileNotFoundException {
		for(File customer : this.customers) {
			if (name.equals(FileController.convertTxtBack(customer.getName())))
				return new Customer(customer);
		}
		return null;
	}

	public <T> Room getRoom(T room) throws FileNotFoundException {
		int roomNumber = Integer.valueOf(String.valueOf(room));
		int level = roomNumber / 100;
		int num = roomNumber % 100;
		return Room.getRoomFile(this.rooms[level - 1][num]);
	}

	public Employee getEmployee(String name) {
		//return the employee matching name, or maybe just ID
		return null;
	}

//	private Employee getEmployee(String name) {
//		return EmployeeFileController.getEmployee(name);
//	}

}
