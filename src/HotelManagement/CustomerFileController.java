package HotelManagement;

import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CustomerFileController implements FileController {
    public static void menuLookUp(File customer) throws IOException {
        System.out.println("Enter customer name");
        Scanner sc = new Scanner(System.in);
        registerCustomer(customer, sc.nextLine());
    }
    public static void displayAll(File customers) {

    }

    public static File lookUpCustomer(File customers, String name) {
        String filename = FileController.convertToTxt(name);
        for(File customer : customers.listFiles()) {
            if(customer.getName() == filename) {
                return customer;
            }
        }
        return null;
    }

    public static File registerCustomer(File customers, String name) throws IOException {
        if(lookUpCustomer(customers, name) == null)
            return createCustomer(customers, name);
        return null;
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
            writer.flush();
            writer.close();
        }
        return newCustomer;
    }

    public static void getCustomerInfo(File customers, String name, Customer.customerProperty property) throws FileNotFoundException {
        File customer = lookUpCustomer(customers, name);
        if(customer == null) {
            System.out.println("Customer dose not exists");
            return;
        }
        Scanner sc = new Scanner(customer);
        int i = 0;
        while(i++ < property.ordinal() && sc.hasNext())
            sc.nextLine();
        if (!sc.hasNext())
            System.out.println("Error, no such data exists");
        String ret = name + property.name() + sc.nextLine();
        System.out.println(ret);
    }

    public static void getCustomerInfo(File customers, String name, String property) throws FileNotFoundException {
        File customer = lookUpCustomer(customers, name);
        if(customer == null) {
            System.out.println("Customer dose not exists");
            return;
        }
        Scanner sc = new Scanner(customer);
        for(Customer.customerProperty p : Customer.customerProperty.values()) {
            if(p.toString() == property)
                break;
            sc.nextLine();
        }
        if (!sc.hasNext()) {
            System.out.println("Error, no such data exists");
            return;
        }
        String ret = name + property + sc.nextLine();
        System.out.println(ret);
    }

    public static File cdCustomerFile(File hotel) {
        String filePath = hotel.getPath() + File.separator + "Customer";
        return new File(filePath);
    }
}
