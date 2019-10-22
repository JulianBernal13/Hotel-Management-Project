package [insert name here]
/**
 * 
 * @author
 *
 */
public class Employee {

	// Specifies the type of Employee (e.g Manager, Cleaner)
	private String titleName;

	// Specifies the exact person within a position (e.g Cleaner, id:1234 -->
	// person named "Bob")
	private String id;

	// Specifies the salary this type of employee gets
	private int salary;

	// private Employee[] a;

	/**
	 * Constructs an employee
	 * 
	 * @param titleName
	 * @param id
	 * @param salary
	 */
	public Employee(String titleName, String id, int salary) {
		this.titleName = titleName;
		this.id = id;
		this.salary = salary;
	}

}
