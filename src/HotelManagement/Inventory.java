package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import HotelManagement.Employee.Property;

/**
 * 
 * @author Julian Garcia
 *
 */
public class Inventory {

	// Specifies the type of HotelManagement.Employee (e.g
	// HotelManagement.Manager, Cleaner)
	private String name;

	// ID (e.g. C43245)
	private String amount;

	private String brand;
	private int salary;
	private String path;
	private String hotel;
	private boolean clockIn;

	public static enum Property {
		name, amount, brand, // salary
	}

	/**
	 * Constructs an employee
	 * 
	 * @param amount
	 * @param type
	 * @param paymentType
	 * @param salary
	 */
	public Inventory(String name, String amount, String brand) {
		this.name = name;
		this.amount = amount;
		this.brand = brand;
//		hotel = checkCurrentHotel();
	}

	public Inventory(File invItemFile) throws FileNotFoundException {
		Scanner sc = new Scanner(invItemFile);
		this.path = invItemFile.getPath();
		ArrayList<String> info = FileController.extractInfo(invItemFile);
		this.name = info.get(Property.name.ordinal());
		this.amount = info.get(Property.amount.ordinal());
		this.brand = info.get(Property.brand.ordinal());
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setType(String brand) {
		this.brand = brand;
	}

	// public void setSalary(int salary) {
	// this.salary = salary;
	// }

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}

	public String getHotel(String hotel) {
		return this.hotel;
	}

	/**
	 * Returns the path for this hotel
	 * 
	 * @return hotel
	 */
	public String checkCurrentHotel() {
		String hotel = path.substring(19, path.length() - 9);
		return hotel;
	}

	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public String getAmount() {
		return amount;
	}

	public String getBrand() {
		return brand;
	}

	public String toString() {
		return name;
	}
}
