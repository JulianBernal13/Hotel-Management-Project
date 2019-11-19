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
			writer.println("test");
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

	static Boolean checkID(File hotel, String id) {
		File Employee = new File(hotel.getPath() + File.separator + "Employee");
		for (File file : Employee.listFiles()) {
			String file2 = file.getName(); //
			if (file.getName().contentEquals(id)) {
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
	}

	public static void payroll(File hotel) throws FileNotFoundException {
		ArrayList<String> leaveMethod = new ArrayList<>(); // Only thing that
															// works
		ArrayList<String> ids = new ArrayList<>();
		ArrayList<String> idTime = new ArrayList<>();
		ArrayList<Integer> numberOfCI = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		File employees = new File(hotel.getPath() + File.separator + "Employee");
		File f = new File(hotel.getPath() + File.separator + "Schedules");
		if (!f.exists()) {
			System.out.println("The Schedules file doesn't exist");
			return;
		}
		File f2 = new File(f.getPath() + File.separator + "Clock in");
		if (!f2.exists()) {
			System.out.println("The Clock in file doesn't exist");
			return;
		}
		System.out.println("Note: Payroll is calculated every week");
		System.out.println("(1st-7th) (8th-15th) (16th-23rd) (24th-31st)");
		System.out.println("What payroll week do you want to access? Enter yyyy-mm-dd");
		String date = sc.next();
		File f3 = new File(f2.getPath() + File.separator + date + ".txt");
		if (!f3.exists()) {
			System.out.println("This date isn't calculated yet");
			return;
		}
		String day = date.substring(8, 10);
		String yymm = date.substring(0, 8);
		String yy = date.substring(0, 5);
		String mm = date.substring(5, 8);

		if (1 <= Integer.parseInt(day) && Integer.parseInt(day) <= 7) {
			calculateWeek("01", "07", yymm, f2, ids, idTime, numberOfCI, leaveMethod);
			checkEmpCI(f2, yy, mm, "01", ids, idTime, numberOfCI, employees, leaveMethod);
		} else if (8 <= Integer.parseInt(day) && Integer.parseInt(day) <= 15) {
			calculateWeek("08", "15", yymm, f2, ids, idTime, numberOfCI, leaveMethod);
			checkEmpCI(f2, yy, mm, "08", ids, idTime, numberOfCI, employees, leaveMethod);

		} else if (16 <= Integer.parseInt(day) && Integer.parseInt(day) <= 23) {
			calculateWeek("16", "23", yymm, f2, ids, idTime, numberOfCI, leaveMethod);
			checkEmpCI(f2, yy, mm, "16", ids, idTime, numberOfCI, employees, leaveMethod);

		} else if (24 <= Integer.parseInt(day) && Integer.parseInt(day) <= 31) {
			calculateWeek("24", "31", yymm, f2, ids, idTime, numberOfCI, leaveMethod);
			checkEmpCI(f2, yy, mm, "24", ids, idTime, numberOfCI, employees, leaveMethod);
		}
	}

	private static void calculateWeek(String startDate, String endDate, String yymm, File f2, ArrayList<String> ids,
			ArrayList<String> idTime, ArrayList<Integer> numberOfCI, ArrayList<String> leaveMethod)
			throws FileNotFoundException {
		while (Integer.parseInt(startDate) != Integer.parseInt(endDate) + 1) {
			calculateDay(startDate, yymm, f2, ids, idTime, numberOfCI, leaveMethod);
			if (leaveMethod.contains("This week isn't calculated yet"))
				return;
			int a = Integer.parseInt(startDate) + 1;
			if (a < 10)
				startDate = "0" + Integer.toString(a);
			else
				startDate = Integer.toString(a);
		}
	}

	private static void calculateDay(String date, String yymm, File f2, ArrayList<String> ids, ArrayList<String> idTime,
			ArrayList<Integer> numberOfCI, ArrayList<String> leaveMethod) throws FileNotFoundException {

		File f3 = new File(f2.getPath() + File.separator + yymm + date + ".txt");
		if (!f3.exists()) {
			System.out.println("This week isn't calculated yet");
			leaveMethod.add("This week isn't calculated yet");
			return;
		}
		Scanner sc = new Scanner(f3);
		while (sc.hasNext()) {
			String id = sc.next();
			if (id.compareTo("end") == 0)
				break;
			String time = sc.next();
			if (ids.contains(id)) {
				int index = ids.lastIndexOf(id);
				ids.add(id);
				idTime.add(time);
				int a = numberOfCI.get(index) + 1;
				numberOfCI.add(a);
			} else {
				ids.add(id);
				idTime.add(time);
				numberOfCI.add(1);
			}
		}
	}

	// mm:mm- yyyy:yyyy-
	private static void checkEmpCI(File f2, String yy, String mm, String dd, ArrayList<String> ids,
			ArrayList<String> idTime, ArrayList<Integer> numberOfCI, File employees, ArrayList<String> leaveMethod)
			throws FileNotFoundException {
		if (leaveMethod.contains("This week isn't calculated yet"))
			return;
		ArrayList<String> idsAreOdd = new ArrayList<>();
		ArrayList<String> idsAreNeeded = new ArrayList<>();
		for (int i = numberOfCI.size() - 1; i >= 0; i--) {
			if (!idsAreNeeded.contains(ids.get(i))) {
				if (!idsAreOdd.contains(ids.get(i))) {
					idsAreNeeded.add(ids.get(i));
					if (numberOfCI.get(i) % 2 != 0) {
						idsAreOdd.add(ids.get(i));
					}
				}
			} else
				idsAreNeeded.add(ids.get(i));
		}

		if (Integer.parseInt(dd) == 01 && idsAreOdd.size() != 0) {
			if (mm.compareTo("01-") == 0) {
				mm = Integer.toString(12) + '-'; // mm:01- to 12-
				yy = Integer.toString(Integer.parseInt(yy.substring(0, 4)) - 1) + '-'; // yy:1999-
																						// to
																						// 1998-
			} else
				mm = Integer.toString(Integer.parseInt(mm.substring(0, 2)) - 1) + '-'; // mm:11-10

			File f31 = new File(f2.getPath() + File.separator + yy + mm + "31" + ".txt");
			File f30 = new File(f2.getPath() + File.separator + yy + mm + "30" + ".txt");
			File f29 = new File(f2.getPath() + File.separator + yy + mm + "29" + ".txt");
			File f28 = new File(f2.getPath() + File.separator + yy + mm + "28" + ".txt");

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
		if ((Integer.parseInt(dd) == 8) && idsAreOdd.size() != 0) {
			dd = "0" + Integer.toString(Integer.parseInt(dd) - 1) + '-';
			File ff = new File(f2.getPath() + File.separator + yy + mm + dd + ".txt");
			if (ff.exists())
				addLastCL(ff, idsAreOdd, ids, idTime, numberOfCI);
		}
		if (idsAreOdd.size() != 0) {
			dd = Integer.toString(Integer.parseInt(dd) - 1) + '-';
			File ff = new File(f2.getPath() + File.separator + yy + mm + dd + ".txt");
			if (ff.exists())
				addLastCL(ff, idsAreOdd, ids, idTime, numberOfCI);
		}
		calculatePayroll(ids, idTime, numberOfCI, employees);
	}

	private static void calculatePayroll(ArrayList<String> ids, ArrayList<String> idTime, ArrayList<Integer> numberOfCI,
			File employees) throws FileNotFoundException {
		ArrayList<String> idToPrint = new ArrayList<>();
		for (String i : ids) {
			if (!idToPrint.contains(i))
				idToPrint.add(i);
		}
		File[] ftemp = employees.listFiles();
		for (File f : ftemp) {
			f = new File(employees.getPath() + File.separator + f.getName());
			String fID = f.getName().substring(0, f.getName().length() - 4);
			if (!f.exists()) {
				System.out.println("File doesn't exist");
				return;
			}
			if (ids.contains(fID)) {
				int ClockIn = Integer.parseInt(idTime.get(ids.indexOf(fID)));
				idTime.remove(ids.indexOf(fID));
				numberOfCI.remove(ids.indexOf(fID));
				ids.remove(fID);
				int ClockOut = Integer.parseInt(idTime.get(ids.indexOf(fID)));
				idTime.remove(ids.indexOf(fID));
				numberOfCI.remove(ids.indexOf(fID));
				ids.remove(fID);
				int hoursWrkd = 0;
				if (ClockOut > ClockIn)
					hoursWrkd = Math.abs(ClockIn - ClockOut) / 100;
				else
					hoursWrkd = Math.abs((2400 + ClockOut) - ClockIn) / 100;
				while (ids.contains(fID)) {
					ClockIn = Integer.parseInt(idTime.get(ids.indexOf(fID)));
					idTime.remove(ids.indexOf(fID));
					numberOfCI.remove(ids.indexOf(fID));
					ids.remove(fID);
					ClockOut = Integer.parseInt(idTime.get(ids.indexOf(fID)));
					idTime.remove(ids.indexOf(fID));
					numberOfCI.remove(ids.indexOf(fID));
					ids.remove(fID);
					if (ClockOut > ClockIn)
						hoursWrkd += Math.abs(ClockIn - ClockOut) / 100;
					else
						hoursWrkd += Math.abs((2400 + ClockOut) - ClockIn) / 100;
				}
				Employee e = new Employee(f);
				if (!e.getPayroll().contains("1st") && !e.getPayroll().contains("2nd")
						&& !e.getPayroll().contains("3rd")) {
					e.setPayroll(Integer.toString(hoursWrkd) + " (1st week)");
				} else if (e.getPayroll().contains("1st") && e.getPaymentType().contains("bi-weekly")) {
					int tr = e.getAmount();
					e.setPayroll(Integer.toString(e.getAmount() + hoursWrkd) + " (2nd week)");
				} else if (e.getPayroll().contains("2nd") && e.getPaymentType().contains("monthly")) {
					e.setPayroll(Integer.toString(e.getAmount() + hoursWrkd) + " (3rd week)");
				} else if (e.getPayroll().contains("3rd")) {
					e.setPayroll(Integer.toString(e.getAmount() + hoursWrkd) + " (4th week)");
				}

				if (e.getPayroll().contains("1st") && e.getPaymentType().contains("weekly")
						&& !e.getPaymentType().contains("bi-weekly")) {
					System.out.println("The employee with the " + e.getID() + " gets paid this week");
					addMoney(e);
					e.setPayroll("Payroll: ");
				}
				if (e.getPayroll().contains("2nd") && e.getPaymentType().contains("bi-weekly")) {
					System.out.println("The employee with the " + e.getID() + " gets paid this week");
					addMoney(e);
					e.setPayroll("Payroll: ");
				}
				if (e.getPayroll().contains("4th") && e.getPaymentType().contains("montly")) {
					System.out.println("The employee with the " + e.getID() + " gets paid this week");
					addMoney(e);
					e.setPayroll("Payroll: ");
				}
				if (!(e.getPayroll().length() < 11))
					addMoney(e);
				writeEmpToFile(f, e);
			}

		}
		System.out.println("Here's a list of all the ID's history during this week's payroll: \n");
		for (String id : idToPrint) {
			File ftemp2 = new File(employees.getPath() + File.separator + id + ".txt");
			Printer.printFile(ftemp2);
			System.out.println("");
		}
	}

	private static void addLastCL(File fDate, ArrayList<String> idsAreOdd, ArrayList<String> ids,
			ArrayList<String> idTime, ArrayList<Integer> numberOfCI) throws FileNotFoundException {

		Scanner sc = new Scanner(fDate);
		while (sc.hasNext()) {
			String id = sc.next();
			if (id.compareTo("end") == 0)
				break;
			String time = sc.next();
			if (idsAreOdd.contains(id)) {
				int index = ids.lastIndexOf(id);
				ids.add(id);
				idTime.add(time);
				int a = numberOfCI.get(index) + 1;
				numberOfCI.add(a);
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

	private static void addMoney(Employee e) throws FileNotFoundException {

		Scanner sc = new Scanner(System.in);
		System.out.println("Temporary: How much is " + e.getID() + " getting paid? (per hour)");
		String cash = sc.nextLine();
		double r = e.getAmount() * Double.parseDouble(cash);
		System.out
				.println("The employee " + e.getID() + " is getting paid " + r + " so far (payment type matters!) \n");
	}

	private static void writeEmpToFile(File employee, Employee e) throws FileNotFoundException {

		PrintWriter writer = new PrintWriter(employee);
		writer.println(e.getTitleName());
		writer.println(e.getID());
		writer.println(e.getPaymentType());
		writer.println(e.getSalary());
		writer.println(e.getPayroll());
		writer.flush();
		writer.close();
	}
	
	public static int Getwage(File hotel, int id) throws FileNotFoundException {
		File Employee = new File(hotel.getPath() + File.separator + "Employee" + File.separator + id + ".txt");
        ArrayList<String> oldInfo = FileController.extractInfo(Employee);
		return Integer.parseInt(oldInfo.get(3));
	}
}
