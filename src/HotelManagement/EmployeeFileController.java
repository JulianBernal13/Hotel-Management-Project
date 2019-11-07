package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EmployeeFileController implements FileController {
	public static File menuLookUp(File employees) throws IOException {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter Employee ID");
		String id = sc2.nextLine();
		File employee = lookUpEmployee(employees, id);
		if (!employee.exists()) {
			System.out.println("The Employee with the ID: " + id + " does not exist");
			return new File("empty");
		}
		System.out.println("The Employee with the ID: " + id + " does exists and here are its properties" + "\n");
		Printer.printFile(employee);

		return employee;
	}

	public static void displayAll(File employees) {

	}

	public static File lookUpEmployee(File employees, String id) {
		return new File(employees.getPath() + File.separator + id + ".txt");
	}

	// change for later, add manager.addEmployeeToFile code here
	public static void createEmployee(File employees) throws IOException {
		deleteEmployee2(employees);
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the title name for new employee");
		String titleName = sc.nextLine();
		String tempID = randEmpID(employees, titleName);
		System.out.println("Enter the ID for new employee, or use this randomly built one: " + tempID);
		String id = sc.nextLine();
		File f = lookUpEmployee(employees, id);
		if (f.exists()) {
			System.out.println("An Employee with this ID already exists");
			return;
		}
		System.out.println("Enter the paymentType for new employee");
		String paymentType = sc.nextLine();
		System.out.println("Enter the salary for new employee");
		String salary = sc.nextLine();

		String filePath = employees.getPath() + File.separator + id + ".txt";
		File newEmployee = new File(filePath);
		if (newEmployee.createNewFile()) {
			PrintWriter writer = new PrintWriter(filePath);
			writer.println("Titlename: " + titleName);
			writer.println("ID: " + id);
			writer.println("PaymentType: " + paymentType);
			writer.println("Salary: " + salary);
			writer.flush();
			writer.close();
		}
		System.out.println("The employee titled: " + titleName + " with the ID: " + id + " has been added");
	}

	public static int getPropertyOrdinal(String property) {
		int i = 0;
		for (Employee.Property p : Employee.Property.values()) {
			if (p.name().toString().compareTo(property) == 0) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public static void getEmployeeInfo(File employees) throws IOException {
		File employee = menuLookUp(employees);
		if (employee.length() == 0)
			return;
		System.out.print("\n");
	}

	public static void getEmployeesInfo(File employees) throws FileNotFoundException {
		File[] temp = employees.listFiles();
		for (File f : temp) {
			if (f.getName().compareTo("Emp. to delete.txt") != 0) {
				Printer.printFile(f);
				System.out.print("\n");
			}
		}
	}

	// Creates directory
	public static File cdEmployeeFile(File hotel) {
		String filePath = hotel.getPath() + File.separator + "Employee";
		return new File(filePath);
	}

	public static ArrayList<String> extractEmpInfo(File employees, String name) throws FileNotFoundException {
		ArrayList<String> info = new ArrayList<>();
		// File employee = lookUpEmployee(employee, name);
		Scanner sc = new Scanner(lookUpEmployee(employees, name));
		while (sc.hasNext()) {
			info.add(sc.nextLine());
		}
		return info;
	}

	public static void modifyEmployee(File employees) throws IOException {
		File employee = menuLookUp(employees);
		if (employee.length() == 0)
			return;
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Are you sure you want to edit this employee? Re enter ID for final confirmation");
		String id = sc2.nextLine();
		if (FileReader.getEmployeeInfo(employee, Employee.Property.id).compareTo("ID: " + id) != 0) {
			System.out.println("Error: The entered ID is different or doesn't exist");
			return;
		}
		System.out.println("How many properties of this employee do you want to change?");
		String cP = sc2.nextLine();
		int cP2 = Integer.valueOf(cP);
		if (cP2 > 4) {
			System.out.println("Each Employee only has " + "4" + " properties");
			return;
		}
		String[] a = new String[4];
		while (cP2 > 0) {
			System.out.println("Enter what property you would like to change about this Employee");
			String property = sc2.nextLine();
			int i = getPropertyOrdinal(property);
			if (i == -1) {
				System.out.println("The property you are trying to change does not exist");
				return; // Exits program
			}
			a[i] = property;

			if (a[0] != null || a[1] != null) {
				a[0] = "titleName";
				a[1] = "id";
			}
			cP2--;
		}

		String newTName = "";
		String newID = "";
		String newPType = "";
		String newSalary = "";
		ArrayList<String> info = extractEmpInfo(employees, id);

		if (a[0] != null) {
			System.out.println("Enter the new title name for this employee");
			newTName = sc2.nextLine();
			a[0] = newTName;
		}
		if (a[1] != null) {
			String tempID2 = randEmpID(employees, a[0]);
			System.out.println("Enter the new ID for this employee, or use this randomly built one: " + tempID2);
			newID = sc2.nextLine();
			File employ = lookUpEmployee(employees, newID);
			if (employ.exists()) {
				System.out.println("This ID already exists");
				return;
			}
			a[1] = newID;
		}
		if (a[2] != null) {
			System.out.println("Enter the new paymentType for this employee");
			newPType = sc2.nextLine();
			a[2] = newPType;
		}
		if (a[3] != null) {
			System.out.println("Enter the new salary for this employee");
			newSalary = sc2.nextLine();
			a[3] = newSalary;
		}

		// if newID, create new file
		if (newID.length() > 1) {
			File newEmpCur = new File(employees.getPath() + File.separator + newID + ".txt");

			PrintWriter writer1 = new PrintWriter(newEmpCur);
			if (newTName.compareTo("") != 0)
				writer1.println("Titlename: " + newTName);
			else
				writer1.println(info.get(0));
			if (newID.compareTo("") != 0)
				writer1.println("ID: " + newID);
			else
				writer1.println(info.get(1));
			if (newPType.compareTo("") != 0)
				writer1.println("PaymentType: " + newPType);
			else
				writer1.println(info.get(2));
			if (newSalary.compareTo("") != 0)
				writer1.println("Salary: " + newSalary);
			else
				writer1.println(info.get(3));
			writer1.flush();
			writer1.close();

			if (employee.delete()) {
				System.out.println("Edited Employer");
			} else {
				System.out.println("Edited Employeer, however the ID : " + id + " still exists");
				System.out.println("Have to close program to permantly delete");
				addToEmpTxt(employees, id);

			}
		} else {

			// update file
			PrintWriter writer = new PrintWriter(employee);
			for (int i = 0; i < info.size(); i++) {
				if (a[i] != null) {
					int j = info.get(i).indexOf(':');
					String word = info.get(i).substring(0, j + 1);
					writer.println(word + " " + a[i]);
				} else
					writer.println(info.get(i));
			}
			writer.flush();
			writer.close();
		}
	}

	public static void deleteEmployee(File employees) throws IOException {
		File employee = menuLookUp(employees);
		if (employee.length() == 0)
			return;
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you sure you want to fire the Employee? Re enter ID for final confirmation");
		String id = sc.nextLine();
		if (FileReader.getEmployeeInfo(employee, Employee.Property.id).compareTo("ID: " + id) != 0) {
			System.out.println("Error: The entered ID is different or doesn't exist");
			return;
		}
		if (employee.delete()) {
			employee.delete();
			System.out.println("The employee has been terminated");
		} else {
			System.out.println("Success! The employee file will be permantly deleted when the program closes");
			addToEmpTxt(employees, id);
			return;
		}
	}

	/**
	 * This method is here because when the file is open, you can't delete a
	 * file. So, whenever the program starts up, it will delete the files that
	 * were supposed to be deleted
	 * 
	 * @param employees
	 * @throws IOException
	 */
	public static void deleteEmployee2(File employees) throws IOException {
		File temp = new File(employees.getPath() + File.separator + "Emp. to delete.txt");
		Scanner sc = new Scanner(temp);
		ArrayList<String> info = new ArrayList<>();
		while (sc.hasNext()) {
			String t = String.valueOf(sc.next());
			if (!info.contains(t))
				info.add(String.valueOf(t));
		}
		File[] temp3 = employees.listFiles();
		for (File f : temp3) {
			if (info.contains(f.getName())) {
				if (f.delete()) {
					info.remove(f.getName());
				} else {
				}
			}
		}

		if (info.size() == 0) {// Clears file if empty
			PrintWriter writer = new PrintWriter(temp);
			writer.print("");
			writer.flush();
			writer.close();
		}
	}

	@SuppressWarnings("resource")
	public static void addToEmpTxt(File employees, String id) throws IOException {

		File temp = new File(employees.getPath() + File.separator + "Emp. to delete.txt");
		FileWriter fw = new FileWriter(temp, true); // keeps previous
													// data and adds on
													// to it
		try {
			fw = new FileWriter(temp, true); // data
			fw.write(id + ".txt ");
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
			// close resources
		}
	}

	public static String randEmpID(File employees, String titleName) throws IOException {
		Random rand = new Random();
		String titleIni = "" + titleName.charAt(0);
		for (int i = 0; i < titleName.length(); i++) {
			if (titleName.charAt(i) == ' ') {
				titleIni += titleName.charAt(i + 1);
			}
		}
		String tempID = titleIni + rand.nextInt(10000);
		File f = lookUpEmployee(employees, tempID);
		while (f.exists()) {
			tempID = titleName.substring(0, 1) + rand.nextInt(10000);
			f = lookUpEmployee(employees, tempID);
		}
		return tempID;
	}
}
