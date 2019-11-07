package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Manager extends Employee {

	// Only Manager can access this
	private ArrayList<Employee> employees;
	private String hotel;
	private boolean clockIn = false;

	public static enum ManagerProperty {
		titleName, id, paymentType, salary
	}

	/**
	 * Creates a manager whenever a hotel is built
	 * @param path
	 * @param titleName
	 * @param id
	 * @param paymentType
	 * @param salary
	 */
	public Manager(String path, String titleName, String id, String paymentType, int salary) {

		super(path, titleName, id, paymentType, salary);
		clockIn = false;
	}

	public Manager(File employeeFile) throws FileNotFoundException {
		super(employeeFile);
	}


	public Employee getEmployee(String id) {

		for (int i = 0; i < employees.size(); i++) {
			if (employees.get(i).getID().compareTo(id) == 0) {
				return employees.get(i);
			}
		}
		return null;
	}

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

	// public void addManyEmployees(Employee[] employees) {
	//
	// for (int i = 0; i < employees.length; i++) {
	// this.createEmployee(employees[i]);
	// }
	// }

	// Selection Sort (employees alphabet style)

	// path given is ../Employee
	public void addEmployeeToFile(String employeePath, Employee emp) throws IOException {

		String path = employeePath + File.separator + emp.getID() + ".txt";
		File cur = new File(path);
		if (cur.createNewFile()) {
			PrintWriter writer = new PrintWriter(cur);
			writer.println(emp.getTitleName());
			writer.println(emp.getID());
			writer.println(emp.getPaymentType());
			writer.println(String.valueOf(emp.getSalary()));
			writer.flush();
			writer.close();
			System.out.println("The employee with the ID " + emp.getID() + " has been added");
			// titleName, id, paymentType, wage
		} else {
			System.out.println("The employee with the ID " + emp.getID() + " already exists");
		}
	}

	// path given is ../Employee
	public void deleteEmployeeFromFile(String employeePath, String empID) throws IOException {

		File cur = new File(employeePath); // Employee path
		File empCur = new File(employeePath + File.separator + empID + ".txt"); // current
																				// emp
		File[] temp = cur.listFiles();
		for (File f : temp) {

			if (f.equals(empCur)) {
				empCur.delete();
				System.out.println("The employee with the ID " + empID + " has been terminated");
				break;
			}
		}
	}

	public Employee findEmployeeFromFile(String employeePath, String id) throws IOException {

		File cur = new File(employeePath); // Employee path
		File empCur = new File(employeePath + File.separator + id + ".txt"); // current
																				// emp
		File[] temp = cur.listFiles();
		for (File f : temp) {
			if (f.equals(empCur)) {

				String tempTN = FileReader.getEmployeeInfo(f, Property.titleName);
				String tempID = FileReader.getEmployeeInfo(f, Property.id);
				String tempPT = FileReader.getEmployeeInfo(f, Property.paymentType);
				String tempS = FileReader.getEmployeeInfo(f, Property.salary);
				return new Employee(employeePath, tempTN, tempID, tempPT, Integer.parseInt(tempS));

			} else {
				System.out.println("The employee with the ID " + id + " does not work here");
			}
		}
		return null;
	}

	public void editEmployeeFromFile(Employee emp, String titleName2, String id2, String paymentType2, int salary2)
			throws IOException {

		Employee temp2 = findEmployeeFromFile(emp.getPath(), emp.getID());

		temp2.setTitleName(titleName2);
		temp2.setID(id2);
		temp2.setPaymentType(paymentType2);
		temp2.setSalary(salary2);

		File empCur = new File(emp.getPath()); // current emp and then override

		PrintWriter writer1 = new PrintWriter(empCur);
		writer1.println(temp2.getTitleName());
		writer1.println(temp2.getID());
		writer1.println(temp2.getPaymentType());
		writer1.println(String.valueOf(temp2.getSalary()));
		writer1.flush();
		writer1.close();
	}
}
