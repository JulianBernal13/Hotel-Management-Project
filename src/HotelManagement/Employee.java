package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Julian Garcia
 *
 */
public class Employee {

	// Specifies the type of HotelManagement.Employee (e.g
	// HotelManagement.Manager, Cleaner)
	private String titleName;

	// ID (e.g. C43245)
	private String id;

	private String paymentType;
	private int salary;
	private String path;
	private String hotel;
	private boolean clockIn;
	private String payroll;

	public static enum Property {
		titleName, id, paymentType, salary, payroll
	}

	/**
	 * Constructs an employee
	 * 
	 * @param titleName
	 * @param id
	 * @param paymentType
	 * @param salary
	 */
	public Employee(String path, String titleName, String id, String paymentType, int salary, String payroll) {
		this.path = path;
		this.titleName = titleName;
		this.id = id;
		this.paymentType = paymentType;
		this.salary = salary;
		clockIn = false;
		this.payroll = payroll;
		hotel = checkCurrentHotel();
	}

	public Employee(File employeeFile) throws FileNotFoundException {
//		Scanner sc = new Scanner(employeeFile);
		this.path = employeeFile.getPath();
		ArrayList<String> info = FileController.extractInfo(employeeFile);
		this.titleName = info.get(Property.titleName.ordinal());
		this.id = info.get(Property.id.ordinal());
		this.salary = Integer.parseInt(info.get(Property.salary.ordinal()));
		this.paymentType = info.get(Property.paymentType.ordinal());
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public void setID(String id) {
		this.id = id;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public void setPayroll(String payroll) {
		this.payroll = payroll;
	}

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

	public void clockIn() {
		clockIn = true;
	}

	public void clockOut() {
		clockIn = false;
	}

	public String getPath() {
		return path;
	}

	public String getTitleName() {
		return titleName;
	}

	public String getID() {
		return id;
	}

	public int getSalary() {
		return salary;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public String getPayroll() {
		return payroll;
	}

	public String toString() {
		return "Employee{" + "titleName='" + titleName + '\'' + '}';
	}
}
