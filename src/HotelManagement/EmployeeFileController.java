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
	public static void createEmployee(File employees, Hotel hotel) throws IOException {
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
			// writer.println("Titlename: " + titleName);
			// writer.println("ID: " + id);
			// writer.println("PaymentType: " + paymentType);
			// writer.println("Salary: " + salary);
			writer.println(titleName);
			writer.println(id);
			writer.println(paymentType);
			writer.println(salary);
			writer.println("");
			writer.flush();
			writer.close();
			hotel.addEmployee(new Employee(newEmployee));
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
<<<<<<< HEAD
	
	public static Boolean checkID(File hotel,String id) {
		File Employee = new File(hotel.getPath() + File.separator + "Employee");
		for (File file : Employee.listFiles()) {
			if(file.getName().contentEquals(id)) {
				return true;
			}
        }
		return false;
	}
	
	public static void ListAllEmployees(File hotel) {
		File Employee = new File(hotel.getPath() + File.separator + "Employee");
		for (File file : Employee.listFiles()) {
            System.out.println("-" + file.getName());
        }
=======

	public static void Payroll(File hotel) throws FileNotFoundException {
		ArrayList<String> ids = new ArrayList<>();
		ArrayList<String> idTime = new ArrayList<>();
		ArrayList<Integer> numberOfCI = new ArrayList<>();
		File employees = new File(hotel.getPath() + File.separator + "Employees");
		Scanner sc = new Scanner(hotel);
		File f = new File(hotel.getPath() + File.separator + "Scheduler");
		if (!f.exists()) {
			System.out.println("The Scheduler file doesn't exist");
		}
		File f2 = new File(f.getPath() + File.separator + "Clock in");
		if (!f2.exists()) {
			System.out.println("The Clock in file doesn't exist");
		}
		System.out.println("Note: Payroll is calculated every week");
		System.out.println("(1st-7th) (8th-15th) (16th-23rd) (24th-31st)");
		System.out.println("What payroll week do you want to access? Enter yyyy-mm-dd");
		String date = sc.next();
		File f3 = new File(f2.getPath() + File.separator + date);
		if (!f3.exists()) {
			System.out.println("This date isn't calculated yet");
		}
		int day = Integer.parseInt(date.substring(8, 10));
		String yymm = date.substring(0, 8);
		String yy = date.substring(0, 5);
		String mm = date.substring(5, 8);

		if (1 <= day && day <= 7) {
			calculateWeek(1, 7, yymm, f2, ids, idTime, numberOfCI);
			checkEmpCI(f2, yy, mm, "01", ids, idTime, numberOfCI, employees);
		} else if (8 <= day && day <= 15) {
			calculateWeek(8, 15, yymm, f2, ids, idTime, numberOfCI);

		} else if (16 <= day && day <= 23) {
			calculateWeek(16, 23, yymm, f2, ids, idTime, numberOfCI);

		} else if (24 <= day && day <= 31) {
			calculateWeek(24, 31, yymm, f2, ids, idTime, numberOfCI);

		}

	}

	// mm:mm- yyyy:yyyy-
	private static void checkEmpCI(File f2, String yy, String mm, String dd, ArrayList<String> ids,
			ArrayList<String> idTime, ArrayList<Integer> numberOfCI, File employees) throws FileNotFoundException {
		ArrayList<String> idsAreOdd = new ArrayList<>();
		for (int i = 0; i < numberOfCI.size(); i++) {
			if (numberOfCI.get(i) % 2 != 0) {
				idsAreOdd.add(ids.get(i));
			}
		}
		if (idsAreOdd.isEmpty()) {
			System.out.println("The Payroll date is calculated!");
			return;
		}

		if (Integer.parseInt(dd) == 01) {
			if (mm.compareTo("01-") == 0) {
				mm = Integer.toString(12) + '-'; // mm:01- to 12-
				yy = Integer.toString(Integer.parseInt(yy.substring(0, 5)) - 1) + '-'; // yy:1999-
																						// to
																						// 1998-
			} else
				mm = Integer.toString(Integer.parseInt(mm.substring(0, 3)) - 1) + '-'; // mm:11-10

			File f31 = new File(f2.getPath() + File.separator + yy + mm + dd + ".txt");
			File f30 = new File(f2.getPath() + File.separator + yy + mm + dd + ".txt");
			File f29 = new File(f2.getPath() + File.separator + yy + mm + dd + ".txt");
			File f28 = new File(f2.getPath() + File.separator + yy + mm + dd + ".txt");

			if (f31.exists()) {
				addLastCL(f31, idsAreOdd, ids, idTime, numberOfCI);
			} else if (f30.exists()) {
				addLastCL(f30, idsAreOdd, ids, idTime, numberOfCI);
			} else if (f29.exists()) {
				addLastCL(f29, idsAreOdd, ids, idTime, numberOfCI);
			} else if (f28.exists()) {
				addLastCL(f28, idsAreOdd, ids, idTime, numberOfCI);
			}
		}
		calculatePayroll(ids, idTime, numberOfCI, employees);
	}

	private static void calculatePayroll(ArrayList<String> ids, ArrayList<String> idTime, ArrayList<Integer> numberOfCI,
			File employees) throws FileNotFoundException {
		ArrayList<String> idToPrint = new ArrayList<>();
		for (String i : ids) {
			idToPrint.add(i);
		}
		String[] temp = employees.list();
		for (int i = 0; i < temp.length; i++) {
			String test = temp[i]; // TEST
			File f = new File(employees.getPath() + File.separator + temp[i]);
			if (!f.exists())
				System.out.println("File doesn't exist");
			if (ids.contains(temp[i])) {
				int ClockIn = Integer.parseInt(idTime.get(ids.indexOf(temp[i])));
				ids.remove(temp[i]);
				idTime.remove(temp[i]);
				numberOfCI.remove(temp[i]);
				int ClockOut = Integer.parseInt(idTime.get(ids.indexOf(temp[i])));
				ids.remove(temp[i]);
				idTime.remove(temp[i]);
				numberOfCI.remove(temp[i]);
				int hoursWrkd = Math.abs(ClockIn - ClockOut);
				while (ids.contains(temp[i])) {
					ClockIn = Integer.parseInt(idTime.get(ids.indexOf(temp[i])));
					ids.remove(temp[i]);
					idTime.remove(temp[i]);
					numberOfCI.remove(temp[i]);
					ClockOut = Integer.parseInt(idTime.get(ids.indexOf(temp[i])));
					ids.remove(temp[i]);
					idTime.remove(temp[i]);
					numberOfCI.remove(temp[i]);
					hoursWrkd += Math.abs(ClockIn - ClockOut);
				}
				Employee e = new Employee(f);
				if (e.getPayroll() == null)
					e.setPayroll(Integer.toString(hoursWrkd) + " (1st week)");
				if (e.getPayroll().contains("1st") && !e.getPaymentType().contains("weekly")) {
					e.setPayroll(Integer.toString(Integer.parseInt(e.getPayroll()) + hoursWrkd) + " (2nd week)");
				}
				if (e.getPayroll().contains("2nd") && !e.getPaymentType().contains("bi-weekly")) {
					e.setPayroll(Integer.toString(Integer.parseInt(e.getPayroll()) + hoursWrkd) + " (3rd week)");
				}
				if (e.getPayroll().contains("3rd")) {
					e.setPayroll(Integer.toString(Integer.parseInt(e.getPayroll()) + hoursWrkd) + " (4th week)");
				}

				if (e.getPayroll().contains("1st") && e.getPaymentType().contains("weekly")) {
					System.out.println("The employee with the ID gets paid this week");
					e.setPayroll(null);
				}
				if (e.getPayroll().contains("2nd") && e.getPaymentType().contains("bi-weekly")) {
					System.out.println("The employee with the ID gets paid this week");
					e.setPayroll(null);
				}
				if (e.getPayroll().contains("4th") && e.getPaymentType().contains("montly")) {
					System.out.println("The employee with the ID gets paid this week");
					e.setPayroll(null);
				}
				writeEmpToFile(f, e);
			}

		}
		System.out.println("Here's a list of all the ID's history during this week's payroll");
		for (String id : idToPrint) {
			File ftemp = new File(employees.getPath() + File.separator + id + ".txt");
			Printer.printFile(ftemp);
		}
	}

	private static void addLastCL(File fDate, ArrayList<String> idsAreOdd, ArrayList<String> ids,
			ArrayList<String> idTime, ArrayList<Integer> numberOfCI) throws FileNotFoundException {
		Scanner sc2 = new Scanner(fDate);
		while (sc2.next() != "end") {
			String id = sc2.next();
			String time = sc2.next();
			if (idsAreOdd.contains(id)) {
				ids.add(id);
				idTime.add(time);
				numberOfCI.add(numberOfCI.get(ids.lastIndexOf(id)) + 1);
				idsAreOdd.remove(id);
			}
		}
		if (!idsAreOdd.isEmpty()) {
			for (String i : idsAreOdd) {
				System.out.println("This employer with the ID: " + i + " is missing a clock in or clock out");
				System.out.println("Therefore, they are NOT getting paid");
				while (ids.contains(i)) {
					ids.remove(ids.indexOf(i));
					idTime.remove(ids.indexOf(i));
					numberOfCI.add(ids.indexOf(i));

				}
			}
		}
	}

	private static void calculateWeek(int startDate, int endDate, String yymm, File f2, ArrayList<String> ids,
			ArrayList<String> idTime, ArrayList<Integer> numberOfCI) throws FileNotFoundException {

		while (startDate != endDate) {
			calculateDay(startDate++, yymm, f2, ids, idTime, numberOfCI);
		}
	}

	private static void calculateDay(int date, String yymm, File f2, ArrayList<String> ids, ArrayList<String> idTime,
			ArrayList<Integer> numberOfCI) throws FileNotFoundException {

		File f3 = new File(f2.getPath() + File.separator + yymm + date + ".txt");
		if (!f3.exists()) {
			System.out.println("This date isn't calculated yet");
			return;
		}
		Scanner sc = new Scanner(f3);
		while (sc.next() != "end") {
			String id = sc.next();
			String time = sc.next();
			if (ids.contains(id)) {
				ids.add(id);
				idTime.add(time);
				numberOfCI.add(numberOfCI.get(ids.lastIndexOf(id)) + 1);
			}
			ids.add(id);
			idTime.add(time);
			numberOfCI.add(1);
		}

	}

	private static void writeEmpToFile(File employee, Employee e) throws FileNotFoundException {

		PrintWriter writer = new PrintWriter(employee);
		writer.println("Titlename: " + e.getTitleName());
		writer.println("Amount: " + e.getID());
		writer.println("Brand: " + e.getPaymentType());
		writer.println("Brand: " + e.getSalary());
		writer.println("Payroll: " + e.getPayroll());
		writer.flush();
		writer.close();
>>>>>>> 8ec58c884572101ba261974bc69c7e7407b843ad
	}
}
