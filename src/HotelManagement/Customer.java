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

//    public static enum customerProperty {
//        firstname, lastname, isVIP, isStaying, contractHistory, futureContract;
//    }

    String firstname;
    String lastname;
    boolean isVIP = false;
    boolean isStaying = false;
    ArrayList<Contract> contractHistory;
    ArrayList<Contract> futureContract;

    Customer(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

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
}
