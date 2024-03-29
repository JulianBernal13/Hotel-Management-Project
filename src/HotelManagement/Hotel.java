package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Yingxie Gao, Anji Yu
 * @date 10/18/19 22:30
 */

public class Hotel {

	// needs to be matched with the initialization order in constructor
	public static enum Property {
		name, style, location, numOfLevel, levelRmNum, password;
	}

	private final double VIPDiscount = 0.8;
	private String name;
	private String style;
	private Location location;
	private int numOfLevel;
	private int levelRmNum;
	private String password;
	private String path;
	private Room rooms[][];
	private ArrayList<Customer> customers = new ArrayList<>();
	private Manager manager; //
	private ArrayList<Employee> employees = new ArrayList<>();
	private HashMap<Customer, Contract> reservationContracts = new HashMap<>();
	private HashMap<Customer, Contract> inContracts = new HashMap<>();
	private ArrayList<Contract> outContracts = new ArrayList<>();
	private HashMap<String, Integer> price = new HashMap<>();

	public Hotel(File hotelFile) throws IOException {
		this.path = hotelFile.getPath();
		File roomFolder = cdRoomFolder();
		File customerFolder = CustomerFileController.cdCustomerFolder(hotelFile);
		File employeeFolder = EmployeeFileController.cdEmployeeFile(hotelFile);
		File inventoryFolder = InventoryFileController.cdInventoryFile(hotelFile);

		HotelFileController.deleteFilesPermantly(hotelFile); // new code

		ArrayList<String> info = FileController.extractInfo(new File(path + File.separator + "info.txt"));
		this.name = info.get(Property.name.ordinal());
		this.style = info.get(Property.style.ordinal());
		this.password = info.get(Property.password.ordinal());
		this.numOfLevel = Integer.parseInt(info.get(Property.numOfLevel.ordinal()));
		this.levelRmNum = Integer.parseInt(info.get(Property.levelRmNum.ordinal()));
		this.location = new Location(info.get(Property.location.ordinal()));
		rooms = new Room[numOfLevel][levelRmNum];
		for (int i = 0; i < numOfLevel; ++i) {
			for (int j = 0; j < levelRmNum; ++j) {
				rooms[i][j] = new Room(new File(
						roomFolder.getPath() + File.separator + FileController.convertToTxt((i + 1) * 100 + j)));
			}
		}
		for (File f : customerFolder.listFiles())
			customers.add(new Customer(f));
		for (File f : employeeFolder.listFiles()) {
			if (!f.getName().equals("Emp. to delete.txt")) {
				employees.add(new Employee(f));
				if (employees.get(employees.size() - 1).getTitleName().equals("Manager"))
					this.manager = new Manager(f);
			}
		}

		File reservationFoler = new File(path + File.separator + "Contracts" + File.separator + "Reservation");

		for (File f : reservationFoler.listFiles()) {
			Contract c = ContractFileController.readContract(this, f);
			reservationContracts.put(c.getCustomer(), c);
		}
		File inFoler = new File(path + File.separator + "Contracts" + File.separator + "In");
		for (File f : inFoler.listFiles()) {
			Contract c = ContractFileController.readContract(this, f);
			inContracts.put(c.getCustomer(), c);
		}
		File outFoler = new File(path + File.separator + "Contracts" + File.separator + "Out");
		for (File f : outFoler.listFiles()) {
			Contract c = ContractFileController.readContract(this, f);
			outContracts.add(c);
		}
		for (File f : customerFolder.listFiles())
			customers.add(new Customer(f));

		Scanner sc = new Scanner(new File(path + File.separator + "priceInfo.txt"));
		for (Price p : Price.values())
			this.price.put(p.name(), Integer.parseInt(sc.nextLine()));
	}

	public String getPath() {
		return path;
	}

	public int getNumOfLevel() {
		return numOfLevel;
	}

	public int getLevelRmNum() {
		return levelRmNum;
	}

