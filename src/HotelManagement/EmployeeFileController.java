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
        registerEmployee(employees, name);
        getCustomerInfo(employees, name, "isVIP");
        getCustomerInfo(employees, name, Customer.customerProperty.firstname);

    }
    public static void displayAll(File customers) {

    }

    public static File lookUpCustomer(File employees, String id) {
        return new File(employees.getPath() + File.separator + FileController.convertToTxt(id));
    }

    public static File registerEmployee(File employees, String name) throws IOException {
        if(!lookUpCustomer(employees, name).exists())
            return createEmployee(employees, name);
        System.out.println("Error, the customer has already existed");
        return null;
    }

    public static File createEmployee(File customers, String name) throws IOException {
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
        for (Employee.EmployeeProperty p: Employee.EmployeeProperty.values()) {
            if(p.name() == property)
                return i;
            i++;
        }
        return -1;
    }

    public static void getCustomerInfo(File customers, String name, Customer.customerProperty property) throws FileNotFoundException {
        File customer = lookUpCustomer(customers, name);
        if(!customer.exists()) {
            System.out.println("Customer dose not exists");
            return;
        }
        Scanner sc = new Scanner(customer);
        int i = 0;
        while(i++ < property.ordinal() && sc.hasNext())
            sc.nextLine();
        if (!sc.hasNext())
            System.out.println("Error, no such data exists");
        String ret = name + "   " + property.name() + "    " + sc.nextLine();
        System.out.println(ret);
    }

    public static void getCustomerInfo(File customers, String name, String property) throws FileNotFoundException {
        File customer = lookUpCustomer(customers, name);
        if(!customer.exists()) {
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
        String ret = name + "   " + property + "   " + sc.nextLine();
        System.out.println(ret);
    }

    public static File cdCustomerFile(File hotel) {
        String filePath = hotel.getPath() + File.separator + "Customer";
        return new File(filePath);
    }

    public static ArrayList<String> extractCustmorInfo(File customers, String name) throws FileNotFoundException {
        ArrayList<String> info = new ArrayList<>();
//        File customer = lookUpCustomer(customers, name);
        Scanner sc = new Scanner(lookUpCustomer(customers, name));
        while(sc.hasNext()) {
            info.add(sc.nextLine());
        }
        return info;
    }

    public static void modifyCustomer(File customers, String name, String property) throws FileNotFoundException {
        ArrayList<String> info = extractCustmorInfo(customers, name);
        System.out.println("Enter what you like to change to " + name + ": " + property);
        Scanner sc = new Scanner(System.in);
        int i = getPropertyOrdinal(property);
        if(i == -1)
            return;
//        info.get(i) = sc.nextLine();

    }
}
