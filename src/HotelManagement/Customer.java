package HotelManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Anji Yu
 */


public class Customer {
    public static enum Property {
        firstname, lastname, isVIP, isStaying, contractHistory, futureContract;
    }

    private final String firstname;
    private final String lastname;
    private boolean isVIP = false;
    private boolean isStaying = false;
    ArrayList<Contract> contractHistory;
    ArrayList<Contract> futureContract;

    /**
     * @param firstname
     * @param lastname
     * Construct a customer object from given firstname and lastname.
     * This is only used when the customer logs in for the first time.
     */
    Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * @param customerFile
     * @throws FileNotFoundException
     * Construct a customer object form an existing file.
     */
    Customer(File customerFile) throws FileNotFoundException {
        //needs exception handing
        Scanner sc = new Scanner(customerFile);
        this.firstname = sc.nextLine();
        this.lastname = sc.nextLine();
        this.isVIP = Boolean.valueOf(sc.nextLine());
        this.isStaying = Boolean.valueOf((sc.nextLine()));
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
}
