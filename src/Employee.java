
/**
 * 
 * @author
 *
 */
public class Employee {

	// Specifies the type of Employee (e.g Manager, Cleaner)
	private String titleName;

	// private Employee[] a;

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

}
