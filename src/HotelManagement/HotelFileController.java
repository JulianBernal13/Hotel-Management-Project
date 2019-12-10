package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class HotelFileController implements FileController {
	public static void createHotel(File file) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your new hotel's name?");
		String name = sc.nextLine();
		File hotelFile = new File(file.getPath() + File.separator + name);
		while (!hotelFile.mkdir()) {
			System.out.println("This name is taken, please enter another name");
			name = sc.nextLine();
			hotelFile = new File(file.getPath() + File.separator + name);
		}
		System.out.println("What kind of hotel style do you want create?(European/American/Chinese)");
		String ePattern = ".*ur.*";
		String aPattern = ".*meri.*";
		String cPattern = ".*in.*";
		String rawStyle = sc.nextLine();
		String style = "";
		if (Pattern.matches(ePattern, rawStyle))
			style = "European";
		if (Pattern.matches(aPattern, rawStyle))
			style = "Ameican";
		if (Pattern.matches(cPattern, rawStyle))
			style = "Chinese";

		System.out.println("What is your hotel's location? Enter an Address.");
		String address = sc.nextLine();
		System.out.println("How many floors does your hotel have?");
		int floor = Integer.parseInt(sc.nextLine());
		System.out.println("How many rooms does your hotel have for each floor?");
		int numRoom = Integer.parseInt(sc.nextLine());
		System.out.println("Please set a password for this hotel.");
		String password = sc.nextLine();
		writeHotelToFile(hotelFile, name, style, new Location(address), floor, numRoom, password);
	}

	public static void writeHotelToFile(File hotelFile, String name, String style, Location location, int numOfLevel,
			int levelRmNum, String password) throws IOException {
		File info = FileController.createTxtFile(hotelFile, "info");
		PrintWriter writer = new PrintWriter(info);
		writer.println(name);
		writer.println(style);
		writer.println(location);
		writer.println(numOfLevel);
		writer.println(levelRmNum);
		writer.println(password);
		writer.flush();
		writer.close();

		File priceInfo = FileController.createTxtFile(hotelFile, "priceInfo");
		writer = new PrintWriter(priceInfo);
		for (Price p : Price.values()) {
			writer.println(p.getPrice());
		}
		writer.flush();
		writer.close();

		FileController.createDirectory(hotelFile, "Customer");
		File employeeFolder = FileController.createDirectory(hotelFile, "Employee");
		FileController.createDirectory(hotelFile, "Contracts");
		FileController.createDirectory(hotelFile, "Inventory"); // new code

		File contractsFile = new File(hotelFile.getPath() + File.separator + "Contracts");
		FileController.createDirectory(contractsFile, "Reservation");
		FileController.createDirectory(contractsFile, "In");
		FileController.createDirectory(contractsFile, "Out");
		File roomsFolder = FileController.createDirectory(hotelFile, "Rooms");
		for (int i = 1; i <= numOfLevel; i++) {
			for (int j = 0; j < levelRmNum; j++) {
				String roomPath = roomsFolder.getPath() + File.separator + FileController.convertToTxt(i * 100 + j);
				new Room(i * 100 + j, roomPath).createRoomFile();
			}
		}

		Random rand = new Random();
		String employeePath = employeeFolder.getPath();
		Manager manager = new Manager(employeePath, "Manager", "M" + rand.nextInt(10000), "bi-weekly", 80000, "test");

		File cur2 = new File(employeePath + File.separator + "Emp. to delete" + ".txt");
		cur2.createNewFile();

		File cur = new File(employeePath + File.separator + manager.getID() + ".txt");
		if (cur.createNewFile()) {
			PrintWriter writer1 = new PrintWriter(cur);
			writer1.println(manager.getTitleName());
			writer1.println(manager.getID());
			writer1.println(manager.getPaymentType());
			writer1.println(String.valueOf(manager.getSalary()));
			writer1.println(manager.getPayroll());
			writer1.flush();
			writer1.close();
		}

	}

	public static void move(Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What would you like to move from this hotel?");
		System.out.println("Options are: Employees or Inventory");
		String option = sc.nextLine().toLowerCase();
		if (option.contains("employee") || option.contains("employees")) {
			moveEmployee(hotel);
		} else if (option.contains("inventory")) {
			moveInventory(hotel);
		} else {
			System.out.println("Invalid input was entered");
		}

	}

	public static void moveEmployee(Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> empProp = EmployeeFileController.getEmployeeInfoArr(hotel.cdEmployeeFolder());
		if (empProp == null)
			return;
		File employeeFile = new File(hotel.cdEmployeeFolder() + File.separator + empProp.get(1) + ".txt");
		Employee employee = new Employee(employeeFile);
		System.out.println("Are you sure you want to move this employee?");
		System.out.println("Re enter ID name to confirm");
		String cEmpID = sc.nextLine();
		if (!cEmpID.matches(employee.getID())) {
			System.out.println("Error, the employee ID is different");
			return;
		}
		System.out.println("Re enter your manager ID");
		String idMan = sc.nextLine();
		if (!idMan.matches(hotel.getManager().getID())) {
			System.out.println("Error, the wrong ID has been entered");
			return;
		}
		FileController.cleanFileContent(employeeFile);
		addToDeleteFilesPermantly(employeeFile, hotel.getFile());
		System.out.println("The employee is now being officially moved");
		moveHotel(hotel, employee);
	}

	public static void moveInventory(Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<Inventory> items = InventoryFileController.getInventoryItemInfoArr(hotel.cdInventoryFolder());
		if (items == null)
			return;
		System.out.println("Are you sure you want to move this item?");
		System.out.println("Re enter the inventory Item to confirm");
		String invItemName = sc.nextLine().toLowerCase();
		if (!invItemName.matches(items.get(0).getName().toLowerCase())) {
			System.out.println("Error, the inventory Item is different");
			return;
		}
		invItemName = items.get(0).getName();
		System.out.println("Re enter your manager ID");
		String idMan = sc.nextLine();
		if (!idMan.matches(hotel.getManager().getID())) {
			System.out.println("Error, the wrong ID has been entered");
			return;
		}
		System.out.println("Would you like to move the entire item or a certain brand?");
		String iOrb = sc.nextLine().toLowerCase();
		if (iOrb.contains("entire") || iOrb.contains("item")) {
			moveHotel2(hotel, items);
			File invItemFile = new File(
					hotel.getPath() + File.separator + "Inventory" + File.separator + invItemName + ".txt");
			FileController.cleanFileContent(invItemFile);
			addToDeleteFilesPermantly(invItemFile, hotel.getFile());
			return;
		} else if (iOrb.contains("certain") || iOrb.contains("brand")) {
			System.out.println("What specific brand name of this item do you want to move?");
			String brandTp = sc.nextLine().toLowerCase();
			Inventory tempV = null;
			for (Inventory inv : items) {
				if (inv.getBrand().toLowerCase().matches(brandTp)) {
					int a = items.indexOf(inv);
					items.remove(a);
					File invF = new File(
							hotel.getPath() + File.separator + "Inventory" + File.separator + inv.getName() + ".txt");
					InventoryFileController.writeInvToFile(invF, items);
					moveHotel(hotel, inv);
					return;
				}
			}
			moveHotel(hotel, tempV);
			return;
		}

	}

	private static void moveHotel(Hotel hotelss, Object obj) throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		if (obj == null) {
			System.out.println("This object does not exist");
			return;
		}
		System.out.println("What hotel do you want to move this " + obj + " too?");
		File managementSystem = new File("." + File.separator + "ManagementSystem");
		File[] hotels = managementSystem.listFiles();
		Printer.printBorder();
		for (File hotel : hotels) {
			System.out.println(hotel.getName());
		}
		Printer.printBorder();
		File movingHotel = null;
		boolean hName = false;
		while (hName == false || movingHotel == null) {
			String hotelName = sc.nextLine().toLowerCase();
			for (File hotel : hotels) {
				if (hotelName.matches(hotel.getName())) {
					hName = true;
					movingHotel = hotel;
					break;
				}
			}
			if (hName == false)
				System.out.println("This hotel name doesn't exist, try again");
			if (movingHotel != null && movingHotel.getName().matches(hotelss.getName())) {
				System.out.println("You can't move an item to the same hotel, try again");
				movingHotel = null;
				hName = false;
			}
		}
		if (obj instanceof Employee) {
			moveHotelEmployee(movingHotel, obj);

		} else if (obj instanceof Inventory) {
			moveHotelInventory(movingHotel, obj);
		}
		return;
	}

	private static void moveHotel2(Hotel hotelss, ArrayList<?> arr) throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("What hotel do you want to move this " + arr.get(0) + " too?");
		File managementSystem = new File("." + File.separator + "ManagementSystem");
		File[] hotels = managementSystem.listFiles();
		Printer.printBorder();
		for (File hotel : hotels) {
			System.out.println(hotel.getName());
		}
		Printer.printBorder();
		File movingHotel = null;
		boolean hName = false;
		while (hName == false || movingHotel == null) {
			String hotelName = sc.nextLine().toLowerCase();
			for (File hotel : hotels) {
				if (hotelName.matches(hotel.getName())) {
					hName = true;
					movingHotel = hotel;
					break;
				}
			}
			if (hName == false)
				System.out.println("This hotel name doesn't exist, try again");
			if (movingHotel != null && movingHotel.getName().matches(hotelss.getName())) {
				System.out.println("You can't move an item to the same hotel, try again");
				movingHotel = null;
				hName = false;
			}
		}
		if (arr.get(0) instanceof Inventory) {
			moveHotelInventory2(movingHotel, arr);
		}
		return;
	}

	private static void moveHotelInventory(File movingHotel, Object obj) throws FileNotFoundException, IOException {
		Inventory invItem = (Inventory) obj;
		File inventory = new File(movingHotel.getPath() + File.separator + "Inventory");
		File invItemFile = new File(inventory.getPath() + File.separator + invItem.getName() + ".txt");
		if (invItemFile.exists()) {
			ArrayList<Inventory> newInvItem = InventoryFileController.getInventoryItemInfoArr2(invItemFile);
			boolean brandExist = false;
			for (Inventory inv : newInvItem) {

				// add the new contents to the old contents in this hotel
				if (inv.getBrand().matches(invItem.getBrand())) {
					int a = Integer.parseInt(inv.getAmount().split(" ")[0])
							+ Integer.parseInt(invItem.getAmount().split(" ")[0]);
					inv.setAmount(a + " " + inv.getAmount().split(" ")[1]);
					newInvItem.set(newInvItem.indexOf(inv), inv);
					brandExist = true;
					System.out.println("The item: " + invItem.getName() + " with the brand " + invItem.getBrand()
							+ " has now been restocked to this hotel!");
					InventoryFileController.writeInvToFile(invItemFile, newInvItem);
				}
			}
			// add new contents
			if (brandExist == false) {
				newInvItem.add(new Inventory(invItem.getName(), invItem.getAmount(), invItem.getBrand()));
				System.out.println("The item: " + invItem.getName() + " has now been added to this hotel!");
				InventoryFileController.writeInvToFile(invItemFile, newInvItem);
			}

		} else if (invItemFile.createNewFile()) {
			PrintWriter writer = new PrintWriter(invItemFile);
			writer.println("Name: " + invItem.getName());
			writer.println("Amount: " + invItem.getAmount());
			writer.println("Brand: " + invItem.getBrand());
			writer.flush();
			writer.close();
			System.out.println("The item: " + invItem.getName() + " with the brand " + invItem.getBrand()
					+ " has now been added to this hotel!");
		}
	}

	private static void moveHotelInventory2(File movingHotel, ArrayList<?> arr) throws IOException {
		@SuppressWarnings("unchecked")
		ArrayList<Inventory> a = (ArrayList<Inventory>) arr;

		// check if inv item already exists
		File itemsFile = new File(movingHotel.getPath() + File.separator + "Inventory");
		File[] items = itemsFile.listFiles();
		for (File inv : items) {
			ArrayList<Inventory> itemContents = InventoryFileController.getInventoryItemInfoArr2(inv);

			if (a.get(0).getName().matches(itemContents.get(0).getName())) {
				System.out.print("There's already an item with the name " + arr.get(0) + " in the ");
				System.out.println(movingHotel.getName() + " hotel.");
				System.out.println("You have to use the 'certain brand' option for this hotel instead");
				return;
			}
		}
		// else create the inv item
		Inventory invItem = (Inventory) arr.get(0);
		File inventory = new File(movingHotel.getPath() + File.separator + "Inventory");
		File invItemFile = new File(inventory.getPath() + File.separator + invItem.getName() + ".txt");
		if (invItemFile.createNewFile())
			InventoryFileController.writeInvToFile(invItemFile, a);
		System.out.println("Success! We moved the entire item to the hotel named: " + movingHotel.getName());
	}

	private static void moveHotelEmployee(File movingHotel, Object obj) throws IOException {
		Scanner sc = new Scanner(System.in);
		Employee employee = (Employee) obj;
		File employees = new File(movingHotel.getPath() + File.separator + "Employee");
		File employeeFile = new File(employees.getPath() + File.separator + employee.getID() + ".txt");
		if (employeeFile.exists()) {
			System.out.println("There's already an employee with the ID " + employee.getID() + " in this hotel");
			System.out.println("You have to change the employee's ID you are moving, what will be his new ID?");
			String newID = EmployeeFileController.randEmpID(employees, employee.getTitleName());
			System.out.println("Enter the new ID for this employee, or use this randomly built one: " + newID);
			String newID2 = sc.nextLine();
			File employ = EmployeeFileController.lookUpEmployee(employees, newID2);
			boolean exists = false;
			while (employ.exists()) {
				System.out.println("This ID already exists, write another");
				newID2 = sc.nextLine();
				File employ2 = EmployeeFileController.lookUpEmployee(employees, newID2);
				if (!employ2.exists())
					break;
			}
			employee.setID(newID2);
			employeeFile = new File(employees.getPath() + File.separator + employee.getID() + ".txt");
			EmployeeFileController.writeEmpToFile(employeeFile, employee);
		} else
			EmployeeFileController.writeEmpToFile(employeeFile, employee);
		System.out.print("Success! The employee with the ID: " + employee.getID() + " has been moved");
		System.out.println(" to hotel " + movingHotel.getName());
	}

	// (2nd Use case)
	public static void deleteRoom(Hotel hotel) throws IOException {
		File hotelFile = new File(hotel.getPath());
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to delete a room or many rooms?");
		String avaRoomOrR = sc.nextLine().toLowerCase();
		if (avaRoomOrR.contains("room") && !avaRoomOrR.contains("rooms")) {
			RoomFileController.listRooms(hotelFile);
			return;
		} else if (avaRoomOrR.contains("rooms")) {
			manyRoomsDel(hotel);
			return;
		}
		System.out.println("User entered invalid input");
	}

	public static void deleteRoom2(Room curRoom, Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you sure you want to delete the room " + curRoom + "? (y/n)");
		String yOrn = sc.nextLine().toLowerCase();
		if (!yOrn.matches("y")) {
			System.out.println("Invalid input or user entered n");
			return;
		}
		System.out.println("Re enter your manager ID");
		String idMan = sc.nextLine();
		if (!idMan.matches(hotel.getManager().getID())) {
			System.out.println("Error, the wrong ID has been entered");
			return;
		}

		File temp = new File(curRoom.getPath());
		Room t = hotel.getRoom(curRoom);
		t.setEmpty(false);

		PrintWriter writer = new PrintWriter(temp);
		writer.println(t.getNumber());
		writer.println(t.getType());
		writer.println(String.valueOf(t.getPrice()));
		writer.println(String.valueOf(t.isEmpty()));
		writer.println(String.valueOf(t.isClean()));
		writer.println("Maintence:True");
		writer.println("Notes:True");
		writer.println("end");
		writer.flush();
		writer.close();

		// delete contracts and reservations with this room number (only 1 check
		// in or reservation)
		File ReservationFiles = new File(
				hotel.getPath() + File.separator + "Contracts" + File.separator + "Reservation");
		File[] fs = ReservationFiles.listFiles();
		for (File contractResFile : fs) {
			Scanner sc2 = new Scanner(contractResFile);
			sc2.nextLine();
			sc2.nextLine();
			sc2.nextLine();
			String roomNumber = sc2.nextLine();
			String temp2 = "" + curRoom.getNumber();
			if (roomNumber.contains(temp2)) {
				addToDeleteFilesPermantly(contractResFile, hotel.getFile());
			}
		}

		File ContractInFiles = new File(hotel.getPath() + File.separator + "Contracts" + File.separator + "In");
		File[] fs2 = ContractInFiles.listFiles();
		for (File contractInFile : fs2) {
			Scanner sc2 = new Scanner(contractInFile);
			sc2.nextLine();
			sc2.nextLine();
			sc2.nextLine();
			String roomNumber = sc2.nextLine();
			String temp2 = "" + curRoom.getNumber();
			if (roomNumber.contains(temp2)) {
				addToDeleteFilesPermantly(contractInFile, hotel.getFile());
			}
		}
	}

	private static void manyRoomsDel(Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> roomsToDelete = new ArrayList<String>();
		System.out.println("How many rooms would you like to delete? [Only enter a number]");
		int numRms = sc.nextInt();
		Room[][] roomArr = hotel.getRooms();
		System.out.println("\n" + "Here's the list of rooms in the hotel:");
		for (int i = 0; i < roomArr.length; ++i) {
			for (int j = 0; j < roomArr[0].length; ++j) {
				System.out.println(roomArr[i][j]);
			}
		}
		System.out.println("Enter the rooms you want to delete");
		System.out.println("Note: You can only delete rooms in the same floor");
		System.out.println("Also, they have to be in this format Ex. [103]");
		while (numRms > 0) {
			String numRm = sc.nextLine();
			if (numRm.matches("[0-9]+")) {
				if (!roomsToDelete.contains(numRm)) {
					roomsToDelete.add(numRm);
					numRms--;
				} else
					System.out.println("Invalid entry, try again");
			}
		}
		for (int i = 0; i < roomArr.length; ++i) {
			for (int j = 0; j < roomArr[0].length; ++j) {
				Room o = roomArr[i][j];
				if (roomsToDelete.contains(Integer.toString(o.getNumber()))) {
					deleteRoom2(roomArr[i][j], hotel);
					roomsToDelete.remove(Integer.toString(o.getNumber()));
				}
			}
		}
		System.out.println("Success! The rooms that were valid were erased from this hotel");
		if (roomsToDelete.size() != 0)
			roomsToDelete.clear();

		// writeHotelToFile(hotel.getFile(), hotel.getName(), hotel.getStyle(),
		// hotel.getLocation(), hotel.getNumOfLevel(),
		// hotel.getLevelRmNum(), hotel.getPassword());

		// writeHotelToFile(hotel.getFile(), hotel.getName(),
		// hotel.getLocation(), roomArr.length,
		// hotel.getLevelRmNum(), hotel.getPassword());
	}

	public static void addRoom(Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to add one floor of rooms, or many floors of rooms?");
		String roomOrRooms = sc.nextLine().toLowerCase();
		if (roomOrRooms.contains("floor") && !roomOrRooms.contains("floors")) {
			System.out.println("What is the floor you would like to add? [Ex. 4 floor]");
			String roomName = sc.nextLine();
			int roomf = Integer.parseInt(roomName.split(" ")[0]);
			ArrayList<String> info = FileController
					.extractInfo(new File(hotel.getPath() + File.separator + "info.txt"));
			String a = info.get(4);
			Room[] room = new Room[Integer.parseInt(a)];
			for (int i = 0; i < Integer.parseInt(a); i++) {
				if (i < 10) {
					room[i] = new Room(new File(hotel.getRoom(0, 0).getPath()));
					room[i].setNumber(Integer.parseInt(roomf + "0" + i));
				} else {
					room[i] = new Room(new File(hotel.getRoom(0, 0).getPath()));
					room[i].setNumber(Integer.parseInt(roomf + "" + i));
				}
			}
			addRoom2(room, hotel);
			return;
		} else if (roomOrRooms.contains("floors")) {
			boolean toggle = false;
			while (toggle == false) {
				hotel = new Hotel(new File(hotel.getPath()));
				System.out.println("What is the floor room number you would like to add? [Ex. 4 floor]");
				String roomName = sc.nextLine();
				int roomf = Integer.parseInt(roomName.split(" ")[0]);
				ArrayList<String> info = FileController
						.extractInfo(new File(hotel.getPath() + File.separator + "info.txt"));
				String a = info.get(4);
				Room[] room = new Room[Integer.parseInt(a)];
				for (int i = 0; i < Integer.parseInt(a); i++) {
					if (i < 10) {
						room[i] = new Room(new File(hotel.getRoom(0, 0).getPath()));
						room[i].setNumber(Integer.parseInt(roomf + "0" + i));
					} else {
						room[i] = new Room(new File(hotel.getRoom(0, 0).getPath()));
						room[i].setNumber(Integer.parseInt(roomf + "" + i));
					}
				}
				addRoom2(room, hotel);
				System.out.println("\n" + "Do you want to add another floor? (y/n)?");
				String yOrn = sc.nextLine().toLowerCase();
				if (!yOrn.matches("y")) {
					System.out.println("Exiting program");
					toggle = true;
					return;
				}
			}
		}
		System.out.println("User entered invalid input");
	}

	private static void addRoom2(Room[] room, Hotel hotel) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<String> info = FileController.extractInfo(new File(hotel.getPath() + File.separator + "info.txt"));
		String firstLetterOfFloor = Integer.toString(room[0].getNumber()).substring(0, 1);
		Room[][] a = hotel.getRooms();
		int rt = a.length;
		int tr = a[0].length;
		if (!(Integer.parseInt(firstLetterOfFloor) - a.length == 1)) {
			System.out
					.println("There's only " + a.length + " floors, you cannot add floor number " + firstLetterOfFloor);
			return;
		}
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a[0].length; j++) {
				if (Integer.toString(a[i][j].getNumber()).contains(Integer.toString(room[0].getNumber()))) {
					System.out.println("This floor already exists");
					return;
				}
				if (Integer.toString(a[i][j].getNumber()).contains(Integer.toString(room[0].getNumber()))) {

				}
			}
		}
		System.out.println("Are you sure you want to add the " + firstLetterOfFloor + " floor? (y/n)");
		String yOrn = sc.nextLine().toLowerCase();
		if (!yOrn.matches("y")) {
			System.out.println("Invalid input or user entered n");
			return;
		}
		System.out.println("Re enter your manager ID");
		String idMan = sc.nextLine();
		if (!idMan.matches(hotel.getManager().getID())) {
			System.out.println("Error, the wrong ID has been entered");
			return;
		}
		Room roomToDisplay = hotel.getRoom(0, 0);
		File roomToDisplayFile = new File(roomToDisplay.getPath());
		for (Room ro : room) {
			File roomFileToMake = new File(
					hotel.getPath() + File.separator + "Rooms" + File.separator + ro.getNumber() + ".txt");
			if (roomFileToMake.createNewFile()) {
				PrintWriter writer = new PrintWriter(roomFileToMake);
				writer.println(ro.getNumber());
				writer.println(roomToDisplay.getType());
				writer.println(String.valueOf(roomToDisplay.getPrice()));
				writer.println(String.valueOf(roomToDisplay.isEmpty()));
				writer.println(String.valueOf(roomToDisplay.isClean()));
				writer.println("Maintence:True");
				writer.println("Notes:True");
				writer.println("end");
				writer.flush();
				writer.close();
			}
		}
		System.out
				.println("\n" + "Every single room created will be made in the following format. For example:" + "\n");

		Printer.printFile(roomToDisplayFile);

		System.out.println("\n" + "The only difference will be the room #, any edits to this new room can");
		System.out.print("be made in the manager menu \n" + "The floor has been created" + "\n");

		int extraFloor = Integer.parseInt(info.get(3)) + 1;
		File f = new File(hotel.getPath() + File.separator + "info.txt");
		PrintWriter writer = new PrintWriter(f);
		writer.println(info.get(0));
		writer.println(info.get(1));
		writer.println(info.get(2));
		writer.println(extraFloor);
		writer.println(info.get(4));
		writer.println(info.get(5));
		writer.flush();
		writer.close();
		return;
	}

	// private static void manyRoomsAdd(Hotel hotel) throws IOException {
	// Scanner sc = new Scanner(System.in);
	// ArrayList<String> roomsToAdd = new ArrayList<>();
	// boolean toggle = false;
	// System.out.println("How many floors would you like to add? [Only enter a
	// number]");
	// int numRms = sc.nextInt();
	// Room[][] roomArr = hotel.getRooms();
	// Room a = roomArr[0][0];
	// for (int i = 0; i < roomArr.length; ++i) {
	// for (int j = 0; j < roomArr[0].length; ++j) {
	// System.out.println(roomArr[i][j]);
	// }
	// }
	// System.out.println("Enter the rooms you want to add.");
	// System.out.println("Note: You can only add rooms in the same floor");
	// System.out.print("Also, they have to be in this format Ex. [103.txt]");
	// while (numRms > 0) {
	// String numRm = sc.nextLine();
	// if (numRm.matches("[0-9]+")) {
	// if (!roomsToAdd.contains(numRm)) {
	// roomsToAdd.add(numRm);
	// numRms--;
	// } else
	// System.out.println("Invalid entry, try again");
	// }
	// }
	// for (int i = 0; i < roomArr.length; ++i) {
	// for (int j = 0; j < roomArr[0].length; ++j) {
	// if (roomsToAdd.contains(roomArr[i][j].getNumber())) {
	// File f = new File(hotel.getPath() + File.separator + "Rooms" +
	// File.separator
	// + roomArr[i][j].getNumber() + ".txt");
	// addRoom2(roomArr[i][j], hotel);
	// // FileController.cleanFileContent(f);
	// roomsToAdd.remove(roomArr[i][j].getNumber());
	// }
	// }
	// }
	// System.out.println("Success! We added new rooms to this existing hotel");
	// if (roomsToAdd.size() != 0)
	// roomsToAdd.clear();
	//
	// writeHotelToFile(hotel.getFile(), hotel.getName(), hotel.getStyle(),
	// hotel.getLocation(), hotel.getNumOfLevel(),
	// hotel.getLevelRmNum(), hotel.getPassword());

	// writeHotelToFile(hotel.getFile(), hotel.getName(),
	// hotel.getLocation(), roomArr.length,
	// hotel.getLevelRmNum(), hotel.getPassword());
	// }

	@SuppressWarnings("resource")
	public static void addToDeleteFilesPermantly(File file, File hotel) throws IOException {
		String q = file.getPath();
		String path = "";
		if (q.contains("In")) {
			path = "(";
			String q5 = file.getName();
			int q6 = q.length() - q5.length();

			String q7 = q.substring(q6 - 3, q6 - 1);
			path += q7;
			path += ")";
		}
		if (q.contains("Reservation")) {
			path = "(";
			String q5 = file.getName();
			int q6 = q.length() - q5.length();

			String q7 = q.substring(q6 - 12, q6 - 1);
			path += q7;
			path += ")";
		}
		File temp = new File(hotel.getPath() + File.separator + "Files to delete.txt");
		FileWriter fw = new FileWriter(temp, true); // keeps previous
													// data and adds on
													// to it
		try {
			fw = new FileWriter(temp, true); // data
			fw.write(file.getName() + path + " ");
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
			// close resources
		}
	}

	public static void deleteFilesPermantly(File hotel) throws IOException {
		ArrayList<String> info = new ArrayList<>();
		File temp = new File(hotel.getPath() + File.separator + "Files to delete.txt");
		if (temp.exists()) {
			Scanner sc = new Scanner(temp);
			while (sc.hasNext()) {
				String t = sc.next();
				while (!t.contains(".txt")) {
					t += " " + sc.next();

				}
				if (!info.contains(t)) {
					info.add(t);
				}
			}
		} else {
			temp.createNewFile();
			return;
		}
		if (info.size() != 0) {
			File[] hotelFiles = hotel.listFiles();
			for (File fileInHotel : hotelFiles) {
				if (info.size() == 0)
					break;
				if (fileInHotel.getName().contains("Contracts")) {
					File[] contracts = fileInHotel.listFiles();
					for (File contractFile : contracts) {
						if (contractFile.getName().contains("In")) {
							File[] contractsIn = contractFile.listFiles();
							for (File CI : contractsIn) {
								if (info.contains(CI.getName() + "(In)")) {
									if (CI.delete()) {
										info.remove(CI.getName() + "(In)");
									}
								}
							}
						}
						if (contractFile.getName().contains("Reservation")) {
							File[] contractsRes = contractFile.listFiles();
							for (File CR : contractsRes) {
								if (info.contains(CR.getName() + "(Reservation)")) {
									if (CR.delete()) {
										info.remove(CR.getName() + "(Reservation)");
									}
								}
							}
						}
					}
				}
				if (fileInHotel.getName().contains("Customer")) {
					File[] customers = fileInHotel.listFiles();
					for (File cFile : customers) {
						if (info.contains(cFile.getName())) {
							if (cFile.delete()) {
								info.remove(cFile.getName());
							}
						}
					}
				}
				if (fileInHotel.getName().contains("Employee")) {
					File[] employees = fileInHotel.listFiles();
					for (File eFile : employees) {
						if (info.contains(eFile.getName())) {
							if (eFile.delete()) {
								info.remove(eFile.getName());
							}
						}
					}
				}
				if (fileInHotel.getName().contains("Inventory")) {
					File[] inventory = fileInHotel.listFiles();
					for (File iFile : inventory) {
						if (info.contains(iFile.getName())) {
							if (iFile.delete()) {
								info.remove(iFile.getName());
							}
						}
					}
				}
				if (fileInHotel.getName().contains("Rooms")) {
					File[] rooms = fileInHotel.listFiles();
					for (File rFile : rooms) {
						if (info.contains(rFile.getName())) {
							if (rFile.delete()) {
								info.remove(rFile.getName());
							}
						}
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
	}
}
