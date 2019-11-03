package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Anji Yu
 */


public class Customer {
    public static enum Property {
        firstname, lastname, isVIP, isStaying;
    }

    private final String firstname;
    private final String lastname;
    private String path;
    private boolean isVIP = false;
    private boolean isStaying = false;

    /**
     * @param firstname
     * @param lastname
     * @param customerFolder
     * Construct a customer object from given firstname, lastname and current customer folder.
     * The customer will then be added to the folder
     * This is only used when the customer logs in for the first time.
     */
    Customer(String firstname, String lastname, File customerFolder) throws IOException {
        this.firstname = firstname;
        this.lastname = lastname;
        this.path = customerFolder.getPath() + File.separator + FileController.convertToTxt(this.toString());
        this.writeCustomerToFile();
    }

    public String toString() {
        return firstname + " " + lastname;
    }

    /**
     * @param customerFile
     * @throws FileNotFoundException
     * Construct a customer object form an existing customer file.
     */
    Customer(File customerFile) throws FileNotFoundException {
        //needs exception handling
        Scanner sc = new Scanner(customerFile);
        this.firstname = sc.nextLine();
        this.lastname = sc.nextLine();
        this.isVIP = Boolean.parseBoolean(sc.nextLine());
        this.isStaying = Boolean.parseBoolean((sc.nextLine()));
        this.path = customerFile.getPath();
    }

    public boolean isVIP() {
        return isVIP;
    }

    public void setVIP(boolean VIP) {
        isVIP = VIP;
    }

    public boolean isStaying() {
        return isStaying;
    }

    public void setStaying(boolean staying) {
        isStaying = staying;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void writeCustomerToFile() throws IOException {
        File customer = new File(path);
        if(customer.exists())
            customer.delete();
        customer.createNewFile();
        PrintWriter writer = new PrintWriter(customer);
        writer.println(this.firstname);
        writer.println(this.lastname);
        writer.println(this.isVIP);
        writer.println(this.isStaying);
    }
}
