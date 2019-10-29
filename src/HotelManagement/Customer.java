package HotelManagement;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @author Anji Yu
 */


public class Customer {
    private String firstName;
    private String lastName;
    private boolean isVIP = false;
    private ArrayList<Contract> contractHistory;
    private ArrayList<Contract> futureContracts;

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
//        this.contracts = contracts;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void createCostomerFile (String path) throws IOException {
        File cur = new File(path + File.separator + this.toString() + ".txt");
        if(cur.createNewFile()) {
            PrintWriter writer = new PrintWriter(cur);
            writer.println(firstName);
            writer.println(lastName);
            writer.println(isVIP);
            writer.flush();
            writer.close();
        }
    }



}
