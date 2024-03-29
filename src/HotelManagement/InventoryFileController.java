package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class InventoryFileController implements FileController {

	public static File menuLookUp(File inventory) throws IOException {
		Scanner sc2 = new Scanner(System.in);
		System.out.println("Enter Inventory Item");
		String name = sc2.nextLine();
		File invItem = lookUpInventory(inventory, name);
		if (!invItem.exists()) {
			System.out.println("The Inventory with the name " + name + " does not exist");
			return new File("empty");
		}
		// System.out.println("The Inventory with the name " + name + " does
		// exists and here are its properties" + "\n");
		// Printer.printFile(invItem);

		return invItem;
	}

	public static void displayAll(File employees) {

	}

	public static File lookUpInventory(File inventory, String invItem) {
		return new File(inventory.getPath() + File.separator + invItem + ".txt");
	}

	public static void createItem(File inventory) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the new item you want to add");
		String invName = sc.nextLine();
		File invItem = lookUpInventory(inventory, invName);
		if (invItem.exists()) {
			addOnToInvItem(invItem);
			return;
		}
		addNewInvItem(inventory, invItem, invName);
	}

	private static void addNewInvItem(File inventory, File invItem, String invName)
			throws FileNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		String filePath = inventory.getPath() + File.separator + invName + ".txt";
		File newInvItem = new File(filePath);
		if (newInvItem.createNewFile()) {
			PrintWriter writer = new PrintWriter(filePath);
			System.out.println("Enter the amount you want to stock for this item");
			String amount = sc.nextLine();
			System.out.println("Enter the brand for this item");
			String brand = sc.nextLine();
			writer.println("Name: " + invName);
			writer.println("Amount: " + amount);
			writer.println("Brand: " + brand);
			writer.flush();
			writer.close();
		}
		System.out.println("The item: " + invName + " has now been added to the hotel!");
	}

	public static void addOnToInvItem(File invItem) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("An item like this already exists");
		System.out.println("Would you want like to restock the item: "
				+ invItem.getName().substring(0, invItem.getName().length() - 4) + "? (yes or no)");
		String yesOrNo = sc.nextLine();
		if (yesOrNo.compareTo("yes") != 0) {
			System.out.println("Invalid input or user entered no \n");
			return;
		}
		System.out.println("What brand of " + invItem.getName().substring(0, invItem.getName().length() - 4)
				+ " would you like to restock?");
		String brand = sc.nextLine();
		ArrayList<Inventory> info = extractInvInfo(invItem);
		boolean addToOldBrandTorF = false;
		for (int i = 0; i < info.size(); i++) {
			if (info.get(i).getBrand().compareTo(brand) == 0) {
				addToOldBrand(invItem, info, info.get(i), i);
				addToOldBrandTorF = true;
			}
		}
		if (addToOldBrandTorF == false)
			addNewBrand(invItem, info, brand);
		writeInvToFile(invItem, info);
	}

	// Creates Inv object from a file
	public static ArrayList<Inventory> extractInvInfo(File invItem) throws FileNotFoundException {
		ArrayList<Inventory> info = new ArrayList<>();
		Scanner sc = new Scanner(invItem);
		while (sc.hasNext()) {
			sc.next();
			String name = sc.nextLine();
			sc.next();
			String amount = sc.nextLine();
			sc.next();
			String brand = sc.nextLine();
			info.add(new Inventory(name.trim(), amount.trim(), brand.trim()));
		}
		return info;
	}

	private static void addToOldBrand(File invItem, ArrayList<Inventory> info, Inventory inv, int index) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the amount you want to restock for the brand: " + inv.getBrand());
		String amountS = sc.nextLine();
		int amount = Integer.parseInt(amountS.split(" ")[0]) + Integer.parseInt(inv.getAmount().split(" ")[0]);
		String amountName = inv.getAmount().split(" ")[1];
		inv.setAmount(amount + " " + amountName);
		info.set(index, new Inventory(inv.getName(), inv.getAmount(), inv.getBrand()));
		System.out.println("Success, now there's " + inv.getAmount() + " of stock for the brand: " + inv.getBrand());
	}

	private static void addNewBrand(File invItem, ArrayList<Inventory> info, String brand) {
		Scanner sc = new Scanner(System.in);
		System.out.println("This hotel doesn't contain the brand: " + brand + ", of the item: "
				+ invItem.getName().substring(0, invItem.getName().length() - 4));
		System.out.println("Would you like to add the brand: " + brand + " for this item? (yes or no)");
		String yesOrNo = sc.nextLine();
		if (yesOrNo.compareTo("yes") != 0) {
			System.out.println("Invalid input or user entered no \n");
			return;
		} else {
			System.out.println("Enter the amount you want to stock for the brand: " + brand);
			String amount = sc.nextLine();
			info.add(new Inventory(invItem.getName().substring(0, invItem.getName().length() - 4), amount, brand));
		}
		System.out.println("The brand: " + brand + " of the item: "
				+ invItem.getName().substring(0, invItem.getName().length() - 4) + " has now been added to the hotel!");
	}

	// Formatting issue. Basically gets all contents of invItem and puts it into
	// an ArrayList
	public static void writeInvToFile(File invItem, ArrayList<Inventory> inv) throws IOException {
		PrintWriter writer = new PrintWriter(invItem);
		for (Inventory v : inv) {
			writer.println("Name: " + v.getName());
			writer.println("Amount: " + v.getAmount());
			writer.println("Brand: " + v.getBrand());
		}
		writer.flush();
		writer.close();
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

	public static void getInventoryItemInfo(File inventory) throws IOException {
		File invItem = menuLookUp(inventory);
		if (invItem.length() == 0)
			return;
		Printer.printFile(invItem);
		System.out.print("\n");
	}

	// reads a file and returns the contents into an arraylist. Call this or
	// void (get..)
	public static ArrayList<Inventory> getInventoryItemInfoArr(File inventory) throws IOException {
		File invItem = menuLookUp(inventory);
		if (invItem.length() == 0)
			return null;
		return extractInvInfo(invItem);
	}

	public static ArrayList<Inventory> getInventoryItemInfoArr2(File invItem) throws IOException {
		return extractInvInfo(invItem);
	}

	public static void getInventoryInfo(File inventory) throws FileNotFoundException {
		Printer.printFiles(inventory);
	}

	public static void useItem(File inventory) throws IOException {
		File invItem = menuLookUp(inventory);
		if (invItem.length() == 0)
			return;
		Scanner sc = new Scanner(System.in);
		System.out.println("Are you use you want access the item: "
				+ invItem.getName().substring(0, invItem.getName().length() - 4) + "? (yes or no)");
		String yesOrNo = sc.nextLine();
		if (yesOrNo.compareTo("yes") != 0) {
			System.out.println("Invalid input or user entered no \n");
			return;
		} else {
			System.out.println("Which brand of this item would you like to use?");
			String brand = sc.nextLine();
			ArrayList<Inventory> info = extractInvInfo(invItem);
			boolean tr = false;
			for (int i = 0; i < info.size(); i++) {
				if (info.get(i).getBrand().compareTo(brand) == 0) {
					accessCertainBrand(invItem, info, info.get(i), i);
					tr = true;
				}
			}
			if (tr == false) {
				System.out.println("The brand: " + brand + ", does not exist for the item name: "
						+ invItem.getName().substring(0, invItem.getName().length() - 4));
				return;
			}
			writeInvToFile(invItem, info);
		}
	}

	private static void accessCertainBrand(File invItem, ArrayList<Inventory> info, Inventory inv, int index) {
		Scanner sc = new Scanner(System.in);
		if (Integer.parseInt(inv.getAmount().split(" ")[0]) <= 0) {
			System.out.println("There's no stock for this item brand left");
			return;
		}
		System.out.println("Enter the amount you want to use for the brand: " + inv.getBrand());
		String amount = sc.nextLine();
		if ((Integer.parseInt(inv.getAmount().split(" ")[0]) - Integer.parseInt(amount.split(" ")[0])) < 0) {
			System.out.println("There's only " + inv.getAmount() + " of this brand stock left");
			System.out.println("You can't take " + amount.split(" ")[0]);
			return;
		}
		int a = Integer.parseInt(inv.getAmount().split(" ")[0]) - Integer.parseInt(amount.split(" ")[0]);
		inv.setAmount(a + " " + inv.getAmount().split(" ")[1]);
		info.set(index, new Inventory(inv.getName(), inv.getAmount(), inv.getBrand()));

		System.out.println(
				"Success, now there's only " + inv.getAmount() + " of stock left for the brand: " + inv.getBrand());
		return;
	}

	// Creates directory
	public static File cdInventoryFile(File hotel) {
		String filePath = hotel.getPath() + File.separator + "Employee";
		return new File(filePath);
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
		File f = lookUpInventory(employees, tempID);
		while (f.exists()) {
			tempID = titleName.substring(0, 1) + rand.nextInt(10000);
			f = lookUpInventory(employees, tempID);
		}
		return tempID;
	}
}
