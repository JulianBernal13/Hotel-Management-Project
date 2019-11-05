package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerFileController implements FileController {
    public static void menuLookUp(File customers) throws IOException {
        System.out.println("Enter customer name");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        File customer = lookUpCustomer(customers, name);
        if(!customer.exists()) {
            System.out.println("Customer " + name + " does not exist");
            return;
        }
        Printer.printCustomer(customer);
    }
    public static void displayAll(File customers) {

    }

    public static File lookUpCustomer(File customers, String name) {
        return new File(customers.getPath() + File.separator + FileController.convertToTxt(name));
    }

    public static File registerCustomer(File customers, String name) throws IOException {
        if(!lookUpCustomer(customers, name).exists())
            return createCustomer(customers, name);
//        System.out.println("Error, the customer has already existed");
        return lookUpCustomer(customers, name);
    }

    public static File createCustomer(File customers, String name) throws IOException {
        int i = name.indexOf(' ');
        String firstName = name.substring(0, i), lastName = name.substring(i + 1);
        String filePath = customers.getPath() + File.separator + FileController.convertToTxt(name);
        File newCustomer = new File(filePath);
        if(newCustomer.createNewFile()) {
            PrintWriter writer = new PrintWriter(filePath);
            writer.println(firstName);
            writer.println(lastName);
            writer.println(false);
            writer.println(false);
            writer.flush();
            writer.close();
        }
        return newCustomer;
    }

    public static int getPropertyOrdinal(String property) {
        int i = 0;
        for (Customer.Property p: Customer.Property.values()) {
            if(p.name() == property)
                return i;
            i++;
        }
        return -1;
    }

    public static String getCustomerInfo(File customer, Customer.Property property) throws FileNotFoundException {
        Scanner sc = new Scanner(customer);
        int i = 0;
        while(i++ < property.ordinal() && sc.hasNext())
            sc.nextLine();
        if (!sc.hasNext()) {
            System.out.println("Error, no such data exists");
            return "";
        }
        return sc.nextLine();
    }

    public static String getCustomerInfo(File customer, String property) throws FileNotFoundException {
        Scanner sc = new Scanner(customer);
        for(Customer.Property p : Customer.Property.values()) {
            if(p.toString() == property)
                break;
            sc.nextLine();
        }
        if (!sc.hasNext()) {
            System.out.println("Error, no such data exists");
            return "";
        }
        return sc.nextLine();
    }

    public static File cdCustomerFolder(File hotelFile) {
        return cdCustomerFolder(hotelFile.getPath());
    }

    public static File cdCustomerFolder(String hotelPath) {
        return new File(hotelPath + File.separator + "Customer");
    }

    public static ArrayList<String> extractCustmorInfo(File customer) throws FileNotFoundException {
        ArrayList<String> info = new ArrayList<>();
//        File customer = lookUpCustomer(customers, name);
        Scanner sc = new Scanner(customer);
        while(sc.hasNext()) {
            info.add(sc.nextLine());
        }
        return info;
    }

    public static void modifyCustomer(File customer, String property, String modified) throws FileNotFoundException {
        ArrayList<String> info = extractCustmorInfo(customer);
//        System.out.println("Enter what you like to change to " + property);
        int i = getPropertyOrdinal(property);
        if(i == -1)
            return;
        info.set(i, modified);
        PrintWriter writer = new PrintWriter(customer);
        for(String s : info) {
            writer.println(s);
        }
        writer.flush();
        writer.close();
    }
}
