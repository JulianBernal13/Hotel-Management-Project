package HotelManagement;

/**
 * 
 * @author
 *
 */
public class Employee {

	// Specifies the type of HotelManagement.Employee (e.g HotelManagement.Manager, Cleaner)
	private String titleName;

	// private HotelManagement.Employee[] a;

	/**
	 * Constructs an employee
	 * 
	 * @param titleName
	 * @param id
	 * @param salary
	 */
	public Employee(String titleName) {
		this.titleName = titleName;
	}

	// OVERRIDE IN SUBCLASSES
	public void setEmployeeID(String id) {

	}

	// OVERRIDE IN SUBCLASSES
	public void setSalary(int salary) {

	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"titleName='" + titleName + '\'' +
				'}';
	}
}
