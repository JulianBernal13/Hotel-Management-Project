package HotelManagement;

import com.sun.security.jgss.GSSUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CustomerFileController implements FileController {
    public static void displayAll(File cur) {

    }

    public static File lookUpCustomer(File customers, String name) {
//        System.out.println("Please enter customer's name:");
        for(File tmp : customers.listFiles()) {
            if(tmp.getName() == name) {
                return tmp;
            }
        }
//        System.out.println("Customer does not exists");
        return null;
    }

    public static File registerCustomer(File customers, String name) {
        if(lookUpCustomer(customers, name) != null)
            return createCustomer(customers, name);
        return null;
    }




    public static File createCustomer(File customers, String name) {
        return null;
    }

    public static void getCustomerInfo(File customers, String name, Customer.customerProperty property) throws FileNotFoundException {
        File customer = lookUpCustomer(customers, name);
        if(customer == null)
            System.out.println("Customer dose not exists");
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
        if(customer == null)
            System.out.println("Customer dose not exists");
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
}
