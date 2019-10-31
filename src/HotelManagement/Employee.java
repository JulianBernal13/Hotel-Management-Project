package HotelManagement;

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

	public static enum EmployeeProperty {
		titleName, id, paymentType, salary
	}

	/**
	 * Constructs an employee
	 * 
	 * @param titleName
	 * @param id
	 * @param paymentType
	 * @param salary
	 */
	public Employee(String path, String titleName, String id, String paymentType, int salary) {
		this.path = path;
		this.titleName = titleName;
		this.id = id;
		this.paymentType = paymentType;
		this.salary = salary;
		clockIn = false;
		hotel = checkCurrentHotel();
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

	public String toString() {
		return "Employee{" + "titleName='" + titleName + '\'' + '}';
	}
}
