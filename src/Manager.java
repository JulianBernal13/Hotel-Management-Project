

public class Manager extends Employee {
	// Specifies the exact person within a position (e.g Cleaner, id:1234 -->
	// person named "Bob")
	private String id;

	// Specifies the salary this type of employee receives
	private int salary;

	public Manager(String titleName, String id, int salary) {
		super("Manager");
		setEmployeeID(id);
		setSalary(salary);
	}

	@Override
	public void setEmployeeID(String id) {
		this.id = id;
	}

	@Override
	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void deleteEmployee(Employee[] a, String titleName, String id) {

	}

	public void addEmployee(Employee[] a, String titleName, String id) {

	}
}
