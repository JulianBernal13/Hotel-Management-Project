package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Anji Yu
 */


public class Customer {
    public static enum customerProperty {
        firstname, lastname, isVIP, isStaying, contractHistory, futureContract;
    }

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

    Customer(File customerFile) {

    }
}
