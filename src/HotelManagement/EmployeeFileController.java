package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeFileController implements FileController {
	public static void menuLookUp(File employees) throws IOException {
		System.out.println("Enter Employee name");
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();
		getEmployeeInfo(employees, name, Employee.EmployeeProperty.titleName);
		getEmployeeInfo(employees, name, Employee.EmployeeProperty.id);

	}

	public static void displayAll(File employees) {

	}

	public static File lookUpEmployee(File employees, String id) {
		return new File(employees.getPath() + File.separator + FileController.convertToTxt(id));
	}

	public static File registerEmployee(File employees, String name) throws IOException {
		if (!lookUpEmployee(employees, name).exists())
			return createEmployee(employees, name);
		System.out.println("Error, the employee has already existed");
		return null;
	}

	public static File createEmployee(File employees, String name) throws IOException {
		int i = name.indexOf(' ');
		String firstName = name.substring(0, i), lastName = name.substring(i + 1);
		String filePath = employees.getPath() + File.separator + FileController.convertToTxt(name);
		File newEmployee = new File(filePath);
		if (newEmployee.createNewFile()) {
			PrintWriter writer = new PrintWriter(filePath);
			writer.println(firstName);
			writer.println(lastName);
			writer.println(false);
			writer.println(false);
			writer.flush();
			writer.close();
		}
		return newEmployee;
	}

	public static int getPropertyOrdinal(String property) {
		int i = 0;
		for (Employee.EmployeeProperty p : Employee.EmployeeProperty.values()) {
			if (p.name() == property)
				return i;
			i++;
		}
		return -1;
	}

	public static void getEmployeeInfo(File employees, String name, Employee.EmployeeProperty property)
			throws FileNotFoundException {
		File employee = lookUpEmployee(employees, name);
		if (!employee.exists()) {
			System.out.println("Employee does not exists");
			return;
		}
		Scanner sc = new Scanner(employee);
		int i = 0;
		while (i++ < property.ordinal() && sc.hasNext())
			sc.nextLine();
		if (!sc.hasNext())
			System.out.println("Error, no such data exists");
		String ret = name + "   " + property.name() + "    " + sc.nextLine();
		System.out.println(ret);
	}

	public static void getEmployeeInfo(File employees, String name, String property) throws FileNotFoundException {
		File employee = lookUpEmployee(employees, name);
		if (!employee.exists()) {
			System.out.println("Employee does not exists");
			return;
		}
		Scanner sc = new Scanner(employee);
		for (Employee.EmployeeProperty p : Employee.EmployeeProperty.values()) {
			if (p.toString() == property)
				break;
			sc.nextLine();
		}
		if (!sc.hasNext()) {
			System.out.println("Error, no such data exists");
			return;
		}
		String ret = name + "   " + property + "   " + sc.nextLine();
		System.out.println(ret);
	}

	// helps to look through hotel
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

	public static void modifyEmployee(File employees, String name, String property) throws FileNotFoundException {
		ArrayList<String> info = extractEmpInfo(employees, name);
		System.out.println("Enter what you like to change to " + name + ": " + property);
		Scanner sc = new Scanner(System.in);
		int i = getPropertyOrdinal(property);
		if (i == -1)
			return;
		// info.get(i) = sc.nextLine();
	}

	public static void deleteEmployee(File employees, String name) throws FileNotFoundException {
		File employee = lookUpEmployee(employees, name);
		if (!employee.exists()) {
			System.out.println("This Employee does not exists");
			return;
		}
		employee.delete();
		System.out.println("The employee with the name: " + name + "has been terminated");

	}
}
