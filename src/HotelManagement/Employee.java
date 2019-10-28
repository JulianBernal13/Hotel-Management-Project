package HotelManagement;

/**
 * 
 * @author
 *
 */
public class Employee {

	// Specifies the type of HotelManagement.Employee (e.g
	// HotelManagement.Manager, Cleaner)
	private String titleName;
	
	//ID (e.g. C43245)
	private String id;
	
	// private HotelManagement.Employee[] a;

	/**
	 * Constructs an employee
	 * 
	 * @param titleName
	 */
	public Employee(String titleName, String id) {
		this.titleName = titleName;
		this.id = id;
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

	public String getID() {
		return id;
	}

	public void setID(String id) {
		this.id = id;
	}

	/**
	 * A=10, B=11, C=12, .... , Z=35
	 * 
	 * Employee has "CXXX" with C indicating its titleName (e.g. Cleaner). True ID looks like
	 * "12"
	 * 
	 * (e.g ACXXX=1012 , MXXXXX=22 , etc.)
	 * 
	 * (This method is used to sort employees alphabet style)
	 * 
	 * @return alphabet
	 */
	public String getTrueID(String id) {

		String trueID = "";
		for (int i = 0; i < id.length(); i++) {
			if (Character.isLetter(id.charAt(i)) == true) {
				if (id.charAt(i) == 'A') {
					trueID += "10";
				}
				if (id.charAt(i) == 'B') {
					trueID += "11";
				}
				if (id.charAt(i) == 'C') {
					trueID += "12";
				}
				if (id.charAt(i) == 'D') {
					trueID += "13";
				}
				if (id.charAt(i) == 'E') {
					trueID += "14";
				}
				if (id.charAt(i) == 'F') {
					trueID += "15";
				}
				if (id.charAt(i) == 'G') {
					trueID += "16";
				}
				if (id.charAt(i) == 'H') {
					trueID += "17";
				}
				if (id.charAt(i) == 'I') {
					trueID += "18";
				}
				if (id.charAt(i) == 'J') {
					trueID += "19";
				}
				if (id.charAt(i) == 'K') {
					trueID += "20";
				}
				if (id.charAt(i) == 'L') {
					trueID += "21";
				}
				if (id.charAt(i) == 'M') {
					trueID += "22";
				}
				if (id.charAt(i) == 'N') {
					trueID += "23";
				}
				if (id.charAt(i) == 'O') {
					trueID += "24";
				}
				if (id.charAt(i) == 'P') {
					trueID += "25";
				}
				if (id.charAt(i) == 'Q') {
					trueID += "26";
				}
				if (id.charAt(i) == 'R') {
					trueID += "27";
				}
				if (id.charAt(i) == 'S') {
					trueID += "28";
				}
				if (id.charAt(i) == 'T') {
					trueID += "29";
				}
				if (id.charAt(i) == 'U') {
					trueID += "30";
				}
				if (id.charAt(i) == 'V') {
					trueID += "31";
				}
				if (id.charAt(i) == 'W') {
					trueID += "32";
				}
				if (id.charAt(i) == 'X') {
					trueID += "33";
				}
				if (id.charAt(i) == 'Y') {
					trueID += "34";
				}
				if (id.charAt(i) == 'Z') {
					trueID += "35";
				}
			}
		}
		return trueID;
	}

	public String toString() {
		return "Employee{" + "titleName='" + titleName + '\'' + '}';
	}
}
