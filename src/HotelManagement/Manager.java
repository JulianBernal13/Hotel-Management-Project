package HotelManagement;

import java.util.ArrayList;

public class Manager extends Employee {

	private String id;
	private int salary;
	// Only Manager can access this
	private ArrayList<Employee> employees;

	public Manager(String titleName, String id, int salary) {

		super(titleName, id);

		employees = new ArrayList<Employee>();
		addEmployee(this);
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

	public Employee getEmployee(String id) {

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getID().compareTo(id) == 0) {
				return employees.get(i);
			}
		}
		return null;
	}

	// List of employees
	public int getEmployees() {
		return employees.size();
	}

	public void editEmployee(String id, String titleName, String id2) {
		this.getEmployee(id).setTitleName(titleName);
		this.getEmployee(id).setID(id2);

	}

	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

	public void addManyEmployees(Employee[] employees) {

		for (int i = 0; i < employees.length; i++) {
			this.addEmployee(employees[i]);
		}
	}

	public int compareTwoEmployees(Employee employee1, Employee employee2) {

		String alphabet = employee1.getTrueID(employee1.getID());
		Employee temp = new Employee(employee2.getTitleName(), employee2.getID());
		String alphabet2 = employee2.getTrueID(employee2.getID());

		// if this, employee 1 is first (i.e. -1)
		if (Integer.parseInt(alphabet) < Integer.parseInt(alphabet2)) {
			return -1;
		}
		// if this, employee 2 is first (i.e. 1)
		else if (Integer.parseInt(alphabet) > Integer.parseInt(alphabet2)) {
			return 1;
		}
		// if this, order doesn't matter, (i.e. 0)
		else {
			return 0;
		}
	}

	// Selection Sort (employees alphabet style)
	public void sortEmployees() {

		// One by one move boundary of unsorted subarray
		for (int i = 0; i < employees.size() - 1; i++) {
			// Find the minimum element in unsorted array
			int min_idx = i;
			for (int j = i + 1; j < employees.size(); j++)
				if (this.compareTwoEmployees(employees.get(j), employees.get(min_idx)) == -1) {
					min_idx = j;
				}
			// Swap the found minimum element with the first
			// element
			Employee temp = employees.get(min_idx);
			employees.set(min_idx, employees.get(i));
			employees.set(i, temp);
		}
	}

}
