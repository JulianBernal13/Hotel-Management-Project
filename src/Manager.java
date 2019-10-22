package [insert name here]

public class Manager extends Employee {
	private String id;
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