	public void writeHotelInfo() throws FileNotFoundException {
		File info = new File(path + File.separator + "info.txt");
		PrintWriter writer = new PrintWriter(info);
		writer.println(name);
		writer.println(style);
		writer.println(location);
		writer.println(numOfLevel);
		writer.println(levelRmNum);
		writer.println(password);
		writer.flush();
		writer.close();
	}

	public void writePriceInfo() throws FileNotFoundException {
		File info = new File(path + File.separator + "priceInfo.txt");
		PrintWriter writer = new PrintWriter(info);
		for (Price p : Price.values()) {
			writer.println(price.get(p.name()));
		}
		writer.flush();
		writer.close();
	}

	public Customer getCustomer(String name) throws FileNotFoundException {
		for (Customer customer : this.customers) {
			if (name.equals(customer.toString())) {
				// System.out.println("find customer");
				return customer;
			}
		}
		return null;
	}

	public void addCustomer(Customer c) {
		this.customers.add(c);
	}

	public void addEmployee(Employee e) {
		this.employees.add(e);
	}

	public void addCustomer(String name) throws IOException {
		int i = name.indexOf(' ');
		String firstName = name.substring(0, i), lastName = name.substring(i + 1);
		addCustomer(new Customer(firstName, lastName, CustomerFileController.cdCustomerFolder(path)));
	}

	public String getName() {
		return name;
	}

	public String getStyle() {
		return style;
	}

	public Location getLocation() {
		return location;
	}

	public String getPassword() {
		return password;
	}

	public File getFile() {
		return new File(this.getPath());
	}

	public File cdRoomFolder() {
		return new File(this.getPath() + File.separator + "Rooms");
	}

	public File cdInventoryFolder() {
		return new File(this.getPath() + File.separator + "Inventory");
	}

	public File cdEmployeeFolder() {
		return new File(this.getPath() + File.separator + "Employee");
	}

	public File cdCustomerFolder() {
		return new File(this.getPath() + File.separator + "Customer");
	}

	public File cdContractFolder() {
		return new File(this.getPath() + File.separator + "Contracts");
	}

	public <T> Room getRoom(T room) {
		int roomNumber = Integer.parseInt(String.valueOf(room));
		int level = roomNumber / 100;
		int num = roomNumber % 100;
		return rooms[level - 1][num];
	}

	public Room getRoom(int i, int j) {
		return rooms[i][j];
	}

	public Room[][] getRooms() { // new line of code
		return rooms;
	}

	public Employee getEmployee(String id) {
		for (Employee e : employees) {
			if (e.getID().equals(id))
				return e;
		}
		return null;
	}

	public Manager getManager(String id) {
		if (this.manager.getID().equals(id))
			return this.manager;
		return null;
	}

	public Manager getManager() {
		return this.manager;
	}

	public HashMap<Customer, Contract> getReservationContracts() {
		return reservationContracts;
	}

	public HashMap<Customer, Contract> getInContracts() {
		return inContracts;
	}

	public ArrayList<Contract> getOutContracts() {
		return outContracts;
	}

	public int showTypeRoom(String type) {
		int counter = 0;
		for (int i = 0; i < numOfLevel; ++i) {
			for (int j = 0; j < levelRmNum; ++j) {
				Room cur = rooms[i][j];
				if (cur.isClean() && cur.isEmpty() && cur.getType().equals(type)) {
					System.out.print(cur.getNumber() + "   ");
					++counter;
				}
			}
			System.out.println("");
		}
		return counter;
	}

	public void addReservationContract(Contract c) {
		reservationContracts.put(c.getCustomer(), c);
	}

	public void addInContract(Contract c) {
		inContracts.put(c.getCustomer(), c);
	}

	public void addOutContract(Contract c) {
		outContracts.add(c);
	}

	public double getVIPDiscount() {
		return VIPDiscount;
	}
	// private Employee getEmployee(String name) {
	// return EmployeeFileController.getEmployee(name);
	// }

}
